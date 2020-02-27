/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.Gui;

import com.esprit.Entite.Reclamation;
import com.esprit.Entite.ReclamationClient;
import com.esprit.Service.ServiceReclamation;
import com.esprit.gui.LoginUserController;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import static java.util.Optional.empty;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author darra
 */

import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;
import sun.invoke.empty.Empty;
public class ReclamationClientController implements Initializable {

    private Image image;
    @FXML
    private Button btnRec;
    @FXML
    private TextField SujetRec;
    @FXML
    private TextArea DescriptionRec;
    @FXML
    private ComboBox<String> categRec;
    private ObservableList<String> oblist=FXCollections.observableArrayList("Probleme de compte","probleme de commande","autre");
    private ServiceReclamation  servRec= new ServiceReclamation();
    private FileInputStream fis;
    String [] words={"java","probleme de compte","probleme de commande","ma commande"};
    Set<String> possiblewordSet=new HashSet<>();
    private AutoCompletionBinding<String>autocompletionbinding;
    
    @FXML
    private void redirectToClientmerecl(ActionEvent event) throws IOException {
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("CllientMesReclamations.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    }
    @FXML
    private void redirectToRecClient(ActionEvent event) throws IOException {
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("ReclamationClient.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println(LoginUserController.NumId);
        Collections.addAll(possiblewordSet, words);
        autocompletionbinding=TextFields.bindAutoCompletion(SujetRec, possiblewordSet);
        SujetRec.setOnKeyPressed((KeyEvent e)->{   
           switch(e.getCode()){
                   
               case ENTER:learnworld(SujetRec.getText());
                  break;
               default:
                   break;
           }
//           if((SujetRec.getText().isEmpty()) && (DescriptionRec.getText().isEmpty()) && (categRec.getValue().isEmpty()))
//             {
//               btnRec.setVisible(false);
//              
//             }else {
//                 btnRec.setVisible(true);
//                 //ajouterReclamation(); 
//             }
           
           if(!SujetRec.getText().isEmpty()){
               if (!DescriptionRec.getText().isEmpty()){
                   if (!categRec.getValue().isEmpty()){
                       btnRec.setVisible(false);
                   }
               }
           }else{
               btnRec.setVisible(true);
           }
        
        });
        
        
        categRec.setItems(oblist);
         btnRec.setOnAction(e->{
         try{
             Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation Dialog");
		alert.setHeaderText(null);
		alert.setContentText("Voulez vous envoyer votre reclamation ? ");
		Optional<ButtonType> action = alert.showAndWait();
                if(action.get() == ButtonType.OK)
                 ajouterReclamation(); 
                //java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
            
            
         }  catch (SQLException ex) {
                Logger.getLogger(ReclamationClientController.class.getName()).log(Level.SEVERE, null, ex);
            }});
    }    

    private void ajouterReclamation() throws SQLException{
        int id = LoginUserController.NumId;
        String sujet=SujetRec.getText();
        String description=DescriptionRec.getText();
        String combo=categRec.getValue();
        ReclamationClient rec;
        rec= new ReclamationClient(id,combo,sujet,description);
        servRec.ajouterReclamation(rec);
        categRec.setValue(combo);
        SujetRec.setText("");
        DescriptionRec.setText("");
        if(categRec.getValue()=="probleme de commande"){
            servRec.ajouterReclamation(rec);
        }
        Alert alert=new Alert(AlertType.INFORMATION);
        alert.setTitle("Reclamation client");
        //alert.setHeaderText("Information");
        alert.setContentText("Reclamation envoyee");
        alert.showAndWait();
        
        
        
    }
       /*public void  ajouterReclamationAvecImage(Reclamation r) throws SQLException
    {
       // int st=0;
    PreparedStatement ps=con.prepareStatement("INSERT INTO `minipot`.`reclamation` ( `type`, `objet`, `description`,`image`,`dater`) VALUES ( ?,?, ?, ?,sysdate());");
    ps.setString(1, r.getType());
    ps.setString(2, r.getObjet());
    ps.setString(3, r.getDescription());
   
   // ps.setString(3, r.getEtatr());
    //ps.setInt(4, r.getIdclt());
   // ps.setInt(5, r.getIdcmd());
    ps.executeUpdate();
    }

   /* @FXML
    private void AnnulerReclamation(ActionEvent event) {
    }*/
    private boolean validate(String field, String value, String pattern){
		if(!value.isEmpty()){
		Pattern p = Pattern.compile(pattern);
	        Matcher m = p.matcher(value);
	        if(m.find() && m.group().equals(value)){
	            return true;
	        }else{
	        	validationAlert(field, false);            
	            return false;            
	        }
		}else{
			validationAlert(field, true);            
            return false;
		}        
    }
	
	private boolean emptyValidation(String field, boolean empty){
        if(!empty){
            return true;
        }else{
        	validationAlert(field, true);            
            return false;            
        }
    }	
	
	private void validationAlert(String field, boolean empty){
	Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Validation Error");
        alert.setHeaderText(null);
        if(field.equals("Role")) alert.setContentText("Please Select "+ field);
        else{
        	if(empty) alert.setContentText("Please Enter "+ field);
        	else alert.setContentText("Please Enter Valid "+ field);
        }
        alert.showAndWait();
	}  
    private void learnworld(String text) {
        possiblewordSet.add(text);
        
        if(autocompletionbinding!=null){
            autocompletionbinding.dispose();
             autocompletionbinding=TextFields.bindAutoCompletion(SujetRec, possiblewordSet);
        }
    }
    
}

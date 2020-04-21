/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui;

import com.esprit.Entite.Reclamation;
import com.esprit.Entite.ReclamationClient;
import com.esprit.Gui.AcceuilController;
import com.esprit.Gui.Interface1Controller;
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
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author darra
 */

import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;
//import sun.invoke.empty.Empty;
public class ReclamationClientCmdController implements Initializable {

    private Image image;
    @FXML
    private Button btnRec;
    @FXML
    private TextField SujetRec;
    @FXML
    private TextArea DescriptionRec;
     @FXML
    private Label refcmd;
    @FXML
    private Label idcmd;
   
    private ServiceReclamation  servRec= new ServiceReclamation();
    private FileInputStream fis;
    String [] words={"java","probleme de compte","probleme de commande","ma commande"};
    Set<String> possiblewordSet=new HashSet<>();
    private AutoCompletionBinding<String>autocompletionbinding;
    @FXML
    private Button btnlog;
    
    public void setIdcmd(int idcmd ) {
        this.idcmd.setText(String.valueOf(idcmd));
    }

    public void setRefcmd(String refcmd) {
        this.refcmd.setText(refcmd);
    }
    
    
     @FXML
    private void LogoutAction(ActionEvent event) {
        
        try {
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("LoginUser.fxml"));
            Scene tableViewScene = new Scene(tableViewParent);
            
            //This line gets the Stage information
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            
            window.setScene(tableViewScene);
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
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
           
          
        
        });
        
        
        
         
    }    

    private void ajouterReclamation() throws SQLException{
        int id = LoginUserController.NumId;
        String idCmd=idcmd.getText();
        String sujet=SujetRec.getText();
        String description=DescriptionRec.getText();
       
        ReclamationClient rec;
        rec= new ReclamationClient(SujetRec.getText(),DescriptionRec.getText(),Integer.parseInt(idCmd),id);
        servRec.ajouterReclamationCommande(rec);
        //categRec.setValue(combo);
        SujetRec.setText("");
        DescriptionRec.setText("");
        
        Alert alert=new Alert(AlertType.INFORMATION);
        alert.setTitle("Reclamation client");
        //alert.setHeaderText("Information");
        alert.setContentText("Reclamation envoyee");
        alert.showAndWait();
        
        
        
    }
    @FXML
    void Reclamer(ActionEvent event) throws SQLException, IOException {
       Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation Dialog");
		alert.setHeaderText(null);
		alert.setContentText("Voulez vous envoyer votre reclamation ? ");
		Optional<ButtonType> action = alert.showAndWait();
                if(action.get() == ButtonType.OK)
                 ajouterReclamation(); 
                Parent tableViewParent = FXMLLoader.load(getClass().getResource("/com/esprit/Gui/CllientMesReclamations.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();

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

//*******************************menu espace client*************************    

//*****************************acceuil***************************

  @FXML
    private void AcceuilAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Acceuil.fxml"));
            
            Parent root = loader.load();
            btnRec.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(Interface1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
//*****************************Produit***************************

    @FXML
    private void ProduitAction(ActionEvent event) {
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Acceuil.fxml"));
            
            Parent root = loader.load();
            btnRec.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(Interface1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//*****************************Panier***************************
    @FXML
    private void PanierAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("Interface1.fxml"));
        Parent root = loader.load();
       btnRec.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(Interface1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//*****************************Commande***************************
    @FXML
    private void CommandeAction(ActionEvent event) {
        try{
        FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("ListeCmdClient.fxml"));
        Parent root = loader.load();
        btnRec.getScene().setRoot(root);
        }catch(Exception e){
        
        System.out.println(e.getMessage());
        }
    }

//*****************************Facture***************************

    @FXML
    private void FacturesAction(ActionEvent event) {
        try{
        FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("FacturesClt.fxml"));
        Parent root = loader.load();
        btnRec.getScene().setRoot(root);
        }catch(Exception e){
        
        System.out.println(e.getMessage());
        } 
    }

//*****************************Reclamation***************************
@FXML
    private void ReclamationAction(ActionEvent event) {
        
        try {
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("ReclamationClient.fxml"));
            Scene tableViewScene = new Scene(tableViewParent);
            
            //This line gets the Stage information
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            
            window.setScene(tableViewScene);
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//*****************************Mes Reclamation***************************
    @FXML
    private void MesReclamationAction(ActionEvent event) {
        
         try {
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("CllientMesReclamations.fxml"));
            Scene tableViewScene = new Scene(tableViewParent);
            
            //This line gets the Stage information
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            
            window.setScene(tableViewScene);
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.Gui;

import com.esprit.Entite.Reclamation;
import com.esprit.Service.ServiceReclamation;
import java.io.FileInputStream;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashSet;
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

/**
 * FXML Controller class
 *
 * @author darra
 */
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;
import sun.invoke.empty.Empty;
public class ReclamationClientController implements Initializable {

    @FXML
    private AnchorPane ReclamationClient;
    @FXML
    private ImageView imageRec;
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
    @FXML
    private Button btnImg;
    String [] words={"java","probleme de compte","probleme de commande","ma commande"};
    Set<String> possiblewordSet=new HashSet<>();
    private AutoCompletionBinding<String>autocompletionbinding;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
             ajouterReclamation(); 
            
         }  catch (SQLException ex) {
                Logger.getLogger(ReclamationClientController.class.getName()).log(Level.SEVERE, null, ex);
            }});
    }    

    private void ajouterReclamation() throws SQLException{
        String sujet=SujetRec.getText();
        String description=DescriptionRec.getText();
        String combo=categRec.getValue();
        Reclamation rec;
        rec= new Reclamation(categRec.getValue(),SujetRec.getText(),DescriptionRec.getText());
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

    private void learnworld(String text) {
        possiblewordSet.add(text);
        
        if(autocompletionbinding!=null){
            autocompletionbinding.dispose();
             autocompletionbinding=TextFields.bindAutoCompletion(SujetRec, possiblewordSet);
        }
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui;

import com.esprit.Service.ServicePersonne;
import com.esprit.service.service_bcrypt;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class LoginUserController implements Initializable {

    @FXML
    private TextField tfusername;
    @FXML
    private PasswordField tfpassword;
    ServicePersonne sp ;
    @FXML
    private Button btnconnect;
    @FXML
    private Hyperlink btnforget;
    @FXML
    private Button btnreg;
    
    @FXML
    private PasswordField tfCpass;
    ServicePersonne ser = new ServicePersonne();
    
    public static int NumId ;
   // NumId = ser.rechercherID(0);
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
     private void validateusername() throws SQLException{
      sp = new ServicePersonne();
     String name= tfusername.getText();
     String password= tfpassword.getText();
        ResultSet s = sp.listerUser1(name);
        
     if(name.isEmpty()||password.isEmpty()){
         Alert alert = new Alert(Alert.AlertType.ERROR);
         alert.setHeaderText(null);
         alert.setContentText("s'il vous plait remplir les champs vides");
         alert.showAndWait();
         
     }
     else {
         while(s.next()){
         if(name.equals(s.getString("username"))&&service_bcrypt.checkpw(password, s.getString(password))){
         Alert alert = new Alert(Alert.AlertType.WARNING);
         alert.setHeaderText(null);
         alert.setContentText("Connecté avec succées");
         alert.showAndWait();
             }
         }
         }
     }
   /*  private boolean validatePassword(){
        Pattern p = Pattern.compile("((?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,15})");
        Matcher m = p.matcher(tfpassword.getText());
        if (m.matches()){
            return true;
     
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("validate Password");
            alert.setHeaderText(null);
            alert.setContentText("Password must contain at least one(Digit,Lowercase,Uppercase and special characteres");
            alert.showAndWait();
            return false;
        }
         
    }*/
       

   /* @FXML
    private void connect(ActionEvent event) throws SQLException, IOException {
           
       if(validatePassword()==true )
    {
        validateusername();
        Parent root = FXMLLoader.load(getClass().getResource("Forgetpass.fxml"));
         Scene scene = new Scene(root);
            //scene.getStylesheets().add(getClass().getResource("Login.css").toExternalForm());
            //primaryStage.setTitle("Ajouter User!");
             Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(scene);
        window.show();

    }
    }*/
      @FXML
    private void connect(ActionEvent event) throws SQLException, IOException {
        //button connecter pour pouvoir rederiger  a une autre interface
        String username = tfusername.getText();
        
        
            //if(validatePassword()==true)
    {
       
         validateusername();
         System.out.println(ser.getRoleUser(username));
      if (ser.getRoleUser(username).contains("ROLE_ADMIN")) { // login tant que Administrateur

//            FXMLLoader fxml = new FXMLLoader(getClass().getResource("AccueilReclamationAd.fxml"));
//            Parent root = fxml.load();
//                 tfusername.getScene().setRoot(root);
//                 ForgetpassController rc = fxml.getController();
// EspaceProduit
                Parent tableViewParent = FXMLLoader.load(getClass().getResource("testUser.fxml"));
                Scene tableViewScene = new Scene(tableViewParent);

                //This line gets the Stage information
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

                window.setScene(tableViewScene);
                window.show();

            }
            if (ser.getRoleUser(username).contains("ROLE_LIVREUR")) { // login tant que Administrateur

//            FXMLLoader fxml = new FXMLLoader(getClass().getResource("AccueilReclamationAd.fxml"));
//            Parent root = fxml.load();
//                 tfusername.getScene().setRoot(root);
//                 ForgetpassController rc = fxml.getController();
// EspaceProduit
                NumId = ser.rechercherID(tfusername.getText());
                Parent tableViewParent = FXMLLoader.load(getClass().getResource("livraison_liv.fxml"));
                Scene tableViewScene = new Scene(tableViewParent);

                //This line gets the Stage information
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

                window.setScene(tableViewScene);
                window.show();

            }
        if (ser.getRoleUser(username).contains("ROLE_RH")){ //login tant que Responsable RH
            
                 
                 Parent tableViewParent = FXMLLoader.load(getClass().getResource("Employe.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        tableViewScene.getStylesheets().add(getClass().getResource("employe.css").toExternalForm());
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
        
          }
        if (ser.getRoleUser(username).contains("ROLE_EMPLOYE")){ //login tant que Employe
            
                 NumId = ser.rechercherID(tfusername.getText());
                 System.out.println(NumId);
                 Parent tableViewParent = FXMLLoader.load(getClass().getResource("DemandeConge.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        tableViewScene.getStylesheets().add(getClass().getResource("employe.css").toExternalForm());
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
        
        
          }
        

        if (ser.getRoleUser(username).contains("ROLE_CLIENT")){ //login tant que Employe
            
                 NumId = ser.rechercherID(tfusername.getText());
                 System.out.println(NumId);
//                 ReclamationClient.fxml ListeCmdClient
                 Parent tableViewParent = FXMLLoader.load(getClass().getResource("Acceuil.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
//        tableViewScene.getStylesheets().add(getClass().getResource("fullpackstyling.css").toExternalForm());
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
        
        
        }
    }
             
    }

    @FXML
    private void btn_forgot(ActionEvent event) throws IOException {
    
        
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("Forgetpass.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
                       
        }

    @FXML
    private void redirectToRegister(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("Inscription.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    }
    
}

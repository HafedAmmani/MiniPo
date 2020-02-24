/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui;

import com.esprit.Entite.User;
import com.esprit.Service.ServicePersonne;
import com.esprit.service.service_bcrypt;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class InscriptionController implements Initializable {

        @FXML
        private TextField tfname;
        @FXML
        private TextField tfusername;
        @FXML
        private TextField tflastname;
        @FXML
        private TextField tfemail;
        @FXML
        private PasswordField tfpassword;
        @FXML
        private PasswordField tfCpass;
        @FXML
        private Button btnsign;
        @FXML
        private ComboBox<String> cbgenre;
        private ObservableList<String> list = FXCollections.observableArrayList("Male","female");

         
  



    /**
     * Initializes the controller class.
     */
        
        
    private ServicePersonne sp = new ServicePersonne();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbgenre.setItems(list);
       
    
    }
           
    
   
      private void insertNewUser() throws SQLException{ // for adding new Employe
            String nom = tfname.getText();
            String unom= tfusername.getText();
            String prenom = tflastname.getText();
            String mail = tfemail.getText();
            String pass = tfpassword.getText();
            String Cpass = tfCpass.getText();
            String genre =cbgenre.getValue();
            String role="client";
            String pIcrypt = service_bcrypt.hashpw(pass,service_bcrypt.gensalt());
            
      
       User p1;
        p1 = new User(tfusername.getText(), tfemail.getText(), pIcrypt,role,tfname.getText(), tflastname.getText(), cbgenre.getValue());
        sp.ajouter(p1);
        tfusername.setText("");
        tfname.setText("");
        tflastname.setText("");
        tfemail.setText("");
        tfpassword.setText("");
        cbgenre.setValue(genre);
        String roles="client";
        
        clear();
        
    }  
       private void clear(){
            tfusername.clear();
            tflastname.clear();
            tfname.clear();
            tfemail.clear();
            tfpassword.clear();
            tfCpass.clear();
            
            
    }
        private boolean validateEmail(){
       // Pattern p = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9]+([@])+([.][a-zA-Z]+)+");
       Pattern p = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*[@][a-zA-Z0-9]+([.][a-zA-Z]+)+");
        Matcher m = p.matcher(tfemail.getText());
        if (m.find()&& m.group().equals(tfemail.getText())){
            return true;
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("validate Email");
            alert.setHeaderText(null);
            alert.setContentText("please Enter Valid Email");
            alert.showAndWait();
            return false;
        }
    }
            private boolean validatePassword(){
        String pass = tfpassword.getText();
        String Cpass = tfCpass.getText();
        Pattern p = Pattern.compile("((?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,15})");
        Matcher m = p.matcher(tfpassword.getText());
        if (m.matches() ){
            return true;
            
        }else if (!pass.equals(Cpass)){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Mot de passe invalide");
            alert.showAndWait();
            return false;
        }
            else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("mot de passe valide");
            alert.setHeaderText(null);
            alert.setContentText("le mot de passe doit contenir entier un majiscule ,miniscule et des caractere specifique");
            alert.showAndWait();
            return false;
        }
    }

    @FXML
    private void connect(ActionEvent event) throws SQLException {
        if (validateEmail() & validatePassword() )
        {
                insertNewUser();
                
            }
    }
        
    }


    


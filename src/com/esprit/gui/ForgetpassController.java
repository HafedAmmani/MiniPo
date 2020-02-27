/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui;

import com.esprit.Service.ServicePersonne;
import com.mysql.cj.Session;
import com.mysql.cj.protocol.Message;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sun.rmi.transport.Transport;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ForgetpassController implements Initializable {

    @FXML
    private Label luser;
    @FXML
    private TextField tuser;
    @FXML
    private PasswordField tpass;
    @FXML
    private Button btn_change;
    ServicePersonne sp ;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    
    private void update_pass() throws SQLException{
        sp = new ServicePersonne();
        String name = tuser.getText();
        String pass = tpass.getText();
        sp.Updatepass(pass,name);
        
        if(name.isEmpty()||pass.isEmpty()){
         Alert alert = new Alert(Alert.AlertType.ERROR);
         alert.setHeaderText(null);
         alert.setContentText("s'il vous plait remplir les champs vides");
         alert.showAndWait();
          }
    }
    
         private void validateusername() throws SQLException{
      sp = new ServicePersonne();
     String name= tuser.getText();
        ResultSet s = sp.listerUser1(name);
        
     if(name.isEmpty()){
         Alert alert = new Alert(Alert.AlertType.ERROR);
         alert.setHeaderText(null);
         alert.setContentText("s'il vous plait remplir le username");
         alert.showAndWait();
         
     }
     else{
         while(s.next()){
             if(!name.equals(s.getString("username"))){
         Alert alert = new Alert(Alert.AlertType.WARNING);
         alert.setHeaderText(null);
         alert.setContentText("cet utilisateur n'existe pas");
         alert.showAndWait();
             }else{
         Alert alert = new Alert(Alert.AlertType.WARNING);
         alert.setHeaderText(null);
         alert.setContentText("Error");
         alert.showAndWait();

     }
     
    }}}
    
    private boolean validatePassword(){
        Pattern p = Pattern.compile("((?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,15})");
        Matcher m = p.matcher(tpass.getText());
        if (m.matches()){
            return true;
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validation du mot de passe");
            alert.setHeaderText(null);
            alert.setContentText("Mot de passe doit contenirau moins un entier, un miniscule,majuscule characteres speciaux");
            alert.showAndWait();
            return false;
        }
    }
    
  
    @FXML
    private void changePass(ActionEvent event) throws SQLException {
        validateusername();
         if(validatePassword()==true)
    {
        update_pass();
    }
    }
    
    
    
    
    
    
  
    
}

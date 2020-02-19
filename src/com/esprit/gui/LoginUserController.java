/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui;

import com.esprit.Service.ServicePersonne;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
         alert.setContentText("Please fill all required");
         alert.showAndWait();
         
     }
     else{
         while(s.next()){
             if(name.equals(s.getString("username"))&&password.equals(s.getString("password"))){
              Alert alert = new Alert(Alert.AlertType.ERROR);
         alert.setHeaderText(null);
         alert.setContentText("Successfully logged in");
         alert.showAndWait();
             }
         }
                   Alert alert = new Alert(Alert.AlertType.ERROR);
         alert.setHeaderText(null);
alert.setContentText("Error");
         alert.showAndWait();

     }
     
    }
     private boolean validatePassword(){
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
    }

    @FXML
    private void connect(ActionEvent event) throws SQLException {
            if(validatePassword()==true)
    {
        validateusername();
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

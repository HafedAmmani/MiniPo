/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui;

import com.esprit.Entite.User;
import com.esprit.Service.ServicePersonne;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
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
        private TextField tfpassword;
        @FXML
        private TextField tfCpass;
        @FXML
        private Button btnsign;
        @FXML
        private ComboBox<String> cbrole;
        private ObservableList<String> list = FXCollections.observableArrayList("Male","female");





    /**
     * Initializes the controller class.
     */
        
        
    private ServicePersonne sp = new ServicePersonne();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbrole.setItems(list);
        // TODO
         btnsign.setOnAction(e->{
            try {
                
                insertNewUser();
            } catch (SQLException ex) {
                Logger.getLogger(InscriptionController.class.getName()).log(Level.SEVERE, null, ex);
            }
		});
    
    
    
    
    
    }
           
    
   

      private void insertNewUser() throws SQLException{ // for adding new Employe
        String nom = tfname.getText();
            String unom= tfusername.getText();
            String prenom = tflastname.getText();
            String mail = tfemail.getText();
            String pass = tfpassword.getText();
            String Cpass = tfCpass.getText();
            String genre =cbrole.getValue();
            
            
      
        User p1;
        p1 = new User(tfusername.getText(),tfname.getText(), tflastname.getText(), tfemail.getText(), tfpassword.getText(),cbrole.getValue());
        sp.ajouter(p1);
        tfusername.setText("");
        tflastname.setText("");
        tfemail.setText("");
        tfpassword.setText("");
        cbrole.setValue(genre);
        
    }  

}
    


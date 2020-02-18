/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui;

import com.esprit.Service.ServicePersonne;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author ASUS
 */

public class LoginController implements Initializable {
    
    @FXML
    private TextField tlogin;

    @FXML
    private TextField tpassword;

    @FXML
    private Button btnconnect;

    @FXML
    private Hyperlink lienhypertext;

    @FXML
    private Button btnsign;

        




    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    private void Login(){
        String email=tlogin.getText().toString();
        String password=tpassword.getText().toString();
        
        
    }
    
}

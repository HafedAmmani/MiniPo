/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.Gui;

import static com.esprit.Gui.ListeProduitController.prod;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class BoitteDialogueController implements Initializable {

    @FXML
    private Text txtdiag;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnValiderCmdAction(ActionEvent event) {
        
        try{
        FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("Panier.fxml"));
        Parent root = loader.load();
        txtdiag.getScene().setRoot(root);
        
        }catch(Exception e){
        
        System.out.println(e.getMessage());
        }
        
    }

    @FXML
    private void btnRetourAction(ActionEvent event) {
        
        try{
        FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("ListeProduit.fxml"));
        Parent root = loader.load();
        txtdiag.getScene().setRoot(root);
        }catch(Exception e){
        
        System.out.println(e.getMessage());
        }
    }
    
    void setTxtDiag(String designation) {
        
        this.txtdiag.setText(designation);
        
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.Gui;


import com.esprit.Entite.Reclamationemploye;

import com.esprit.Service.ServiceReclamationEmploye;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author darra
 */
public class ReclamationEmployeController implements Initializable {

    @FXML
    private TextField sujetRec;
    @FXML
    private TextArea description;
    @FXML
    private Button btnReclamer;

    private ObservableList<String> oblist=FXCollections.observableArrayList("Probleme de compte","probleme de commande","autre");
    private ServiceReclamationEmploye  servRec= new ServiceReclamationEmploye() ;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML
    private void redirectToDemandeConge(ActionEvent event) throws IOException {
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("DemandeConge.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    }  

    @FXML
    private void AjouterReclamationEmploye(ActionEvent event) throws SQLException {
        ajouterReclamationEmploye();
    }


    private void ajouterReclamationEmploye() throws SQLException {
        int id=1;
        String sujet=sujetRec.getText();
        String Description=description.getText();
        
        
        Reclamationemploye rec= new Reclamationemploye(id, sujet, Description);
        servRec.ajouterReclamationEmploye(rec);
        
        sujetRec.setText("");
        description.setText("");
        
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Reclamation Employe");
        //alert.setHeaderText("Information");
        alert.setContentText("Reclamation envoyee");
        alert.showAndWait();
    }
    
    
}

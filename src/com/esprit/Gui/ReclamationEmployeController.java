/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.Gui;

import com.esprit.Entite.Reclamation;
import com.esprit.Entite.Reclamationemploye;
import com.esprit.Service.ServiceReclamation;
import com.esprit.Service.ServiceReclamationEmploye;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

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
    @FXML
    private Button btnAnnuler;

    private ObservableList<String> oblist=FXCollections.observableArrayList("Probleme de compte","probleme de commande","autre");
    private ServiceReclamationEmploye  servRec= new ServiceReclamationEmploye() ;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AjouterReclamationEmploye(ActionEvent event) throws SQLException {
        ajouterReclamationEmploye();
    }

    @FXML
    private void AnnulerReclamation(ActionEvent event) {
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

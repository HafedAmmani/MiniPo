/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.Gui;


import com.esprit.Entite.Reclamationemploye;
import com.esprit.Entite.ReclamationsEmploye;
import com.esprit.Service.ServiceReclamationEmploye;
import com.esprit.gui.LoginUserController;
import java.io.IOException;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
    private ComboBox<String> categRec;
 private ObservableList<String> oblist=FXCollections.observableArrayList("Probleme de compte","autre");

    @FXML
    private TextField sujetRec;
    @FXML
    private TextArea description;
    @FXML
    private Button btnReclamer;

    
    private ServiceReclamationEmploye  servRec= new ServiceReclamationEmploye() ;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        categRec.setItems(oblist);
        int id = LoginUserController.NumId;
        System.out.println(id);
    }
 @FXML
    private void LogoutAction(ActionEvent event) {
        
        try {
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("LoginUser.fxml"));
            Scene tableViewScene = new Scene(tableViewParent);
            
            //This line gets the Stage information
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            
            window.setScene(tableViewScene);
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    private void redirectToMesRec(ActionEvent event) throws IOException {
        //EmployeMeReclamation
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("EmployeMesReclamations.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    }

    @FXML
    private void AjouterReclamationEmploye(ActionEvent event) throws SQLException, IOException {
       // ajouterReclamationEmploye();
       int id = LoginUserController.NumId;
        System.out.println(id);
        String sujet=sujetRec.getText();
        String Description=description.getText();
         int combo=0;
        if(categRec.getValue()=="Probleme de compte"){
        combo=1;}
        if(categRec.getValue()=="autre"){
        combo=2;}
        
        
        Reclamationemploye rec= new Reclamationemploye(combo, sujet, Description,id);
        servRec.ajouterReclamationEmploye(rec);
        
        sujetRec.setText("");
        description.setText("");
        
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Reclamation Employe");
        //alert.setHeaderText("Information");
        alert.setContentText("Reclamation envoyee");
        alert.showAndWait();
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("/com/esprit/Gui/EmployeMesReclamations.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    }
    
    
    
    private void ajouterReclamationEmploye() throws SQLException, IOException {
        int id = LoginUserController.NumId;
        System.out.println(id);
        String sujet=sujetRec.getText();
        String Description=description.getText();
         int combo=0;
        if(categRec.getValue()=="Probleme de compte"){
        combo=1;}
        if(categRec.getValue()=="autre"){
        combo=2;}
        
        
        Reclamationemploye rec= new Reclamationemploye(combo, sujet, Description,id);
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

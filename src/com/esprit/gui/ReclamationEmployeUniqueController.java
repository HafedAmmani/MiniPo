/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.Gui;

import com.esprit.Service.ServiceReclamationEmploye;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author darra
 */
public class ReclamationEmployeUniqueController implements Initializable {

    @FXML
    private Label date;
    @FXML
    private Label categorie;
    @FXML
    private TextField objet;
    @FXML
    private TextArea description;
    @FXML
    private TextArea reponse;
    
    @FXML
    private Button btn_modifier;
    @FXML
    private Label idr;
    @FXML
    private Label etat;
    @FXML
    private Button btnretour;
    ServiceReclamationEmploye sr= new ServiceReclamationEmploye();

      public void setDate(String date) {
        this.date.setText(date);
    }

    public void setObjet(String objet) {
        this.objet.setText(objet);
    }
    public void setCategorie(String categorie) {
        this.categorie.setText(categorie);
    }
   
    public void setDescription(String description) {
        this.description.setText(description);
    }

    public void setReponse(String reponse) {
        this.reponse.setText(reponse);
    }

    public void setEtat(String etat) {
        this.etat.setText(etat);
    }

    public void setIdr(int idr) {
        this.idr.setText(Integer.toString(idr));
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
    private void redirectToRecEmp(ActionEvent event) throws IOException {
        //EmployeMeReclamation
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("ReclamationEmploye.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    }
   
    @FXML
    private void modifier(ActionEvent event) throws SQLException, IOException {
        if(etat.getText().equals("non traiter")){
            sr.updateEmploye(Integer.parseInt(idr.getText()), description.getText());
            TrayNotification tray =new TrayNotification();
            tray.setTitle(" Reclamation Valider");
        tray.setMessage("La réclamation a bien été modifier");
        tray.setAnimationType(AnimationType.POPUP);
        tray.setNotificationType(NotificationType.INFORMATION);
        tray.showAndWait();
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("/com/esprit/Gui/EmployeMesReclamations.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
        }
        else {
             TrayNotification tray =new TrayNotification();
            tray.setTitle("non valide");
        tray.setMessage("La réclamation a deja été traité");
        tray.setAnimationType(AnimationType.POPUP);
        tray.setNotificationType(NotificationType.INFORMATION);
        tray.showAndWait();
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("/com/esprit/Gui/EmployeMesReclamations.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
        }
    }

   @FXML
    private void BoutonRetour(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("/com/esprit/Gui/EmployeMesReclamations.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    }
    
}

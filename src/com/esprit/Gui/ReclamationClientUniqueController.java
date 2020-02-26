/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.Gui;

import com.esprit.Service.ServiceReclamation;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
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
public class ReclamationClientUniqueController implements Initializable {

    @FXML
    private Label date;
    @FXML
    private TextField objet;
    @FXML
    private Label type;
    @FXML
    private Button btn_modifier;
    @FXML
    private TextArea description;
    @FXML
    private TextArea reponse;
    @FXML
    private Label idr;
    @FXML
    private Label etat;
    ServiceReclamation sr = new ServiceReclamation();

    public void setDate(String date) {
        this.date.setText(date);
    }

    public void setObjet(String objet) {
        this.objet.setText(objet);
    }

    public void setType(String type) {
        this.type.setText(type);
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
    private void Modifier(ActionEvent event) throws SQLException, IOException {
        
        
        if(etat.getText().equals("non traité")){
            sr.updateClient(Integer.parseInt(idr.getText()), description.getText());
            TrayNotification tray =new TrayNotification();
            tray.setTitle(" Reclamation Valider");
        tray.setMessage("La réclamation a bien été modifier");
        tray.setAnimationType(AnimationType.POPUP);
        tray.setNotificationType(NotificationType.INFORMATION);
        tray.showAndWait();
        }
        else {
             TrayNotification tray =new TrayNotification();
            tray.setTitle("non valide");
        tray.setMessage("La réclamation a deja été traité");
        tray.setAnimationType(AnimationType.POPUP);
        tray.setNotificationType(NotificationType.INFORMATION);
        tray.showAndWait();
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("/com/esprit/Gui/ClientMesReclamations.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
        }
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.Gui;

import com.esprit.Service.ServiceReclamation;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 *
 * @author darra
 */
public class ReclamationChaqueEmployeController implements Initializable{

    @FXML
    private TextArea description;

    @FXML
    private TextArea reponse;

    @FXML
    private Button btnTraiter;

    @FXML
    private TextField objetTxtField;

    @FXML
    private TextField idREmp;

    @FXML
    private Label labelrec;

    private Label nomPrenom;
    @FXML
    private Label nomprenom;
    @FXML
    private Label date;

    @FXML
    private void redirectToProduit(ActionEvent event) throws IOException {
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("EspaceProduit.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    }
    @FXML
    private void redirectToReclamation(ActionEvent event) throws IOException {
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("AcceuilReclamationAd.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    }

    public void setDescription(String  description) {
        this.description.setText(description);
    }

    public void setObjetTxtField(String objetTxtField) {
        this.objetTxtField.setText(objetTxtField);
    }

    public void setIdREmp(int idREmp) {
        this.idREmp.setText(Integer.toString(idREmp));
    }


   


    public void setNomPrenom(String nom , String prenom) {
        String var=nom+" "+prenom;
        this.nomprenom.setText(var);
    }

    public void setDate(String date) {
        this.date.setText(date);
    }
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }

    @FXML
    void Traiter(ActionEvent event) {
        ServiceReclamationEmploye sre=new ServiceReclamationEmploye();
        try {
            sre.updateAdmin(Integer.parseInt(idREmp.getText()),"traiter",reponse.getText());
            //AfficherListeReclamations();
            TrayNotification tray =new TrayNotification();
            tray.setTitle("Valider");
        tray.setMessage("La réclamation a bien été traité");
        tray.setAnimationType(AnimationType.POPUP);
        tray.setNotificationType(NotificationType.INFORMATION);
        tray.showAndWait();
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("/com/esprit/Gui/ListerReclamationEmployes.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationChaqueClientController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ReclamationChaqueClientController.class.getName()).log(Level.SEVERE, null, ex);
        }}
    
}

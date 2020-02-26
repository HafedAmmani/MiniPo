/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.Gui;

import com.esprit.Entite.ReclamationClient;
import com.esprit.Service.ServiceReclamation;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author darra
 */
public class ReclamationChaqueClientController implements Initializable {

    @FXML
    private ComboBox<String> categorie;
    @FXML
    private TextArea description;
    @FXML
    private TextArea reponse;
    @FXML
    private Button btnTraiter;
    @FXML
    private TextField objetTxtField;
    String objet;
    @FXML
    private TextField idClient;
    @FXML
    private Label nomPrenom;
    @FXML
    private Label labelrec;
    @FXML
    private TextField etat;
    @FXML
    private Label date;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       reponse.setPromptText("veuillez entrer votre reponse !");
       reponse.setPrefColumnCount(50);
       reponse.setPrefRowCount(5);
       reponse.setWrapText(true);
    }    

    //@FXML
    //private void Traiter(ActionEvent event) {
        
   // }

   // public void setCategorie(ComboBox<ReclamationClient> categorie) {
       // this.categorie.setText(ReclamationClient.setObjet());
    //}

    public void setDescription(String description) {
        this.description.setText(description);
    }

    public void setObjetTxtField(String objet) {
        this.objetTxtField.setText(objet);
    }

    public void setCategorie(String categorie) {
        this.categorie.setValue(categorie);
    }

    public void setNomPrenom(String nom ,  String prenom) {
        String var=nom+" "+prenom;
        this.nomPrenom.setText(var);
    }

    public void setEtat(String etat) {
        this.etat.setText(etat);
    }

    public void setDate(String  date) {
        this.date.setText(date);
    }

   

    
   

    public String getObjet() {
        return objet;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }

    public void setIdClient(int id ) {
        this.idClient.setText(Integer.toString(id));
    }

    
      
    @FXML
    private void Traiter(ActionEvent event) {
        
        ServiceReclamation sr=new ServiceReclamation();
        try {
            if(etat.getText().equals("traiter")){
                TrayNotification tray =new TrayNotification();
            tray.setTitle("Valider");
        tray.setMessage("La réclamation a deja été traité");
        tray.setAnimationType(AnimationType.POPUP);
        tray.setNotificationType(NotificationType.INFORMATION);
        tray.showAndWait();
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("/com/esprit/Gui/ListerReclamationClients.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
            
            }else{
            sr.updateAdmin("traiter",Integer.parseInt(idClient.getText()),reponse.getText());
            //AfficherListeReclamations();
            TrayNotification tray =new TrayNotification();
            tray.setTitle("Valider");
        tray.setMessage("La réclamation a bien été traité");
        tray.setAnimationType(AnimationType.POPUP);
        tray.setNotificationType(NotificationType.INFORMATION);
        tray.showAndWait();
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("/com/esprit/Gui/ListerReclamationClients.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();}
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationChaqueClientController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ReclamationChaqueClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    

    

   
    
}

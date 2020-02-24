/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.Gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author darra
 */
public class AccueilReclamationAdminController implements Initializable {
     Stage prevStage;
   public void setPrevStage(Stage stage)
   {
    this.prevStage = stage;
   }
    private AnchorPane AccueilReclamation;

    @FXML
    private Button ListeRecBtn;

    @FXML
    private Button StatBtn;

    @FXML
   private ImageView ListeRecImg;

    @FXML
    private ImageView StatImg;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        Image ListeRec= new Image("/Images/ListeRec.png");
        ListeRecImg.setImage(ListeRec);
        Image Stat= new Image("/Images/imagestat.png");
        StatImg.setImage(Stat);
        
    }  
                 private void setNode(Node node) {
        AccueilReclamation.getChildren().clear();
        AccueilReclamation.getChildren().add((Node) node);
        FadeTransition ft = new FadeTransition(Duration.seconds(1));//dure de la translation
        ft.setNode(node);
        ft.setFromValue(0.10);//dispartion 
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(true);
        ft.play();
    }

    void ListerReclamation(ActionEvent event) throws IOException {
        
          setNode(FXMLLoader.load(getClass().getResource("/com/esprit/Gui/ListesReclamation.fxml")));
          
    }

    @FXML
    void ListerStatistiques(ActionEvent event) throws IOException {
         
         setNode( FXMLLoader.load(getClass().getResource("/com/esprit/Gui/chart.fxml")));
          

    }
     @FXML
    private void redirectToListe(ActionEvent event) throws IOException {
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("ListesReclamation.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    } 
    
}

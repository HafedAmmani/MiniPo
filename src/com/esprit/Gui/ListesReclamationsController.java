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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author darra
 */
public class ListesReclamationsController implements Initializable {
    private AnchorPane RecCltEmp;

    @FXML
    private Button RecClient;
    @FXML
    private Button RecEmpBtn;

    @Override
    
    public void initialize(URL url, ResourceBundle rb) {
        
        
        // TODO
    }
private void setNode(Node node) {
        RecCltEmp.getChildren().clear();
        RecCltEmp.getChildren().add((Node) node);
        FadeTransition ft = new FadeTransition(Duration.seconds(1));//dure de la translation
        ft.setNode(node);
        ft.setFromValue(0.10);//dispartion 
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(true);
        ft.play();
    }  
  @FXML
    void ListerRecEmployes(ActionEvent event) throws IOException {
          Parent tableViewParent = FXMLLoader.load(getClass().getResource("/com/esprit/Gui/ListerReclamationEmployes.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
              //setNode(FXMLLoader.load(getClass().getResource("/com/esprit/Gui/ListerReclamationEmploye.fxml")));
    }
    @FXML
    void ListerRecClient(ActionEvent event) throws IOException {
         Parent tableViewParent = FXMLLoader.load(getClass().getResource("/com/esprit/Gui/ListerReclamationClients.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
            // setNode(FXMLLoader.load(getClass().getResource("/com/esprit/Gui/ListerReclamationClients.fxml")));
    }
}
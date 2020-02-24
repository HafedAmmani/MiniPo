/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.Gui;

import com.esprit.Entite.ReclamationClient;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author darra
 */
public class ReclamationDetailController extends ListCell<ReclamationClient> {

    @FXML
    private TextArea description;
    @FXML
    private TextArea reponse;
    @FXML
    private TextField type;
    @FXML
    private TextField objet;
    private FXMLLoader mLLoader;

   @Override
    protected void updateItem(ReclamationClient rc, boolean empty) {
        
        mLLoader = new FXMLLoader(getClass().getResource("/com/esprit/Gui/ReclamationDetail.fxml"));
        mLLoader.setController(this);
         try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
         type.setText(rc.getType());
         objet.setText(rc.getObjet());
         description.setText(rc.getDescription());
         
}
}
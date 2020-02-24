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
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author darra
 */
public class ReclamationObjetController extends ListCell<ReclamationClient> {

    @FXML
    private TextField objet;
    @FXML
    private TextField type;
    @FXML
    private TextField etat;
    private FXMLLoader mLLoader;

   @Override
    protected void updateItem(ReclamationClient rc, boolean empty) {
        super.updateItem(rc, empty);

        if (empty || rc == null) {

            setText(null);
            setGraphic(null);

        } else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("/com/esprit/Gui/ReclamationObjet.fxml"));
                mLLoader.setController(this);

                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
      
         type.setText(rc.getType());
         objet.setText(rc.getObjet());
         etat.setText(rc.getEtatr());
         
}
   
    }
}

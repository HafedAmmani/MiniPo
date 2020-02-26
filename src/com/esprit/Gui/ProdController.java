/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.Gui;

import com.esprit.Entite.Panier;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class ProdController extends ListCell<Panier> {

    private FXMLLoader mLLoader;
    
    
    @FXML
    private ImageView imgprod;
    @FXML
    private Text tot;
    @FXML
    private Text qte;
    @FXML
    private Text categ;

    /**
     * Initializes the controller class.
     */
    public void initialize(URL url, ResourceBundle rb) {
        
       if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("/PI/src/com/esprit/Gui/prod.fxml"));
                mLLoader.setController(this);

                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
    } 
        Parent tableViewPrestations;
                        try {
                            tableViewPrestations = FXMLLoader.load(getClass().getResource("/PI/src/com/esprit/Gui/View.fxml"));
                            
 
                        } catch (IOException ex) {
                            
                        }
    }
}

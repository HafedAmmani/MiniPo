/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.Gui;

import com.esprit.Entite.Panier;
import com.esprit.Service.ServiceLigneCommande;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class ViewController implements Initializable {

    @FXML
    private ListView<Panier> Listpan;
    
    private ObservableList<Panier> oblist=FXCollections.observableArrayList();
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ServiceLigneCommande slc=new ServiceLigneCommande();
        oblist = slc.ListerPannier();
        Listpan.setItems(oblist);
        Listpan.setCellFactory(( variable -> new ProdController()));
       
        // TODO
    }    
    
}

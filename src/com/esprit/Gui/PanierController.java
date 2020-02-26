/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.Gui;

import com.esprit.Entite.Panier;
import com.esprit.Service.ServiceLigneCommande;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class PanierController implements Initializable {

    @FXML
    private TableView<Panier> tabpan;
    @FXML
    private TableColumn<Panier, String> col_desc;
    @FXML
    private TableColumn<Panier, String> col_categ;
    @FXML
    private TableColumn<Panier, Float> col_prix;
    @FXML
    private TableColumn<Panier, Integer> col_qte;

    private ObservableList<Panier> oblist=FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        AfficherPanier();
    }   
    
    private void AfficherPanier(){
    
         ServiceLigneCommande slc=new ServiceLigneCommande();
         oblist = slc.ListerPannier();
         
            col_desc.setCellValueFactory(new PropertyValueFactory<>("designation"));     
            col_prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
            col_categ.setCellValueFactory(new PropertyValueFactory<>("nom"));
            col_qte.setCellValueFactory(new PropertyValueFactory<>("qte"));

            tabpan.setItems(oblist);
      
     }
    @FXML
    private void PanierAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Interface1.fxml"));
            
            Parent root = loader.load();
            tabpan.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(Interface1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
 

    
    
}

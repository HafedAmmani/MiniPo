/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.Gui;

import com.esprit.Entite.Commande;
import com.esprit.Entite.LigneCommande;
import com.esprit.Entite.Panier;
import com.esprit.Service.ServiceCommande;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class Interface1Controller implements Initializable {
    @FXML
    private TableColumn<Panier, Integer> id;
    @FXML
    private TextField tfqte;
    @FXML
    private Button btnmod;
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
    @FXML
    private TableColumn<Panier, Integer> col_id;
    
    @FXML
    private Button Acceuill;
    @FXML
    private Button panier;
    
    
    private ObservableList<Panier> oblist=FXCollections.observableArrayList();
    @FXML
    private Button retour;
    @FXML
    private Button valider;
    @FXML
    private Button supp;
    @FXML
    private TableColumn<?, ?> col_idcmd;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AfficherPanier();
        
    }    


    @FXML
    private void ModifierAction(ActionEvent event) {
        
        oblist=tabpan.getSelectionModel().getSelectedItems();
        
        ServiceLigneCommande slc=new ServiceLigneCommande();
        LigneCommande lc=new LigneCommande(oblist.get(0).getIdLc(),Integer.parseInt(tfqte.getText()));
        
        slc.modifierQte(oblist.get(0).getIdLc(),oblist.get(0).getQte(),lc);
        AfficherPanier();
        
        
    }
    
    
    
    
    private void AfficherPanier(){
    
         ServiceLigneCommande slc=new ServiceLigneCommande();
         oblist = slc.ListerPannier();
         
            col_desc.setCellValueFactory(new PropertyValueFactory<>("designation"));     
            col_prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
            col_categ.setCellValueFactory(new PropertyValueFactory<>("nom"));
            col_qte.setCellValueFactory(new PropertyValueFactory<>("qte"));
            col_id.setCellValueFactory(new PropertyValueFactory<>("idLc"));
            col_idcmd.setCellValueFactory(new PropertyValueFactory<>("idcmd"));

            tabpan.setItems(oblist);
      
     }

    @FXML
    private void AcceuilAction(ActionEvent event) {
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Acceuil.fxml"));
            
            Parent root = loader.load();
            tfqte.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(Interface1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void PanierAction(ActionEvent event) {
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Interface1.fxml"));
            
            Parent root = loader.load();
            tfqte.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(Interface1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void OnCliqAction(MouseEvent event) {
        
        oblist=tabpan.getSelectionModel().getSelectedItems();
        tfqte.setText(String.valueOf(oblist.get(0).getQte()));
    }

    @FXML
    private void btnRetourAction(ActionEvent event) {
        
        
        try{
        FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("Acceuil.fxml"));
        Parent root = loader.load();
        tfqte.getScene().setRoot(root);
        }catch(Exception e){
        
        System.out.println(e.getMessage());
        }
    }

    @FXML
    private void btnValiderCmdAction(ActionEvent event) {
        
        TrayNotification tray =new TrayNotification();
            tray.setTitle("Valider");
        tray.setMessage("Commande validéé !!!!!");
        tray.setAnimationType(AnimationType.POPUP);
        tray.setNotificationType(NotificationType.INFORMATION);
        tray.showAndWait();
        
        oblist=tabpan.getSelectionModel().getSelectedItems();
        //ServiceLigneCommande slc=new ServiceLigneCommande();
        //LigneCommande lc=new LigneCommande(Integer.parseInt(tfid.getText()),Integer.parseInt(tfqte.getText()));
        ServiceCommande sc=new ServiceCommande();
        //Commande c=sc.getCommande(lc.getCommande().getIdcmd());
        sc.modifierEtatCommande(oblist.get(0).getIdcmd());
        AfficherPanier();   
     
        
    }

    @FXML
    private void SuppessionAction(ActionEvent event) {
        ServiceLigneCommande slc=new ServiceLigneCommande();
        LigneCommande lc=new LigneCommande(oblist.get(0).getIdLc(),Integer.parseInt(tfqte.getText()));
        slc.supprimerLigneCommande(lc);
        AfficherPanier();
    }
    
   
}

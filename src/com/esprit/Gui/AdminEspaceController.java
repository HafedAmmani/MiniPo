/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.Gui;

import com.esprit.Entite.Client;
import com.esprit.Entite.Commande;
import com.esprit.Entite.Commandes;
import com.esprit.Service.ServiceCommande;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class AdminEspaceController implements Initializable {

    @FXML
    private TableView tabcom;
    @FXML
    private TableColumn <Object,?> col_id;
    @FXML
    private TableColumn <Object,?> col_non;
    @FXML
    private TableColumn <Object,?> col_pre;
    @FXML
    private TableColumn <Object,?> col_date;
    @FXML
    private TableColumn <Object,?> col_et;
    @FXML
    private TableColumn <Object,?> col_btnMod;
    @FXML
    private TableColumn <Object,?> col_btnSupp;
    @FXML
    private Button Acceuil;
    
    @FXML
    private Button mod ;
    @FXML
    private Button supp;
    
    
    //private ObservableList<Commandes> oblist=FXCollections.observableArrayList();
    
    private ObservableList<Commande> oblist=FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
    
        //AfficherCommandes(mod,supp);
        
        AfficherCommandes();
    }    

    @FXML
    private void AcceuilAction(ActionEvent event) {
    }

    /*private void AfficherCommandes(Button mod,Button supp){
        
        try{
        
        
        
        ServiceCommande sc=new ServiceCommande();
        oblist = sc.Commandes(mod,supp);
        
        col_id.setCellValueFactory(new PropertyValueFactory<Commandes,String>("idCmd"));
        col_non.setCellValueFactory(new PropertyValueFactory<Commandes,String>("nomClt"));
        col_pre.setCellValueFactory(new PropertyValueFactory<Commandes,String>("prenomClt"));
        col_date.setCellValueFactory(new PropertyValueFactory<Commandes,String>("datec"));
        col_et.setCellValueFactory(new PropertyValueFactory<Commandes,String>("etatc"));
        col_btnMod.setCellValueFactory(new PropertyValueFactory<Commandes,Button>("btnMod"));
        col_btnSupp.setCellValueFactory(new PropertyValueFactory<Commandes,Button>("btnSupp"));
        tabcom.setItems(oblist);

        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    
    }*/
    
    private void AfficherCommandes(){
        
        try{
        
        
        
        ServiceCommande sc=new ServiceCommande();
        oblist = sc.CommandesValider();
        
        col_id.setCellValueFactory(new PropertyValueFactory ("idCmd"));
        col_non.setCellValueFactory(new PropertyValueFactory("nomClt"));
        col_pre.setCellValueFactory(new PropertyValueFactory("prenomClt"));
        col_date.setCellValueFactory(new PropertyValueFactory("datec"));
        col_et.setCellValueFactory(new PropertyValueFactory("etatc"));
        col_btnMod.setCellValueFactory(new PropertyValueFactory("btnMod"));
        col_btnSupp.setCellValueFactory(new PropertyValueFactory("btnSupp"));
        tabcom.setItems(oblist);

        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    
    }
    
    public void btnModifierAction(ActionEvent event){
        
        /*if(event.getSource().equals(mod)){
           oblist=tabcom.getSelectionModel().getSelectedItems();
           ServiceCommande sc=new ServiceCommande();
           Commande c=sc.getCommande(Integer.parseInt(oblist.get(0).getIdCmd()));
           sc.AccepterCommande(c);
           

        }*/
            
            
        System.out.println("modif");
    }
    
    public void btnSupprimerAction(ActionEvent event){
        
       /* if(event.getSource().equals(supp)){
           oblist=tabcom.getSelectionModel().getSelectedItems();
           ServiceCommande sc=new ServiceCommande();
           Commande c=sc.getCommande(Integer.parseInt(oblist.get(0).getIdCmd()));
           sc.supprimerCommande(c);
           
           AfficherCommandes();
           System.out.println("supp");
         }   
        }*/
        
        System.out.println("supp");
    
   
    }

    @FXML
    private void OnClickAction(MouseEvent event) {
        
        
    }
    

}

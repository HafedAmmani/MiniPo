/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.Gui;

import com.esprit.Entite.User;
import com.esprit.Entite.Produit;
import com.esprit.Service.ServiceProduit;
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
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class AcceuilController implements Initializable {
    
    public static User clt= new User(2,"test","test","Saadi", "meiem", "tunis","test@gmail.com"); 

    public static Produit prod=new Produit();
    
    public static Produit getProd() {
        return prod;
    }
    
    public static void setProd() {
        ServiceProduit sp=new ServiceProduit();
        AcceuilController.prod = sp.getProduit(2);
    }


    @FXML
    private TableView<Produit> tblview;
//    @FXML
//    private TableColumn<Produit, Integer> id;
    @FXML
    private TableColumn<Produit, Integer> col_id;
    @FXML
    private TableColumn<Produit, String> col_des;
//    @FXML
//    private TableColumn<Produit, String> col_categ;
    @FXML
    private TableColumn<Produit, Float> col_prix;
    ObservableList<Produit>oblist=FXCollections.observableArrayList();
    @FXML
    private Button ach;
    @FXML
    private Button Acceuil;
    @FXML
    private Button panier;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Produit>oblist=FXCollections.observableArrayList();
    	

                oblist.add(prod);
                col_id.setCellValueFactory(new PropertyValueFactory<>("idprod"));
		col_des.setCellValueFactory(new PropertyValueFactory<>("designation"));
		col_prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
		
		
		tblview.setItems(oblist);
    }    


    @FXML
    private void acheterAction(ActionEvent event) {
        
        try{
        FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("InfoProd.fxml"));
        
        Parent root = loader.load();
        InfoProdController tc = loader.getController();
        
        AcceuilController.setProd();
        tc.setTfNom(prod.getDesignation());
        tc.setTfCategorie(prod.getCategorie().getNom());
        tc.setTfPrix((float)prod.getPrix());
        
        
        ach.getScene().setRoot(root);
        }catch(Exception e){
        
        System.out.println(e.getMessage());
        }
        
    
}

    @FXML
    private void AcceuilAction(ActionEvent event) {
        
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Acceuil.fxml"));
            
            Parent root = loader.load();
            ach.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(Interface1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void PanierAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Interface1.fxml"));
            
            Parent root = loader.load();
            ach.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(Interface1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    
  
  
    

    }
}

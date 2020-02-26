/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.Gui;

import com.esprit.Entite.Client;
import com.esprit.Entite.Produit;
import com.esprit.Service.ServiceProduit;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class AcceuilController implements Initializable {
    
    public static Client clt= new Client(2,"test","test","07818586","Saadi", "meiem", "tunis","99652033","test@gmail.com"); 

    public static Produit prod=new Produit();
    
    public static Produit getProd() {
        return prod;
    }
    
    public static void setProd() {
        ServiceProduit sp=new ServiceProduit();
        AcceuilController.prod = sp.getProduit(6);
    }


    @FXML
    private TableView<Produit> tblview;
    @FXML
    private TableColumn<Produit, Integer> id;
    @FXML
    private TableColumn<Produit, Integer> col_id;
    @FXML
    private TableColumn<Produit, String> col_des;
    @FXML
    private TableColumn<Produit, String> col_categ;
    @FXML
    private TableColumn<Produit, Float> col_prix;
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
        // TODO
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
        tc.setTfPrix(prod.getPrix());
        
        
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

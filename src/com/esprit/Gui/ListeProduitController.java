/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.Gui;

import com.esprit.Entite.Client;
import com.esprit.Entite.Produit;
import com.esprit.Service.ServiceProduit;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class ListeProduitController implements Initializable {
    
    public static Client clt= new Client(2,"test","test","07818586","Saadi", "meiem", "tunis","99652033","test@gmail.com"); 

    public static Produit prod=new Produit();
    
    public static Produit getProd() {
        return prod;
    }

    public static void setProd() {
        ServiceProduit sp=new ServiceProduit();
        ListeProduitController.prod = sp.getProduit(2);
    }

    @FXML
    private Button btnAcheter;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void btnAcheterAction(ActionEvent event) {
        try{
        FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("test.fxml"));
        
        Parent root = loader.load();
        TestController tc = loader.getController();
        
        ListeProduitController.setProd();
        tc.setTfNom(prod.getDesignation());
        tc.setTfCategorie(prod.getCategorie().getNom());
        tc.setTfPrix(prod.getPrix());
        
        
        btnAcheter.getScene().setRoot(root);
        }catch(Exception e){
        
        System.out.println(e.getMessage());
        }
        
        
    }
    
}

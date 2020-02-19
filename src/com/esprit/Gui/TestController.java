/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.Gui;

import com.esprit.Entite.Categorie;
import com.esprit.Entite.Client;
import com.esprit.Entite.Commande;
import com.esprit.Entite.Fournisseur;
import com.esprit.Entite.LigneCommande;
import com.esprit.Entite.Produit;
import static com.esprit.Gui.ListeProduitController.prod;
import com.esprit.Service.ServiceLigneCommande;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class TestController implements Initializable {
    
    
    
    
    
    @FXML
    private Button btnLogout;
    @FXML
    private ImageView img;
    @FXML
    private Label nom;
    @FXML
    private Button btnAjouter;
    @FXML
    private TextField qte;
    @FXML
    private Label tfcateg;
    @FXML
    private Label tfprix;
    
  


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnLogoutAction(ActionEvent event) {
    }

    @FXML
    private void btnAjouterAction(ActionEvent event) {
        
        
        
        ServiceLigneCommande slc=new ServiceLigneCommande();
        LigneCommande lc=new LigneCommande(ListeProduitController.prod,Integer.parseInt(qte.getText()));
        slc.ajouterLigneCommande(lc);
        
        try{
        FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("BoitteDialogue.fxml"));
        
        Parent root = loader.load();
        BoitteDialogueController bc = loader.getController();
        bc.setTxtDiag(prod.getDesignation());
        tfcateg.getScene().setRoot(root);
       
        }catch(IOException e){
        
        System.out.println(e.getMessage());
        }
        
     
        
        
    }
    
   
   public void setTfNom(String nom){
       this.nom.setText(nom);
   }
   
   public void setTfPrix(Float prix){
       this.tfprix.setText(prix.toString());
   }
   
   public void setQte(TextField qte){
       this.qte.setText(qte.getText());
   }
   
   public void setTfCategorie(String categ){
       this.tfcateg.setText(categ);
   }


   
   
           
    
}

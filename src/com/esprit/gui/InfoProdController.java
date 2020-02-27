/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.Gui;

import com.esprit.Entite.LigneCommande;
import com.esprit.Entite.Produit;
import com.esprit.Entite.User;
import com.esprit.Service.ServiceLigneCommande;
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
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class InfoProdController implements Initializable {
    
    
     public static User clt= new User(2,"test","test","Saadi", "meiem", "tunis","test@gmail.com"); 

    public static Produit prod=new Produit();
    
    public static Produit getProd() {
        return prod;
    }
    
    public static void setProd() {
        ServiceProduit sp=new ServiceProduit();
        AcceuilController.prod = sp.getProduit(6);
    }

    
    
    

    private Text txtdiag;
    @FXML
    private ImageView img;
    @FXML
    private TextField qte;
    @FXML
    private Text nom;
    @FXML
    private Text tfcateg;
    @FXML
    private Text tfprix;
    @FXML
    private Button btnAjouter;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    private void btnValiderCmdAction(ActionEvent event) {
        
        try{
        FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("Panier.fxml"));
        Parent root = loader.load();
        txtdiag.getScene().setRoot(root);
        
        }catch(Exception e){
        
        System.out.println(e.getMessage());
        }
        
    }

    private void btnRetourAction(ActionEvent event) {
        
        try{
        FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("Acceuil.fxml"));
        Parent root = loader.load();
        txtdiag.getScene().setRoot(root);
        }catch(Exception e){
        
        System.out.println(e.getMessage());
        }
    }
    
    void setTxtDiag(String designation) {
        
        this.txtdiag.setText(designation);
        
    }

    @FXML
    private void btnAjouterAction(ActionEvent event) {
        
        ServiceLigneCommande slc=new ServiceLigneCommande();
        LigneCommande lc=new LigneCommande(AcceuilController.prod,Integer.parseInt(qte.getText()));
        System.out.println(lc);
        slc.ajouterLigneCommande(lc);
        
        try{
        FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("Interface1.fxml"));
        
        Parent root = loader.load();
        Interface1Controller bc = loader.getController();
        //bc.setTxtDiag(prod.getDesignation());
        img.getScene().setRoot(root);
       
        }catch(IOException e){
        
        System.out.println(e.getMessage());
        }
    }

    private void AcceuilAction(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Acceuil.fxml"));
            
            Parent root = loader.load();
             txtdiag.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(Interface1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    private void PanierAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Interface1.fxml"));
            
            Parent root = loader.load();
            nom.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(Interface1Controller.class.getName()).log(Level.SEVERE, null, ex);
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

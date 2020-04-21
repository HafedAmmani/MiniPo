/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.Gui;

import com.esprit.Entite.LigneCommande;
import com.esprit.Entite.Produit;
import com.esprit.Service.ServiceLigneCommande;
import com.esprit.Service.ServiceProduit;
import com.esprit.gui.LoginUserController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class InfoProdController implements Initializable {

    int idUser = LoginUserController.NumId;
    public int idprod=0;        
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
    @FXML
    private Button btnFact;
    @FXML
    private Button btnAcceuil;
    @FXML
    private Button btnProd;
    @FXML
    private Button btnpanier;
    @FXML
    private Button btnCmd;
    @FXML
    private Button btnReclamation;
    @FXML
    private Button btnReclamation1;
 
   
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    private void LogoutAction(ActionEvent event) {
        
        try {
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("LoginUser.fxml"));
            Scene tableViewScene = new Scene(tableViewParent);
            
            //This line gets the Stage information
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            
            window.setScene(tableViewScene);
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
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
        
        ServiceProduit sp=new ServiceProduit();
        Produit prod=sp.getProduit(this.getIdprod());
        ServiceLigneCommande slc=new ServiceLigneCommande();
        LigneCommande lc=new LigneCommande(prod,Integer.parseInt(qte.getText()));
        System.out.println(this.getIdprod());
        System.out.println(lc);
        slc.ajouterLigneCommande(lc);
        
        try{
        FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("Interface1.fxml"));
        
        Parent root = loader.load();
        Interface1Controller bc = loader.getController();
        img.getScene().setRoot(root);
       
        }catch(IOException e){
        
        System.out.println(e.getMessage());
        }
    }

   
     public void setTfNom(String nom){
       this.nom.setText(nom);
   }
   
   public void setTfPrix(String prix){
       this.tfprix.setText(prix);
   }
   
   public void setQte(TextField qte){
       this.qte.setText(qte.getText());
   }
   
   public void setTfCategorie(String categ){
       this.tfcateg.setText(categ);
   }

    public int getIdprod() {
        return idprod;
    }

    public void setIdprod(String idprod) {
        this.idprod = Integer.parseInt(idprod);
    }
    
     @FXML
    private void AcceuilAction(ActionEvent event) {
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Acceuil.fxml"));
            
            Parent root = loader.load();
             txtdiag.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(Interface1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }


    private void ProduitsAction(ActionEvent event) {
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Acceuil.fxml"));
            
            Parent root = loader.load();
             txtdiag.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(Interface1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void PanierAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Interface1.fxml"));
            
            Parent root = loader.load();
            nom.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(Interface1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @FXML
    private void FacturesAction(ActionEvent event) {
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FacturesClt.fxml"));
            
            Parent root = loader.load();
            nom.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(Interface1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }


    @FXML
    private void ReclamationAction(ActionEvent event) {
        
         try {
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("ReclamationClient.fxml"));
            Scene tableViewScene = new Scene(tableViewParent);
            
            //This line gets the Stage information
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            
            window.setScene(tableViewScene);
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void MesReclamationAction(ActionEvent event) {
    
         try {
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("CllientMesReclamations.fxml"));
            Scene tableViewScene = new Scene(tableViewParent);
            
            //This line gets the Stage information
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            
            window.setScene(tableViewScene);
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }

    @FXML
    private void ProduitAction(ActionEvent event) {
    }

    @FXML
    private void CommandeAction(ActionEvent event) {
    }
   
   
    
}

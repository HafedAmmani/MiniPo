/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.Gui;

import com.esprit.Entite.Produit;
import com.esprit.Entite.Produits;
import com.esprit.Service.ServiceCommande;
import com.esprit.Service.ServiceProduit;
import com.esprit.gui.LoginUserController;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class AcceuilController implements Initializable {
    
    int idUser = LoginUserController.NumId;
    
   /* public static User clt= new User(56,"test","test","Saadi", "meiem", "tunis","test@gmail.com");

    public static User getClt() {
        return clt;
    }*/

    private ObservableList<Produits> oblist=FXCollections.observableArrayList();
        

    public static Produit prod=new Produit();
    
    public static Produit getProd() {
        return prod;
    }
    
    public static void setProd() {
        ServiceProduit sp=new ServiceProduit();
        AcceuilController.prod = sp.getProduit(6);
    }
    @FXML
    private TableView tabProd;
    @FXML
    private TableColumn<Object, ?> col_id;
    @FXML
    private TableColumn<Object, ?> col_img;
    @FXML
    private TableColumn<Object, ?> col_des;
    @FXML
    private TableColumn<Object, ?> col_prix;
    @FXML
    private TableColumn<Object, ?> col_qte;
    @FXML
    private TableColumn<Object, ?> col_categ;
    @FXML
    private Button btnach;
    @FXML
    private Button btnFact;
    @FXML
    private Button Acceuil;
    @FXML
    private Button panier;
    @FXML
    private TableColumn<?, ?> id;



    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AfficherProduits();
    }    


    @FXML
    private void acheterAction(ActionEvent event) {
        oblist=tabProd.getSelectionModel().getSelectedItems();
        try{
        FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("InfoProd.fxml"));
        
        Parent root = loader.load();
        InfoProdController tc = loader.getController();
        
        tc.setTfNom(oblist.get(0).getDesignation());
        tc.setTfCategorie(oblist.get(0).getCateg());
        tc.setTfPrix(oblist.get(0).getPrix());
        tc.setIdprod(oblist.get(0).getIdprod());
 
        btnach.getScene().setRoot(root);
        }catch(Exception e){
        
        System.out.println(e.getMessage());
        }
        
    
}
    @FXML
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

   

    

    /*private void ToListeCmdAction(ActionEvent event) {
        
         try{
        FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("ListeCmdClient.fxml"));
        Parent root = loader.load();
        btnach.getScene().setRoot(root);
        }catch(Exception e){
        
        System.out.println(e.getMessage());
        }
    }*/
    
    private void AfficherProduits(){
        
        try{

        ServiceCommande sc=new ServiceCommande();
        oblist = sc.Produits();
        col_id.setCellValueFactory(new PropertyValueFactory ("idprod"));
        col_img.setCellValueFactory(new PropertyValueFactory("photo"));
        col_des.setCellValueFactory(new PropertyValueFactory("designation"));
        col_prix.setCellValueFactory(new PropertyValueFactory("prix"));
        col_qte.setCellValueFactory(new PropertyValueFactory("qtestock"));
        col_categ.setCellValueFactory(new PropertyValueFactory("categ"));
       
        tabProd.setItems(oblist);

        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    
    } 

   

    //*******************************menu espace client*************************    

//*****************************acceuil***************************

     @FXML
    private void AcceuilAction(ActionEvent event) throws IOException {
        
    
         Parent tableViewParent = FXMLLoader.load(getClass().getResource("Acceuil.fxml"));
            Scene tableViewScene = new Scene(tableViewParent);
            
            //This line gets the Stage information
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            
            window.setScene(tableViewScene);
            window.show();
    }

//*****************************Produit***************************

    private void ProduitAction(ActionEvent event) throws IOException {
        
       Parent tableViewParent = FXMLLoader.load(getClass().getResource("Acceuil.fxml"));
            Scene tableViewScene = new Scene(tableViewParent);
            
            //This line gets the Stage information
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            
            window.setScene(tableViewScene);
            window.show();
    }

//*****************************Panier***************************
 @FXML
    private void PanierAction(ActionEvent event) throws IOException, IOException {
       
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("Interface1.fxml"));
            Scene tableViewScene = new Scene(tableViewParent);
            
            //This line gets the Stage information
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            
            window.setScene(tableViewScene);
            window.show();
    }
//*****************************Commande***************************
    @FXML
    private void CommandeAction(ActionEvent event) throws IOException {
     
        
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("ListeCmdClient.fxml"));
            Scene tableViewScene = new Scene(tableViewParent);
            
            //This line gets the Stage information
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            
            window.setScene(tableViewScene);
            window.show();
     
    }

//*****************************Facture***************************

    @FXML
    private void FacturesAction(ActionEvent event) throws IOException {
        
       
       Parent tableViewParent = FXMLLoader.load(getClass().getResource("FacturesClt.fxml"));
            Scene tableViewScene = new Scene(tableViewParent);
            
            //This line gets the Stage information
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            
            window.setScene(tableViewScene);
            window.show();
        
    }
//*****************************Reclamation***************************
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
//*****************************Mes Reclamation***************************
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

    







}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.Gui;

import com.esprit.Entite.Commande;
import com.esprit.Entite.LigneCommande;
import com.esprit.Entite.Panier;
import com.esprit.Entite.User;
import com.esprit.Service.ServiceCommande;
import com.esprit.Service.ServiceLigneCommande;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class Interface1Controller implements Initializable {

    int idUser = LoginUserController.NumId;
    
    private ObservableList<Panier> oblist=FXCollections.observableArrayList();

    @FXML
    private Button Acceuill;
    @FXML
    private Button panier;
    @FXML
    private TextField tfqte;
    @FXML
    private Button btnmod;
    @FXML
    private Button supp;
    @FXML
    private Button retour;
    @FXML
    private Button valider;
    @FXML
    private TableView<Panier> tabpan;
    @FXML
    private TableColumn<Object, ?> col_idlc;
    @FXML
    private TableColumn<Object, ?> col_idcmd;
    @FXML
    private TableColumn<Object, ?> col_des;
    @FXML
    private TableColumn<Object, ?> col_categ;
    @FXML
    private TableColumn<Object, ?> col_px;
    @FXML
    private TableColumn<Object, ?> col_qte;
    @FXML
    private TableColumn<Object, ?> col_tot;
    @FXML
    private Text txt_total;
    @FXML
    private Button btnFact;
   
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        AfficherPanier();
        
        setLab_tot();
        
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
    private void ToListeCmdAction(ActionEvent event) {
        
        try{
        FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("ListeCmdClient.fxml"));
        Parent root = loader.load();
        tfqte.getScene().setRoot(root);
        }catch(Exception e){
        
        System.out.println(e.getMessage());
        }
    }

    @FXML
    private void ModifierAction(ActionEvent event) {
        
        oblist=tabpan.getSelectionModel().getSelectedItems();
        ServiceLigneCommande slc= new ServiceLigneCommande();
        LigneCommande lc=slc.getLc(oblist.get(0).getIdLc());
        slc.modifierQteLc(Integer.parseInt(tfqte.getText()), lc);
        AfficherPanier();
        
    }

    @FXML
    private void SuppessionAction(ActionEvent event) {
        oblist=tabpan.getSelectionModel().getSelectedItems();
        ServiceLigneCommande slc= new ServiceLigneCommande();
        LigneCommande lc=slc.getLc(oblist.get(0).getIdLc());
        slc.deleteLc(lc);
        AfficherPanier();
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
        
        ServiceCommande sc=new ServiceCommande();
        Commande c=sc.RechercherPanierParClient(idUser);
        
        try{
            
        FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("checkout.fxml"));
        
        Parent root = loader.load();
        CheckoutController tc = loader.getController();
        

        tc.setLab_tot(c.getTotal()); 
        tc.setTab(this.tabpan.getItems());

        btnFact.getScene().setRoot(root);
        
        }catch(Exception e){
        
        System.out.println(e.getMessage());
        }
    }

    private void AfficherPanier(){
    
         
         ServiceLigneCommande slc=new ServiceLigneCommande();
         oblist = slc.ListerPannier(idUser);
         
            col_des.setCellValueFactory(new PropertyValueFactory<>("designation"));     
            col_px.setCellValueFactory(new PropertyValueFactory<>("prix"));
            col_categ.setCellValueFactory(new PropertyValueFactory<>("nom"));
            col_qte.setCellValueFactory(new PropertyValueFactory<>("qte"));
            col_tot.setCellValueFactory(new PropertyValueFactory<>("subtotal"));
            col_idlc.setCellValueFactory(new PropertyValueFactory<>("idLc"));
            col_idcmd.setCellValueFactory(new PropertyValueFactory<>("idcmd"));

            tabpan.setItems(oblist);
      
     }

    @FXML
    private void OnCliqueAction(MouseEvent event) {
        
        oblist=tabpan.getSelectionModel().getSelectedItems();
        tfqte.setText(String.valueOf(oblist.get(0).getQte()));
        
    }
    
    public void setLab_tot() {
        
        ServiceCommande sc=new ServiceCommande();
        Commande c=sc.RechercherPanierParClient(idUser);
        if(c != null){
        this.txt_total.setText(String.valueOf(c.getTotal()));
        }
        else{
                this.txt_total.setText("0");
                TrayNotification tray =new TrayNotification();
                tray.setTitle("Panier");
                tray.setMessage("Votre Panier est vide");
                tray.setAnimationType(AnimationType.POPUP);
                tray.setNotificationType(NotificationType.INFORMATION);
                tray.showAndWait();
        }
    }

    @FXML
    private void FacturesAction(ActionEvent event) {
        
        try{
        FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("FacturesClt.fxml"));
        Parent root = loader.load();
        btnmod.getScene().setRoot(root);
        }catch(Exception e){
        
        System.out.println(e.getMessage());
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

  
}

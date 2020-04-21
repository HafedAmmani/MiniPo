/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.Gui;

import com.esprit.Entite.Commande;
import com.esprit.Entite.Panier;
import com.esprit.Service.ServiceCommande;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class CheckoutController implements Initializable {
    
    int idUser = LoginUserController.NumId;

    private ObservableList<Panier> obPan=FXCollections.observableArrayList();
    private String id;
    
    @FXML
    private Button btnAcceuil;
    @FXML
    private Button btnProfil;
    @FXML
    private Button btnProd;
    @FXML
    private Button btnpanier;
    @FXML
    private Button btnCmd;
    @FXML
    private Button btnFact;
    @FXML
    private Button btnReclamation;
    @FXML
    private TableView<Panier> tabpan;
    @FXML
    private TableColumn<Object, ?> col_idlc;
    @FXML
    private TableColumn<Object, ?> col_idcmd;
    @FXML
    private TableColumn<Object, ?> col_des;
    @FXML
    private TableColumn<Object, ?> col_px;
    @FXML
    private TableColumn<Object, ?> col_qte;
    @FXML
    private TableColumn<Object, ?> col_tot;
    @FXML
    private Label lab_tot;
    @FXML
    private TextArea txt_adr;
    @FXML
    private Button btnLivrer;
    @FXML
    private Button btnReclamation1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        AfficherCommandes();
        
    }    

    @FXML
    private void AcceuilAction(ActionEvent event) {
    }

    @FXML
    private void ProfilAction(ActionEvent event) {
    }

    @FXML
    private void ProduitsAction(ActionEvent event) {
        
      try{
        FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("Acceuil.fxml"));
        Parent root = loader.load();
        btnCmd.getScene().setRoot(root);
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

    @FXML
    private void PanierAction(ActionEvent event) {
        
        try{
        FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("Interface1.fxml"));
        Parent root = loader.load();
        btnCmd.getScene().setRoot(root);
        }catch(Exception e){
        
        System.out.println(e.getMessage());
        }
        
    }

    @FXML
    private void CommandesAction(ActionEvent event) {
        
        try{
        FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("ListeCmdClient.fxml"));
        Parent root = loader.load();
        btnCmd.getScene().setRoot(root);
        }catch(Exception e){
        
        System.out.println(e.getMessage());
        }
        
        
    }

    @FXML
    private void FacturesAction(ActionEvent event) {
        
        try{
        FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("FacturesClt.fxml"));
        Parent root = loader.load();
        btnCmd.getScene().setRoot(root);
        }catch(Exception e){
        
        System.out.println(e.getMessage());
        } 
        
    }

    @FXML
    private void ReaclamationAction(ActionEvent event) {
    }

    @FXML
    private void LivrerAction(ActionEvent event) {
        if(txt_adr.getText().length() != 0){   
        ServiceCommande sc =new ServiceCommande();
        
        Commande  c= sc.RechercherPanierParClient(idUser);
                
        sc.validerCommande(txt_adr.getText(),idUser);
        
        //************************************************************************************
        
        Commande cc=sc.getCommande(c.getIdcmd());
        
       try{
            
        FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("InfoCmd.fxml"));
        
        Parent root = loader.load();
        InfoCmdController tc = loader.getController();
        
        tc.setLab_dat(cc.getDatec().toString());
        tc.setLab_et(cc.getEtatc());
        tc.setLab_ref(cc.getRefC());
        tc.setLab_tot(cc.getTotal()); 
        tc.setTab(sc.detailCmdClt(c));

        btnAcceuil.getScene().setRoot(root);
        
        }catch(Exception e){
        
            TrayNotification tray =new TrayNotification();
            tray.setTitle("Erreur");
            tray.setMessage(e.getMessage());
            tray.setAnimationType(AnimationType.POPUP);
            tray.setNotificationType(NotificationType.INFORMATION);
            tray.showAndWait();
        
        }
        
        //************************************************************************************
        
        }
        else{
            TrayNotification tray =new TrayNotification();
            tray.setTitle("Erreur");
            tray.setMessage("Entrez votre adresse s'il vous plait !!!!");
            tray.setAnimationType(AnimationType.POPUP);
            tray.setNotificationType(NotificationType.INFORMATION);
            tray.showAndWait();
        }
        
        
        
        
    }
    
    public void setLab_tot(Float tot) {
        this.lab_tot.setText(tot.toString());
    }
    public void setList(ObservableList<Panier> obPan){
       this.obPan=obPan;
   }
   public ObservableList<Panier> getList(){
       return this.obPan;
   }
   
   public void setTab (ObservableList<Panier> tab) {
       
            col_des.setCellValueFactory(new PropertyValueFactory<>("designation"));     
            col_px.setCellValueFactory(new PropertyValueFactory<>("prix"));
            col_qte.setCellValueFactory(new PropertyValueFactory<>("qte"));
            col_tot.setCellValueFactory(new PropertyValueFactory<>("subtotal"));
            col_idlc.setCellValueFactory(new PropertyValueFactory<>("idLc"));
            col_idcmd.setCellValueFactory(new PropertyValueFactory<>("idcmd"));
            
            this.tabpan.setItems(tab);
    }

    
    private void AfficherCommandes(){
        
        ObservableList<Panier> tt=FXCollections.observableArrayList();
        
        tt=this.getList();
        try{
            
            
            col_des.setCellValueFactory(new PropertyValueFactory<>("designation"));     
            col_px.setCellValueFactory(new PropertyValueFactory<>("prix"));
            col_qte.setCellValueFactory(new PropertyValueFactory<>("qte"));
            col_tot.setCellValueFactory(new PropertyValueFactory<>("subtotal"));
            col_idlc.setCellValueFactory(new PropertyValueFactory<>("idLc"));
            col_idcmd.setCellValueFactory(new PropertyValueFactory<>("idcmd"));

            tabpan.setItems(tt);

        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    
    }
    
    private void Detail(int idPan) {
        
        ServiceCommande sc=new ServiceCommande();
        Commande c=sc.getCommande(idPan);
        
        try{
            
        FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("InfoCmd.fxml"));
        
        Parent root = loader.load();
        InfoCmdController tc = loader.getController();
        
        tc.setLab_dat(c.getDatec().toString());
        tc.setLab_et(c.getEtatc());
        tc.setLab_ref(c.getRefC());
        tc.setLab_tot(c.getTotal()); 
        tc.setTab(sc.detailCmdClt(c));

        btnAcceuil.getScene().setRoot(root);
        
        }catch(Exception e){
        
        System.out.println(e.getMessage());
        }
    }   

    @FXML
    private void MesReaclamationAction(ActionEvent event) {
        
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

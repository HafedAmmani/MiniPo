/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.Gui;

import com.esprit.Entite.Panier;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class DetailFactCltController implements Initializable {

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
    private TableColumn<Object, ?> col_categ;
    @FXML
    private TableColumn<Object, ?> col_px;
    @FXML
    private TableColumn<Object, ?> col_qte;
    @FXML
    private TableColumn<Object, ?> col_tot;
    @FXML
    private Label lab_tot;
    @FXML
    private Label lab_ref;
    @FXML
    private Label lab_dat;
    @FXML
    private Label lab_et;
    @FXML
    private Button btnMesReclamation;

    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
            btnReclamation.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(Interface1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void ProfilAction(ActionEvent event) {
    }

    @FXML
    private void ProduitsAction(ActionEvent event) {
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Acceuil.fxml"));
            
            Parent root = loader.load();
            btnAcceuil.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(Interface1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void PanierAction(ActionEvent event) {
        
        try {
            FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("Interface1.fxml"));
        Parent root = loader.load();
        btnAcceuil.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(Interface1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void CommandesAction(ActionEvent event) {
        
        try{
        FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("ListeCmdClient.fxml"));
        Parent root = loader.load();
        btnAcceuil.getScene().setRoot(root);
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
        btnAcceuil.getScene().setRoot(root);
        }catch(Exception e){
        
        System.out.println(e.getMessage());
        } 
    }

    @FXML
    private void ReaclamationAction(ActionEvent event) {
        
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
    
    
    public void setLab_dat(String dat) {
        this.lab_dat.setText(dat);
    }

    public void setLab_et(String etat) {
        this.lab_et.setText(etat);
    }

    public void setLab_tot(Float tot) {
        this.lab_tot.setText(tot.toString());
    }

    public void setLab_ref(String lab_ref) {
        this.lab_ref.setText(lab_ref);
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
            col_categ.setCellValueFactory(new PropertyValueFactory<>("nom"));
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
            col_categ.setCellValueFactory(new PropertyValueFactory<>("nom"));
            col_qte.setCellValueFactory(new PropertyValueFactory<>("qte"));
            col_tot.setCellValueFactory(new PropertyValueFactory<>("subtotal"));
            col_idlc.setCellValueFactory(new PropertyValueFactory<>("idLc"));
            col_idcmd.setCellValueFactory(new PropertyValueFactory<>("idcmd"));

            tabpan.setItems(tt);

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

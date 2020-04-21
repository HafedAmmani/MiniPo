/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.Gui;

import com.esprit.Entite.Commande;
import com.esprit.Entite.Commandes;
import com.esprit.Service.ServiceCommande;
import com.esprit.gui.LoginUserController;
import com.esprit.gui.ReclamationClientCmdController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class ListeCmdClientController implements Initializable {

    int idUser = LoginUserController.NumId;
    
    private AnchorPane ligne;
    

    private ObservableList<Commandes> oblist=FXCollections.observableArrayList();

    @FXML
    private Button Acceuil;
    @FXML
    private Button panier;
    @FXML
    private TableView tabcom;
     @FXML
    private TableColumn<Object, ?> col_ref;
    @FXML
    private TableColumn<Object, ?> col_non;
    @FXML
    private TableColumn<Object, ?> col_pre;
    @FXML
    private TableColumn<Object, ?> col_dat;
    @FXML
    private TableColumn<Object, ?> col_et;
    @FXML
    private TableColumn<Object, ?> col_tot;
    @FXML
    private TableColumn<Object, ?> col_id;
    @FXML
    private TextField tfrech;
    
    @FXML
    private Button btnDet;
    @FXML
    private Button btnSupp;
    @FXML
    private Button btnRec;
    @FXML
    private Button btnFact;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
      AfficherCommandes();
      RechercherCommandes();
        
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
private void AfficherCommandes(){
        
        try{

        ServiceCommande sc=new ServiceCommande();
        oblist = sc.Commandes(idUser);
        col_ref.setCellValueFactory(new PropertyValueFactory("refC"));
        col_id.setCellValueFactory(new PropertyValueFactory ("idCmd"));
        col_non.setCellValueFactory(new PropertyValueFactory("nomClt"));
        col_pre.setCellValueFactory(new PropertyValueFactory("prenomClt"));
        col_dat.setCellValueFactory(new PropertyValueFactory("datec"));
        col_et.setCellValueFactory(new PropertyValueFactory("etatc"));
        col_tot.setCellValueFactory(new PropertyValueFactory("total"));
       
        tabcom.setItems(oblist);

        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    
    } 

    
     private void RechercherCommandes(){
         FilteredList<Commandes>filteredData=new FilteredList<>(oblist,b -> true);
            tfrech.setOnKeyReleased(e->{
        tfrech.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate((Predicate<? super Commandes>)Commande -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (Commande.getEtatc().toLowerCase().contains(lowerCaseFilter) ) {
					return true; // Filter matches first name.
				} else if (Commande.getDatec().toString().toLowerCase().contains(lowerCaseFilter)) {
					return true; }// Filter matches last name.
				
				      
				    	 return false; // Does not match.
			});
		});
        SortedList<Commandes>soretedData=new SortedList<>(filteredData);
        soretedData.comparatorProperty().bind(tabcom.comparatorProperty());
        tabcom.setItems(soretedData);
            });
     }

    @FXML
    private void AcceuilAction(ActionEvent event) {
        
        try{
        FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("Acceuil.fxml"));
        Parent root = loader.load();
        tfrech.getScene().setRoot(root);
        }catch(Exception e){
        
        System.out.println(e.getMessage());
        }
    }

    @FXML
    private void PanierAction(ActionEvent event) {
        
        try{
        FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("Interface1.fxml"));
        Parent root = loader.load();
        tfrech.getScene().setRoot(root);
        }catch(Exception e){
        
        System.out.println(e.getMessage());
        }
    }

    @FXML
    private void SupprimerAction(ActionEvent event) {
        
        oblist=tabcom.getSelectionModel().getSelectedItems();
        ServiceCommande sc=new ServiceCommande();
        Commande c=new Commande(Integer.parseInt(oblist.get(0).getIdCmd()));
        sc.deleteCmdClt(c);
        AfficherCommandes();  
    }

    @FXML
    private void DetailAction(ActionEvent event) {
        
        oblist=tabcom.getSelectionModel().getSelectedItems();
        ServiceCommande sc=new ServiceCommande();
        Commande c=sc.getCommande(Integer.parseInt(oblist.get(0).getIdCmd()));
        
        try{
            
        FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("InfoCmd.fxml"));
        
        Parent root = loader.load();
        InfoCmdController tc = loader.getController();
        
        tc.setLab_dat(oblist.get(0).getDatec());
        tc.setLab_et(oblist.get(0).getEtatc());
        tc.setLab_ref(oblist.get(0).getRefC());
        tc.setLab_tot(oblist.get(0).getTotal()); 
        tc.setTab(sc.detailCmdClt(c));

        btnDet.getScene().setRoot(root);
        
        }catch(Exception e){
        
        System.out.println(e.getMessage());
        }
    }   

    @FXML
    private void ReclamerAction(ActionEvent event) throws IOException {
         oblist=tabcom.getSelectionModel().getSelectedItems();
        
           
      
            
            FXMLLoader loader = new FXMLLoader
                                (getClass()
                                .getResource("/com/esprit/gui/ReclamationClientCmd.fxml"));
            
            Parent root = loader.load();
            ReclamationClientCmdController tc = loader.getController();
            
            tc.setIdcmd(Integer.parseInt(oblist.get(0).getIdCmd()));
            tc.setRefcmd(oblist.get(0).getRefC());
                    
            btnRec.getScene().setRoot(root); 
  
    }

    @FXML
    private void FacturesAction(ActionEvent event) {
        
        try{
        FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("FacturesClt.fxml"));
        Parent root = loader.load();
        btnDet.getScene().setRoot(root);
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

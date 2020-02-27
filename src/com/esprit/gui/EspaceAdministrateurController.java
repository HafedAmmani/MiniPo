/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.Gui;

import com.esprit.Entite.Commande;
import com.esprit.Entite.Commandes;
import com.esprit.Service.ServiceCommande;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
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
public class EspaceAdministrateurController implements Initializable {

    @FXML
    private TableView tabcom;
    @FXML
    private TableColumn <Object,?>  col_id;
    @FXML
    private TableColumn<Object,?>  col_nom;
    @FXML
    private TableColumn <Object,?> col_pre;
    @FXML
    private TableColumn <Object,?>  col_dat;
    @FXML
    private TableColumn <Object,?>  col_et;
    private TextField tfId;
    @FXML
    private ComboBox<String> cbxEtat;
    @FXML
    private Button btnMod;
    @FXML
    private Button btnSupp;
    
    private ObservableList<Commandes> oblist=FXCollections.observableArrayList();
    private ObservableList<String> lcbx=FXCollections.observableArrayList("Accepter");
    @FXML
    private TextField tfRech;
    @FXML
    private TableColumn<?, ?> col_tot;
    @FXML
    private void redirectToAcceuilReclamation(ActionEvent event) throws IOException {
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("AccueilReclamationAd.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    }
    @FXML
    private void redirectToCommande(ActionEvent event) throws IOException {
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("EspaceAdministrateur.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    }
    @FXML
    private void redirectTofacture(ActionEvent event) throws IOException {
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("ListeFacture.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    }
    @FXML
    private void redirectToProduit(ActionEvent event) throws IOException {
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("EspaceProduit.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    }
    @FXML
    private void redirectToUtilisateur(ActionEvent event) throws IOException {
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("testingUser.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       AfficherCommandes();
       RechercherCommandes();
       cbxEtat.setItems(lcbx);
    }    

    @FXML
    private void ListCmdOnClickAction(MouseEvent event) {
        
        oblist=tabcom.getSelectionModel().getSelectedItems();
        //tfId.setText(String.valueOf(oblist.get(0).getIdCmd()));
        //tfId.anchorProperty();
        cbxEtat.setValue(oblist.get(0).getEtatc());
        
    }

    @FXML
    private void btnModifierAction(ActionEvent event) {

        oblist=tabcom.getSelectionModel().getSelectedItems();
        ServiceCommande slc=new ServiceCommande();
        Commande c=new Commande(Integer.parseInt(oblist.get(0).getIdCmd()),cbxEtat.getSelectionModel().getSelectedItem());
        slc.AccepterCommande(c);
        AfficherCommandes();
    }

    @FXML
    private void btnSupprimerAction(ActionEvent event) {
        
        ServiceCommande slc=new ServiceCommande();
        Commande c=new Commande(Integer.parseInt(tfId.getText()),cbxEtat.getSelectionModel().getSelectedItem());
        slc.supprimerCommande(c);
        AfficherCommandes();
    }

    private void AcceuilAction(ActionEvent event) {
        
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("EspaceAdministrateur.fxml"));
            
            Parent root = loader.load();
            tfId.getScene().setRoot(root);
        } catch (IOException ex) {
           System.out.println(ex.getMessage());
        }
        
    }

    private void BtnListClickAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("EspaceAdministrateur.fxml"));
            
            Parent root = loader.load();
            tfId.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
   /* private void AfficherCommandes(){
        
        try{
        
        
        
        ServiceCommande sc=new ServiceCommande();
        oblist = sc.CommandesValider();
        
        col_id.setCellValueFactory(new PropertyValueFactory ("idCmd"));
        col_nom.setCellValueFactory(new PropertyValueFactory("nomClt"));
        col_pre.setCellValueFactory(new PropertyValueFactory("prenomClt"));
        col_dat.setCellValueFactory(new PropertyValueFactory("datec"));
        col_et.setCellValueFactory(new PropertyValueFactory("etatc"));
       
        tabcom.setItems(oblist);

        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    
    }*/
    
   private void AfficherCommandes(){
        
        try{
        
        
        
        ServiceCommande sc=new ServiceCommande();
        oblist = sc.Commandes();
        
        col_id.setCellValueFactory(new PropertyValueFactory ("idCmd"));
        col_nom.setCellValueFactory(new PropertyValueFactory("nomClt"));
        col_pre.setCellValueFactory(new PropertyValueFactory("prenomClt"));
        col_dat.setCellValueFactory(new PropertyValueFactory("datec"));
        col_et.setCellValueFactory(new PropertyValueFactory("etatc"));
        col_tot.setCellValueFactory(new PropertyValueFactory("total"));
       
        tabcom.setItems(oblist);

        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    
    } 

    @FXML
    private void RechercheAction(KeyEvent event) {
    }
    
     private void RechercherCommandes(){
         FilteredList<Commandes>filteredData=new FilteredList<>(oblist,b -> true);
            tfRech.setOnKeyReleased(e->{
        tfRech.textProperty().addListener((observable, oldValue, newValue) -> {
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
				//else if (Reclamation.getIdR().contains(newValue)){
				   // return true;}
				      
				    	 return false; // Does not match.
			});
		});
        SortedList<Commandes>soretedData=new SortedList<>(filteredData);
        soretedData.comparatorProperty().bind(tabcom.comparatorProperty());
        tabcom.setItems(soretedData);
            });
     }

    
    
}

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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class EspaceAdministrateurController implements Initializable {

 
    
    private ObservableList<Commandes> oblist=FXCollections.observableArrayList();
    @FXML
    private TextField tfRech;
    @FXML
    private TableView<Commandes> tabcom;
    @FXML
    private TableColumn<Object, ?> col_id;
    @FXML
    private TableColumn<Object, ?> col_ref;
    @FXML
    private TableColumn<Object, ?> col_nom;
    @FXML
    private TableColumn<Object, ?> col_pre;
    @FXML
    private TableColumn<Object, ?> col_dat;
    @FXML
    private TableColumn<Object, ?> col_et;
    @FXML
    private TableColumn<Object, ?> col_tot;
    @FXML
    private Button btnDet;
    @FXML
    private Button btnRef;
    @FXML
    private Button btnAcpt;
    private Text btnGestVA;
 
    

    /**
     * Initializes the controller class.
     */
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
        oblist = sc.AllCommandes();
        col_ref.setCellValueFactory(new PropertyValueFactory("refC"));
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
     
    private void BtnListClickAction(MouseEvent event) {
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("EspaceAdministrateur.fxml"));
            
            Parent root = loader.load();
            tfRech.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(Interface1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void AcceuilAction(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("EspaceAdministrateur.fxml"));
            
            Parent root = loader.load();
            tfRech.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(Interface1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void DetailAction(ActionEvent event) {
        
        oblist=tabcom.getSelectionModel().getSelectedItems();
        ServiceCommande sc=new ServiceCommande();
        
        try{
            
        FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("DetailCmdAdmin.fxml"));
        
        Parent root = loader.load();
        DetailCmdAdminController tc = loader.getController();
        
        tc.setLab_dat(oblist.get(0).getDatec());
        tc.setLab_et(oblist.get(0).getEtatc());
        tc.setLab_ref(oblist.get(0).getRefC());
        tc.setLab_tot(oblist.get(0).getTotal()); 
        tc.setTab(sc.detailCmdClt(new Commande(Integer.parseInt(oblist.get(0).getIdCmd()))));
        
        
        
        
        btnDet.getScene().setRoot(root);
        
        }catch(Exception e){
        
        System.out.println(e.getMessage());
        }
    }

    @FXML
    private void RefuserAction(ActionEvent event) {
        
        oblist=tabcom.getSelectionModel().getSelectedItems();
        ServiceCommande sc=new ServiceCommande();
        Commande c =sc.getCommande(Integer.parseInt(oblist.get(0).getIdCmd()));
        sc.refuserCommande(c);
        AfficherCommandes();  
    }

    @FXML
    private void AccepterAction(ActionEvent event) {
        
        oblist=tabcom.getSelectionModel().getSelectedItems();
        ServiceCommande sc=new ServiceCommande();
        Commande c =sc.getCommande(Integer.parseInt(oblist.get(0).getIdCmd()));
        sc.AccepterCommande(c);
        AfficherCommandes();  
    }

//*******************Espace Admin****************

  @FXML
    private void GestionUserAction(ActionEvent event) throws IOException {
        
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("testUser.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    }

    @FXML
    private void GestionProdAction(ActionEvent event) throws IOException {
         Parent tableViewParent = FXMLLoader.load(getClass().getResource("EspaceProduit.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    }

    @FXML
    private void GestionVAAction(ActionEvent event) throws IOException {
         Parent tableViewParent = FXMLLoader.load(getClass().getResource("GestionVA.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    }

    @FXML
    private void GestionReclamAction(ActionEvent event) throws IOException {

   Parent tableViewParent = FXMLLoader.load(getClass().getResource("AccueilReclamationAd.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    }

    @FXML
    private void GestionLivAction(ActionEvent event) throws IOException {
        
         Parent tableViewParent = FXMLLoader.load(getClass().getResource("livraison.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    }

    @FXML
    private void chartsAction(ActionEvent event) {
        
    }

    @FXML
    private void calendarAction(ActionEvent event) {
    }

    @FXML
    private void PageAction(ActionEvent event) {
    }

    
}

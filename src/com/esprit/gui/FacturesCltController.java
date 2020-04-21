/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.Gui;

import com.esprit.Entite.Facture;
import com.esprit.Entite.ListeFact;
import com.esprit.Service.ServiceFacture;
import com.esprit.gui.LoginUserController;
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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class FacturesCltController implements Initializable {
    
    int idUser = LoginUserController.NumId;
    
    private ObservableList<ListeFact> oblist=FXCollections.observableArrayList();

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
    private TextField tfRech;
    @FXML
    private TableView<ListeFact> tabFact;
    @FXML
    private TableColumn<Object, ?> col_idfact;
    @FXML
    private TableColumn<Object, ?> col_idcmd;
    @FXML
    private TableColumn<Object, ?> col_idClt;
    @FXML
    private TableColumn<Object, ?> col_reff;
    @FXML
    private TableColumn<Object, ?> col_dat;
    @FXML
    private TableColumn<Object, ?> col_et;
    @FXML
    private TableColumn<Object, ?> col_ref;
    @FXML
    private TableColumn<Object, ?> col_tot;
    @FXML
    private Button btnDet;
    @FXML
    private Button btnMesReclamation;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        AfficherFactures();
        RechercherFactures();
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
    
    private void AfficherFactures(){
        
        try{
        
        
        
        ServiceFacture sc=new ServiceFacture();
        
        
        oblist = sc.FacturesClt(idUser);
        
        col_idfact.setCellValueFactory(new PropertyValueFactory ("idFact"));
        col_reff.setCellValueFactory(new PropertyValueFactory ("idFact"));
        col_dat.setCellValueFactory(new PropertyValueFactory("dateFact"));
        col_et.setCellValueFactory(new PropertyValueFactory("etatFact"));
        col_idClt.setCellValueFactory(new PropertyValueFactory ("idclt"));
        col_idcmd.setCellValueFactory(new PropertyValueFactory ("idcmd"));
        col_ref.setCellValueFactory(new PropertyValueFactory ("refc"));
        col_tot.setCellValueFactory(new PropertyValueFactory ("total"));
        
       
        tabFact.setItems(oblist);
        
        System.out.println(oblist);

        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    
    } 
    
    
     private void RechercherFactures(){
         FilteredList<ListeFact>filteredData=new FilteredList<>(oblist,b -> true);
            tfRech.setOnKeyReleased(e->{
        tfRech.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate((Predicate<? super ListeFact>)Facture -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (Facture.getRefc().toLowerCase().contains(lowerCaseFilter) ) {
					return true; // Filter matches first name.
				} else if (Facture.getEtatFact().toLowerCase().startsWith(lowerCaseFilter)) {
					return true; }// Filter matches last name.
				//else if (Reclamation.getIdR().contains(newValue)){
				   // return true;}
				      
				    	 return false; // Does not match.
			});
		});
        SortedList<ListeFact>soretedData=new SortedList<>(filteredData);
        soretedData.comparatorProperty().bind(tabFact.comparatorProperty());
        tabFact.setItems(soretedData);
            });
     }


    @FXML
    private void AcceuilAction(ActionEvent event) {
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Acceuil.fxml"));
            
            Parent root = loader.load();
            btnDet.getScene().setRoot(root);
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
            btnDet.getScene().setRoot(root);
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
        btnDet.getScene().setRoot(root);
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
        btnDet.getScene().setRoot(root);
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
        btnDet.getScene().setRoot(root);
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

    @FXML
    private void RechercheAction(KeyEvent event) {
    }

    @FXML
    private void DetailAction(ActionEvent event) {
        
        oblist=tabFact.getSelectionModel().getSelectedItems();
        ServiceFacture sf=new ServiceFacture();
        Facture f=sf.getFacture(oblist.get(0).getIdFact());
        
        try{
            
        FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("DetailFactClt.fxml"));
        
        Parent root = loader.load();
        DetailFactCltController tc = loader.getController();
        
        tc.setLab_dat(oblist.get(0).getDateFact().toString());
        tc.setLab_et(oblist.get(0).getEtatFact());
        tc.setLab_ref(String.valueOf(oblist.get(0).getIdFact()));
        tc.setLab_tot(oblist.get(0).getTotal());
        tc.setTab(sf.detailFact(f));

        btnDet.getScene().setRoot(root);
        
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

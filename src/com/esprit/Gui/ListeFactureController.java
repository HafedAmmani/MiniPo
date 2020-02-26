/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.Gui;

import com.esprit.Entite.Commandes;
import com.esprit.Entite.ListeFact;
import com.esprit.Service.ServiceFacture;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class ListeFactureController implements Initializable {

    @FXML
    private TextField tfRech;
    @FXML
    private TableView tabcom;
    @FXML
    private TableColumn<Object, ?> col_idfact;
    @FXML
    private TableColumn <Object,?> col_nom;
    @FXML
    private TableColumn <Object,?> col_pre;
    @FXML
    private TableColumn <Object,?> col_dat;
    @FXML
    private TableColumn <Object,?> col_et;
    @FXML
    private TableColumn <Object,?> col_idClt;
    @FXML
    private ComboBox<String> cbxEtat;
    @FXML
    private Button btnMod;
    @FXML
    private Button btnSupp;
    @FXML
    private Button Acceuil;
    @FXML
    private Button btnListCmd;
    
    private ObservableList<ListeFact> oblist=FXCollections.observableArrayList();
    private ObservableList<String> lcbx=FXCollections.observableArrayList("Payée");
    @FXML
    private TableColumn<Object, ?> col_idcmd;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        AfficherFactures();
        RechercherFactures();
         
    }    

    @FXML
    private void RechercheAction(KeyEvent event) {
       
    }

    @FXML
    private void ListCmdOnClickAction(MouseEvent event) {
    }

    @FXML
    private void btnModifierAction(ActionEvent event) {
    }

    @FXML
    private void btnSupprimerAction(ActionEvent event) {
    }

    @FXML
    private void AcceuilAction(ActionEvent event) {
    }

    @FXML
    private void BtnListClickAction(ActionEvent event) {
    }
    
    private void AfficherFactures(){
        
        try{
        
        
        
        ServiceFacture sc=new ServiceFacture();
        oblist = sc.Factures();
        
        col_idfact.setCellValueFactory(new PropertyValueFactory ("idFact"));
        col_nom.setCellValueFactory(new PropertyValueFactory("nom"));
        col_pre.setCellValueFactory(new PropertyValueFactory("prenom"));
        col_dat.setCellValueFactory(new PropertyValueFactory("dateFact"));
        col_et.setCellValueFactory(new PropertyValueFactory("etatFact"));
        col_idClt.setCellValueFactory(new PropertyValueFactory ("idclt"));
        col_idcmd.setCellValueFactory(new PropertyValueFactory ("idcmd"));
        
       
        tabcom.setItems(oblist);
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
				
				if (Facture.getEtatFact().toLowerCase().contains(lowerCaseFilter) ) {
					return true; // Filter matches first name.
				} else if (Facture.getNom().toLowerCase().contains(lowerCaseFilter)) {
					return true; }// Filter matches last name.
				//else if (Reclamation.getIdR().contains(newValue)){
				   // return true;}
				      
				    	 return false; // Does not match.
			});
		});
        SortedList<ListeFact>soretedData=new SortedList<>(filteredData);
        soretedData.comparatorProperty().bind(tabcom.comparatorProperty());
        tabcom.setItems(soretedData);
            });
     }
    
    
}

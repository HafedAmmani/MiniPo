/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui;

import com.esprit.Service.ServiceLivraison;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import com.esprit.Entite.Livraison;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author sinda
 */
public class Livraison_livController implements Initializable {

     @FXML
    private TableView<Livraison> tblview;
    @FXML
    private TableColumn<Livraison, String> col_idliv;
    @FXML
    private TableColumn<Livraison, String> col_dest;
    @FXML
    private TableColumn<Livraison, String> col_etatl;
    @FXML
    private TableColumn<Livraison, String> col_idc;
    @FXML
    private TableColumn<Livraison, String> col_salaire;
    ObservableList<Livraison> obList = FXCollections.observableArrayList();
    private ServiceLivraison serv = new ServiceLivraison();
    
     @FXML
    private TextField fil_recherche;
    @FXML
    private ComboBox<String> id_etat;//= new ComboBox<>("livrée", "non livrée","en cours");
    
    private int id;
    private String etat;
    @FXML
    private AnchorPane recherche;
    @FXML
    private Button modifier;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initTable();
        //recherche();
         combo(); 
          
    }  
     private void initTable() {
            obList = serv.getAllLivraison();
            col_idliv.setCellValueFactory(new PropertyValueFactory<>("matriculeL"));
            col_dest.setCellValueFactory(new PropertyValueFactory<>("destination"));
            col_etatl.setCellValueFactory(new PropertyValueFactory<>("etatl"));
            col_idc.setCellValueFactory(new PropertyValueFactory<>("idc"));
            col_salaire.setCellValueFactory(new PropertyValueFactory<>("idl"));
            tblview.setItems(obList);
            
	}
     public void combo()
    {
        ObservableList<String> listcmbx = FXCollections.observableArrayList("livrée", "non livrée","en cours");
//        id_cmd.setCellFactory(new PropertyValueFactory<>("idcmd"));
        
        id_etat.setItems(listcmbx);
    }
     /*@FXML
      private void recherche(){
      
        // 1. Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Livraison> filteredData = new FilteredList<>(oblist, p -> true);
        
        // 2. Set the filter Predicate whenever the filter changes.
        fil_recherche.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(livraison -> {
                
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                
                return false; // Does not match.
            });
        });
        
        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Livraison> sortedData = new SortedList<>(filteredData);
        
        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(tblview.comparatorProperty());
        
        // 5. Add sorted (and filtered) data to the table.
        tblview.setItems(sortedData);
    }
*/

   
    /*@FXML
     private void Rechercher(){

         FilteredList<Livraison>filteredData=new FilteredList<>(obList ,b -> true);
            fil_recherche.setOnKeyReleased(e->{
        fil_recherche.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(obList -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (obList.getEtatl().toLowerCase().contains(lowerCaseFilter) ) {
					return true; // Filter matches first name.
				} else if (obList.getDestination().toLowerCase().contains(lowerCaseFilter)) {
					return true; }// Filter matches last name.

				
				          	 return false; // Does not match.
			});
		});

        SortedList<Livraison>soretedData=new SortedList<>(filteredData);
        soretedData.comparatorProperty().bind(tblview.comparatorProperty());
        tblview.setItems(soretedData);

            });
                                                            

     }*/
        /* private void Rechercher(){
         FilteredList<Livraison>filteredData=new FilteredList<>(obList,b -> true);
            fil_recherche.setOnKeyReleased(e->{
        fil_recherche.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate((Predicate<? super Livraison>)userrec -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (obList .toLowerCase().contains(lowerCaseFilter) ) {
					return true; // Filter matches first name.
				} else if (Livraison.getPrenom().toLowerCase().contains(lowerCaseFilter)) {
					return true; }// Filter matches last name.
                                 else if (Livraison.getNom().toLowerCase().contains(lowerCaseFilter)) {
					return true; }
				//else if (Reclamation.getIdR().contains(newValue)){
				    // return true;}
				      
				    	 return false; // Does not match.
			});
		});
        SortedList<Livraison>soretedData=new SortedList<>(filteredData);
        soretedData.comparatorProperty().bind(tblview.comparatorProperty());
        tblview.setItems(soretedData);
            });
     }*/

    @FXML
    private void Rechercher(KeyEvent event) {
         FilteredList<Livraison>filteredData=new FilteredList<>(obList ,b -> true);
            fil_recherche.setOnKeyReleased(e->{
        fil_recherche.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(obList -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (obList.getEtatl().toLowerCase().contains(lowerCaseFilter) ) {
					return true; // Filter matches first name.
				} else if (obList.getDestination().toLowerCase().contains(lowerCaseFilter)) {
					return true; }// Filter matches last name.

				
				          	 return false; // Does not match.
			});
		});

        SortedList<Livraison>soretedData=new SortedList<>(filteredData);
        soretedData.comparatorProperty().bind(tblview.comparatorProperty());
        tblview.setItems(soretedData);

            }); 
    }

    @FXML
    private void updateEtat(ActionEvent event) throws SQLException {
        Livraison l;
        Livraison selected = tblview.getSelectionModel().getSelectedItem();
         id = selected.getIdliv();
        etat = id_etat.getSelectionModel().getSelectedItem();
        l = new Livraison(id,selected.getDestination(),etat,selected.getIdc(),selected.getIdl(),selected.getDateliv(),selected.getMatriculeL());
        serv.updateLiv(etat,id);
        initTable();
    }
    
}

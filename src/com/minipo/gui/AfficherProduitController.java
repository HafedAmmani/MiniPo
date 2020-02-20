package com.minipo.gui;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.minipo.Entite.Produit;
import com.minipo.Service.ServiceProduit;
import com.mysql.cj.x.protobuf.MysqlxNotice.Warning.Level;
import com.sun.istack.internal.logging.Logger;
import com.sun.xml.internal.ws.org.objectweb.asm.Label;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import sun.print.resources.serviceui;

public class AfficherProduitController implements Initializable {

	@FXML
    private AnchorPane anchhorpane_parent;

    @FXML
    private Pane pane_top;

    @FXML
    private Label label_title;

    @FXML
    private AnchorPane anchorpane_center;

    @FXML
    private AnchorPane anchorpane_left;

    @FXML
    private JFXTextField txt_designation;

    @FXML
    private JFXTextField txt_qtestock;

    @FXML
    private JFXTextField txt_prix;

    @FXML
    private JFXButton btn_save;

    @FXML
    private JFXTextField txt_categorie;

    @FXML
    private JFXTextField txt_fournisseur;

    @FXML
    private JFXButton btn_edit;

    @FXML
    private JFXButton btn_add_new;

    @FXML
    private JFXButton btn_delete;

    @FXML
    private AnchorPane anchorpane_right;

    @FXML
    private TableView<Produit> tblview;

    @FXML
    private TableColumn<Produit, String> column_designation;

    @FXML
    private TableColumn<Produit, Integer> column_qtestock;

    @FXML
    private TableColumn<Produit, Integer> column_prix;

    @FXML
    private TableColumn<Produit, Integer> column_categorie;

    @FXML
    private TableColumn<Produit, Integer> column_fournisseur;

    @FXML
    private JFXButton btn_print_preview;
    
    @FXML
    private JFXTextField idfld;

    @FXML
    private JFXTextField fil_recherche;
    
    private ServiceProduit ser = new ServiceProduit();
    private int ID;
    ObservableList<Produit> oblist = FXCollections.observableArrayList();
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		initTable();
		
	
	}
	
	 private void SaveProduit() throws SQLException{ //modifier Produit
         Alert alert = new Alert(AlertType.INFORMATION);
	alert.setTitle("Produit modifier avec sucess !.");
	alert.setHeaderText(null);
     int id = Integer.parseInt(idfld.getText());
     Produit p1;
     p1 = new Produit(id, txt_designation.getText(), txt_qtestock.getText(), txt_prix.getText(), txt_categorie.getText(), txt_fournisseur.getText());
     ser.update(p1);
     alert.setContentText("The Product "+txt_designation.getText()+" has been updated.");
     alert.showAndWait();
     clear();
     initTable();
 
}
	 
	 private void deleteProduit() throws SQLException{
		 Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Confirmation Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Are you sure you want to delete selected product?");
			Optional<ButtonType> action = alert.showAndWait();
	        Produit selected = tblview.getSelectionModel().getSelectedItem();
	        ID = selected.getIdprod();
	            Produit p1;
	            p1 = new Produit(ID, txt_designation.getText(), txt_qtestock.getText(), txt_prix.getText(), txt_categorie.getText(), txt_fournisseur.getText());	            
	            		if(action.get() == ButtonType.OK)
	            ser.delete(p1);
	            initTable();
	 }
	 
	 private void EditProduit(){
	        // for updating existing account
			Produit selected = tblview.getSelectionModel().getSelectedItem();
	                idfld.setText(String.valueOf(selected.getIdprod()));
			txt_designation.setText(selected.getDesignation());
			txt_qtestock.setText(selected.getQtestock());
			txt_prix.setText(selected.getPrix());
			txt_categorie.setText(selected.getCategorie());
			txt_fournisseur.setText(selected.getFournisseur());
	    }
	 
	    private void insertNewProduit() throws SQLException{ // for adding new Produit
            Alert alert = new Alert(AlertType.INFORMATION);
	alert.setTitle("Produit enregistrer avec succes.");
	alert.setHeaderText(null);
    if(validate("Designation", txt_designation.getText(), "[a-zA-Z]+") && 
         validate("Qte Stock", txt_qtestock.getText(), "[0-9]+") &&
           validate("Prix", txt_prix.getText(), "[0-9]+") &&
            validate("Categorie", txt_categorie.getText(), "[0-9]+") && 
             validate("Fournisseur", txt_fournisseur.getText(), "[a-zA-Z]+") )
	   {

    Produit p1;
    p1 = new Produit(txt_designation.getText(), txt_qtestock.getText(), txt_prix.getText(), txt_categorie.getText(), txt_fournisseur.getText());
    ser.ajouter(p1);
        alert.setContentText("Produit "+txt_designation.getText()+" est ajouté.");
        alert.showAndWait();
    clear();
    initTable();
                    
    }
}

	private void initTable() {
		oblist = ser.getAllProduit();
		column_designation.setCellValueFactory(new PropertyValueFactory<>("designation"));
		column_qtestock.setCellValueFactory(new PropertyValueFactory<>("qtestock"));
		column_prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
		column_categorie.setCellValueFactory(new PropertyValueFactory<>("categorie"));
		column_fournisseur.setCellValueFactory(new PropertyValueFactory<>("fournisseur"));
		tblview.setItems(oblist);
		
	}
	private void clear(){
        txt_designation.clear();
        txt_qtestock.clear();
        txt_prix.clear();
        txt_categorie.clear();
        txt_fournisseur.clear();
	}
	
        private void recherche(){
            
            // 1. Wrap the ObservableList in a FilteredList (initially display all data).
            FilteredList<Produit> filteredData = new FilteredList<>(oblist, p -> true);
            
            // 2. Set the filter Predicate whenever the filter changes.
            fil_recherche.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(produit -> {
                    // If filter text is empty, display all persons.
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    
                    // Compare first name and last name of every person with filter text.
                    String lowerCaseFilter = newValue.toLowerCase();
                    
                    if (produit.getDesignation().toLowerCase().contains(lowerCaseFilter)) {
                        return true; // Filter drlon frdignstion
                    }
                    return false; // Does not match.
                });
            });
            
            // 3. Wrap the FilteredList in a SortedList. 
            SortedList<Produit> sortedData = new SortedList<>(filteredData);
            // 4. Bind the SortedList comparator to the TableView comparator.
            sortedData.comparatorProperty().bind(tblview.comparatorProperty());
            
            // 5. Add sorted (and filtered) data to the table.
            tblview.setItems(sortedData);
        }
	
        /*
    	 * Validations
    	 */
    	private boolean validate(String field, String value, String pattern){
    		if(!value.isEmpty()){
    		Pattern p = Pattern.compile(pattern);
    	        Matcher m = p.matcher(value);
    	        if(m.find() && m.group().equals(value)){
    	            return true;
    	        }else{
    	        	validationAlert(field, false);            
    	            return false;            
    	        }
    		}else{
    			validationAlert(field, true);            
                return false;
    		}        
        }
    	private boolean emptyValidation(String field, boolean empty){
            if(!empty){
                return true;
            }else{
            	validationAlert(field, true);            
                return false;            
            }
        }	
    	
    	private void validationAlert(String field, boolean empty){
    	Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Validation Error");
            alert.setHeaderText(null);
            if(field.equals("Role")) alert.setContentText("Please Select "+ field);
            else{
            	if(empty) alert.setContentText("Please Enter "+ field);
            	else alert.setContentText("Please Enter Valid "+ field);
            }
            alert.showAndWait();
    	}  
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

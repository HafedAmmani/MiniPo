 package com.esprit.gui;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.esprit.Entite.Produit;
import com.esprit.Service.ServiceCategorie;
import com.esprit.Service.ServiceFournisseur;
import com.esprit.Service.ServiceProduit;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.sun.javafx.scene.control.SelectedCellsMap;
import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

public class EspaceProduitController implements Initializable {

	

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
    private JFXTextField txt_stock;

    @FXML
    private JFXTextField txt_prix;

    @FXML
    private JFXComboBox<String> combo_categorie;

    @FXML
    private JFXComboBox<String> combo_fournisseur;

    @FXML
    private JFXButton btn_categorie;
    
    @FXML
    private TextField txt_idproooo;

    @FXML
    private JFXButton btn_fournisseur;

    @FXML
    private JFXButton btn_ajouter;

    @FXML
    private AnchorPane anchorpane_right;

    @FXML
    private TableView<Produit> tblview;

    @FXML
    private TableColumn<?, ?> column_designation;

    @FXML
    private TableColumn<?, ?> column_qtestock;

    @FXML
    private TableColumn<?, ?> column_prix;

    @FXML
    private TableColumn<?, ?> column_categorie;

    @FXML
    private TableColumn<?, ?> column_fournisseur;
    
    @FXML
    private TableColumn<?, ?> column_idc;

    @FXML
    private TableColumn<?, ?> column_idf;
    
    @FXML
    private TableColumn<?, ?> column_idp;

	
	
	private FXMLLoader loader;
	
	
	private ServiceProduit ser = new ServiceProduit();
	private ServiceCategorie sc= new ServiceCategorie();
	private ServiceFournisseur sf = new ServiceFournisseur();
	
	ObservableList<Produit> oblist = FXCollections.observableArrayList();
    @FXML
    private JFXButton btn_delete;
    @FXML
    private JFXButton btn_modifier;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		try {
			combo_categorie.setItems(sc.getCatNames());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			combo_fournisseur.setItems(sf.getFourNames());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		initTable();
	}
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
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("testUser.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    }
	
	@FXML
	public void ajouterProduit() throws SQLException{
		Produit p1;
		p1= new Produit(txt_designation.getText(), Integer.parseInt(txt_stock.getText()), Integer.parseInt(txt_prix.getText()), sc.getIdByName(combo_categorie.getSelectionModel().getSelectedItem()), combo_categorie.getSelectionModel().getSelectedItem(), sf.getIdByName(combo_fournisseur.getSelectionModel().getSelectedItem()), combo_fournisseur.getSelectionModel().getSelectedItem());
		ser.ajouter(p1);
		initTable();
		
	}
	
	@FXML
	public void update() throws SQLException{
		Produit p1= new Produit(Integer.parseInt(txt_idproooo.getText()), txt_designation.getText(), Integer.parseInt(txt_stock.getText()), Integer.parseInt(txt_prix.getText()), sc.getIdByName(combo_categorie.getSelectionModel().getSelectedItem()), combo_categorie.getSelectionModel().getSelectedItem(), sf.getIdByName(combo_fournisseur.getSelectionModel().getSelectedItem()), combo_fournisseur.getSelectionModel().getSelectedItem());
		ser.update(p1);
		initTable();
	}
	
	@FXML
	private void modifierCellule (MouseEvent event) {
		if (event.getClickCount()==2)
			
		editProduit();
		
	}
	
	private void editProduit() {
		Produit select = tblview.getSelectionModel().getSelectedItem();
		
		txt_designation.setText(select.getDesignation());
		txt_stock.setText(String.valueOf(select.getQtestock()));
		txt_prix.setText(String.valueOf(select.getPrix()));
		combo_categorie.setValue(select.getNomcateg());
		combo_fournisseur.setValue(select.getNomfour());
		System.out.println(select.getIdprod());
		txt_idproooo.setText(String.valueOf(select.getIdprod()));
		
	}
	
	
	@FXML
	private void deleteProduit() throws SQLException {
		
		Produit selected = tblview.getSelectionModel().getSelectedItem();
		
		Produit p1;
		p1 = new Produit(selected.getIdprod());
		ser.delete(p1);
		initTable();
	}
	
    @FXML
	private void showListCategorie() {
		try {
			loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("EspaceCategorie.fxml"));
//			AjoutEquipeController controller = new AjoutEquipeController();
//			loader.setController(controller);
			loader.load();
			Scene scene = new Scene(loader.getRoot());
			scene.getStylesheets().add(getClass().getResource("categorie.css").toExternalForm());
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setTitle("Espace Produit");
			stage.showAndWait();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
    @FXML
    private void showListFournisseur() {
    	try {
			loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("EspaceFournisseur.fxml"));
//			AjoutEquipeController controller = new AjoutEquipeController();
//			loader.setController(controller);
			loader.load();
			Scene scene = new Scene(loader.getRoot());
			scene.getStylesheets().add(getClass().getResource("fournisseur.css").toExternalForm());
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setTitle("Espace Fournisseur");
			stage.showAndWait();
		} catch(Exception e) {
			e.printStackTrace();
		}
    }
	
	
    private void initTable() {
    	
    	
    	
		oblist = ser.getAllProduit();
		column_idp.setCellValueFactory(new PropertyValueFactory<>("idprod"));
		column_designation.setCellValueFactory(new PropertyValueFactory<>("designation"));
		column_qtestock.setCellValueFactory(new PropertyValueFactory<>("qtestock"));
		column_prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
		column_categorie.setCellValueFactory(new PropertyValueFactory<>("nomcateg"));
		column_fournisseur.setCellValueFactory(new PropertyValueFactory<>("nomfour"));
		column_idc.setCellValueFactory(new PropertyValueFactory<>("idc"));
		column_idf.setCellValueFactory(new PropertyValueFactory<>("idf"));
		
		tblview.setItems(oblist);
		
		
    }
    
    @FXML
   private void refreshCategorie ( ) throws SQLException {
	   combo_categorie.setItems(sc.getCatNames());
   }
	
    @FXML
    private void refreshFournisseur() throws SQLException {
    	combo_fournisseur.setItems(sf.getFourNames());
    }

    @FXML
    private void redirectToAcceuilReclamation(MouseEvent event) {
    }
    


}

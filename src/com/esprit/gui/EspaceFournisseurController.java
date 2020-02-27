package com.esprit.gui;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.esprit.Entite.Categorie;
import com.esprit.Entite.Fournisseur;
import com.esprit.Service.ServiceFournisseur;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class EspaceFournisseurController implements Initializable{

    @FXML
    private Pane pane_top;

    @FXML
    private Label label_title;

    @FXML
    private Pane pane_left;
    
    @FXML
    private TextField idfournisseur;

    @FXML
    private JFXTextField fournisseur_nom;

    @FXML
    private JFXTextField fournisseur_adresse;

    @FXML
    private JFXTextField fournisseur_tel;

    @FXML
    private JFXTextField fournisseur_email;

    @FXML
    private JFXButton btn_ajouter;

    @FXML
    private JFXButton btn_modifier;

    @FXML
    private JFXButton btn_delete;

    @FXML
    private Pane pane_visualiser;

    @FXML
    private JFXButton btn_visual_nbre;

    @FXML
    private JFXButton btn_visual_valeur;
    
    @FXML
    private JFXButton btn_clear;

    @FXML
    private Pane pane_table;

    @FXML
    private TableView<Fournisseur> tbl_fournisseur;

    @FXML
    private TableColumn<?, ?> tbl_id;
    
    @FXML
    private TableColumn<?, ?> tbl_nom;

    @FXML
    private TableColumn<?, ?> tbl_adresse;

    @FXML
    private TableColumn<?, ?> tbl_telephone;

    @FXML
    private TableColumn<?, ?> tbl_email;
    
    
	
	private ServiceFournisseur ser = new ServiceFournisseur();

	
	ObservableList<Fournisseur> oblist = FXCollections.observableArrayList();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		initTable();
	}

	private void initTable() {
		oblist = ser.getAllFournisseur();
		tbl_id.setCellValueFactory(new PropertyValueFactory<>("idf"));
		tbl_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
		tbl_adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
		tbl_telephone.setCellValueFactory(new PropertyValueFactory<>("tel"));
		tbl_email.setCellValueFactory(new PropertyValueFactory<>("email"));


		tbl_fournisseur.setItems(oblist);
	}
	
	@FXML
	public void ajouterFournisseur() throws SQLException {
		for(int i=0;i<fournisseur_tel.getText().length();i++) {
		if (fournisseur_tel.getText().charAt(i)>='a' &&
				fournisseur_tel.getText().charAt(i) <='z') {
				showAlertWithHeaderText();
				return;
			
		}
		
		}
			
			Fournisseur f1;
		f1 = new Fournisseur(fournisseur_nom.getText(), fournisseur_adresse.getText(), fournisseur_tel.getText(), fournisseur_email.getText());
		ser.ajouter(f1);
		initTable();
		clear();
		
	
	}
	
	 private void showAlertWithHeaderText() {
	        Alert alert = new Alert(AlertType.INFORMATION);
	        alert.setTitle("Verification des champs");
	        alert.setContentText("doit etre que numero");
	 
	        alert.showAndWait();
	    }
	
	
	@FXML
	private void delete () throws SQLException {
		
		Fournisseur selected = tbl_fournisseur.getSelectionModel().getSelectedItem();
		
		Fournisseur f1;
		f1 = new Fournisseur(selected.getIdf(), selected.getNom(), selected.getAdresse(), selected.getTel(), selected.getEmail());
		ser.delete(f1);
		initTable();
	}
	
	@FXML
	private void modifier() throws SQLException{
		System.out.println("modifier is working");
		int id= Integer.parseInt(idfournisseur.getText());
		Fournisseur f1;
		f1 = new Fournisseur(id, fournisseur_nom.getText(), fournisseur_adresse.getText(), fournisseur_tel.getText(), fournisseur_email.getText());
		ser.update(f1);
		initTable();
		clear();
	}
	
	@FXML
	private void modifierCelluleeee (MouseEvent event) {
		if (event.getClickCount()==2)
			System.out.println("its workinggggg");
			editFournisseur();
	}
	
	private void editFournisseur() {
		Fournisseur select = tbl_fournisseur.getSelectionModel().getSelectedItem();
		fournisseur_nom.setText(select.getNom());
		fournisseur_adresse.setText(select.getAdresse());
		fournisseur_tel.setText(select.getTel());
		fournisseur_email.setText(select.getEmail());
		idfournisseur.setText(String.valueOf(select.getIdf()));
		
	}
	
	@FXML
	private void clear() {
		
		fournisseur_nom.setText("");
		fournisseur_adresse.setText("");
		fournisseur_tel.setText("");
		fournisseur_email.setText("");
		
	}
	
	

}




























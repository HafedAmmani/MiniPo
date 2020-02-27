package com.esprit.gui;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.esprit.Entite.Categorie;
import com.esprit.Service.ServiceCategorie;
import com.jfoenix.controls.JFXButton;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class EspaceCategorieController implements Initializable {


    @FXML
    private JFXButton btn_ajouter;

    @FXML
    private JFXButton btn_modifier;


    @FXML
    private JFXButton btn_delete;

    @FXML
    private TextField input_categorie;

    @FXML
    private TableView<Categorie> tblview;
    @FXML
    private TextField idc;

    @FXML
    private TableColumn<?, ?> col_id;

    @FXML
    private TableColumn<?, ?> col_nom;
	
    private ServiceCategorie ser = new ServiceCategorie();
    
    ObservableList<Categorie> oblist = FXCollections.observableArrayList();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initTable();
		
	}
	
	private void initTable() {
        oblist   = ser.getAllCategorie();
        col_id.setCellValueFactory(new PropertyValueFactory<>("idcateg"));
        col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        tblview.setItems(oblist);
} 
	@FXML
	public void ajouterCategorie() throws SQLException {
		
		
		if(input_categorie.getText().equals("")) {
			showAlertWithHeaderText();
		}
		
		
		else {
		Categorie c1;
		c1 = new Categorie(input_categorie.getText());
		ser.ajouter(c1);
		ser.getCatNames();
		initTable();
		}
		
	}

	@FXML
	private void deletetos () throws SQLException {
		
		Categorie selected = tblview.getSelectionModel().getSelectedItem();
		
		Categorie c1;
		c1 = new Categorie(selected.getIdcateg(), selected.getNom());
		ser.delete(c1);
		initTable();
	}
	@FXML
	private void modifier() throws SQLException{
		System.out.println("im here");

		int id = Integer.parseInt(idc.getText());
		Categorie c1;
		c1 = new Categorie(id, input_categorie.getText());
		ser.update(c1);
		initTable();
		
	}
	@FXML
	private void modefierCellule (MouseEvent event) {
		if (event.getClickCount()==2)
			editCategorie();
	}
	
	private void editCategorie() {
		Categorie select = tblview.getSelectionModel().getSelectedItem();
		input_categorie.setText(select.getNom());
		idc.setText(String.valueOf(select.getIdcateg()));
		
	}
	
	
	
	
	 private void showAlertWithHeaderText() {
	        Alert alert = new Alert(AlertType.INFORMATION);
	        alert.setTitle("Verification des champs");
	        alert.setContentText("categorie doit etre non vide");
	 
	        alert.showAndWait();
	    }
	 
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

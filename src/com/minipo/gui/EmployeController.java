/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.minipo.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.minipo.Entite.Employe;
import com.minipo.Service.ServiceEmploye;
import java.awt.Button;
import java.awt.TextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.event.ActionEvent;
import javafx.scene.control.Cell;
import javafx.scene.control.cell.PropertyValueFactory;



/**
 * FXML Controller class
 *
 * @author hafed
 */
public class EmployeController implements Initializable {

    @FXML
    private JFXTextField txt_nom;

    @FXML
    private JFXTextField txt_prenom;

    @FXML
    private JFXTextField txt_adresse;

    @FXML
    private JFXTextField txt_tel;

    @FXML
    private JFXTextField txt_email;

    @FXML
    private JFXTextField txt_salaire;
    
    @FXML
    private JFXTextField idfld;

    @FXML
    private JFXButton btn_save;

    @FXML
    private JFXButton btn_add_new;

    @FXML
    private JFXButton btn_edit;

    @FXML
    private JFXButton btn_delete;
    
    @FXML
    private TableView<Employe> tblview;

    @FXML
    private TableColumn<Employe, String> col_nom;

    @FXML
    private TableColumn<Employe, String> col_prenom;

    @FXML
    private TableColumn<Employe, String> col_adresse;

    @FXML
    private TableColumn<Employe, String> col_telephone;

    @FXML
    private TableColumn<Employe, String> col_email;

    @FXML
    private TableColumn<Employe, String> col_salaire;
    @FXML
    private TableColumn<Employe, Integer> id;

    @FXML
    private javafx.scene.control.TextField btn_recherche;
    
    private ServiceEmploye ser = new ServiceEmploye();
    private int ID;
    ObservableList<Employe> oblist = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initTable();
        btn_add_new.setOnAction(e->{
            try {
                
                insertNewEmploye();
            } catch (SQLException ex) {
                Logger.getLogger(EmployeController.class.getName()).log(Level.SEVERE, null, ex);
            }
		});
        btn_edit.setOnAction(e->{
            
            EditEmploye();
		});
        btn_delete.setOnAction(e->{
            
            try {
                deleteEmploye();
            } catch (SQLException ex) {
                Logger.getLogger(EmployeController.class.getName()).log(Level.SEVERE, null, ex);
            }
		});
        btn_save.setOnAction(e->{
            
            try {
                SaveEmploye();
            } catch (SQLException ex) {
                Logger.getLogger(EmployeController.class.getName()).log(Level.SEVERE, null, ex);
            }
		});
        
        
    }    
    private void SaveEmploye() throws SQLException{
            int id = Integer.parseInt(idfld.getText());
            Employe p1;
            p1 = new Employe(id,txt_nom.getText(), txt_prenom.getText(), txt_adresse.getText(), txt_tel.getText(), txt_email.getText(), txt_salaire.getText());
            ser.update(p1);
            initTable();
        
    }
    private void deleteEmploye() throws SQLException{
        Employe selected = tblview.getSelectionModel().getSelectedItem();
        ID = selected.getIdemp();
            Employe p1;
            p1 = new Employe(ID,txt_nom.getText(), txt_prenom.getText(), txt_adresse.getText(), txt_tel.getText(), txt_email.getText(), txt_salaire.getText());
            ser.delete(p1);
            initTable();
    }
    private void EditEmploye(){
        // for updating existing account
		Employe selected = tblview.getSelectionModel().getSelectedItem();
                idfld.setText(String.valueOf(selected.getIdemp()));
		txt_nom.setText(selected.getNom());
		txt_prenom.setText(selected.getPrenom());
		txt_adresse.setText(selected.getAdresse());
		txt_tel.setText(selected.getTel());
		txt_email.setText(selected.getEmail());
		txt_salaire.setText(selected.getSalaire());
    }
    
    private void insertNewEmploye() throws SQLException{ // for adding new Employe
        
        Employe p1;
        p1 = new Employe(txt_nom.getText(), txt_prenom.getText(), txt_adresse.getText(), txt_tel.getText(), txt_email.getText(), txt_salaire.getText());
        ser.ajouter(p1);
        txt_nom.setText("");
        txt_prenom.setText("");
        txt_adresse.setText("");
        txt_tel.setText("");
        txt_email.setText("");
        txt_salaire.setText("");
        initTable();
    }
    
    private void initTable() {
            oblist   = ser.getAllEmploye();
            col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            col_prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            col_adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
            col_telephone.setCellValueFactory(new PropertyValueFactory<>("tel"));
            col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
            col_salaire.setCellValueFactory(new PropertyValueFactory<>("salaire"));
            id.setCellValueFactory(new PropertyValueFactory<>("idemp"));
            tblview.setItems(oblist);
	}
	
//	private void refreshTable() throws SQLException {
//            initTable();
//            
//            tblview.setItems((ObservableList<Employe>) ser.readAll());
//            
//	}
        
}

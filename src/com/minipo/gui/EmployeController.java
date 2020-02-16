/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.minipo.gui;

import com.minipo.Entite.Employe;
import java.awt.Button;
import java.awt.TextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author hafed
 */
public class EmployeController implements Initializable {

    @FXML
    private TextField txt_nom;

    @FXML
    private TextField txt_prenom;

    @FXML
    private TextField txt_adresse;

    @FXML
    private TextField txt_tel;

    @FXML
    private TextField txt_email;

    @FXML
    private TextField txt_salaire;

    @FXML
    private Button btn_save;

    @FXML
    private Button btn_add_new;

    @FXML
    private Button btn_edit;

    @FXML
    private Button btn_delete;
    
    @FXML // fx:id="tblview"
    private TableView<Employe> tblview; // Value injected by FXMLLoader

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
    private TextField btn_recherche;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}

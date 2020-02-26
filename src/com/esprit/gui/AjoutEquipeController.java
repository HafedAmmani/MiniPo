/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.esprit.Entite.Equipe;

import com.esprit.Service.ServiceEquipe;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author hafed
 */
public class AjoutEquipeController implements Initializable {

    @FXML
    private JFXTextField fil_nomeq;
    @FXML
    private JFXTextField fil_nb;
    @FXML
    private JFXButton btn_addEquipe;
    @FXML
    private JFXButton btn_modifierEquipe;
    @FXML
    private JFXButton btn_supprimerEquipe;
    @FXML
    private TableColumn<Equipe, String> col_nomeq;
    @FXML
    private TableColumn<?, ?> col_nb;
    private TableColumn<?, ?> col_ideq;
    
    private ServiceEquipe ser = new ServiceEquipe();
    ObservableList<Equipe> oblist = FXCollections.observableArrayList();
    @FXML
    private TableView<Equipe> tblview;
    private int ID;
    @FXML
    private TableColumn<?, ?> ideq;
    @FXML
    private JFXTextField fil_ideq;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initTable();
    }    
     private void initTable() {
            oblist   = ser.getAllEquipe();
            col_nomeq.setCellValueFactory(new PropertyValueFactory<>("Nomeq"));
            col_nb.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
            ideq.setCellValueFactory(new PropertyValueFactory<>("ideq"));
            tblview.setItems(oblist);
	}
    @FXML
     private void AjouterEquipe() throws SQLException{
         Equipe E1;
         E1 = new Equipe(fil_nomeq.getText(),Integer.valueOf(fil_nb.getText()));
        ser.ajouter(E1);
        initTable();
     }
    @FXML
     private void DeleteEquipe() throws SQLException {
     
         Equipe selected = tblview.getSelectionModel().getSelectedItem();
        ID = selected.getIdeq();
        
            Equipe p1;
            p1 = new Equipe(ID,selected.getNomeq(),selected.getNombre());
            ser.delete(p1);
            initTable();
     }
     
    @FXML
     private void SaveEmploye() throws SQLException{ //modifier employe
                
            int id = Integer.parseInt(fil_ideq.getText());
            Equipe p1;
            p1 = new Equipe(id,fil_nomeq.getText(),Integer.valueOf(fil_nb.getText()));
            ser.update(p1);
            
            initTable();
        
    }
     private void EditEquipe(){
        // for updating existing account
		Equipe selected = tblview.getSelectionModel().getSelectedItem();
                fil_ideq.setText(String.valueOf(selected.getIdeq()));
		fil_nomeq.setText(selected.getNomeq());
		fil_nb.setText(String.valueOf(selected.getNombre()));	
    }
     
     
    @FXML
     private void modifiercell(MouseEvent event) {
        if (event.getClickCount()==2)
            EditEquipe();
            
    }
}

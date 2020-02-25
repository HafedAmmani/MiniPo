/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui;

import com.esprit.Entite.Conge;
import com.esprit.Service.ServiceConge;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author hafed
 */
public class AfficherdemandeCongeController implements Initializable {

    @FXML
    private TableView<Conge> tblview;
    @FXML
    private TableColumn<?, ?> col_type;
    @FXML
    private TableColumn<?, ?> col_dated;
    @FXML
    private TableColumn<?, ?> col_datef;
    @FXML
    private TableColumn<?, ?> col_nbr;
    @FXML
    private TableColumn<?, ?> col_descr;
    @FXML
    private TableColumn<?, ?> col_etat;
    @FXML
    private TableColumn<?, ?> col_idc;
    @FXML
    private TextField txt_type;
    @FXML
    private TextField txt_dated;
    @FXML
    private TextField txt_datef;
    @FXML
    private TextField txt_nb;
    @FXML
    private TextArea txt_descr;
    @FXML
    private Button btn_confirmer;
    private int idcon;
    private ServiceConge ser = new ServiceConge();
    ObservableList<Conge> oblist = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initTable();
    }    
    private void initTable() {
            oblist   = ser.getAllDemande();
            col_type.setCellValueFactory(new PropertyValueFactory<>("type"));
            col_dated.setCellValueFactory(new PropertyValueFactory<>("datedebut"));
            col_datef.setCellValueFactory(new PropertyValueFactory<>("datefin"));
            col_nbr.setCellValueFactory(new PropertyValueFactory<>("nbrjrs"));
            col_descr.setCellValueFactory(new PropertyValueFactory<>("description"));
            
            col_etat.setCellValueFactory(new PropertyValueFactory<>("etatc"));
            col_idc.setCellValueFactory(new PropertyValueFactory<>("idcon"));
            
            tblview.setItems(oblist);
	}
    private void EditDemande(){
        // for updating existing account
		Conge selected = tblview.getSelectionModel().getSelectedItem();
                
		txt_type.setText(selected.getType());
		txt_dated.setText(selected.getDatedebut());
		txt_datef.setText(selected.getDatefin());
		txt_nb.setText(String.valueOf(selected.getNbrjrs()));
                txt_descr.setText(selected.getDescription());
    }
     
    @FXML
     private void modifiercell(MouseEvent event) {
        if (event.getClickCount()==2)
            EditDemande();
            
    }
    @FXML
     private void ConfirmereDemande() throws SQLException {
     
         Conge selected = tblview.getSelectionModel().getSelectedItem();
            idcon = selected.getIdcon();
            
            Conge g1;
            g1 = new Conge(idcon,selected.getType(),selected.getDatedebut(),selected.getDatefin(),selected.getNbrjrs(),selected.getDescription(),true);
            ser.update(g1);
            
            initTable();
     }
}

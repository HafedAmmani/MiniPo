/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui;

import com.esprit.Entite.Commande;
import com.esprit.Service.ServiceLivraison;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import com.esprit.Entite.Livraison;
import com.esprit.Entite.User;
import java.time.LocalDate;
import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author sinda
 */
public class LivraisonController implements Initializable {

    @FXML
    public ComboBox<String> id_cmd;
    @FXML
    private TextField destinantion;
    @FXML
    private ComboBox<String> id_liv;
    @FXML
    private DatePicker id_date;
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

    ObservableList<String> list = FXCollections.observableArrayList();
    
    private ServiceLivraison serv = new ServiceLivraison();
    private int ID;
    ObservableList<Livraison> obList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initTable();
        combo();
        comboliv();
    }
//    @FXML
//    private void ComBo() throws SQLException{
//        
//        list = (ObservableList<Commande>) ser.readIdCommande();
//        id_cmd.setItems(list);
//    }

    public void combo() {
        ObservableList<String> cmbl = serv.getListRefCommande();
//        id_cmd.setCellFactory(new PropertyValueFactory<>("idcmd"));

        id_cmd.setItems(cmbl);
    }

    public void comboliv() {
        ObservableList<String> cmbl = serv.getListLivreur();
//        id_cmd.setCellFactory(new PropertyValueFactory<>("idcmd"));

        id_liv.setItems(cmbl);
    }

    private void initTable() {
        obList = serv.getLiv();
        System.out.println(obList);
        col_idliv.setCellValueFactory(new PropertyValueFactory<>("matriculeL"));
        col_dest.setCellValueFactory(new PropertyValueFactory<>("destination"));
        col_etatl.setCellValueFactory(new PropertyValueFactory<>("etatl"));
        col_idc.setCellValueFactory(new PropertyValueFactory<>("commandeRefC"));
        col_salaire.setCellValueFactory(new PropertyValueFactory<>("livreur"));
        tblview.setItems(obList);
    }

    @FXML
    private void AjouterLivraison() throws SQLException {
        Livraison p1;
        String ld = id_date.getValue().toString();
        // System.out.println(ld);
        Commande commande = serv.getComandeFromReference(id_cmd.getSelectionModel().getSelectedItem());
        User livreur = serv.getLivreurFromUsername(id_liv.getSelectionModel().getSelectedItem());
        p1 = new Livraison(destinantion.getText(), "non livree", ld, livreur, commande);
        serv.ajouterLivraison(p1);
        initTable();
    }

    @FXML
    private void deleteLiv() throws SQLException {

        Livraison p1;
        Livraison selected = tblview.getSelectionModel().getSelectedItem();
        ID = selected.getIdliv();
        p1 = new Livraison(ID);
        serv.deleteLivraison(p1);
        initTable();
    }

    @FXML
    private void redirectToUtilisateur(ActionEvent event) {
    }

    @FXML
    private void redirectToProduit(ActionEvent event) {
    }

    @FXML
    private void redirectToCommande(ActionEvent event) {
    }

    @FXML
    private void redirectTofacture(ActionEvent event) {
    }

    @FXML
    private void redirectToAcceuilReclamation(ActionEvent event) {
    }

  

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui;


import com.esprit.Entite.Commande;
import com.esprit.Service.ServiceCommande;
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
import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author sinda
 */
public class LivraisonController implements Initializable {

    @FXML
    public ComboBox<String> id_cmd;
    ServiceCommande serc=new ServiceCommande();
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
  
    ObservableList<String> list= FXCollections.observableArrayList();
    private ServiceCommande ser = new ServiceCommande();
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
    public void combo()
    {
        ObservableList<String> cmbl=ser.getIdCommande();
//        id_cmd.setCellFactory(new PropertyValueFactory<>("idcmd"));
        
        id_cmd.setItems(cmbl);
    }
    public void comboliv()
    {
        ObservableList<String> cmbl=serv.getIdLivreur();
//        id_cmd.setCellFactory(new PropertyValueFactory<>("idcmd"));
        
        id_liv.setItems(cmbl);
    }
    private void initTable() {
            obList = serv.getAllLivraison();
            col_idliv.setCellValueFactory(new PropertyValueFactory<>("idliv"));
            col_dest.setCellValueFactory(new PropertyValueFactory<>("destination"));
            col_etatl.setCellValueFactory(new PropertyValueFactory<>("etatl"));
            col_idc.setCellValueFactory(new PropertyValueFactory<>("idc"));
            col_salaire.setCellValueFactory(new PropertyValueFactory<>("idl"));
            tblview.setItems(obList);
	}
    @FXML
    private void AjouterLivraison() throws SQLException{
        Livraison p1;
        int idcom1=1;
        int idLiv=1;
        idcom1 += id_cmd.getSelectionModel().getSelectedIndex();
        idLiv += id_liv.getSelectionModel().getSelectedIndex();
        p1 = new Livraison(destinantion.getText(),"non livrée",idcom1,idLiv);
        serv.ajouterLivraison(p1);
        initTable();
    }
    @FXML
    private void deleteLiv() throws SQLException{
        
        Livraison p1;
        Livraison selected = tblview.getSelectionModel().getSelectedItem();
        ID = selected.getIdliv();
        p1 = new Livraison(ID,selected.getDestination(),selected.getEtatl(),selected.getIdc(),selected.getIdl());
        serv.deleteLivraison(p1);
        initTable();
    }

}

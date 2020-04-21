/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui;

import com.esprit.Service.ServiceLivraison;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import com.esprit.Entite.Livraison;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author sinda
 */
public class LivraisonController implements Initializable {

    @FXML
    private ComboBox<String> id_liv;
    private String livreur;
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
        // combo();
        comboliv();
    }
     @FXML
    private void LogoutAction(ActionEvent event) {
        
        try {
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("LoginUser.fxml"));
            Scene tableViewScene = new Scene(tableViewParent);
            
            //This line gets the Stage information
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            
            window.setScene(tableViewScene);
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//    @FXML
//    private void ComBo() throws SQLException{
//        
//        list = (ObservableList<Commande>) ser.readIdCommande();
//        id_cmd.setItems(list);
//    }

    /* public void combo() {
        ObservableList<String> cmbl = serv.getListRefCommande();
//        id_cmd.setCellFactory(new PropertyValueFactory<>("idcmd"));

        id_cmd.setItems(cmbl);
    }*/
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
    private void deleteLiv() throws SQLException {

        Livraison p1;
        Livraison selected = tblview.getSelectionModel().getSelectedItem();
        ID = selected.getIdliv();
        p1 = new Livraison(ID);
        serv.deleteLivraison(p1);
        initTable();
    }

    @FXML
    private void updateLivraison() throws SQLException {
        Livraison selected = tblview.getSelectionModel().getSelectedItem();
        livreur = id_liv.getSelectionModel().getSelectedItem();
        serv.updateLivreur(livreur, selected.getMatriculeL());
        System.out.println("out");
        initTable();
    }
    private void redirectToUtilisateur(ActionEvent event) {
         try{
        FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("testUser.fxml"));
        Parent root = loader.load();
        tblview.getScene().setRoot(root);
        }catch(Exception e){
        
        System.out.println(e.getMessage());
        }
    }


    private void redirectToCommande(ActionEvent event) {
         try{
        FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("EspaceAdministrateur.fxml"));
        Parent root = loader.load();
        tblview.getScene().setRoot(root);
        }catch(Exception e){
        
        System.out.println(e.getMessage());
        }
    }

 //*******************Espace Admin****************

  @FXML
    private void GestionUserAction(ActionEvent event) throws IOException {
        
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("testUser.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    }

    @FXML
    private void GestionProdAction(ActionEvent event) throws IOException {
         Parent tableViewParent = FXMLLoader.load(getClass().getResource("EspaceProduit.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    }

    @FXML
    private void GestionVAAction(ActionEvent event) throws IOException {
         Parent tableViewParent = FXMLLoader.load(getClass().getResource("GestionVA.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    }

    @FXML
    private void GestionReclamAction(ActionEvent event) throws IOException {

   Parent tableViewParent = FXMLLoader.load(getClass().getResource("AccueilReclamationAd.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    }

    @FXML
    private void GestionLivAction(ActionEvent event) throws IOException {
        
         Parent tableViewParent = FXMLLoader.load(getClass().getResource("livraison.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    }

    @FXML
    private void chartsAction(ActionEvent event) {
        
    }

    @FXML
    private void calendarAction(ActionEvent event) {
    }

    @FXML
    private void PageAction(ActionEvent event) {
    }

}

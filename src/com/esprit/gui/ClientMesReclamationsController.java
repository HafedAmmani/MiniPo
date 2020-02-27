/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.Gui;

import com.esprit.Entite.Reclamation;
import com.esprit.Entite.ReclamationClient;
import com.esprit.Service.ServiceReclamation;
import com.esprit.gui.LoginUserController;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author darra
 */
public class ClientMesReclamationsController implements Initializable {

    @FXML
    private TableView<ReclamationClient> tableView;
    private ObservableList<ReclamationClient>oblist;
    @FXML
    private TableColumn<ReclamationClient, String> type;
    @FXML
    private TableColumn<ReclamationClient, String> objet;
    @FXML
    private TableColumn<ReclamationClient, String> description;
    @FXML
    private TableColumn<ReclamationClient, String> etat;
    private ServiceReclamation reclamation=new ServiceReclamation();
    //ObservableList<Reclamation>oblistReclamation=FXCollections.observableArrayList();
    @FXML
    private TableColumn<Reclamation, Date> col_date;
    @FXML
    private TableColumn<ReclamationClient, String> col_reponse;
    @FXML
    private TableColumn<ReclamationClient, Integer> idr;
     @FXML
    private void redirectToClientmerecl(ActionEvent event) throws IOException {
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("CllientMesReclamations.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    }
    @FXML
    private void redirectToRecClient(ActionEvent event) throws IOException {
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("ReclamationClient.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            ListerMesReclamation();
        } catch (SQLException ex) {
            Logger.getLogger(ClientMesReclamationsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    private void ListerMesReclamation() throws SQLException {
         int id = LoginUserController.NumId;
        
        oblist=reclamation.ListerReclamationsById(id);
         
            type.setCellValueFactory(new PropertyValueFactory<>("type"));
            objet.setCellValueFactory(new PropertyValueFactory<>("objet"));
            description.setCellValueFactory(new PropertyValueFactory<>("description"));
            etat.setCellValueFactory(new PropertyValueFactory<>("etatr"));
            col_date.setCellValueFactory(new PropertyValueFactory<>("dateR"));
            col_reponse.setCellValueFactory(new PropertyValueFactory<>("reponse"));
             idr.setCellValueFactory(new PropertyValueFactory<>("idr"));
            //col_image.setCellValueFactory(new PropertyValueFactory<>("image"));
            
            tableView.setItems(oblist);
    }

    @FXML
    private void selectionDonnee(MouseEvent event) {
        oblist = tableView.getSelectionModel().getSelectedItems();
        String objet = oblist.get(0).getObjet();
        String description= oblist.get(0).getDescription();
        String type=oblist.get(0).getType();
        String reponse=oblist.get(0).getReponse();
        SimpleDateFormat sdfr = new SimpleDateFormat("dd/MMM/yyyy");
       // String nom=oblist.get(0).getFirstname();
        //String prenom=oblist.get(0).getLastname();
        int idr=oblist.get(0).getIdR();
        String etat=oblist.get(0).getEtatr();
        Date date= oblist.get(0).getDateR();
        
         FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("/com/esprit/Gui/ReclamationClientUnique.fxml"));
            try {
                Parent root = loader.load();
                ReclamationClientUniqueController apc = loader.getController();
                apc.setObjet(objet);
                apc.setDescription(description);
                apc.setType(type);
                apc.setReponse(reponse);
                apc.setDate(sdfr.format(date));
                apc.setIdr(idr);
                
                //apc.setNomPrenom(nom,prenom);
                apc.setEtat(etat);
                tableView.getScene().setRoot(root);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
                }
    }
    
}

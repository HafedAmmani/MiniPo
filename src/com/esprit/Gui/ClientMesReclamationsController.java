/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.Gui;

import com.esprit.Entite.Reclamation;
import com.esprit.Entite.ReclamationClient;
import com.esprit.Service.ServiceReclamation;
import java.net.URL;
import java.sql.Date;
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
import javafx.scene.control.cell.PropertyValueFactory;

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
         int id=1;
        
        oblist=reclamation.ListerReclamationsById(id);
         
            type.setCellValueFactory(new PropertyValueFactory<>("type"));
            objet.setCellValueFactory(new PropertyValueFactory<>("objet"));
            description.setCellValueFactory(new PropertyValueFactory<>("description"));
            etat.setCellValueFactory(new PropertyValueFactory<>("etatr"));
            col_date.setCellValueFactory(new PropertyValueFactory<>("dateR"));
            col_reponse.setCellValueFactory(new PropertyValueFactory<>("reponse"));
            //col_image.setCellValueFactory(new PropertyValueFactory<>("image"));
            
            tableView.setItems(oblist);
    }
    
}

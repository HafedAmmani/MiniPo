/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.Gui;

import com.esprit.Entite.ReclamationClient;
import com.esprit.Entite.ReclamationsEmploye;
import com.esprit.Service.ServiceReclamation;
import com.esprit.Service.ServiceReclamationEmploye;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class EmployeMesReclamationsController implements Initializable {

    @FXML
    private TableView<ReclamationsEmploye> tabviewRec;
    @FXML
    private TableColumn<ReclamationsEmploye, String> col_objet;
    @FXML
    private TableColumn<ReclamationsEmploye, String> col_descrption;
    @FXML
    private TableColumn<ReclamationsEmploye, String> col_etat;
    @FXML
    private TableColumn<ReclamationsEmploye, String> col_date;
    @FXML
    private TableColumn<ReclamationsEmploye, String> col_reponse;
    private ServiceReclamationEmploye reclamation=new ServiceReclamationEmploye();
     private ObservableList<ReclamationsEmploye>oblist;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            ListerMesReclamation();
        } catch (SQLException ex) {
            Logger.getLogger(EmployeMesReclamationsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
     private void ListerMesReclamation() throws SQLException {
         int id=1;
        
        oblist=reclamation.ListerReclamationsById(1);
         
            col_objet.setCellValueFactory(new PropertyValueFactory<>("objet"));
            col_descrption.setCellValueFactory(new PropertyValueFactory<>("description"));
            col_etat.setCellValueFactory(new PropertyValueFactory<>("etatRemp"));
            col_date.setCellValueFactory(new PropertyValueFactory<>("dateRemp"));
            col_reponse.setCellValueFactory(new PropertyValueFactory<>("reponse"));
            //col_image.setCellValueFactory(new PropertyValueFactory<>("image"));
            
            tabviewRec.setItems(oblist);
    }
}

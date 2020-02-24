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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author darra
 */
public class ListeMesReclamationsClientController implements Initializable {

    @FXML
    private ListView<ReclamationClient> listViewRec;
     @FXML
    private ListView<ReclamationClient> details;
    ObservableList<ReclamationClient>oblistClient;
    public static List<ReclamationClient>data_details=new ArrayList<ReclamationClient>();
    
    //private ListView<ReclamationClient> description;
    
     
   
  
    
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      ServiceReclamation sr=new ServiceReclamation();
        try {
            oblistClient=sr.ListerReclamationsById(1);
        } catch (SQLException ex) {
            Logger.getLogger(ListeMesReclamationsClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
        listViewRec.setItems(oblistClient);
        ObservableList<ReclamationClient> oblist=FXCollections.observableArrayList(data_details);
        details.setItems(oblist);
        listViewRec.setCellFactory(ReclamationsListView -> new ReclamationObjetController());
        details.setCellFactory(ReclamationsListView -> new ReclamationDetailController());
    
    }    

    private void AfficherMesReclamations() throws SQLException {
      
        
    
        
      
        
        
    }

   
}



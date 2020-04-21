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
import com.esprit.gui.LoginUserController;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
public class EmployeMesReclamationsController implements Initializable {
    @FXML
    private Button actualiser;

    @FXML
    private TableView<ReclamationsEmploye> tabviewRec;
    @FXML
    private TableColumn<ReclamationsEmploye, String> col_categorie;
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
    @FXML
    private TableColumn<ReclamationsEmploye, Integer> idr;
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
    
     @FXML
    private void redirectToDemandeConge(ActionEvent event) throws IOException {
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("DemandeConge.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    }  
     @FXML
    private void redirectToRecEmp(ActionEvent event) throws IOException {
        //EmployeMeReclamation
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("ReclamationEmploye.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    }
    @FXML
    void BoutonActualiser(ActionEvent event) throws IOException {
             Parent tableViewParent = FXMLLoader.load(getClass().getResource("/com/esprit/Gui/EmployeMesReclamations.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    }
     private void ListerMesReclamation() throws SQLException {
         int id = LoginUserController.NumId;
        
        oblist=reclamation.ListerReclamationsById(id);
            col_categorie.setCellValueFactory(new PropertyValueFactory<>("nom"));
            col_objet.setCellValueFactory(new PropertyValueFactory<>("objet"));
            col_descrption.setCellValueFactory(new PropertyValueFactory<>("description"));
            col_etat.setCellValueFactory(new PropertyValueFactory<>("etatRemp"));
            col_date.setCellValueFactory(new PropertyValueFactory<>("dateRemp"));
            col_reponse.setCellValueFactory(new PropertyValueFactory<>("reponse"));
            idr.setCellValueFactory(new PropertyValueFactory<>("idRemp"));
            //col_image.setCellValueFactory(new PropertyValueFactory<>("image"));
            
            tabviewRec.setItems(oblist);
    }

    @FXML
    private void SelectionDonnee(MouseEvent event) {
          oblist = tabviewRec.getSelectionModel().getSelectedItems();
        String categorie = oblist.get(0).getNom();
        String objet = oblist.get(0).getObjet();
        String description= oblist.get(0).getDescription();
        String reponse=oblist.get(0).getReponse();
        SimpleDateFormat sdfr = new SimpleDateFormat("dd/MMM/yyyy");
       // String nom=oblist.get(0).getFirstname();
        //String prenom=oblist.get(0).getLastname();
        int idr=oblist.get(0).getIdRemp();
        String etat=oblist.get(0).getEtatRemp();
        Date date= oblist.get(0).getDateRemp();
        
         FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("/com/esprit/Gui/ReclamationEmployeUnique.fxml"));
            try {
                Parent root = loader.load();
                ReclamationEmployeUniqueController apc = loader.getController();
                apc.setCategorie(categorie);
                apc.setObjet(objet);
                apc.setDescription(description);
                apc.setReponse(reponse);
                apc.setDate(sdfr.format(date));
                apc.setIdr(idr);
                
                //apc.setNomPrenom(nom,prenom);
                apc.setEtat(etat);
                tabviewRec.getScene().setRoot(root);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
                }
    }
}

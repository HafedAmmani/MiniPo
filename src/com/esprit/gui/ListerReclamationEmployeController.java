/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.Gui;

import com.esprit.Entite.ReclamationClient;
import com.esprit.Entite.Reclamationemploye;
import com.esprit.Entite.ReclamationsEmploye;
import static com.esprit.Gui.ListerReclamationClientController.stageAffichageUnique;
import com.esprit.Service.ServiceReclamation;
import com.esprit.Service.ServiceReclamationEmploye;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author darra
 */
public class ListerReclamationEmployeController implements Initializable {

    @FXML
    private TableColumn<ReclamationsEmploye, Integer> col_IdRec;
     @FXML
    private TableColumn<ReclamationsEmploye, String> col_categorie;
     @FXML
    private TableColumn<ReclamationsEmploye, String> col_reponse;
    @FXML
    private TableColumn<ReclamationsEmploye, String> col_Obj;
    @FXML
    private TableColumn<ReclamationsEmploye, String> col_description;
    @FXML
    private TableColumn<ReclamationsEmploye, String> col_etat;
    @FXML
    private TableColumn<ReclamationsEmploye, String> col_nom;
    @FXML
    private TableColumn<ReclamationsEmploye, String> col_prenom;
    @FXML
    private TableColumn<ReclamationsEmploye, Date> col_DateRec;
    @FXML
    private Button btnRetour;
    @FXML
    private Button Actualiser;
    @FXML
    private TableView<ReclamationsEmploye> tableViewRecEmp;
    private ObservableList<ReclamationsEmploye>oblistEmp;
    private ServiceReclamationEmploye reclamationEmploye=new ServiceReclamationEmploye();
    @FXML
    private TextField TextField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        tableViewRecEmp.setItems(oblistEmp);
          tableViewRecEmp.setEditable(true);
          AfficherListeReclamationsEmploye();
          RechercherReclamationEmploye();
          //getEtat();
          //modifierEtat();
        
          
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
    private void redirectToProduit(ActionEvent event) throws IOException {
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("EspaceProduit.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    }
    private void redirectToReclamation(ActionEvent event) throws IOException {
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("AcceuilReclamationAd.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    }

    private void AfficherListeReclamationsEmploye(){
        
        try {
            oblistEmp=reclamationEmploye.ListerEmploye();
            //TableColumn Action = new TableColumn("Action");
        } catch (SQLException ex) {
            Logger.getLogger(ListerReclamationEmployeController.class.getName()).log(Level.SEVERE, null, ex);
        }
              
            col_IdRec.setCellValueFactory(new PropertyValueFactory<>("idRemp"));  
            col_categorie.setCellValueFactory(new PropertyValueFactory<>("nom"));
            col_Obj.setCellValueFactory(new PropertyValueFactory<>("objet"));
            col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
            //col_image.setCellValueFactory(new PropertyValueFactory<>("image"));
            col_etat.setCellValueFactory(new PropertyValueFactory<>("etatRemp"));
            col_nom.setCellValueFactory(new PropertyValueFactory<>("firstname"));
            col_prenom.setCellValueFactory(new PropertyValueFactory<>("lastname"));
            col_reponse.setCellValueFactory(new PropertyValueFactory<>("reponse"));
            col_DateRec.setCellValueFactory(new PropertyValueFactory<>("dateRemp"));
            //Action.setCellValueFactory(new PropertyValueFactory<>("button"));
            tableViewRecEmp.setItems(oblistEmp);
    }

    private void RechercherReclamationEmploye() {
        
        FilteredList<ReclamationsEmploye>filteredData=new FilteredList<>(oblistEmp,b -> true);
            TextField.setOnKeyReleased(e->{
        TextField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate((Predicate<? super ReclamationsEmploye>)ReclamationsEmploye -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (ReclamationsEmploye.getEtatRemp().toLowerCase().contains(lowerCaseFilter) ) {
					return true; // Filter matches first name.
				} else if (ReclamationsEmploye.getNom().toLowerCase().contains(lowerCaseFilter)) 
					return true;  // Filter matches last name.
				//else if (ReclamationsEmploye.getIdRemp().contains(newValue))
				   //return true;
				      
				    	 return false; // Does not match.
			});
		});
        SortedList<ReclamationsEmploye>soretedData=new SortedList<>(filteredData);
        soretedData.comparatorProperty().bind(tableViewRecEmp.comparatorProperty());
        tableViewRecEmp.setItems(soretedData);
            });
    }
          
        
       
        
    
    
    @FXML
    private void BoutonRetour(ActionEvent event) throws IOException {
          Parent tableViewParent = FXMLLoader.load(getClass().getResource("/com/esprit/Gui/ListesReclamation.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
        
    }
@FXML
    private void BoutonActualiser(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("/com/esprit/Gui/ListerReclamationEmployes.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    }
    @FXML
    private void AfficherRecUnique(MouseEvent event) {
          oblistEmp = tableViewRecEmp.getSelectionModel().getSelectedItems();
        String categorie = oblistEmp.get(0).getNom();
        String objet = oblistEmp.get(0).getObjet();
        String description= oblistEmp.get(0).getDescription();
        String nom=oblistEmp.get(0).getFirstname();
        String prenom=oblistEmp.get(0).getLastname();
        String reponse=oblistEmp.get(0).getReponse();
        String etat=oblistEmp.get(0).getEtatRemp();
        Date date=oblistEmp.get(0).getDateRemp();
        SimpleDateFormat sdfr = new SimpleDateFormat("dd/MMM/yyyy");
        int id=oblistEmp.get(0).getIdRemp();
        
         FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("/com/esprit/Gui/ReclamationChaqueEmployes.fxml"));
            try {
                Parent root = loader.load();
                ReclamationChaqueEmployeController rce = loader.getController();
                rce.setCategorie(categorie);
                rce.setObjetTxtField(objet);
                rce.setDescription(description);
                rce.setIdREmp(id);
                rce.setNomPrenom(nom,prenom);
                rce.setEtat(etat);
                rce.setReponse(reponse);
                rce.setDate(sdfr.format(date));
                tableViewRecEmp.getScene().setRoot(root);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
                }
    }

   
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

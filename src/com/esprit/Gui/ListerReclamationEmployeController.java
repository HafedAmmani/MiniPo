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
    private AnchorPane RecEmp;
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

    private void AfficherListeReclamationsEmploye(){
        
        try {
            oblistEmp=reclamationEmploye.ListerEmploye();
            //TableColumn Action = new TableColumn("Action");
        } catch (SQLException ex) {
            Logger.getLogger(ListerReclamationEmployeController.class.getName()).log(Level.SEVERE, null, ex);
        }
              
            col_IdRec.setCellValueFactory(new PropertyValueFactory<>("idRemp"));     
            col_Obj.setCellValueFactory(new PropertyValueFactory<>("objet"));
            col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
            //col_image.setCellValueFactory(new PropertyValueFactory<>("image"));
            col_etat.setCellValueFactory(new PropertyValueFactory<>("etatRemp"));
            col_nom.setCellValueFactory(new PropertyValueFactory<>("firstname"));
            col_prenom.setCellValueFactory(new PropertyValueFactory<>("lastname"));
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
				
				if (ReclamationsEmploye.getFirstname().toLowerCase().contains(lowerCaseFilter) ) {
					return true; // Filter matches first name.
				} else if (ReclamationsEmploye.getLastname().toLowerCase().contains(lowerCaseFilter)) 
					return true; // Filter matches last name.
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
    private void AfficherRecUnique(MouseEvent event) {
          oblistEmp = tableViewRecEmp.getSelectionModel().getSelectedItems();
        String objet = oblistEmp.get(0).getObjet();
        String description= oblistEmp.get(0).getDescription();
        String nom=oblistEmp.get(0).getFirstname();
        String prenom=oblistEmp.get(0).getLastname();
        Date date=oblistEmp.get(0).getDateRemp();
        SimpleDateFormat sdfr = new SimpleDateFormat("dd/MMM/yyyy");
        int id=oblistEmp.get(0).getIdRemp();
        
         FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("/com/esprit/Gui/ReclamationChaqueEmployes.fxml"));
            try {
                Parent root = loader.load();
                ReclamationChaqueEmployeController rce = loader.getController();
                rce.setObjetTxtField(objet);
                rce.setDescription(description);
                rce.setIdREmp(id);
                rce.setNomPrenom(nom,prenom);
                rce.setDate(sdfr.format(date));
                tableViewRecEmp.getScene().setRoot(root);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
                }
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.minipo.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.minipo.Entite.Affectation;
import com.minipo.Service.ServiceAffectation;
import com.minipo.Service.ServiceEmploye;
import com.minipo.Service.ServiceEquipe;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hafed
 */
public class EquipeController implements Initializable {

    @FXML
    private JFXButton btn_add_new;
    @FXML
    private JFXTextField idfld;
    @FXML
    private JFXComboBox<String> com_Eq;
    @FXML
    private JFXButton btn_add_new1;
    @FXML
    private JFXComboBox<String> com_Emp;
    
    private ServiceEquipe serv = new ServiceEquipe();
    private ServiceEmploye ser = new ServiceEmploye();
    private ServiceAffectation serAff = new ServiceAffectation();
    @FXML
    private TableColumn<?, ?> col_emp;
    @FXML
    private TableColumn<?, ?> col_eq;
    @FXML
    private TableView<Affectation> tblview;
    ObservableList<Affectation> oblist = FXCollections.observableArrayList();
    @FXML
    private JFXTextField fill_recherche;
    @FXML
    private JFXButton btn_show;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comboEquipe();
        
        comboEmlpoye();
        initTable();
       
    }    
    @FXML
    private void redirectToEmp(ActionEvent event) throws IOException {
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("Employe.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    }
    
    public void comboEquipe()
    {   
        
        ObservableList<String> cmbl=serv.getNomEq();        
        com_Eq.setItems(cmbl);
    }
    
    public void comboEmlpoye()
    {
        ObservableList<String> cmbl=ser.getNomEmp();        
        com_Emp.setItems(cmbl);
    }
    private String Equipe;
    private String Employe;
    @FXML
    private void affectation() throws SQLException{
        Equipe = com_Eq.getSelectionModel().getSelectedItem();
        Employe = com_Emp.getSelectionModel().getSelectedItem();
        Affectation aff;
        aff = new Affectation(Equipe, Employe);
        serAff.ajouter(aff);
        initTable();
        
    }
    
   private void initTable() {
            oblist   = serAff.getAllAffectation();
            col_emp.setCellValueFactory(new PropertyValueFactory<>("nom"));
            col_eq.setCellValueFactory(new PropertyValueFactory<>("NomEq"));
            tblview.setItems(oblist);
	} 

    @FXML
    private void recherche(KeyEvent event) {
        // 1. Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Affectation> filteredData = new FilteredList<>(oblist, p -> true);
        
        // 2. Set the filter Predicate whenever the filter changes.
        fill_recherche.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(person -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                
                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();
                
                if (person.getNomEq().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter by equipes.
                } else if (person.getNom().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter by nom.
                }
                return false; // Does not match.
            });
        });
        
        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Affectation> sortedData = new SortedList<>(filteredData);
        
        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(tblview.comparatorProperty());
        
        // 5. Add sorted (and filtered) data to the table.
        tblview.setItems(sortedData);
    }
    private FXMLLoader loader;
    @FXML
    private void showListEquipe() {
		try {
			loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("AjoutEquipe.fxml"));
//			AjoutEquipeController controller = new AjoutEquipeController();
//			loader.setController(controller);
			loader.load();
			Scene scene = new Scene(loader.getRoot());
			scene.getStylesheets().add(getClass().getResource("employe.css").toExternalForm());
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.show();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
    
    
    
}

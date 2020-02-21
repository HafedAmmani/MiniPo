/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.minipo.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.minipo.Entite.Employe;
import com.minipo.Service.ServiceEmploye;
import java.awt.Button;
import java.awt.TextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Cell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;



/**
 * FXML Controller class
 *
 * @author hafed
 */
public class EmployeController implements Initializable {

    @FXML
    private JFXTextField txt_nom;

    @FXML
    private JFXTextField txt_prenom;

    @FXML
    private JFXTextField txt_adresse;

    @FXML
    private JFXTextField txt_tel;

    @FXML
    private JFXTextField txt_email;

    @FXML
    private JFXTextField txt_salaire;
    
    @FXML
    private JFXTextField idfld;

    @FXML
    private JFXButton btn_save;

    @FXML
    private JFXButton btn_add_new;

    private JFXButton btn_edit;

    @FXML
    private JFXButton btn_delete;
    @FXML
    private JFXButton btn_print_preview;
    
    @FXML
    private TableView<Employe> tblview;

    @FXML
    private TableColumn<Employe, String> col_nom;

    @FXML
    private TableColumn<Employe, String> col_prenom;

    @FXML
    private TableColumn<Employe, String> col_adresse;

    @FXML
    private TableColumn<Employe, String> col_telephone;

    @FXML
    private TableColumn<Employe, String> col_email;

    @FXML
    private TableColumn<Employe, String> col_salaire;
    @FXML
    private TableColumn<Employe, Integer> id;

    @FXML
    private JFXTextField fil_recherche;
    
    private ServiceEmploye ser = new ServiceEmploye();
    private int ID;
    ObservableList<Employe> oblist = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initTable();
        btn_add_new.setOnAction(e->{
            try {
                
                insertNewEmploye();
            } catch (SQLException ex) {
                Logger.getLogger(EmployeController.class.getName()).log(Level.SEVERE, null, ex);
            }
		});
//        btn_edit.setOnAction(e->{
//            
//            EditEmploye();
//		});
        btn_delete.setOnAction(e->{
            
            try {
                deleteEmploye();
            } catch (SQLException ex) {
                Logger.getLogger(EmployeController.class.getName()).log(Level.SEVERE, null, ex);
            }
		});
        btn_save.setOnAction(e->{
            
            try {
                SaveEmploye();
            } catch (SQLException ex) {
                Logger.getLogger(EmployeController.class.getName()).log(Level.SEVERE, null, ex);
            }
		});
        btn_print_preview.setOnAction(e->{
			printReport();
		});
        //recherche();
        
    }
    @FXML
    private void redirectToEq(ActionEvent event) throws IOException {
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("Equipe.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    }    
    public void printReport() { //avec jaspersoft
        ser.printReport();
    }
    private void SaveEmploye() throws SQLException{ //modifier employe
                Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Employe modifier avex sucess !.");
		alert.setHeaderText(null);
            int id = Integer.parseInt(idfld.getText());
            Employe p1;
            p1 = new Employe(id,txt_nom.getText(), txt_prenom.getText(), txt_adresse.getText(), txt_tel.getText(), txt_email.getText(), txt_salaire.getText());
            ser.update(p1);
            alert.setContentText("The Employe "+txt_nom.getText()+" "+txt_prenom.getText() +" has been updated.");
            alert.showAndWait();
            clear();
            initTable();
        
    }
    private void deleteEmploye() throws SQLException{
                Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation Dialog");
		alert.setHeaderText(null);
		alert.setContentText("Are you sure you want to delete selected?");
		Optional<ButtonType> action = alert.showAndWait();
        Employe selected = tblview.getSelectionModel().getSelectedItem();
        ID = selected.getIdemp();
            Employe p1;
            p1 = new Employe(ID,txt_nom.getText(), txt_prenom.getText(), txt_adresse.getText(), txt_tel.getText(), txt_email.getText(), txt_salaire.getText());
            if(action.get() == ButtonType.OK)
            ser.delete(p1);
            initTable();
    }
    private void EditEmploye(){
        // for updating existing account
		Employe selected = tblview.getSelectionModel().getSelectedItem();
                idfld.setText(String.valueOf(selected.getIdemp()));
		txt_nom.setText(selected.getNom());
		txt_prenom.setText(selected.getPrenom());
		txt_adresse.setText(selected.getAdresse());
		txt_tel.setText(selected.getTel());
		txt_email.setText(selected.getEmail());
		txt_salaire.setText(selected.getSalaire());
    }
    
    private void insertNewEmploye() throws SQLException{ // for adding new Employe
                Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Employe enregistrer avec succes.");
		alert.setHeaderText(null);
        if(validate("Nom", txt_nom.getText(), "[a-zA-Z]+") && 
             validate("Prenom", txt_prenom.getText(), "[a-zA-Z]+") &&
               validate("Addresse", txt_adresse.getText(), "[a-zA-Z]+") &&
                validate("Telephone", txt_tel.getText(), "[0-9]+") && 
                 validate("Email", txt_email.getText(), "[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+") &&
                  validate("Salaire", txt_salaire.getText(), "[0-9]+") )  
    	   {

        Employe p1;
        p1 = new Employe(txt_nom.getText(), txt_prenom.getText(), txt_adresse.getText(), txt_tel.getText(), txt_email.getText(), txt_salaire.getText());
        ser.ajouter(p1);
            alert.setContentText("Employe "+txt_nom.getText()+" "+txt_prenom.getText() +" est ajouté.");
            alert.showAndWait();
        clear();
        initTable();
                        
        }
    }
    
    private void initTable() {
            oblist   = ser.getAllEmploye();
            col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            col_prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            col_adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
            col_telephone.setCellValueFactory(new PropertyValueFactory<>("tel"));
            col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
            col_salaire.setCellValueFactory(new PropertyValueFactory<>("salaire"));
            id.setCellValueFactory(new PropertyValueFactory<>("idemp"));
            tblview.setItems(oblist);
	}
    private void clear(){
            txt_nom.clear();
            txt_prenom.clear();
            txt_adresse.clear();
            txt_tel.clear();
            txt_email.clear();
            txt_salaire.clear();
    }
    @FXML
      private void recherche(){
      
        // 1. Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Employe> filteredData = new FilteredList<>(oblist, p -> true);
        
        // 2. Set the filter Predicate whenever the filter changes.
        fil_recherche.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(person -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                
                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();
                
                if (person.getNom().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (person.getPrenom().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }
                return false; // Does not match.
            });
        });
        
        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Employe> sortedData = new SortedList<>(filteredData);
        
        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(tblview.comparatorProperty());
        
        // 5. Add sorted (and filtered) data to the table.
        tblview.setItems(sortedData);
    }
      
      
      
        /*
	 * Validations
	 */
	private boolean validate(String field, String value, String pattern){
		if(!value.isEmpty()){
		Pattern p = Pattern.compile(pattern);
	        Matcher m = p.matcher(value);
	        if(m.find() && m.group().equals(value)){
	            return true;
	        }else{
	        	validationAlert(field, false);            
	            return false;            
	        }
		}else{
			validationAlert(field, true);            
            return false;
		}        
    }
	
	private boolean emptyValidation(String field, boolean empty){
        if(!empty){
            return true;
        }else{
        	validationAlert(field, true);            
            return false;            
        }
    }	
	
	private void validationAlert(String field, boolean empty){
	Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Validation Error");
        alert.setHeaderText(null);
        if(field.equals("Role")) alert.setContentText("Please Select "+ field);
        else{
        	if(empty) alert.setContentText("Please Enter "+ field);
        	else alert.setContentText("Please Enter Valid "+ field);
        }
        alert.showAndWait();
	}  

    @FXML
    private void modifiercell(MouseEvent event) {
        if (event.getClickCount()==2)
            EditEmploye();
            
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui;

import com.esprit.Entite.User;
import com.esprit.Service.ServicePersonne;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class TestingUserController implements Initializable {

    @FXML
    private TextField username;
    @FXML
    private TextField firstname;
    @FXML
    private TextField lastname;
    @FXML
    private PasswordField password;
    @FXML
    private TextField email;
    @FXML
    private ComboBox<String> genre;
    @FXML
    private ComboBox<String> role;
    @FXML
    private Button ajouter;
    @FXML
    private TableColumn<User, String> col_username;
    @FXML
    private TableColumn<User, String> col_firstname;
    @FXML
    private TableColumn<User, String> col_lastname;
    @FXML
    private TableColumn<User, String> col_email;
    @FXML
    private TableColumn<User, String> col_password;
    @FXML
    private TableColumn<User, String> col_genre;
    @FXML
    private TableColumn<User, String> col_role;
    private ObservableList<User> oblist =FXCollections.observableArrayList();
    ServicePersonne sp = new ServicePersonne();
    @FXML
    private TableView<User> tbview;
    private ObservableList<String> list = FXCollections.observableArrayList("Genre","Male","female");
    private ObservableList<String> list1 = FXCollections.observableArrayList("Role","agent RH","emplyé","livreur","fournisseur");
    @FXML
    private Button supprimer;
    @FXML
    private Button modifier;
    @FXML
    private Button clear;
    @FXML
    private void redirectToAcceuilReclamation(ActionEvent event) throws IOException {
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("AccueilReclamationAd.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    }
    @FXML
    private void redirectToCommande(ActionEvent event) throws IOException {
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("EspaceAdministrateur.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    }
    @FXML
    private void redirectToProduit(ActionEvent event) throws IOException {
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("EspaceProduit.fxml"));
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
        genre.setItems(list);
        role.setItems(list1);
        try {
            initTable();
            
        } catch (SQLException ex) {
            Logger.getLogger(TestingUserController.class.getName()).log(Level.SEVERE, null, ex);
        } 
        // TODO
    }    
     private void initTable() throws SQLException {
            oblist= sp.listerUserOB();
//            col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
            col_username.setCellValueFactory(new PropertyValueFactory<>("username"));
            col_firstname.setCellValueFactory(new PropertyValueFactory<>("firstname"));
            col_lastname.setCellValueFactory(new PropertyValueFactory<>("lastname"));
            col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
            col_password.setCellValueFactory(new PropertyValueFactory<>("password"));
            col_genre.setCellValueFactory(new PropertyValueFactory<>("genre"));
            col_role.setCellValueFactory(new PropertyValueFactory<>("roles"));
            tbview.setItems(oblist);
	}

    @FXML
    private void AjouterUser(ActionEvent event) throws SQLException {
            String nom = firstname.getText();
            String unom= username.getText();
            String prenom = lastname.getText();
            String mail = email.getText();
            String pass = password.getText();
            String rol = role.getValue();
            String genr =genre.getValue();
           
     
          if(!nom.isEmpty() & !unom.isEmpty() & !prenom.isEmpty() & !mail.isEmpty() & !pass.isEmpty() & !rol.isEmpty() & !genr.isEmpty())
          {
              ADD();
             
           }else{
               Alert alert = new Alert(Alert.AlertType.WARNING);
               alert.setTitle("Verifier champs");
               alert.setHeaderText(null);
               alert.setContentText("Remplir les champs vides");
               alert.showAndWait(); 
           }
    }  
    private void ADD() throws SQLException{
        String nom = firstname.getText();
            String unom= username.getText();
            String prenom = lastname.getText();
            String mail = email.getText();
            String pass = password.getText();
            String rol = role.getValue();
            String genr =genre.getValue();
           
       User p1;
        p1 = new User(username.getText(),lastname.getText(),firstname.getText(), email.getText(), password.getText(),genre.getValue(),role.getValue()); 
         initTable();
         sp.ajouter(p1);
    }
     
     private void Delete() throws SQLException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Dialogue de Confirmation ");
		alert.setHeaderText(null);
		alert.setContentText("etes-vous sur de vouloir supprimer l'element selectionné");
		Optional<ButtonType> action = alert.showAndWait();
            sp.Delete(oblist.get(0).getUsername());
            initTable();
    }
      private void EditUser() throws SQLException{
        // for updating existing account
		User selected = tbview.getSelectionModel().getSelectedItem();
                username.setText(selected.getUsername());
                lastname.setText(selected.getLastname());
		firstname.setText(selected.getFirstname());
		email.setText(selected.getEmail());
		password.setText(selected.getPassword());
                genre.setValue(selected.getGenre());
                role.setValue(selected.getRoles());
                
		
    }

   /* private void SelectionLigne(MouseEvent event) throws SQLException {
        oblist=tbview.getSelectionModel().getSelectedItems();
        EditUser();
    
    }*/
    @FXML
        private void modifiercell(MouseEvent event) throws SQLException {
        if (event.getClickCount()==2)
            oblist=tbview.getSelectionModel().getSelectedItems();
            EditUser();
            
    }

    @FXML
    private void supprimer(ActionEvent event) throws SQLException {
        Delete();
    }

    @FXML
    private void modifier(ActionEvent event) throws SQLException {
        //EditUser();
        String username=oblist.get(0).getUsername() ;
        User p=new User(username, lastname.getText(), firstname.getText(), email.getText(), password.getText(), genre.getValue(), role.getValue());
        sp.Modifier(p);
        initTable();
        clear();
    }
     private void clear(){
            username.clear();
            lastname.clear();
            firstname.clear();
            email.clear();
            password.clear();
            genre.getSelectionModel().select(0);
            role.getSelectionModel().select(0);
            
            
    }

    @FXML
    private void clear1(ActionEvent event) {
        clear();
    }
     
    }

    


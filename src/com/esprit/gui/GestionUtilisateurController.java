/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui;
import com.esprit.Service.ServicePersonne;
import com.esprit.Utils.DataBase;
import com.esprit.Entite.User;
import com.esprit.Service.ServicePersonne;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class GestionUtilisateurController implements Initializable {
    private Connection con;
    private Statement ste;
   @FXML
   private TableView<User>TbView;
   @FXML
   private ComboBox<String> combgenre;
   private ObservableList<String> list = FXCollections.observableArrayList("Male","female");
    @FXML
   private ComboBox<String> comborole;
   private ObservableList<String> list1 = FXCollections.observableArrayList("agent RH","emplyé","livreur","fournisseur");
   @FXML
   private TextField Tfirstname;
   @FXML
   private TextField tlastname;
   @FXML
    private TextField temail;
   @FXML
    private TextField tpassword;
   private TextField tid;
   
    

    @FXML
    private TableColumn<User, String> col_firstname;

    @FXML
    private TableColumn<User, String> col_lastname;

    @FXML
    private TableColumn<User, String> col_username;

    @FXML
    private TableColumn<User, String> col_email;

    @FXML
    private TableColumn<User, String> col_password; 
    @FXML
    private TableColumn<User, String> col_genre;
    private String Username;


    ObservableList<User> oblist = FXCollections.observableArrayList();
     ServicePersonne sp= new ServicePersonne ();
    @FXML
    private TextField tusername;
    private int id;
    @FXML
    private Button btn_sauvegarde;
    @FXML
    private Button btn_supp;
    @FXML
    private Button btn_mod;
    @FXML
    private TableColumn<User, String> col_role;
    /**
     * Initializes the controller class.
     */
    
    public void initialize(URL url, ResourceBundle rb) {
        combgenre.setItems(list);
        comborole.setItems(list1);
        try {
            initTable();
        } catch (SQLException ex) {
            Logger.getLogger(GestionUtilisateurController.class.getName()).log(Level.SEVERE, null, ex);
        } 
//       btn_sauvegarde.setOnAction(e->{
//            try {
//                
//                insertNewUser();
//            } catch (SQLException ex) {
//                Logger.getLogger(GestionUtilisateurController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//		});
        btn_mod.setOnAction(e->{
            
            try {
                UpdateUser();
            } catch (SQLException ex) {
                Logger.getLogger(GestionUtilisateurController.class.getName()).log(Level.SEVERE, null, ex);
            }
		});
        btn_supp.setOnAction(e->{
            
            try {
                Delete();
            } catch (SQLException ex) {
                Logger.getLogger(GestionUtilisateurController.class.getName()).log(Level.SEVERE, null, ex);
            }
		});
    }
       
       
       
        
    
   
     private void initTable() throws SQLException {
            oblist= sp.listerUserOB();
//            col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
            col_lastname.setCellValueFactory(new PropertyValueFactory<>("lastname"));
            col_firstname.setCellValueFactory(new PropertyValueFactory<>("firstname"));
            col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
            col_username.setCellValueFactory(new PropertyValueFactory<>("username"));
            col_password.setCellValueFactory(new PropertyValueFactory<>("password"));
            col_genre.setCellValueFactory(new PropertyValueFactory<>("genre"));
            col_role.setCellValueFactory(new PropertyValueFactory<>("roles"));
            TbView.setItems(oblist);
	}
    private boolean validatePassword(){
        Pattern p = Pattern.compile("((?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,15})");
        Matcher m = p.matcher(tpassword.getText());
        if (m.matches()){
            return true;
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("validate Password");
            alert.setHeaderText(null);
            alert.setContentText("Password must contain at least one(Digit,Lowercase,Uppercase and special characteres");
            alert.showAndWait();
            return false;
        }
    }
    @FXML
    private void insertNewUser() throws SQLException{ // for adding new Employe
        String nom = Tfirstname.getText();
            String unom= tusername.getText();
            String prenom = tlastname.getText();
            String mail = temail.getText();
            String pass = tpassword.getText();
            String role = comborole.getValue();
            String genre =combgenre.getValue();
       User p1;
       p1 = new User(tusername.getText(),Tfirstname.getText(), tlastname.getText(), temail.getText(), tpassword.getText(),combgenre.getValue(),comborole.getValue());
     // p1 = new User(tlastname.getText(),Tfirstname.getText(), temail.getText(),tusername.getText(), tpassword.getText(),role, combgenre.getValue(),comborole.getValue());  
      sp.ajouter(p1);
        tusername.setText("");
        Tfirstname.setText("");
        tlastname.setText("");
        temail.setText("");
        tpassword.setText("");
        combgenre.setValue(genre);
        comborole.setValue(role);
        clear();
        initTable();
    }  
    private void clear(){
            tusername.clear();
            tlastname.clear();
            Tfirstname.clear();
            temail.clear();
            tpassword.clear();
            
            
            
    }
      
    
    /*  void addNewUser(ActionEvent event) throws SQLException {
        insertNewUser();

    }*/
//       @FXML
//    void delete(ActionEvent event) {
//           Delete();
//    }
    /*    void modifier_user(ActionEvent event) {
     
    }*/

    private void Delete() throws SQLException {
        Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation Dialog");
		alert.setHeaderText(null);
		alert.setContentText("Are you sure you want to delete selected?");
		Optional<ButtonType> action = alert.showAndWait();
        //User selected = TbView.getSelectionModel().getSelectedItem();
        //Username = selected.getUsername();
           // User p1;
            //p1 = new User(id,Tfirstname.getText(), tlastname.getText(), temail.getText(), tpassword.getText(), tusername.getText(),combgenre.getValue(),comborole.getValue());
           // if(action.get() == ButtonType.OK)
            sp.Delete(oblist.get(0).getId());
            initTable();
    }
    private void UpdateUser() throws SQLException{ //modifier employe
            Alert alert = new Alert(AlertType.INFORMATION);
	    alert.setTitle("Employe modifier avex sucess !.");
	    alert.setHeaderText(null);
            int id = Integer.parseInt(tid.getText());
            User p1;
            p1 = new User(id,Tfirstname.getText(),tlastname.getText(), temail.getText(), tpassword.getText(), tusername.getText(), combgenre.getValue(),comborole.getValue());
            sp.Modifier(p1);
            alert.setContentText("le personnel "+Tfirstname.getText()+" "+tlastname.getText() +" est bien modifié.");
            alert.showAndWait();
            clear();
            initTable();
        
    }
    private void EditUser(){
        // for updating existing account
		User selected = TbView.getSelectionModel().getSelectedItem();
                tusername.setText(String.valueOf(selected.getUsername()));
                tlastname.setText(String.valueOf(selected.getLastname()));
		Tfirstname.setText(selected.getFirstname());
		temail.setText(selected.getEmail());
		tpassword.setText(selected.getPassword());
                combgenre.setValue(selected.getGenre());
                comborole.setValue(selected.getGenre());
		
    }

    private void SelectionLigne(MouseEvent event) {
        oblist=TbView.getSelectionModel().getSelectedItems();
    
    }
    @FXML
        private void modifiercell(MouseEvent event) {
        if (event.getClickCount()==2)
            EditUser();
            
    }
}

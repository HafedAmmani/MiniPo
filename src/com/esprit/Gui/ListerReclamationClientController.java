/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.Gui;

import com.esprit.Entite.Reclamation;
import com.esprit.Service.ServiceReclamation;
import com.esprit.Utils.DataBase;
import com.mysql.cj.protocol.Resultset;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import com.esprit.Entite.Reclamation;
import com.esprit.Entite.ReclamationClient;
import com.esprit.Entite.User;
import java.io.IOException;
import java.util.function.Predicate;
import javafx.collections.transformation.SortedList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.cell.TextFieldTreeTableCell;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
/**
 * FXML Controller class
 *
 * @author darra
 */
public class ListerReclamationClientController implements Initializable {
      //private Connection con;
      //private Statement ste;
   // @FXML
    //private ComboBox<String> RechCmbBox;
    //ObservableList<String> listeTypeRecherche = FXCollections.observableArrayList("Tout", "idr", "description", "dater", "etatr", "idclt", "idcmd");
    //ObservableList<String> listeTypeRechercheMembre = FXCollections.observableArrayList("Objet", "Message", "Status");
    @FXML
    private TextField TextField;
    @FXML
    private HBox Hbox;
    @FXML
    private TableView<ReclamationClient> TableViewRec;
    //private ObservableList<Reclamation>oblist=FXCollections.observableArrayList();
    private ObservableList<ReclamationClient>oblistClient;
    private ObservableList<String> oblistCombo=FXCollections.observableArrayList("En cours","traiter");
    
    @FXML
    private TableColumn<ReclamationClient, Integer> col_IdRec;
    @FXML
    private TableColumn<ReclamationClient, String> col_Type;
    @FXML
    private TableColumn<ReclamationClient, String> col_Obj;
    @FXML
    private TableColumn<ReclamationClient, String> col_description;
    @FXML
    private TableColumn<ReclamationClient, ?> col_image;
    @FXML
    private TableColumn<ReclamationClient , String> col_etat;
    @FXML
    private TableColumn<ReclamationClient, String> col_nom;
    @FXML
    private TableColumn<ReclamationClient, String> col_prenom;
    @FXML
    private TableColumn<ReclamationClient, Date> col_DateRec;
    
    private ServiceReclamation reclamation=new ServiceReclamation();
    @FXML
    private ComboBox<String> etat;
    @FXML
    private Button BtnModifier;
    @FXML
    private Button btnRetour;
    @FXML
    private TextField numeroRec;
    static Stage stageAffichageUnique;
    static ReclamationClient ReclamationSelectionne;
    //ObservableList<Reclamation>oblistReclamation=FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          etat.setItems(oblistCombo);
          
          TableViewRec.setItems(oblistClient);
          TableViewRec.setEditable(true);
          stageAffichageUnique = new Stage();
        
         // TableColumn Action = new TableColumn("Action");
          //Action.setCellValueFactory(new PropertyValueFactory<>("button"));
          
          //TableViewRec.getColumns().add(Action);
          try {
              AfficherListeReclamations();
              RechercherReclamation();
              //getEtat();
              //modifierEtat();
              
          } catch (SQLException ex) {
              Logger.getLogger(ListerReclamationClientController.class.getName()).log(Level.SEVERE, null, ex);
          }
        
          
        }
   
    
    
    
    
    
   

    private void AfficherListeReclamations() throws SQLException{
    
         oblistClient=reclamation.Lister();
       //TableColumn Action = new TableColumn("Action");
          
                
            col_IdRec.setCellValueFactory(new PropertyValueFactory<>("idR"));     
            col_Type.setCellValueFactory(new PropertyValueFactory<>("type"));
            col_Obj.setCellValueFactory(new PropertyValueFactory<>("objet"));
            col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
            //col_image.setCellValueFactory(new PropertyValueFactory<>("image"));
            col_etat.setCellValueFactory(new PropertyValueFactory<>("etatr"));
            col_nom.setCellValueFactory(new PropertyValueFactory<>("firstname"));
            col_prenom.setCellValueFactory(new PropertyValueFactory<>("lastname"));
            col_DateRec.setCellValueFactory(new PropertyValueFactory<>("dateR"));
            //Action.setCellValueFactory(new PropertyValueFactory<>("button"));
            TableViewRec.setItems(oblistClient);
      
     }
     
   @FXML
    private void ModifierEtat(ActionEvent event) throws SQLException {
        ServiceReclamation sr=new ServiceReclamation();
        //sr.updateAdmin(Integer.parseInt(numeroRec.getText()),etat.getValue());
	AfficherListeReclamations();		
            
    }

    /* public void getEtat(){
    // oblist=TableViewRec.getSelectionModel().getSelectedItems();
     String etatr=oblist.get(0).getEtatr();
     int idr=oblist.get(0).getIdR();
    System.out.println(oblist);
      //etat.setValue(etatr);
            
                    }*/
     
     private void RechercherReclamation(){
         FilteredList<ReclamationClient>filteredData=new FilteredList<>(oblistClient,b -> true);
            TextField.setOnKeyReleased(e->{
        TextField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate((Predicate<? super ReclamationClient>)ReclamationClient -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (ReclamationClient.getEtatr().toLowerCase().contains(lowerCaseFilter) ) {
					return true; // Filter matches first name.
				} else if (ReclamationClient.getType().toLowerCase().contains(lowerCaseFilter)) {
					return true; }// Filter matches last name.
				//else if (Reclamation.getIdR().contains(newValue)){
				   // return true;}
				      
				    	 return false; // Does not match.
			});
		});
        SortedList<ReclamationClient>soretedData=new SortedList<>(filteredData);
        soretedData.comparatorProperty().bind(TableViewRec.comparatorProperty());
        TableViewRec.setItems(soretedData);
            });
     }

    @FXML
    private void BoutonRetour(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("/com/esprit/Gui/ListesReclamations.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    }

    @FXML
    private void changerEtat(MouseEvent event)  {
        
        oblistClient = TableViewRec.getSelectionModel().getSelectedItems();
        String objet = oblistClient.get(0).getObjet();
        String description= oblistClient.get(0).getDescription();
        String categorie=oblistClient.get(0).getType();
        String nom=oblistClient.get(0).getFirstname();
        String prenom=oblistClient.get(0).getLastname();
        int id=oblistClient.get(0).getIdR();
        
         FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("/com/esprit/Gui/ReclamationChaqueClient.fxml"));
            try {
                Parent root = loader.load();
                ReclamationChaqueClientController apc = loader.getController();
                apc.setObjetTxtField(objet);
                apc.setDescription(description);
                apc.setCategorie(categorie);
                apc.setIdClient(id);
                apc.setNomPrenom(nom,prenom);
                TableViewRec.getScene().setRoot(root);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
                }
       
        
        //String objet = oblistClient.get(0).getObjet();
        //String description= oblistClient.get(0).getDescription();
        /*Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/com/esprit/Gui/ReclamationChaqueClient.fxml"));
            Scene scene = new Scene(root);
            stageAffichageUnique.setScene(scene);
            stageAffichageUnique.show();
        } catch (IOException ex) {
            Logger.getLogger(ListerReclamationClientController.class.getName()).log(Level.SEVERE, null, ex);
        }*/
                        
        //This line gets the Stage information
        //Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
       // window.setScene(tableViewScene);
        //window.show();
      // FXMLLoader.load(getClass().getResource("/com/esprit/Gui/ReclamationChaqueClient.fxml"));
            
        /*etat.setValue(oblistClient.get(0).getEtatr());
        numeroRec.setText(String.valueOf(oblistClient.get(0).getIdR()));
              TrayNotification tray =new TrayNotification();
            tray.setTitle("Erreur");
        tray.setMessage("La réclamation a été déjà traitée");
        tray.setAnimationType(AnimationType.POPUP);
        tray.setNotificationType(NotificationType.INFORMATION);
        tray.showAndWait();*/
    }
     
        
        
    }
    
   
  


       



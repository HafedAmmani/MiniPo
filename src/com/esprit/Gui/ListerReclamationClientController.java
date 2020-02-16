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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author darra
 */
public class ListerReclamationClientController implements Initializable {
      private Connection con;
      private Statement ste;
    @FXML
    private ComboBox<String> RechCmbBox;
    ObservableList<String> listeTypeRecherche = FXCollections.observableArrayList("Tout", "idr", "description", "dater", "etatr", "idclt", "idcmd");
    //ObservableList<String> listeTypeRechercheMembre = FXCollections.observableArrayList("Objet", "Message", "Status");
    @FXML
    private TextField TextField;
    @FXML
    private HBox Hbox;
    @FXML
    private TableView<Reclamation> TableViewRec;
    private ObservableList<Reclamation>oblist;
    private DataBase db;
    /**
     * Initializes the controller class.
     
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
        Connection conn=db.getConnection();
        oblist=FXCollections.observableArrayList();
          
              ResultSet rs = conn.createStatement().executeQuery("select * from reclamation;");
              while(rs.next()){
               oblist.add(new Reclamation(rs.getInt(1),rs.getString(2), rs.getDate(3), rs.getString(4), rs.getInt(6),rs.getInt(6)));
              
              
              }
              
              
              
              
              
              
              
              
          } catch (SQLException ex) {
              Logger.getLogger(ListerReclamationClientController.class.getName()).log(Level.SEVERE, null, ex);
          }
        TableViewRec.setItems(null);
        TableViewRec.setItems(oblist);
       RechCmbBox.setItems(listeTypeRecherche);
       RechCmbBox.setValue("Tout");
      
     
          
         
       //Nommer les colonnes de la TableView 
       TableColumn<Reclamation, Integer> idColumn = new TableColumn<>("ID");
       //TableViewRec.getColumns().setAll(idColumn);
       idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
       idColumn.setStyle("-fx-alignment: CENTER;");
       
       TableColumn<Reclamation, String> decriptionColumn = new TableColumn<>("Description");
       idColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
       idColumn.setStyle("-fx-alignment: CENTER;");
       
       TableColumn<Reclamation, Date> daterecColumn = new TableColumn<>("date de la reclamation ");
       idColumn.setCellValueFactory(new PropertyValueFactory<>("dater "));
       idColumn.setStyle("-fx-alignment: CENTER;");
       
       TableColumn<Reclamation, String> etatcColumn = new TableColumn<>("Etat ");
       idColumn.setCellValueFactory(new PropertyValueFactory<>("etatr "));
       idColumn.setStyle("-fx-alignment: CENTER;");
       
       TableColumn<Reclamation, Integer> idcltColumn = new TableColumn<>("Id client  ");
       idColumn.setCellValueFactory(new PropertyValueFactory<>("idclt "));
       idColumn.setStyle("-fx-alignment: CENTER;");
       
       TableColumn<Reclamation, Integer> idcmdColumn = new TableColumn<>("Id commande ");
       idColumn.setCellValueFactory(new PropertyValueFactory<>("idclt "));
       idColumn.setStyle("-fx-alignment: CENTER;");
       
       TableViewRec.getColumns().setAll(idColumn, decriptionColumn,daterecColumn,etatcColumn,idcltColumn,idcmdColumn );
       TableViewRec.setItems(null);
        TableViewRec.setItems(oblist);
       /*Callback<TableColumn<Reclamation, Integer>, TableCell<Reclamation, Integer>> cellFactoryRef
                =
               
               new Callback<TableColumn<Reclamation, Integer>, TableCell<Reclamation, Integer>>() {
           @Override
           public TableCell<Reclamation, Integer> call(TableColumn<Reclamation, Integer> param) {
               final TableCell<Reclamation, Integer> cell = new TableCell<Reclamation, Integer>() {
                    @Override
                    public void updateItem(Integer item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            
                        }
                    }
               }
           }
               return cell;
    };
    
    
    
    /*public static ObservableList<Reclamation>getAllRecords() throws SQLException{
        String requete ="select * from Reclamation;";
        
        ResultSet rs=ste.executeQuery(sql);
}*/
}}
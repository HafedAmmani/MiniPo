/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.Gui;

import com.esprit.Entite.Commandes;
import com.esprit.Service.ServiceCommande;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class ListeCmdClientController implements Initializable {

    private AnchorPane ligne;
    
    private ObservableList<Commandes> oblist=FXCollections.observableArrayList();
    @FXML
    private AnchorPane anchorpane_parent;
    @FXML
    private Button Acceuil;
    @FXML
    private Button panier;
    @FXML
    private TableView tabcom;
    @FXML
    private TableColumn<Object, ?> col_non;
    @FXML
    private TableColumn<Object, ?> col_pre;
    @FXML
    private TableColumn<Object, ?> col_dat;
    @FXML
    private TableColumn<Object, ?> col_et;
    @FXML
    private TableColumn<Object, ?> col_tot;
    @FXML
    private TableColumn<Object, ?> col_id;
    @FXML
    private TextField tfrech;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       /* 
        ServiceLigneCommande slc=new ServiceLigneCommande();
         oblist = slc.ListerPannier();
         VBox vg=new VBox();
         for(int i=0;i<oblist.size();i++){
             
             ImageView img=new ImageView("D:\\Esprit\\3A7\\Semestre_2\\PI\\Sprint_JAVA\\load.png");
             Text pp=new Text("Prix: ");
             Text qq=new Text("Quantité: ");
             Text d=new Text(oblist.get(i).getDesignation());
             Text c=new Text(oblist.get(i).getNom());
             Text p=new Text(String.valueOf(oblist.get(i).getPrix()));
             Spinner<Integer> q=new Spinner<>();
             Button btn=new Button("Ajouter");
             VBox vb1=new VBox(d,c);
             VBox vb2=new VBox(pp,qq);
             VBox vb3=new VBox(p,q);
             HBox hb=new HBox();
             List <Node> chld=new ArrayList<Node>();
             chld.add(img);
             chld.add(vb1);
             chld.add(vb2);
             chld.add(vb3);
             chld.add(btn);
             hb.getChildren().addAll(oblist.get(i).getIdLc(),chld);
             vg.getChildren().add(i,hb);
             
         }
         tab.getChildren().add(vg);
         tab.isVisible(); */
        
    }    
private void AfficherCommandes(){
        
        try{
        
        
        
        ServiceCommande sc=new ServiceCommande();
        oblist = sc.Commandes();
        
        col_id.setCellValueFactory(new PropertyValueFactory ("idCmd"));
        col_non.setCellValueFactory(new PropertyValueFactory("nomClt"));
        col_pre.setCellValueFactory(new PropertyValueFactory("prenomClt"));
        col_dat.setCellValueFactory(new PropertyValueFactory("datec"));
        col_et.setCellValueFactory(new PropertyValueFactory("etatc"));
        col_tot.setCellValueFactory(new PropertyValueFactory("total"));
       
        tabcom.setItems(oblist);

        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    
    } 

    
     private void RechercherCommandes(){
         FilteredList<Commandes>filteredData=new FilteredList<>(oblist,b -> true);
            tfrech.setOnKeyReleased(e->{
        tfrech.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate((Predicate<? super Commandes>)Commande -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (Commande.getEtatc().toLowerCase().contains(lowerCaseFilter) ) {
					return true; // Filter matches first name.
				} else if (Commande.getDatec().toString().toLowerCase().contains(lowerCaseFilter)) {
					return true; }// Filter matches last name.
				//else if (Reclamation.getIdR().contains(newValue)){
				   // return true;}
				      
				    	 return false; // Does not match.
			});
		});
        SortedList<Commandes>soretedData=new SortedList<>(filteredData);
        soretedData.comparatorProperty().bind(tabcom.comparatorProperty());
        tabcom.setItems(soretedData);
            });
     }

    @FXML
    private void AcceuilAction(ActionEvent event) {
    }

    @FXML
    private void PanierAction(ActionEvent event) {
    }
    
    
}

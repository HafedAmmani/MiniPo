/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui;

import com.esprit.Entite.Conge;
import com.esprit.Service.ServiceConge;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author hafed
 */
public class DemandeCongeController implements Initializable {

    @FXML
    private Button btn_envoyer;
    @FXML
    private ComboBox<String> type;
    @FXML
    private DatePicker datedebut;
    @FXML
    private DatePicker fil_datefin;
    @FXML
    private TextField nbjrs;
    @FXML
    private TextArea desc;
    private String typeC [] = {"Maladie", "vacance"};
    
    int nbr;
    ServiceConge ser = new ServiceConge();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initType();
    }    
    private void initType() {
		List<String> list = new ArrayList<String>();

		// foreach loop
		for(String content:typeC) {
			list.add(content);
		}
		
		// convert list to observable list
		ObservableList obList = FXCollections.observableArrayList(list);
		type.setItems(obList);

	}
    @FXML
    private void ajouterconge() throws SQLException{
        
        String ty = type.getSelectionModel().getSelectedItem();

        int nb = Integer.parseInt(nbjrs.getText());
        String descr = desc.getText();
        boolean etat = false;
        Conge g1;
        g1 = new Conge(ty,((TextField)datedebut.getEditor()).getText(),((TextField)fil_datefin.getEditor()).getText(),nb,descr,etat);
        ser.ajouter(g1);
        
    }
    
}

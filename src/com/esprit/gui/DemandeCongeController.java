/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui;

import com.esprit.Entite.Conge;
import com.esprit.Service.ServiceConge;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
    private void redirectToRecEmp(ActionEvent event) throws IOException {
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("ReclamationEmploye.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    }
    
    @FXML
    private void ajouterconge() throws SQLException{
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Employe enregistrer avec succes.");
		alert.setHeaderText(null);
                String ty1 = type.getSelectionModel().getSelectedItem();

        if(validate("Type", ty1, "[a-zA-Z]+") && 
                emptyValidation(((TextField)datedebut.getEditor()).getText(),false)
              )  
    	   {
        String ty = type.getSelectionModel().getSelectedItem();

        int nb = Integer.parseInt(nbjrs.getText());
        String descr = desc.getText();
        boolean etat = false;
        Conge g1;
        g1 = new Conge(ty,((TextField)datedebut.getEditor()).getText(),((TextField)fil_datefin.getEditor()).getText(),nb,descr,etat);
        ser.ajouter(g1);
        alert.setContentText("La demande est envoye avec succes.");
            alert.showAndWait();
           }
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
	Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Validation Error");
        alert.setHeaderText(null);
        if(field.equals("Role")) alert.setContentText("Please Select "+ field);
        else{
        	if(empty) alert.setContentText("Please Enter "+ field);
        	else alert.setContentText("Please Enter Valid "+ field);
        }
        alert.showAndWait();
	} 
    
}

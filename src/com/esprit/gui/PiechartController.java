/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.Gui;

import com.esprit.Service.ServiceReclamation;
import com.esprit.Service.ServiceReclamationEmploye;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;

/**
 * FXML Controller class
 *
 * @author darra
 */
public class PiechartController implements Initializable {

    @FXML
    private PieChart piechart;
     ServiceReclamation sr=new ServiceReclamation();
     ServiceReclamationEmploye sre=new ServiceReclamationEmploye();
    @FXML
    private PieChart piechartemp;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            statistique() ;
            statistiqueEmploye();
            
            // TODO
        } catch (SQLException ex) {
            Logger.getLogger(PiechartController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    public void statistique() throws SQLException{
        
        ResultSet rs=null;
        rs=sr.Stat();
        piechart.getData().clear();
        while(rs.next()){
         piechart.getData().add(new PieChart.Data(rs.getString(1),rs.getDouble(2)));
        }}
        public void statistiqueEmploye() throws SQLException{
        
        ResultSet rs=null;
        rs=sre.Stat();
        piechartemp.getData().clear();
        while(rs.next()){
         piechartemp.getData().add(new PieChart.Data(rs.getString(1),rs.getDouble(2)));
        }
        
    }
    
}

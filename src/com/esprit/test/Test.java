/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.test;

import com.esprit.Entite.Reclamation;
import com.esprit.Entite.Reclamationemploye;
import com.esprit.Service.ServiceReclamation;
import com.esprit.Service.ServiceReclamationEmploye;
import com.esprit.Utils.DataBase;
import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author House
 */
public class Test {
    
    public void start(Stage stage) throws IOException{
        Parent root=FXMLLoader.load(getClass().getResource("/Gui/chart.fxml"));
        Scene scene = new Scene(root);
        //Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        
        
    }
    
    public static void main(String[] args) throws SQLException {
        launch(args);
        ServiceReclamation ser = new ServiceReclamation();
        
        Reclamation p1 = new Reclamation(1,1,1,"probleme de ....","en cours ");
        Reclamation p2 = new Reclamation(2,2,2,"bbbbbbbb","en cours ");
        Reclamation p3 = new Reclamation(3,3,3,"ccccc","en cours ");
        Reclamation p4 = new Reclamation(4,4,4,"ddddd","en cours ");
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        java.sql.Date d2 = new java.sql.Date(2020, 02, 12);
        p1.setDateR(d2);
        p2.setDateR(d2);
        p3.setDateR(d2);
        p4.setDateR(d2);
        //ser.ajouterReclamation(p1);
        //ser.ajouterReclamation(p2);
        //ser.ajouterReclamation(p3);
        //ser.ajouterReclamation(p4);
        //ser.updateAdmin(p1, 1, "valider");
        //ser.updateClient(p1, 1, "aaaaaaa");
        //ser.updateAdmin(p2, 2, "valider");
        //ser.readAll();
        //System.out.println(ser.readAll());
        //ser.ChercherReclamationParIdId(1);
        //System.out.println(ser.ChercherReclamationParIdId(2));
        //System.out.println(ser.ChercherReclamationParEtat("valider"));
         //System.out.println(ser.ChercherReclamationParEtat("en cours"));
         //System.out.println(ser.ChercherReclamationParIdcltetEtat(4,"en cours"));
         
        //ser.ChercherReclamationParId(1);
        //ser.Delete(1);
        
        ServiceReclamationEmploye seremp = new ServiceReclamationEmploye();
        Reclamationemploye e1=new Reclamationemploye(1,1,"Probleme de ...","en cours");
        Reclamationemploye e2=new Reclamationemploye(2,2,"aaaaaa","en cours");
        Reclamationemploye e3=new Reclamationemploye(3,3,"bbbbbbbbbb","valider");
         Reclamationemploye e4=new Reclamationemploye(4,4,"cccccccccc.","valider");
        SimpleDateFormat sd1 = new SimpleDateFormat("yyyy-MM-dd");
        java.sql.Date d1 = new java.sql.Date(2020, 02, 12);
        e1.setDateRemp(d1);
        e2.setDateRemp(d2);
        e3.setDateRemp(d2);
        e4.setDateRemp(d2);
        //seremp.ajouterReclamationEmploye(e1);
        //seremp.ajouterReclamationEmploye(e2);
        //seremp.ajouterReclamationEmploye(e3);
        //seremp.ajouterReclamationEmploye(e4);
        //seremp.Delete(0);
        //seremp.updateEmploye(3, "ababababab");
         // seremp.updateAdmin(3, "en cours");
        System.out.println (seremp.readAll());
        
        
    }
        
        
        //Reclamation p2 = new Reclamation("Karray", "Gargouri", 10);
        
       /* try {
//         
            ser.ajouterReclamation(p1);
            ser.ajouter(p1);
            /*
            List<Reclamation> list = ser.readAll();
            System.out.println(list);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }*/
    
    
    
}

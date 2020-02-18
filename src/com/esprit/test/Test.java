/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.test;


import com.esprit.Service.ServicePersonne;
import com.esprit.Utils.DataBase;
import java.sql.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.esprit.Entite.User;

/**
 *
 * @author House
 */
public class Test {

    public static void main(String[] args) throws SQLException {
        ServicePersonne ser = new ServicePersonne();
        User u=new User( "safabensalah", "safa.bensalah@esprit.com","1234", "client", "safa","ben salah","femme");
       // client p2 = new client("4545", "hiba",1, "ben ahmed", "457756", "tunis", "@hiba");
        
        //u.setPassword("ggggggggg");
           //         try {
               //ser.Modifier("0258963","ahmed",5,"ben ahmed","grand tunis","2569348","@ahmed","esprit","esprit");
              //          System.out.println(u);
          //   ser.ajouter(u);            
             //ser.ajouter(p2);
             //ser.listerClt();
             //ser.RechercheById(5);
             //ser.Delete(1);
          //         } catch (SQLException ex) {
          //  System.out.println(ex);
        }
   
  //  }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.Service;

import com.esprit.Entite.Categorie;
import com.esprit.Utils.DataBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lenovo
 */
public class ServiceCategorie {
    
    private Connection con;
    private PreparedStatement pre;
    private Statement ste;

    public ServiceCategorie() {
        
         con = DataBase.getInstance().getConnection();
    }
    
    public Categorie getCategorie(int idcateg){
    Categorie categ=null;
        try {
        ste=con.createStatement();
        ResultSet rs=ste.executeQuery("select * from categorie where idcateg="+idcateg);
        
        while (rs.next()) {  
  
            categ=new Categorie(rs.getInt("idcateg"),rs.getString("nom"));  
            return categ;
        }
        
        
    
    }  catch (SQLException ex) {
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
            categ=null;
        }
        
        return categ;
    
    }
    
}

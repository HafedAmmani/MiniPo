/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.Service;

import com.esprit.Entite.Fournisseur;
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
public class ServiceFournisseur {
    
    private Connection con;
    private PreparedStatement pre;
    private Statement ste;

    public ServiceFournisseur() {
        
         con = DataBase.getInstance().getConnection();
    }
    
    public Fournisseur getFournisseur(int idf) {
        
        Fournisseur f=null;
        try {
        ste=con.createStatement();
        ResultSet rs=ste.executeQuery("select * from fournisseur where idf="+idf);
        
        while (rs.next()) {  
  
            f=new Fournisseur(rs.getInt("idf"),rs.getString("nom"),rs.getString("adresse"),rs.getString("tel"),rs.getString("email"));  
            return f;
        }
        
        
    
    }  catch (SQLException ex) {
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
            f=null;
        }
        
        return f;
    
    }
    
}

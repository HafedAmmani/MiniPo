/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.Service;

import com.esprit.Entite.User;
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
public class ServicePersonne {
    
    private Connection con;
    private PreparedStatement pre;
    private Statement ste;

    public ServicePersonne() {
        
         con = DataBase.getInstance().getConnection();
    }
    
    public User getUser(int idclt) {
        
        User clt=null;
        try {
        ste=con.createStatement();
        ResultSet rs=ste.executeQuery("select * from user where id="+idclt);
        
        while (rs.next()) {  
  
            clt=new User(rs.getInt("id"),rs.getString("username"),rs.getString("password"),
                    rs.getString("nom"),rs.getString("prenom"),rs.getString("adresse"),
                    rs.getString("email"));  
            return clt;
        }
        
        
    
    }  catch (SQLException ex) {
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
            clt=null;
        }
        
        return clt;
    
    }
    
}

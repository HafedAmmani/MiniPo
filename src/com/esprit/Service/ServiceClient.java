/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.Service;

import com.esprit.Entite.Client;
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
public class ServiceClient {
    
    private Connection con;
    private PreparedStatement pre;
    private Statement ste;

    public ServiceClient() {
        
         con = DataBase.getInstance().getConnection();
    }
    
    public Client getClient(int idclt) {
        
        Client clt=null;
        try {
        ste=con.createStatement();
        ResultSet rs=ste.executeQuery("select * from client where idclt="+idclt);
        
        while (rs.next()) {  
  
            clt=new Client(rs.getInt("idclt"),rs.getString("login"),rs.getString("password"),rs.getString("cin"),
                    rs.getString("nom"),rs.getString("prenom"),rs.getString("adresse"),rs.getString("tel"),
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

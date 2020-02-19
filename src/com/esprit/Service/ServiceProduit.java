/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.Service;

import com.esprit.Entite.Categorie;
import com.esprit.Entite.Fournisseur;
import com.esprit.Entite.Produit;
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
public class ServiceProduit {
    
    private Connection con;
    private PreparedStatement pre;
    private Statement ste;

    public ServiceProduit() {
        
         con = DataBase.getInstance().getConnection();
    }
    
    
    public Produit getProduit(int idprod) {
        Produit pp;
        pp = null;
        try {
        ste=con.createStatement();
        ResultSet rsp=ste.executeQuery("select * from produit where idprod="+idprod);
        
        while (rsp.next()) {  
            
            ServiceCategorie sc=new ServiceCategorie();
            Categorie c=sc.getCategorie(rsp.getInt("idcateg"));
            
            ServiceFournisseur sf=new ServiceFournisseur();
            Fournisseur f=sf.getFournisseur(rsp.getInt("idf"));
               
            pp=new Produit(rsp.getInt("idprod"),rsp.getString("designation"),rsp.getFloat("prix"),rsp.getInt("qtestock"),c,f);  
            return pp;
        }
        
        
    
    }  catch (SQLException ex) {
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
            pp=null;
        }
        
        return pp;
    
    }
    
    public void modifierProduit(Produit p) {
        try {
            PreparedStatement pre=con.prepareStatement("UPDATE produit SET qtestock=? where idprod="
                +p.getIdprod()+";");
        
            pre.setFloat(1,p.getQtestock());
           
            pre.executeUpdate();
        System.out.println("Commande Modifier avec succes");
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    
}

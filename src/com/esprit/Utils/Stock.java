/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.Utils;

import com.esprit.Entite.Commande;
import com.esprit.Entite.LigneCommande;
import com.esprit.Entite.Produit;
import com.esprit.Service.ServiceCommande;
import com.esprit.Service.ServiceProduit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lenovo
 */
public class Stock {
    
    private Connection con;
    private PreparedStatement pre;
    private Statement ste;
    

    public Stock() {
       con = DataBase.getInstance().getConnection();
    }
    
    public Produit v(int idprod){
        
        ServiceProduit sp=new ServiceProduit();
        Produit p=sp.getProduit(sp.getProduit(idprod).getIdprod());
        
        return p;
    
    }
    public boolean verifierStock(int idprod){
        try{
            ste=con.createStatement();
            ResultSet rs=ste.executeQuery("select qte from lignecommande where idprod="+idprod);
            int q=0;
            while (rs.next()) {                

                q=q+rs.getInt("qte");
            }
            
            ServiceProduit sp=new ServiceProduit();
            Produit p=sp.getProduit(sp.getProduit(idprod).getIdprod());
            
            System.out.println("qteAcheté:"+q);
            System.out.println("qteEnStock:"+p.getQtestock());
            return q<=p.getQtestock();
            
        }catch (SQLException ex) {
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    
    
    }
    
}

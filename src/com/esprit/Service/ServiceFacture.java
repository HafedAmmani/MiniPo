/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.esprit.Service;

import com.esprit.Entite.Commande;
import com.esprit.Entite.Facture;
import com.esprit.Utils.DataBase;
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
public class ServiceFacture {
    
    private Connection con;
    private PreparedStatement pre;
    private Statement ste;
    

    public ServiceFacture() {
       con = DataBase.getInstance().getConnection();
    }
    
    public void ajouterFacture(Facture f) {
        try{
            ServiceCommande sc=new ServiceCommande();
            Commande c=sc.getCommande(f.getCommande().getIdcmd());
            if(c.getEtatc().equals("validée")){
                PreparedStatement pre=con.prepareStatement("INSERT INTO facture (`datef`,`etatf`,`idcmd`) "
                        + "VALUES (?, ?, ?);");
                pre.setDate(1, f.getDateFact());
                pre.setString(2, f.getEtatFact());
                pre.setInt(3, f.getCommande().getIdcmd());
                pre.executeUpdate();
                System.out.println("Facture ajouter avec succes");
            }
            else{
                System.out.println("Vous devez valider votre commande svp");
            }
            
        }catch (SQLException ex) {
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
     
    public void modifierFacture(Facture f){
    
        try{
            PreparedStatement pre=con.prepareStatement("UPDATE facture SET datef=?,etatf=?,idcmd=? where idfact="
                    +f.getIdfact()+";");
            
            pre.setDate(1, f.getDateFact());
            pre.setString(2, f.getEtatFact());
            pre.setInt(3, f.getCommande().getIdcmd());
            pre.executeUpdate();
            System.out.println("Facture Modifier avec succes");
        }catch (SQLException ex) {
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    public void supprimerFacture(Facture f){
       
        try{
            PreparedStatement pre=con.prepareStatement("DELETE FROM commande where idcmd="+f.getIdfact()+";");
            pre.execute();
            System.out.println("Facture Supprimer avec succes");
        }catch (SQLException ex) {
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    public List<Facture> afficherFacture(){
    
        try{
            List<Facture> arr=new ArrayList<>();
            ste=con.createStatement();
            ResultSet rs=ste.executeQuery("select * from facture");
            while (rs.next()) {                

               ServiceCommande sc=new ServiceCommande();
               Commande c=sc.getCommande(rs.getInt("idcmd"));
               
               Facture f=new Facture(rs.getInt("idfact"),rs.getDate("datef"),rs.getString("etatf"),c);
               
               arr.add(f);
            }
            return arr;
         }catch (SQLException ex) {
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    
    }
    
    
}

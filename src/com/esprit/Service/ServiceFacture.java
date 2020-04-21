/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.esprit.Service;

import com.esprit.Entite.Commande;
import com.esprit.Entite.Facture;
import com.esprit.Entite.ListeFact;
import com.esprit.Entite.Panier;
import com.esprit.Entite.User;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

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
                pre.setString(2,"non payée");
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
    
 //*******************************************************************************************************************   
    public ObservableList<ListeFact> AllFactures(){
       
        
        ObservableList oblist = FXCollections.observableArrayList();
        try { 
            
            ste=con.createStatement();
            /*ResultSet rs=ste.executeQuery("SELECT f.idfact,f.datef,f.etatf,cmd.idcmd,c.id,c.Firstname,c.Lastname,"
                                          + "cmd.total,cmd.refc "
                                          + "from facture f ,commande cmd,user c "
                                          + "where f.idcmd=cmd.idcmd "
                                          + "And cmd.id=c.id;"
                                          + "And c.id="+clt.getId()
                                          + ";");*/
            
            ResultSet rs=ste.executeQuery("SELECT f.idfact,f.datef,f.etatf,cmd.idcmd,c.id,c.Firstname,c.Lastname,"
                    + "cmd.total,cmd.refc " 
                    +  "from facture f ,commande cmd,user c " 
                    +  "where f.idcmd=cmd.idcmd " 
                    +  "And cmd.id=c.id;");
            while (rs.next()) {              
                
               
               oblist.add(new ListeFact(rs.getInt("idfact"), rs.getDate("datef"), rs.getString("etatf"), 
                       rs.getString("firstname"), rs.getString("lastname"), rs.getString("refc"),
                       rs.getFloat("total"),rs.getInt("id"), rs.getInt("idcmd")));

            }
           
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
           
        }   
        
        System.out.println(oblist);
     return oblist;
    }
    
     public ObservableList<ListeFact> FacturesClt(int idUser){
       
        
        ObservableList oblist = FXCollections.observableArrayList();
        try { 
            
            ste=con.createStatement();
            ResultSet rs=ste.executeQuery("SELECT f.idfact,f.datef,f.etatf,cmd.idcmd,c.id,c.Firstname,c.Lastname,"
                                        + "cmd.total,cmd.refc " 
                                        + "from facture f ,commande cmd,user c " 
                                        + "where f.idcmd=cmd.idcmd " 
                                        + "And cmd.id=c.id " 
                                        + "And c.id="+idUser
                                        + " ;");
            
          
            while (rs.next()) {              
                
               
               oblist.add(new ListeFact(rs.getInt("idfact"), rs.getDate("datef"), rs.getString("etatf"), 
                       rs.getString("firstname"), rs.getString("lastname"), rs.getString("refc"),
                       rs.getFloat("total"),rs.getInt("id"), rs.getInt("idcmd")));

            }
           
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
           
        }   
        
        System.out.println(oblist);
     return oblist;
    }
    
    public Facture getFacture(int idFact) {
        Facture ff;
        ff = null;
        try {
        ste=con.createStatement();
        ResultSet rs=ste.executeQuery("select * from Facture where idfact="+idFact);
        while (rs.next()) { 
            
            ServiceCommande sc=new ServiceCommande();
            Commande c=sc.getCommande(rs.getInt("idcmd"));
            
            ff=new Facture(rs.getInt("idFact"),rs.getDate("datef"),rs.getString("etatf"),c);  
            return ff;
        }
        
        
    
        }catch (SQLException ex) {
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
            ff=null;
        }
        
        return ff;
    
    }
    
    public ObservableList<Panier> detailFact(Facture f)
    {
        
        
        ObservableList oblist = FXCollections.observableArrayList();
                
        try {
         
            ste=con.createStatement();
            ResultSet rs=ste.executeQuery("select p.designation,p.prix,c.nom,l.qte,l.idlc,l.idcmd,l.subtotal " +
                                   "FROM lignecommande l " +
                                   "join produit p on l.idprod=p.idprod " +
                                   "JOIN categorie c oN p.idcateg=c.idcateg " +
                                   "join commande cmd on l.idcmd=cmd.idcmd " +
                                   "WHERE cmd.idcmd="+f.getCommande().getIdcmd()+";");
            
            while (rs.next()) {
                
                int id=rs.getInt("idlc");
                String des= rs.getString("designation");
                float p= rs.getFloat("prix");
                String n= rs.getString("nom");
                int q= rs.getInt("qte");
                int idcmd= rs.getInt("idcmd");
                float tot=rs.getFloat("subtotal");
                
                
                oblist.add(new Panier(id,des,p,n,q,idcmd,tot));
 
            }
            
            
            
            //return oblist;
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceLigneCommande.class.getName()).log(Level.SEVERE, null, ex);
            TrayNotification tray =new TrayNotification();
            tray.setTitle("Erreur");
            tray.setMessage(ex.getMessage());
            tray.setAnimationType(AnimationType.POPUP);
            tray.setNotificationType(NotificationType.INFORMATION);
            tray.showAndWait();
           
        }
        
        System.out.println("obliste:**************\n"+oblist);
        return oblist;
           
}
    
}

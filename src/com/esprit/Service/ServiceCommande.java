/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.esprit.Service;

import com.esprit.Entite.Client;
import com.esprit.Entite.Commande;
import com.esprit.Entite.Commandes;
import com.esprit.Gui.AcceuilController;
import com.esprit.Utils.DataBase;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 *
 * @author Lenovo
 */
public class ServiceCommande {
    
    private Connection con;
    private PreparedStatement pre;
    private Statement ste;
    

    public ServiceCommande() {
       con = DataBase.getInstance().getConnection();
    }
    
    public void ajouterCommande(Commande c) {
        try {
            
            if (c.getEtatc().equals("valideé")){
            PreparedStatement pre=con.prepareStatement("INSERT INTO commande (`total`, `datec`,`etatc`,idclt) VALUES "
                    + "(?, ?, ?, ?);");
            pre.setFloat(1, c.getTotal());
            pre.setDate(2, c.getDatec());
            pre.setString(3, c.getEtatc());
            pre.setInt(4, c.getClient().getId());
            pre.executeUpdate();
            System.out.println("Commande ajouter avec succes");
            }
            else{
            System.out.println("Ajout echouée : validez votre cammande svp");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    public void modifierCommande(Commande c) {
        try {
            PreparedStatement pre=con.prepareStatement("UPDATE commande SET total=?,datec=?,etatc=?,idclt=? where idcmd="
                +c.getIdcmd()+";");
        
            pre.setFloat(1, c.getTotal());
            pre.setDate(2, c.getDatec());
            pre.setString(3, c.getEtatc());
            pre.setInt(4, c.getClient().getId());
            pre.executeUpdate();
        System.out.println("Commande Modifier avec succes");
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    public void modifierEtatCommande(int id) {
        try {
            PreparedStatement pre=con.prepareStatement("UPDATE commande SET etatc='valide' where idcmd="
                +id+";");
            pre.executeUpdate();
        System.out.println("Commande Modifier avec succes");
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
   public void supprimerCommande(Commande c){
    try {    
        PreparedStatement pre=con.prepareStatement("DELETE FROM commande where idcmd="+c.getIdcmd()+";");
        pre.execute();
        System.out.println("Commande Supprimer avec succes");
    } catch (SQLException ex) {
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Commande> afficherCommandes(){
        
        try { 
            List<Commande> arr=new ArrayList<>();
        
            ste=con.createStatement();
            ResultSet rs=ste.executeQuery("select * from commande");
            while (rs.next()) {              
            
                ServiceClient sc=new ServiceClient();
                Client clt=sc.getClient(rs.getInt("idclt"));
               
                Commande c=new Commande(rs.getInt("idcmd"),rs.getDate("datec"),rs.getFloat("total"),rs.getString("etatc"),clt);
               
                arr.add(c);
            }
            return arr;
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }    
    
    }
    
   public Commande getCommande(int idcmd) {
        Commande cc;
        cc = null;
        try {
        ste=con.createStatement();
        ResultSet rs=ste.executeQuery("select * from commande where idcmd="+idcmd);
        while (rs.next()) { 
            
            ServiceClient sc=new ServiceClient();
            Client clt=sc.getClient(rs.getInt("idclt"));
            
            cc=new Commande(rs.getInt("idcmd"),rs.getDate("datec"),rs.getFloat("total"),rs.getString("etatc"),clt);  
            return cc;
        }
        
        
    
        }catch (SQLException ex) {
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
            cc=null;
        }
        
        return cc;
    
    }
   
   public void ajouterPanier(Commande c) {
        try {

            PreparedStatement pre=con.prepareStatement("INSERT INTO commande (`total`, `datec`,`etatc`,`idclt`)"
                    + " VALUES (?, ?, ?, ?);");
            pre.setFloat(1, c.getTotal());
            pre.setDate(2, c.getDatec());
            pre.setString(3, "non valide");
            pre.setInt(4, c.getClient().getId());
            pre.executeUpdate();
            System.out.println("Commande ajouter avec succes");
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
   
   public Commande RechercherPanierParClient(int idclt){
        Commande c=null;
        ServiceClient sc=new ServiceClient();
        Client clt=sc.getClient(idclt);
        try {
            ste=con.createStatement();
            ResultSet rscmd=ste.executeQuery("select * from commande where etatc='non valide' and idclt="+idclt);
            while((rscmd.next())){
                c=new Commande(rscmd.getInt("idcmd"), rscmd.getDate("datec"),rscmd.getFloat("total"),rscmd.getString("etatc")
                    ,clt );
                return c;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
   return c;
   }
    
   public void validerCommande(int idclt){
   
       try {
           Commande c=RechercherPanierParClient(idclt);
           Statement ste=con.prepareStatement("UPDATE commande SET etatc='valide' where idcmd="+c.getIdcmd()+";");
           pre.executeUpdate();
           System.out.println("Commande a été valider avec succes");
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
   
   public void viderPannier(int idclt){
       
       try {  
           Commande c=RechercherPanierParClient(idclt);
           PreparedStatement pre=con.prepareStatement("DELETE FROM commande where idcmd="+c.getIdcmd()+";");
           pre.execute();
           System.out.println("Pannier Vide");
    } catch (SQLException ex) {
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
        }
   
   }
   
   
   public ObservableList<Commande>getCommandesValider(){
       ObservableList oblist = FXCollections.observableArrayList();
        try {
            
            ste=con.createStatement();
            
            ResultSet rs;
            rs = ste.executeQuery("SELECT * FROM commande WHERE etatc='valide';");
            while (rs.next()) {
                oblist.add(new Commande(rs.getInt("idcmd"),rs.getDate("datec"),rs.getFloat("total"),
                        rs.getString("idcmd"),AcceuilController.clt));
  
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceLigneCommande.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return oblist;
            
   }
   
   
   public ObservableList<Commandes> Commandes(){
       
        ObservableList oblist = FXCollections.observableArrayList();
        try { 
            
            ste=con.createStatement();
            ResultSet rs=ste.executeQuery("SELECT cmd.idcmd,cmd.idclt,c.nom,c.prenom,cmd.datec,cmd.etatc,cmd.total from commande cmd "
                    + "JOIN client c ON c.idclt=cmd.idclt where cmd.etatc='valide' Or cmd.etatc='Accepter' ;");
            while (rs.next()) {              
               DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");  
               String strDate = dateFormat.format(rs.getDate("datec")); 
               oblist.add(new Commandes(rs.getInt("idcmd"),rs.getString("nom"),rs.getString("prenom"),
                       strDate,rs.getString("etatc"),rs.getFloat("total")));

            }
           
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
           
        }    
     return oblist;
   }
   
   
    public void AccepterCommande(Commande c) {
        try {
            PreparedStatement pre=con.prepareStatement("UPDATE commande SET etatc=? where idcmd="
                +c.getIdcmd()+";");
            pre.setString(1,c.getEtatc());
            pre.executeUpdate();
            TrayNotification tray =new TrayNotification();
            tray.setTitle("Accepter");
            tray.setMessage("La commande est Acceptée !!!");
            tray.setAnimationType(AnimationType.POPUP);
            tray.setNotificationType(NotificationType.INFORMATION);
            tray.showAndWait();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
            TrayNotification tray =new TrayNotification();
            tray.setTitle("Accepter");
            tray.setMessage("Echec de l'acceptation !!!!");
            tray.setAnimationType(AnimationType.POPUP);
            tray.setNotificationType(NotificationType.INFORMATION);
            tray.showAndWait();
        }
    
    }
    
    public ObservableList<Commande>CommandesValider(){
       ObservableList oblist = FXCollections.observableArrayList();
        try {
            ste=con.createStatement();
            ResultSet rs = ste.executeQuery("SELECT cmd.idcmd,cmd.idclt,c.nom,c.prenom,cmd.datec,cmd.etatc,cmd.total from commande cmd JOIN client c ON c.idclt=cmd.idclt where cmd.etatc='valide';");
            while (rs.next()) {
                ServiceClient scl=new ServiceClient();
                Client clt=scl.getClient(rs.getInt("idclt"));
                oblist.add(new Commande(rs.getInt("idcmd"),rs.getDate("datec"),rs.getFloat("total"),
                        rs.getString("etatc"),clt));
  
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceLigneCommande.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return oblist;
            
   }
    
    /*public Commande findById(int id)
    {
         try {
            ste=con.createStatement();
            ResultSet rs = ste.executeQuery("SELECT *from commande where idcmd="+id+";");
            if (rs.next()) {
             return(new Commande(rs.getInt("idcmd"),rs.getString()))
            }
            }catch(IOException ex){
                    System.out.println(ex.getMessage());
                    
                    }
    
    
    }*/

           
}
        
        
        
    
 
    
    


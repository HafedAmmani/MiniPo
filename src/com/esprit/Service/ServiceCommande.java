/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.esprit.Service;

import com.esprit.Entite.User;
import com.esprit.Entite.Commande;
import com.esprit.Entite.Commandes;
import com.esprit.Entite.Panier;
import com.esprit.Entite.Produits;
import com.esprit.Gui.AcceuilController;
import com.esprit.Utils.DataBase;
import java.sql.Connection;
import java.sql.Date;
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
            
            if (c.getEtatc().equals("Validee")){
            PreparedStatement pre=con.prepareStatement("INSERT INTO commande (`total`, `datec`,`etatc`,id) VALUES "
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
            PreparedStatement pre=con.prepareStatement("UPDATE commande SET total=?,datec=?,etatc=?,id=? where idcmd="
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
            PreparedStatement pre=con.prepareStatement("UPDATE commande SET etatc='Validee' where idcmd="
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
            
                ServicePersonne sc=new ServicePersonne();
                User clt=sc.getUser(rs.getInt("id"));
               
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
            
            ServicePersonne sc=new ServicePersonne();
            User clt=sc.getUser(rs.getInt("id"));
            
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

            PreparedStatement pre=con.prepareStatement("INSERT INTO commande (`total`, `datec`,`etatc`,`id`, `refc`)"
                    + " VALUES (?, ?, ?, ?, ?);");
            pre.setFloat(1, c.getTotal());
            pre.setDate(2, c.getDatec());
            pre.setString(3, "Non Validee");
            pre.setInt(4, c.getClient().getId());
            pre.setString(5, "CmdX"+Math.random());
            pre.executeUpdate();
            System.out.println("Commande ajouter avec succes");
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
   
   public Commande RechercherPanierParClient(int idclt){
        
       Commande c=null;
        ServicePersonne sc=new ServicePersonne();
        User clt=sc.getUser(idclt);
        try {
            ste=con.createStatement();
            ResultSet rscmd=ste.executeQuery("select * from commande where etatc='Non Validee' and id="+idclt);
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
   
   
   public ObservableList<Commande>getCommandesValider(int idUser){
       ObservableList oblist = FXCollections.observableArrayList();
       ServicePersonne sc=new ServicePersonne();
       User clt=sc.getUser(idUser);
        try {
            
            ste=con.createStatement();
            
            ResultSet rs;
            rs = ste.executeQuery("SELECT * FROM commande WHERE etatc='Validee';");
            while (rs.next()) {
                oblist.add(new Commande(rs.getInt("idcmd"),rs.getDate("datec"),rs.getFloat("total"),
                        rs.getString("etatc"),clt));
  
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceLigneCommande.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return oblist;
            
   }
   
   
   public ObservableList<Commandes> Commandes(int idUser){
       
        ObservableList oblist = FXCollections.observableArrayList();
        try { 
            
            ste=con.createStatement();
            ResultSet rs=ste.executeQuery("SELECT cmd.idcmd,cmd.id,u.Firstname,u.Lastname,"
                    + "cmd.datec,cmd.etatc,cmd.total,cmd.refC "
                    + "from commande cmd "
                    + "JOIN user u ON u.id=cmd.id "
                    + "where u.id= "+idUser
                    + " And (cmd.etatc='Validee' Or cmd.etatc='Acceptee' Or cmd.etatc='Refusee');");
            while (rs.next()) {              
               DateFormat dateFormat = new SimpleDateFormat("yyyy/mm/dd");  
               String strDate = dateFormat.format(rs.getDate("datec"));
               oblist.add(new Commandes(rs.getInt("idcmd"),rs.getString("Firstname"),rs.getString("Lastname"),
                       strDate,rs.getString("etatc"),rs.getFloat("total"),rs.getString("refC")));
                System.out.println(oblist);

            }
           
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
           
        }    
     return oblist;
   }
   
   
    
    public void ArchiverCommande(Commande c) {
        try {
            PreparedStatement pre=con.prepareStatement("UPDATE commande SET etatc='Archivée' where idcmd="
                +c.getIdcmd()+";");
            
            pre.executeUpdate();
            TrayNotification tray =new TrayNotification();
            tray.setTitle("Suprimer");
            tray.setMessage("La commande est Supprimée !!!");
            tray.setAnimationType(AnimationType.POPUP);
            tray.setNotificationType(NotificationType.INFORMATION);
            tray.showAndWait();
        
        } catch (SQLException ex) {
            //Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
            
            TrayNotification tray =new TrayNotification();
            tray.setTitle("Suppression");
            tray.setMessage("Echec de la suppression !!!!");
            tray.setAnimationType(AnimationType.POPUP);
            tray.setNotificationType(NotificationType.INFORMATION);
            tray.showAndWait();
        }
    
    }
    
    public ObservableList<Commande>CommandesValider(){
       ObservableList oblist = FXCollections.observableArrayList();
        try {
            ste=con.createStatement();
            ResultSet rs = ste.executeQuery("SELECT cmd.idcmd,cmd.id,u.firstname,u.Lastname,cmd.datec,cmd.etatc,cmd.total from commande cmd JOIN user u ON u.id=cmd.id where cmd.etatc='Validee';");
            while (rs.next()) {
                ServicePersonne scl=new ServicePersonne();
                User clt=scl.getUser(rs.getInt("id"));
                oblist.add(new Commande(rs.getInt("idcmd"),rs.getDate("datec"),rs.getFloat("total"),
                        rs.getString("etatc"),clt));
  
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceLigneCommande.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return oblist;
            
   }

    
    public void deleteCmdClt(Commande c)
    {
        int idCmd=c.getIdcmd();
        try {
            ste=con.createStatement();

            
            ResultSet rs = ste.executeQuery("Select * from commande where idcmd="+idCmd+";");
            if(rs.first()){
                if(rs.getString("etatc").equals("Validee")||rs.getString("etatc").equals("Reffusee")){
                    //Supprimer facture
                    PreparedStatement pref=con.prepareStatement("DELETE FROM facture where idcmd="+idCmd+";");
                    pref.execute();
                    //Supprimer les Produits Commandees (LC)
                        PreparedStatement prelc=con.prepareStatement("DELETE FROM lignecommande where idcmd="+idCmd+";");
                        prelc.execute();
                    //Supprimer livraison
                    PreparedStatement prel=con.prepareStatement("DELETE FROM livraison where idc="+idCmd+";");
                    prel.execute();
                    //Supprimer Commande
                    PreparedStatement prec=con.prepareStatement("DELETE FROM commande where idcmd="+idCmd+";");
                    prec.execute();
                    
                    //Notification Commande Supprimer!!!
                    TrayNotification tray =new TrayNotification();
                    tray.setTitle("Suppression");
                    tray.setMessage("Suppression Effectuée avec succes !!!!");
                    tray.setAnimationType(AnimationType.POPUP);
                    tray.setNotificationType(NotificationType.INFORMATION);
                    tray.showAndWait();
                }
                else{
                //Notification Echec de Supprimer Cmd!!!  
                TrayNotification tray =new TrayNotification();
                tray.setTitle("Suppression");
                tray.setMessage("Suppression inpossible,\nVotre commande est en cours de livraison !!!!");
                tray.setAnimationType(AnimationType.POPUP);
                tray.setNotificationType(NotificationType.INFORMATION);
                tray.showAndWait();
                }
            
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceLigneCommande.class.getName()).log(Level.SEVERE, null, ex);
            TrayNotification tray =new TrayNotification();
                tray.setTitle("Erreur");
                tray.setMessage(ex.getMessage());
                tray.setAnimationType(AnimationType.POPUP);
                tray.setNotificationType(NotificationType.INFORMATION);
                tray.showAndWait();
                }
    }
    
    public ObservableList<Panier> detailCmdClt(Commande c)
    {
        
        int idCmd=c.getIdcmd();
        ObservableList oblist = FXCollections.observableArrayList();
                
        try {
         
            ste=con.createStatement();
            ResultSet rs=ste.executeQuery("select p.designation,p.prix,c.nom,l.qte,l.idlc,l.idcmd,l.subtotal\n" +
                                   "FROM lignecommande l\n" +
                                   "join produit p on l.idprod=p.idprod \n" +
                                   "JOIN categorie c oN p.idcateg=c.idcateg \n" +
                                   "join commande cmd on l.idcmd=cmd.idcmd \n" +
                                   "WHERE cmd.idcmd="+idCmd+";");
            
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
            System.out.println(oblist);
            
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
    
        return oblist;
           
}
    
    
    public ObservableList<Produits> Produits(){
       
        ObservableList oblist = FXCollections.observableArrayList();
        try { 
            
            ste=con.createStatement();
            ResultSet rs=ste.executeQuery("SELECT p.idprod,p.photo,p.designation,p.prix,p.qtestock,cat.nom " +
                                            "from produit p " +
                                            "JOIN categorie cat ON p.idcateg=cat.idcateg " +
                                            "ORDER BY p.qtestock DESC ;");
            while (rs.next()) {              
               
               oblist.add(new Produits(rs.getInt("idprod"),rs.getString("photo"),rs.getString("designation"),
                       rs.getInt("prix"),rs.getInt("qtestock"),rs.getString("nom")));
            }
           
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
           
        }    
     return oblist;
   }

public ObservableList<Commandes> AllCommandes(){
       
        ObservableList oblist = FXCollections.observableArrayList();
        try { 
            
            ste=con.createStatement();
            ResultSet rs=ste.executeQuery("SELECT cmd.idcmd,cmd.id,u.Firstname,u.Lastname,cmd.datec,cmd.etatc,cmd.total,cmd.refC "
                                        + "from commande cmd "
                                        + "JOIN user u ON u.id=cmd.id "
                                        + "where cmd.etatc='Validee' Or cmd.etatc='Acceptee' Or cmd.etatc='Refusee';");
            while (rs.next()) {              
               DateFormat dateFormat = new SimpleDateFormat("yyyy/mm/dd");  
               String strDate = dateFormat.format(rs.getDate("datec"));
               oblist.add(new Commandes(rs.getInt("idcmd"),rs.getString("Firstname"),rs.getString("Lastname"),
                       strDate,rs.getString("etatc"),rs.getFloat("total"),rs.getString("refC")));
                System.out.println(oblist);

            }
           
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
           
        }    
     return oblist;
   }

    public void refuserCommande(Commande c) {
        
        if(c.getEtatc().equals("Validee")){
            try {
                //Upadet etat commande Refuser
                PreparedStatement pcc=con.prepareStatement("UPDATE commande SET etatc=? where idcmd=?");
                pcc.setString(1,"Refusee");
                pcc.setInt(2,c.getIdcmd());
                pcc.executeUpdate();
                System.out.println("commande Refuser");
                
                //Upadet etat Livraison Refuser
                PreparedStatement pcl=con.prepareStatement("UPDATE livraison SET etatl=? where idcmd=?");
                pcl.setString(1,"Refusee");
                pcl.setInt(2,c.getIdcmd());
                pcl.executeUpdate();
                System.out.println("commande Refuser");
                
                TrayNotification tray =new TrayNotification();
                tray.setTitle("Refuser Commande");
                tray.setMessage("Commande Refusée !!!");
                tray.setAnimationType(AnimationType.POPUP);
                tray.setNotificationType(NotificationType.INFORMATION);
                tray.showAndWait();
            } catch (SQLException ex) {
                
                Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
                TrayNotification tray =new TrayNotification();
                tray.setTitle("Erreur");
                tray.setMessage(ex.getMessage());
                tray.setAnimationType(AnimationType.POPUP);
                tray.setNotificationType(NotificationType.INFORMATION);
                tray.showAndWait();
            }
        
        }
        
        if(c.getEtatc().equals("Acceptee")){
            
            TrayNotification tray =new TrayNotification();
            tray.setTitle("Refuser Commande");
            tray.setMessage("Vous ne pouvez pas Refuser\nune commande déjà Accepter");
            tray.setAnimationType(AnimationType.POPUP);
            tray.setNotificationType(NotificationType.INFORMATION);
            tray.showAndWait();
        }else {
        
        TrayNotification tray =new TrayNotification();
            tray.setTitle("Refuser Commande");
            tray.setMessage("cette Commane est déja Refusée");
            tray.setAnimationType(AnimationType.POPUP);
            tray.setNotificationType(NotificationType.INFORMATION);
            tray.showAndWait();
        
        }
    
    }
    
    public void AccepterCommande(Commande c) {
        
        if(c.getEtatc().equals("Validee")){
            try {
                //Upadet etat commande Accepter
                PreparedStatement pcc=con.prepareStatement("UPDATE commande SET etatc=? where idcmd=?");
                pcc.setString(1,"Acceptee");
                pcc.setInt(2,c.getIdcmd());
                pcc.executeUpdate();
                System.out.println("Commande Accepter");
                
                //Upadet etat Livraison Refuser
                PreparedStatement pcl=con.prepareStatement("UPDATE livraison SET etatl=? where idcmd=?");
                pcl.setString(1,"en atente");
                pcl.setInt(2,c.getIdcmd());
                pcl.executeUpdate();
                System.out.println("Livraison en atente");
                
                TrayNotification tray =new TrayNotification();
                tray.setTitle("Acceptation du commande");
                tray.setMessage("Commande Accepter avec succes !!!");
                tray.setAnimationType(AnimationType.POPUP);
                tray.setNotificationType(NotificationType.INFORMATION);
                tray.showAndWait();
            } catch (SQLException ex) {
                
                Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
                TrayNotification tray =new TrayNotification();
                tray.setTitle("Erreur");
                tray.setMessage(ex.getMessage());
                tray.setAnimationType(AnimationType.POPUP);
                tray.setNotificationType(NotificationType.INFORMATION);
                tray.showAndWait();
            }
        
        }
        
        if(c.getEtatc().equals("Acceptee")){
            
            TrayNotification tray =new TrayNotification();
            tray.setTitle("Acceptation du commande");
            tray.setMessage("cette Commane est déja Accepter");
            tray.setAnimationType(AnimationType.POPUP);
            tray.setNotificationType(NotificationType.INFORMATION);
            tray.showAndWait();
        }else {
        
            TrayNotification tray =new TrayNotification();
            tray.setTitle("Acceptation du commande");
            tray.setMessage("Vous ne pouvez pas Accepter\nune commande déjà Refusée");
            tray.setAnimationType(AnimationType.POPUP);
            tray.setNotificationType(NotificationType.INFORMATION);
            tray.showAndWait();
        
        }
    
    }
    
    public void validerCommande(String adresse ,int idUser){
   
       
        try {
            Commande c=this.RechercherPanierParClient(idUser);
            PreparedStatement precmd=con.prepareStatement("UPDATE commande SET etatc=?,datec=? where idcmd=? ;");
            precmd.setString(1,"Validee");
            Date date =new Date(15,02,2019);
            precmd.setDate(2,date);
            precmd.setInt(3,c.getIdcmd());
            precmd.executeUpdate();
            System.out.println("Commande a été valider avec succes");
            
            //Create Facture

            PreparedStatement pref=con.prepareStatement("INSERT INTO Facture (`etatf`, `datef`,`idcmd`)"
                    + " VALUES (?, ?, ?);");
 
            pref.setString(1, "Non Payee");
            pref.setDate(2, date);
            pref.setInt(3,c.getIdcmd());
            pref.executeUpdate();
            System.out.println("Facture ajouter avec succes");
            
            //Create Livraison
            
            PreparedStatement prel=con.prepareStatement("INSERT INTO Livraison (`destination`,`etatl`,`dateliv`,"
                    + "`matriculeL` ,`idc`)"
                    + " VALUES (?, ?, ?, ?,?);");
 
            prel.setString(1, adresse);
            prel.setString(2, "non livree");
            prel.setDate(3, date);
            prel.setString(4, "X"+Math.random());
            prel.setInt(5,c.getIdcmd());
            prel.executeUpdate();
            System.out.println("Facture ajouter avec succes");
        
             TrayNotification tray =new TrayNotification();
            tray.setTitle("Valider Commande");
            tray.setMessage("Votre commande à été valider avec succes");
            tray.setAnimationType(AnimationType.POPUP);
            tray.setNotificationType(NotificationType.INFORMATION);
            tray.showAndWait();
        
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
                TrayNotification tray =new TrayNotification();
                tray.setTitle("Erreur");
                tray.setMessage(ex.getMessage());
                tray.setAnimationType(AnimationType.POPUP);
                tray.setNotificationType(NotificationType.INFORMATION);
                tray.showAndWait();
        }
       
   }
    


}
        
        
        
    
 
    
    


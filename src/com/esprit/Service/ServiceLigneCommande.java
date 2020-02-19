/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.esprit.Service;

import com.esprit.Entite.Client;
import com.esprit.Entite.Commande;
import com.esprit.Entite.LigneCommande;
import com.esprit.Entite.Panier;
import com.esprit.Entite.Produit;
import com.esprit.Gui.ListeProduitController;
import com.esprit.Utils.DataBase;
import java.sql.Connection;
import java.sql.Date;
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

/**
 *
 * @author Lenovo
 */
public class ServiceLigneCommande {
    
    private Connection con;
    private PreparedStatement pre;
    private Statement ste;

    public ServiceLigneCommande(){ 
            
            con = DataBase.getInstance().getConnection();
    }
    
    public void ajouterLigneCommande(LigneCommande lc) {
            Client client=ListeProduitController.clt;
            
        try{
            ServiceProduit sp=new ServiceProduit();
            Produit p=sp.getProduit(lc.getProduit().getIdprod());
            
            ste=con.createStatement();
            ResultSet rslc=ste.executeQuery("select * from lignecommande where idprod="+p.getIdprod());
            int q=0;
            while (rslc.next()) {                

                q=q+rslc.getInt("qte");
            }
             q=q+lc.getQte();
            
            if (q<=p.getQtestock()){
                
                /*ste=con.createStatement();
                ResultSet rscmd=ste.executeQuery("select * from commande where etatc='non valide' and idclt="+client.getId());
                 */
                ServiceCommande sc=new ServiceCommande();
                Commande c=sc.RechercherPanierParClient(client.getId());
                        
                if(!(c==null)){
            
                PreparedStatement pre=con.prepareStatement("INSERT INTO lignecommande (`idprod`,`idcmd`,`qte`) "
                     + "VALUES (?, ?, ?);");

                pre.setInt(1, p.getIdprod());
                pre.setInt(2,c.getIdcmd());
                pre.setInt(3, lc.getQte());
                pre.executeUpdate();
                p.setQtestock(p.getQtestock()-lc.getQte());
                sp.modifierProduit(p);
                c.setTotal(c.getTotal()+p.getPrix()*lc.getQte());
                System.out.println("Ligne Commande ajouter avec succes");
                }
                
                else{
                   
                   Date d=new Date (17,12,20);
                   Commande cmd=new Commande(d,0,client);
                   sc.ajouterPanier(cmd);
                   cmd=sc.RechercherPanierParClient(client.getId());
                   cmd.setTotal(lc.getQte()*lc.getProduit().getPrix());
                   sc.modifierCommande(cmd);
                   PreparedStatement pre=con.prepareStatement("INSERT INTO lignecommande (`idprod`,`idcmd`,`qte`) "
                     + "VALUES (?, ?, ?);");

                    pre.setInt(1, lc.getProduit().getIdprod());
                    pre.setInt(2,cmd.getIdcmd());
                    pre.setInt(3, lc.getQte());
                    pre.executeUpdate();
                    System.out.println("Ligne Commande ajouter avec succes");

                }
            }
            else
            {
                System.out.println("la quentité en stock est insuffisante");
            }
            
        }catch (SQLException ex) {
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    public void modifierLigneCommande(LigneCommande lc){
        
        try{
            PreparedStatement pre=con.prepareStatement("UPDATE lignecommande SET idprod=?,idcmd=?,qte=? where idlc="
                +lc.getIdlc()+";");
            pre.setInt(1, lc.getProduit().getIdprod());
            pre.setInt(2, lc.getCommande().getIdcmd());
            pre.setInt(3, lc.getQte());
            pre.executeUpdate();
            System.out.println("Ligne Commande Modifier avec succes");
        }catch (SQLException ex) {
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    public void supprimerLigneCommande(LigneCommande lc){
        try{
            PreparedStatement pre=con.prepareStatement("DELETE FROM lignecommande where idlc="+lc.getIdlc()+";");
            pre.execute();
            System.out.println("Ligne Commande Supprimer avec succes");
        }catch (SQLException ex) {
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
    public List<LigneCommande> afficherLigneCommande(){
    
        try{
            List<LigneCommande> arr=new ArrayList<>();
        
            ste=con.createStatement();
            ResultSet rs=ste.executeQuery("select * from lignecommande");
            while (rs.next()) {                

                ServiceCommande sc=new ServiceCommande();
                Commande c=sc.getCommande(rs.getInt("idcmd"));
               
                ServiceProduit sp=new ServiceProduit();
                Produit p=sp.getProduit(rs.getInt("idprod"));
               
                LigneCommande lc=new LigneCommande(rs.getInt("idlc"),c,p,rs.getInt("qte")); 
               
               
                arr.add(lc);
            }
            return arr;
        }catch (SQLException ex) {
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    
    }
    
    
    public ObservableList<Panier> ListerPannier() {
            ObservableList oblist = FXCollections.observableArrayList();
        try {
            
            ste=con.createStatement();
            
            ResultSet rs;
            rs = ste.executeQuery("select p.designation,p.prix,c.nom,l.qte FROM lignecommande l,produit p,categorie c,commande cmd,client clt WHERE l.idprod=p.idprod AND p.idcateg=c.idcateg AND l.idcmd=cmd.idcmd AND cmd.etatc='non valide' AND cmd.idclt=clt.idclt AND clt.idclt=1");
            while (rs.next()) {
                
                String des= rs.getString("designation");
                float p= rs.getFloat("prix");
                String n= rs.getString("nom");
                int q= rs.getInt("qte");
                
                oblist.add(new Panier(des,p,n,q));
                
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceLigneCommande.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return oblist;
            
    } 
    
    public ObservableList<LigneCommande> Lister() {
            ObservableList oblist = FXCollections.observableArrayList();
        try {
            
            ste=con.createStatement();
            
            ResultSet rs;
            rs = ste.executeQuery("SELECT l.idlc,l.idprod,l.idcmd,l.qte FROM lignecommande l,produit p,categorie c,commande cmd,client clt WHERE l.idprod=p.idprod AND p.idcateg=c.idcateg AND l.idcmd=cmd.idcmd AND cmd.etatc='non valide' AND cmd.idclt=clt.idclt AND clt.idclt=1");
            while (rs.next()) {
                ServiceCommande sc=new ServiceCommande();
                Commande c=sc.getCommande(rs.getInt("idcmd"));
                ServiceProduit sp=new ServiceProduit();
                Produit prod=sp.getProduit(rs.getInt("idprod"));
                
                int des= rs.getInt("idlc");
                int q= rs.getInt("qte");
                
                oblist.add(new LigneCommande(des, c, prod, q));
                
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceLigneCommande.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return oblist;
            
    } 
    
}

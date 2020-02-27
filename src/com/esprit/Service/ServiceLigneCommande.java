/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.esprit.Service;

import com.esprit.Entite.User;
import com.esprit.Entite.Commande;
import com.esprit.Entite.LigneCommande;
import com.esprit.Entite.Panier;
import com.esprit.Entite.Produit;
import com.esprit.Gui.AcceuilController;
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
            User client=AcceuilController.clt;
            
        try{
            ServiceProduit sp=new ServiceProduit();
            Produit p=sp.getProduit(lc.getProduit().getIdprod());
            System.out.println(p);
            //test qte en stok
            ste=con.createStatement();
            //liste des qte comandée d'un prod
                ResultSet rslc=ste.executeQuery("select * from lignecommande where idprod="+p.getIdprod());
            int qeff=0;
            while (rslc.next()) {                

                qeff=qeff+rslc.getInt("qte");
                
              
            }
             qeff=qeff+lc.getQte();
             System.out.println("qte eff demandée: "+qeff);
             System.out.println("qte en stock: "+p.getQtestock());
            //qte suff
            if (qeff<=p.getQtestock()){
                
                //recherche panier
                ServiceCommande sc=new ServiceCommande();
                Commande c=sc.RechercherPanierParClient(client.getId());
                //3andi panier       
                if(c!=null){
                  //verif produit existe dans le panier
                  ste=con.createStatement();
                  ResultSet rdpp=ste.executeQuery("Select * from lignecommande where idprod="+p.getIdprod());
                  //prod existe update qtedemandé / qtestok / totale
                  if(rdpp.next())
                      {
                          //qte demandée
                          PreparedStatement pre=con.prepareStatement("UPDATE lignecommande SET qte=? where idprod="
                             +lc.getProduit().getIdprod()+";");
                                pre.setInt(1, (rdpp.getInt("qte")+lc.getQte()));
                                int k=rdpp.getInt("qte")+lc.getQte();
                                 pre.executeUpdate();
                          System.out.println("qte Ligne Commande Modifier avec succes");
                          
                          //totale
                          PreparedStatement pcc=con.prepareStatement("UPDATE commande SET total=? where idcmd=?");
                                pcc.setFloat(1, c.getTotal()+(rdpp.getInt("qte")*p.getPrix()));
                                pcc.setInt(2,c.getIdcmd());
                                pcc.executeUpdate();
                          System.out.println("Total Commande Modifier avec succes");
                  //qte stock   
                  qeff=qeff-lc.getQte();
             
          }
                  
                  //prod non existe creation nouvelle lc update qtestock et totale commande
                 else{  
                PreparedStatement pre=con.prepareStatement("INSERT INTO lignecommande (`idprod`,`idcmd`,`qte`) "
                     + "VALUES (?, ?, ?);");

                pre.setInt(1, p.getIdprod());
                pre.setInt(2,c.getIdcmd());
                pre.setInt(3,lc.getQte());
                pre.executeUpdate();
                System.out.println("Ligne Commande ajouter avec succes");
                
                
                  ste=con.createStatement();
                  ResultSet sss=ste.executeQuery("Select * from lignecommande where idprod="+p.getIdprod());
                  
                while (sss.next()){
                    
                //totale
                
                PreparedStatement pcc=con.prepareStatement("UPDATE commande SET total=? where idcmd=?");
                                pcc.setFloat(1,c.getTotal()+(sss.getInt("qte")* p.getPrix()));
                                pcc.setInt(2,c.getIdcmd());
                                pcc.executeUpdate();
                             System.out.println("Totale Commande Modifier avec succes");

                //qte en stock  
                
                qeff=qeff-lc.getQte();
           
                 
                
                }
                }
                
                }
                //ma3andich panier ajouter pan
                else{
                   
                   Date d=new Date (17,12,20);
                   Commande cmd=new Commande(d,0,client);
                   sc.ajouterPanier(cmd);
                   cmd=sc.RechercherPanierParClient(client.getId());
                   
                   PreparedStatement pre=con.prepareStatement("INSERT INTO lignecommande (`idprod`,`idcmd`,`qte`) "
                     + "VALUES (?, ?, ?);");

                    pre.setInt(1, lc.getProduit().getIdprod());
                    pre.setInt(2,cmd.getIdcmd());
                    pre.setInt(3, lc.getQte());
                    pre.executeUpdate();
                    System.out.println("Ligne Commande ajouter avec succes");
                    
                    //ste stock
                   /* ste=con.createStatement();
                  ResultSet vp=ste.executeQuery("Select * from produit where idprod="+p.getIdprod());
                  while(vp.next()){
                      PreparedStatement px=con.prepareStatement("UPDATE produit SET qtestock=? where idprod=?");
                                px.setInt(1, vp.getInt("qtestock")-(lc.getQte()));
                                px.setInt(2, vp.getInt("idprod"));
                                px.executeUpdate();
                                System.out.println("qteStock Modifier avec succes");
                                
                  }*/

                    //totale
                    ste=con.createStatement();
                  ResultSet v=ste.executeQuery("Select * from lignecommande where idprod="+p.getIdprod());
                  
                while (v.next()){
                   
                    PreparedStatement pcc=con.prepareStatement("UPDATE commande SET total=? where idcmd=?");
                                pcc.setFloat(1,cmd.getTotal()+(v.getInt("qte")*p.getPrix()));
                                pcc.setInt(2,cmd.getIdcmd());
                                pcc.executeUpdate();
                          System.out.println("Totale Commande Modifier avec succes");
                }
                
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
            rs = ste.executeQuery("select p.designation,p.prix,c.nom,l.qte,l.idlc,l.idcmd FROM lignecommande l,produit p,categorie c,commande cmd,user clt WHERE l.idprod=p.idprod AND p.idcateg=c.idcateg AND l.idcmd=cmd.idcmd AND cmd.etatc='non valide' AND cmd.id=clt.id AND clt.id=2");
            while (rs.next()) {
                
                int id=rs.getInt("idlc");
                String des= rs.getString("designation");
                float p= rs.getFloat("prix");
                String n= rs.getString("nom");
                int q= rs.getInt("qte");
                int idcmd= rs.getInt("idcmd");
                
                
                oblist.add(new Panier(id,des,p,n,q,idcmd));
                
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
            rs = ste.executeQuery("SELECT l.idlc,l.idprod,l.idcmd,l.qte FROM lignecommande l,produit p,categorie c,commande cmd,user clt WHERE l.idprod=p.idprod AND p.idcateg=c.idcateg AND l.idcmd=cmd.idcmd AND cmd.etatc='non valide' AND cmd.id=clt.id AND clt.id=1");
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
    
    public void modifierQte(int id,int qte,LigneCommande lc){
    
        try{
            
            
            
            ServiceProduit sp=new ServiceProduit();
            Produit p=sp.getProduit(id);
            ste=con.createStatement();
            ResultSet rslc=ste.executeQuery("select qte from lignecommande where idprod="+id);
            int qeff=0;
            while (rslc.next()) {                

                qeff=qeff+rslc.getInt("qte");
                
              
            }
             qeff=qeff+lc.getQte();
             
             
            ste=con.createStatement();
            ResultSet r=ste.executeQuery("select * from produit where idprod="+id);
            int qtestock=0;
            while(r.next()){
                qtestock=r.getInt("qtestock");
            }
            System.out.println(qtestock);
            if (qtestock-qeff<0){
                
                    System.out.println("qte insufisante !!!!!");
                }
                
            else
            {
                    PreparedStatement pre=con.prepareStatement("UPDATE lignecommande SET qte=? where idlc=?");
                    pre.setInt(1,lc.getQte());
                    pre.setInt(2,lc.getIdlc());
                    pre.executeUpdate();
                    System.out.println("Ligne Commande Modifier avec succes");
            
                    }
                
            
            
        }catch (SQLException ex) {
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
   
    
}

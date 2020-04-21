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
import com.esprit.gui.LoginUserController;
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
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

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
    
    
    
    public LigneCommande getLc(int idLc) {
        
        LigneCommande pp;
        pp = null;
        try {
        ste=con.createStatement();
        ResultSet rsp=ste.executeQuery("select * from lignecommande where idlc="+idLc);
        
        while (rsp.next()) {  
            
            ServiceProduit ss=new ServiceProduit();
            Produit p=ss.getProduit(rsp.getInt("idprod"));
            
            ServiceCommande sc=new ServiceCommande();
            Commande c=sc.getCommande(rsp.getInt("idcmd"));
               
            pp=new LigneCommande(idLc, c, p,rsp.getInt("qte"), rsp.getFloat("subtotal"));  
            return pp;
        }
        
        
    
    }  catch (SQLException ex) {
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
            pp=null;
        }
        
        return pp;
    
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
    
    public boolean supprimerLigneCommande(LigneCommande lc){
        boolean b=false;
        try{
            
            PreparedStatement pre=con.prepareStatement("DELETE FROM lignecommande where idlc="+lc.getIdlc()+";");
            pre.execute();
            System.out.println("Ligne Commande Supprimer avec succes");
            b=true;
        }catch (SQLException ex) {
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
             b=false;
        }
        return b;
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
    
    
    public ObservableList<Panier> ListerPannier(int idUser) {
            ObservableList oblist = FXCollections.observableArrayList();
        try {
            
            ste=con.createStatement();
            
            ResultSet rs;
            rs = ste.executeQuery("select p.designation,p.prix,c.nom,l.qte,l.subtotal,l.idlc,l.idcmd "
                    + "FROM lignecommande l,produit p,categorie c,commande cmd,user clt "
                    + "WHERE l.idprod=p.idprod "
                    + "AND p.idcateg=c.idcateg "
                    + "AND l.idcmd=cmd.idcmd "
                    + "AND cmd.etatc='Non Validee' "
                    + "AND cmd.id=clt.id "
                    + "AND clt.id="+idUser);
            
            while (rs.next()) {
                
                int id=rs.getInt("idlc");
                String des= rs.getString("designation");
                float p= rs.getFloat("prix");
                String n= rs.getString("nom");
                int q= rs.getInt("qte");
                int idcmd= rs.getInt("idcmd");
                float sub=rs.getFloat("subtotal");
                
                
                oblist.add(new Panier(id,des,p,n,q,idcmd,sub));
                
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
            rs = ste.executeQuery("SELECT l.idlc,l.idprod,l.idcmd,l.qte FROM lignecommande l,produit p,categorie c,commande cmd,user clt WHERE l.idprod=p.idprod AND p.idcateg=c.idcateg AND l.idcmd=cmd.idcmd AND cmd.etatc='Non Validee' AND cmd.id=clt.id AND clt.id=1");
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
    
    public boolean modifierQte(int id,int qte,LigneCommande lc){
        boolean b=false;
        try{
            
            
            
            ServiceProduit sp=new ServiceProduit();
            Produit p=sp.getProduit(id);
            ste=con.createStatement();
            ResultSet rslc=ste.executeQuery("select qte from lignecommande where idprod="+lc.getIdlc());
            int qeff=0;
            while (rslc.next()) {                

                qeff=qeff+rslc.getInt("qte");
                
              
            }
             qeff=qeff+lc.getQte();
             
             System.out.println(qeff);
             
            ste=con.createStatement();
            ResultSet r=ste.executeQuery("select p.idprod,p.qtestock from produit p ,lignecommande l where p.idprod=l.idprod AND l.idlc="+id);
            while(r.next()){
                System.out.println(qeff);
                System.out.println(r.getInt("qtestock"));
               
                if (r.getInt("qtestock") - qeff <0){
                
                   b=false;
                 }
                
                else
                     
                    {
                    PreparedStatement pre=con.prepareStatement("UPDATE lignecommande SET qte=? where idlc=?");
                    pre.setInt(1,lc.getQte());
                    pre.setInt(2,lc.getIdlc());
                    pre.executeUpdate();
                    b=true;
                    }
                } 
            
        }catch (SQLException ex) {
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
        }
    return b;
    }
    
   //****************************************************************************************************************
    
     public void ajouterLigneCommande(LigneCommande lc) {
        //****user connectée****   
        
        ServicePersonne su=new ServicePersonne();
        User client=su.getUser(LoginUserController.NumId);  
        try{
            //****** le produit Commandée ******
            Produit p=lc.getProduit();
            System.out.println(p);
            //****** Quantité demmandée********
            int qted = lc.getQte();
            //****** Quantité En Stock********
            int qtes = p.getQtestock();
            
            //Verifier que la qte en Stock est suffisante
            if(qtes-qted>0 || qtes-qted==0 ){
                 //****qte en Stock est suffisante***
                //Verifier l'existance d'un panier
                ServiceCommande sc=new ServiceCommande();
                Commande c=sc.RechercherPanierParClient(client.getId());
                      
                if(c!=null){
                 //*****Panier existe*****
                 //Verifier l'existance du produit Commandé dans le panier
                 
                  ste=con.createStatement();
                  ResultSet rdpp=ste.executeQuery("Select p.idprod,p.photo,p.designation,p.prix,p.qtestock,lc.qte,lc.subtotal "
                          + "from produit p "
                          + "join lignecommande lc on p.idprod=lc.idprod "
                          + "join commande c on lc.idcmd=c.idcmd "
                          + "where p.idprod="+p.getIdprod()
                          + " And c.idcmd="+c.getIdcmd());
                  
                  if(rdpp.next())
                      {
                        //*****Produit existe*****
                        //modifier qteLc/subtotal/TotalCmd/qtestock
                        //modifier qteLc/subtotal
                          PreparedStatement pre=con.prepareStatement("UPDATE lignecommande SET qte=?,subtotal=? where idprod="
                             +p.getIdprod()+";");
                                pre.setInt(1, (rdpp.getInt("qte")+ qted));
                                pre.setFloat(2, (rdpp.getFloat("subtotal")+ qted * p.getPrix()));
                                 pre.executeUpdate();
                          System.out.println("qte Ligne Commande Modifier avec succes");
                          
                          //totale
                          PreparedStatement pcc=con.prepareStatement("UPDATE commande SET total=? where idcmd=?");
                                pcc.setFloat(1, c.getTotal() + qted * p.getPrix() );
                                pcc.setInt(2,c.getIdcmd());
                                pcc.executeUpdate();
                          System.out.println("Total Commande Modifier avec succes");
                  //qte stock   
                  PreparedStatement pcs=con.prepareStatement("UPDATE produit SET qtestock=? where idprod=?");
                                pcs.setInt(1, p.getQtestock() - qted );
                                pcs.setInt(2,p.getIdprod());
                                pcs.executeUpdate();
                          System.out.println("qte en stock Modifier avec succes");
             
                TrayNotification tray =new TrayNotification();
                tray.setTitle("Ajout");
                tray.setMessage("panier existe/prod existe/\nupdate qted/tot cmd/ qte en stock");
                tray.setAnimationType(AnimationType.POPUP);
                tray.setNotificationType(NotificationType.INFORMATION);
                tray.showAndWait();
                      
                      }
                  else{
                      //*****Produit n'existe pas*****
                       //Ajouter une nouvelle lc et update qtestock et total commande
                 PreparedStatement pre=con.prepareStatement("INSERT INTO lignecommande (`idprod`,`idcmd`,`qte`,`subtotal`) "
                     + "VALUES (?, ?, ?, ?);");

                pre.setInt(1, p.getIdprod());
                pre.setInt(2,c.getIdcmd());
                pre.setInt(3,qted);
                pre.setFloat(4,qted*p.getPrix());
                pre.executeUpdate();
                System.out.println("Ligne Commande ajouter avec succes");
  
                //modifier total Commande
                PreparedStatement pcc=con.prepareStatement("UPDATE commande SET total=? where idcmd=?");
                pcc.setFloat(1,c.getTotal()+qted*p.getPrix());
                pcc.setInt(2,c.getIdcmd());
                pcc.executeUpdate();
                System.out.println("Totale Commande Modifier avec succes");

                //qte en stock  
                
                PreparedStatement pcs=con.prepareStatement("UPDATE produit SET qtestock=? where idprod=?");
                pcs.setInt(1, p.getQtestock() - qted );
                pcs.setInt(2,p.getIdprod());
                pcs.executeUpdate();
                System.out.println("qte en stock Modifier avec succes");
                
                TrayNotification tray =new TrayNotification();
                tray.setTitle("Ajout");
                tray.setMessage("panier existe/prod n'existepas/\najouter prod\nupdatetot cmd/ qte en stock");
                tray.setAnimationType(AnimationType.POPUP);
                tray.setNotificationType(NotificationType.INFORMATION);
                tray.showAndWait();
                }

            }else{
                //*****Panier est inexistante*****
                //***créer panier****
                    
                   Date d=new Date (17,12,20);
                   Commande cmd=new Commande(d,qted*p.getPrix(),client);
                   sc.ajouterPanier(cmd); 
                   
                   Commande Pan=sc.RechercherPanierParClient(client.getId());
                  
                    
                //***Ajouter Lc****
                
                PreparedStatement ppp=con.prepareStatement("INSERT INTO lignecommande (`idprod`,`idcmd`,`qte`,`subtotal`) "
                     + "VALUES (?, ?, ?, ?);");

                    ppp.setInt(1, p.getIdprod());
                    ppp.setInt(2,Pan.getIdcmd());
                    ppp.setInt(3, qted);
                    ppp.setFloat(4,p.getPrix()*qted);
                    ppp.executeUpdate();
                    System.out.println("Ligne Commande ajouter avec succes");
                
                //******update qte en Stock***
                PreparedStatement pcs=con.prepareStatement("UPDATE produit SET qtestock=? where idprod=?");
                pcs.setInt(1, p.getQtestock() - qted );
                pcs.setInt(2,p.getIdprod());
                pcs.executeUpdate();
                System.out.println("qte en stock Modifier avec succes");

                TrayNotification tray =new TrayNotification();
                tray.setTitle("Ajout");
                tray.setMessage("créer panier/ajouter nvlc/update qte en stock");
                tray.setAnimationType(AnimationType.POPUP);
                tray.setNotificationType(NotificationType.INFORMATION);
                tray.showAndWait();
            }

            }else{
                //****qte en Stock est insuffisante***
                System.out.println("la quentité en stock est insuffisante");
                TrayNotification tray =new TrayNotification();
                tray.setTitle("Ajout");
                tray.setMessage("la quentité en stock est insuffisante !!!!!");
                tray.setAnimationType(AnimationType.POPUP);
                tray.setNotificationType(NotificationType.INFORMATION);
                tray.showAndWait();
                
            }
            
        }catch (SQLException ex) {
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
            TrayNotification tray =new TrayNotification();
                tray.setTitle("Erreur");
                tray.setMessage(ex.getMessage());
                tray.setAnimationType(AnimationType.POPUP);
                tray.setNotificationType(NotificationType.INFORMATION);
                tray.showAndWait();
        }
    
    }
     
     public void modifierQteLc(int qte ,LigneCommande lc){
        
        
            
            // LC a modifier => lc
            //Qte en stock
            int qtes=lc.getProduit().getQtestock();
            
            if (qtes - qte > 0 || qtes - qte == 0) {
                
                try {
                    //modifier Subtotal and qte
                    
                    PreparedStatement pre=con.prepareStatement("UPDATE lignecommande SET qte=?,subtotal=? where idlc="
                            +lc.getIdlc()+";");
                    pre.setInt(1, qte);
                    pre.setFloat(2, qte * lc.getProduit().getPrix());
                    pre.executeUpdate();
                    System.out.println("qte Ligne Commande Modifier avec succes");
                    
                    //modifier total commande
                    float tot=lc.getCommande().getTotal();
                    tot-=lc.getSubtotal()-(qte * lc.getProduit().getPrix());
                    PreparedStatement pcc=con.prepareStatement("UPDATE commande SET total=? where idcmd=?");
                    pcc.setFloat(1,tot);
                    pcc.setInt(2,lc.getCommande().getIdcmd());
                    pcc.executeUpdate();
                    System.out.println("Totale Commande Modifier avec succes");
                    
                    //modifier qte en stock
                    qtes+=lc.getQte()-qte;
                    PreparedStatement pcs=con.prepareStatement("UPDATE produit SET qtestock=? where idprod=?");
                    pcs.setInt(1,qtes);
                    pcs.setInt(2,lc.getProduit().getIdprod());
                    pcs.executeUpdate();
                    System.out.println("qte en stock Modifier avec succes");
                    
                    
                    TrayNotification tray =new TrayNotification();
                    tray.setTitle("Ajout");
                    tray.setMessage("Qte demandée a été changée avec succes !!!!!");
                    tray.setAnimationType(AnimationType.POPUP);
                    tray.setNotificationType(NotificationType.INFORMATION);
                    tray.showAndWait();
                
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
            else{
                
            
                
                TrayNotification tray =new TrayNotification();
                tray.setTitle("Ajout");
                tray.setMessage("la quentité en stock est insuffisante !!!!!");
                tray.setAnimationType(AnimationType.POPUP);
                tray.setNotificationType(NotificationType.INFORMATION);
                tray.showAndWait();
                
            }
            
      
     }
     
    public void deleteLc(LigneCommande lc) {
        
        try {
            //LC a modifier => lc
            //delete lc
            PreparedStatement pcs=con.prepareStatement("DELETE from lignecommande where idlc=?");
            pcs.setInt(1,lc.getIdlc());
            pcs.executeUpdate();
            System.out.println("Ligne commande supprimer avec succes");
            /*
            * aprés de supp lc il faut modifier  total commande et Qte en stock
            * total commande = total commande - subtotal lc a supprimer
            * qte stock = qtestock - qte lc a supprimer
            */
            //Modifier qte en stock
            PreparedStatement ps=con.prepareStatement("UPDATE produit SET qtestock=? where idprod=?");
            System.out.println(lc.getProduit().getQtestock());
            System.out.println(lc.getQte());
            ps.setInt(1,lc.getProduit().getQtestock()+lc.getQte());
            ps.setInt(2,lc.getProduit().getIdprod());
            ps.executeUpdate();
            System.out.println("qte en stock Modifier avec succes");
            //Modifier total Commande
            PreparedStatement pcc=con.prepareStatement("UPDATE commande SET total=? where idcmd=?");
            pcc.setFloat(1,lc.getCommande().getTotal()-lc.getSubtotal());
            pcc.setInt(2,lc.getCommande().getIdcmd());
            pcc.executeUpdate();
            System.out.println("Totale Commande Modifier avec succes");
            
            //verifier que la panier est vide ou non
            ste=con.createStatement();
            ResultSet rdpp=ste.executeQuery("Select * "
                    +"from lignecommande lc "
                    + "where lc.idcmd="+lc.getCommande().getIdcmd());
            
            TrayNotification tray =new TrayNotification();
            tray.setTitle("Suppression");
            tray.setMessage("la supprission a été effectuée avec succes");
            tray.setAnimationType(AnimationType.POPUP);
            tray.setNotificationType(NotificationType.INFORMATION);
            
            if(! rdpp.next() ){
                //le Panier est vide alors supprimer Panier
                PreparedStatement pcmd=con.prepareStatement("DELETE from commande where idcmd=?");
                pcmd.setInt(1,lc.getCommande().getIdcmd());
                pcmd.executeUpdate();
                System.out.println("Ligne commande supprimer avec succes");
            }
            
          
        
        } catch (SQLException ex) {
           Logger.getLogger(ServiceLigneCommande.class.getName()).log(Level.SEVERE, null, ex);
                    TrayNotification tray =new TrayNotification();
                    tray.setTitle("Erreur");
                    tray.setMessage(ex.getMessage());
                    tray.setAnimationType(AnimationType.POPUP);
                    tray.setNotificationType(NotificationType.INFORMATION);
        }
        
        
            
     }
    
}

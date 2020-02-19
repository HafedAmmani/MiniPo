/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.test;

import com.esprit.Entite.Client;
import com.esprit.Entite.Commande;
import com.esprit.Entite.Facture;
import com.esprit.Entite.LigneCommande;
import com.esprit.Entite.Produit;
import com.esprit.Gui.ListeProduitController;
import com.esprit.Service.ServiceCategorie;
import com.esprit.Service.ServiceClient;
import com.esprit.Service.ServiceCommande;
import com.esprit.Service.ServiceFacture;
import com.esprit.Service.ServiceFournisseur;
import com.esprit.Service.ServiceLigneCommande;
import com.esprit.Service.ServiceProduit;
import com.esprit.Utils.Stock;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import static sun.security.jgss.GSSUtil.login;

/**
 *
 * @author Lenovo
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        
        ServiceCommande sc=new ServiceCommande();
        ServiceLigneCommande slc= new ServiceLigneCommande();
        ServiceFacture sfact=new ServiceFacture();
        ServiceClient sclt=new ServiceClient();
        ServiceProduit sp=new ServiceProduit();
        ServiceCategorie scateg=new ServiceCategorie();
        ServiceFournisseur sf=new ServiceFournisseur();
         
        /* 
        **********Ajout/Suppression/Modificatio********
            ************Commande*************
        Client clt = sclt.getClient(1);
        Commande c=sc.getCommande(1);
        c.setClient(clt);
        sc.modifierCommande(c);
        List <Commande> list= sc.afficherCommandes();
        System.out.println(list);
        
        Date d=new Date(2020, 02, 20);
        Commande c=new Commande(3,d,(float) 22,"valide",1);
        Commande cc=new Commande(d,(float) 22,"valide");
     
         try {
            sc.ajouterCommande(cc);
            List<Commande> list ;
            list= sc.afficherCommandes();
            System.out.println(list);
            sc.modifierCommande(c);
            sc.supprimerCommande(c);
            list= sc.afficherCommandes();
            System.out.println(list);
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }*/
        
        /*
        **********Ajout/Suppression/Modificatio********
            ************Facture*************
        
        Date d=new Date(2020, 02, 20);
        Commande c=sc.getCommande(1);
        Commande c1=sc.getCommande(4);
        Facture f=new Facture(d,"non payée",c);
       
        Facture f2=new Facture(d,"non payée",c1);
        sfact.ajouterFacture(f);
        sfact.ajouterFacture(f2);
        List <Facture> lf= sfact.afficherFacture();
        System.out.println(lf);
        
        c.setEtatcmd("validée");
        sc.modifierCommande(c);
        f.setCommande(c);
        sfact.modifierFacture(f);
        sfact.ajouterFacture(f);
        List <Facture> lf= sfact.afficherFacture();
        System.out.println(lf); */
        
        /*
        **********Ajout/Suppression/Modificatio********
            ************Facture*************
       
        
        Produit p=sp.getProduit(1);
        Commande c=sc.getCommande(1);
        LigneCommande lc=new LigneCommande(c, p,100);
        
        slc.ajouterLigneCommande(lc);
        
        //List <LigneCommande> llc= slc.afficherLigneCommande();
        //System.out.println(llc);
        
        Produit p1=sp.getProduit(2);
        Commande c1=sc.getCommande(4);
        LigneCommande lc1=new LigneCommande(c, p,100);
        
        slc.ajouterLigneCommande(lc1);
        
        List <LigneCommande> llc= slc.afficherLigneCommande();
        System.out.println(llc);

*/
        
        //Produit p=sp.getProduit(1);
        //Client clt=ListeProduitController.clt;
        /*ListeProduitController.setProd();
        LigneCommande lc=new LigneCommande(ListeProduitController.prod,1);
        slc.ajouterLigneCommande(lc);*/
        
        
        
     
        
       // Client clt = sclt.getClient(1);
        //Date d=new Date(2020, 02, 20);
        //Commande c=new Commande(3,d,(float) 22,"non valide",1,clt);
      
        //Date d=new Date(12, 15, 12);

         ObservableList oblist = FXCollections.observableArrayList();
         oblist=slc.ListerPannier();
         System.out.println(oblist);
        
    }
    
}

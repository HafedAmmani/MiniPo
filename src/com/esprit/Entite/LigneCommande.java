/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.esprit.Entite;

import java.util.Objects;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Lenovo
 */
public class LigneCommande {
    
    private int idlc;
    private Commande commande;
    private Produit produit;
    private int qte;
    private float subtotal;

    public LigneCommande(int idlc, Commande commande, Produit produit, int qte, float subtotal) {
        this.idlc = idlc;
        this.commande = commande;
        this.produit = produit;
        this.qte = qte;
        this.subtotal = subtotal;
    }

    public LigneCommande(int idlc,Commande commande, Produit produit, int qte) {
        this.idlc=idlc;
        this.commande = commande;
        this.produit = produit;
        this.qte = qte;
    }

    public LigneCommande(Commande commande, Produit produit,int qte) {
        this.commande = commande;
        this.produit = produit;
        this.qte = qte;
    }

    public LigneCommande(Commande commande, Produit produit, int qte, float subtotal) {
        this.commande = commande;
        this.produit = produit;
        this.qte = qte;
        this.subtotal = subtotal;
    }
    
    
   
    public LigneCommande(Produit produit, int qte) {
        this.produit = produit;
        this.qte = qte;
    }

    public LigneCommande(Produit produit, int qte, float subtotal) {
        this.produit = produit;
        this.qte = qte;
        this.subtotal = subtotal;
    }
    
    
    
     public LigneCommande(int idlc, int qte) {
        this.idlc=idlc;
        this.qte = qte;
    }

    public LigneCommande(int idlc, int qte, float subtotal) {
        this.idlc = idlc;
        this.qte = qte;
        this.subtotal = subtotal;
    }

    public LigneCommande() {
    }

    public int getIdlc() {
        return idlc;
    }

    public void setIdlc(int idlc) {
        this.idlc = idlc;
    }
    
    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public float getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(float subtotal) {
        this.subtotal = subtotal;
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final LigneCommande other = (LigneCommande) obj;
        if (!Objects.equals(this.commande.getIdcmd(), other.commande.getIdcmd())) {
            return false;
        }
        if (!Objects.equals(this.produit.getIdprod(), other.produit.getIdprod())) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "commande=" + commande + 
                "\tproduit=" + produit + 
                "\t qte=" + qte ;
    }
    
    
    
        
    
    
    
    
    
}

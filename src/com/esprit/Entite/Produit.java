/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.esprit.Entite;
import java.util.ArrayList;

/**
 *
 * @author Lenovo
 */
public class Produit {
    
    private int idprod;
    private String designation;
    private float prix;
    private int qtestock;
    private ArrayList<LigneCommande> ligneCommandes=new ArrayList<>();
    private Categorie categorie ;
    private Fournisseur fournisseur ;

    public Produit(int idprod, String designation, float prix, int qtestock, Categorie categorie, Fournisseur fournisseur) {
        this.idprod = idprod;
        this.designation = designation;
        this.prix = prix;
        this.qtestock = qtestock;
        this.categorie = categorie;
        this.fournisseur = fournisseur;
    }

     public Produit(String designation, float prix, int qtestock, Categorie categorie, Fournisseur fournisseur) {
        
        this.designation = designation;
        this.prix = prix;
        this.qtestock = qtestock;
        this.categorie = categorie;
        this.fournisseur = fournisseur;
    }

    public Produit() {
    }

    public int getIdprod() {
        return idprod;
    }

    public void setIdprod(int idprod) {
        this.idprod = idprod;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public int getQtestock() {
        return qtestock;
    }

    public void setQtestock(int qtestock) {
        this.qtestock = qtestock;
    }

    public ArrayList<LigneCommande> getLigneCommandes() {
        return ligneCommandes;
    }

    public void setLigneCommandes(ArrayList<LigneCommande> ligneCommandes) {
        this.ligneCommandes = ligneCommandes;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public Fournisseur getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(Fournisseur fournisseur) {
        this.fournisseur = fournisseur;
    }

    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Produit other = (Produit) obj;
        if (this.idprod != other.idprod) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "id Produit= "  + idprod + 
                "\tdesignation=" + designation + 
                "\tprix=" + prix +
                "\tquentité en stock=" + qtestock+
                "\tligneCommandes=" + ligneCommandes ;
    }
    
    
    
    
}

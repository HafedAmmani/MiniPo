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
public class Categorie {
    
    private int idcateg;
    private String nom;
    private ArrayList<Produit> produits=new ArrayList<>();

    public Categorie(int idcateg, String nom) {
        this.idcateg = idcateg;
        this.nom = nom;
    }
    
    public Categorie(String nom) {
        
        this.nom = nom;
    }
    
    public Categorie() {
        
    }

    public int getIdcateg() {
        return idcateg;
    }

    public void setIdcateg(int idcateg) {
        this.idcateg = idcateg;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public ArrayList<Produit> getProduits() {
        return produits;
    }

    public void setProduits(ArrayList<Produit> produits) {
        this.produits = produits;
    }

    @Override
    public String toString() {
        return  "\nidcateg=" + idcateg +
                "\tnom=" + nom + 
                "\nproduits=" + produits;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Categorie other = (Categorie) obj;
        if (this.idcateg != other.idcateg) {
            return false;
        }
        return true;
    }
    
}

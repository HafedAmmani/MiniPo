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
public class Fournisseur {
    
    private int idf;
    private String nom;
    private String adresse;
    private String tel;
    private String email;
    private ArrayList<Produit> produits=new ArrayList<>();

    public Fournisseur(int idf, String nom, String adresse, String tel, String email) {
        this.idf = idf;
        this.nom = nom;
        this.adresse = adresse;
        this.tel = tel;
        this.email = email;
    }
    
     public Fournisseur(String nom, String adresse, String tel, String email) {
        this.nom = nom;
        this.adresse = adresse;
        this.tel = tel;
        this.email = email;
    }
     
     public Fournisseur() {

    }

    public int getIdf() {
        return idf;
    }

    public void setIdf(int idf) {
        this.idf = idf;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<Produit> getProduits() {
        return produits;
    }

    public void setProduits(ArrayList<Produit> produits) {
        this.produits = produits;
    }

    @Override
    public String toString() {
        return "\nid fournisseur=" + idf +
                "\tnom=" + nom +
                "\tadresse=" + adresse +
                "\ttel=" + tel +
                "\temail=" + email +
                "\nproduits=" + produits;
    }
     
    
     
    
    
    
    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.Entite;

/**
 *
 * @author Lenovo
 */
public class Panier {
    
    private String designation;
    private float prix;
    private String nom;
    private int qte;

    public Panier(String designation, float prix, String nom, int qte) {
        this.designation = designation;
        this.prix = prix;
        this.nom = nom;
        this.qte = qte;
    }
    
    public Panier() {
        
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

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    @Override
    public String toString() {
        return "Panier{" + "designation=" + designation + ", prix=" + prix + ", nom=" + nom + ", qte=" + qte + "}\n";
    }
    
    
    
}

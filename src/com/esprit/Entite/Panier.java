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
    
    private int idLc;
    private String designation;
    private float prix;
    private String nom;
    private int qte;
    private int idcmd;
    private float subtotal;


    
    public Panier(int idLc, String designation, float prix, String nom, int qte, int idcmd,float tot) {
        this.idLc = idLc;
        this.designation = designation;
        this.prix = prix;
        this.nom = nom;
        this.qte = qte;
        this.idcmd = idcmd;
        this.subtotal=tot;
    }

    
    
    public Panier() {
        
    }

    public int getId() {
        return idLc;
    }

    public void setId(int id) {
        this.idLc = id;
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

    public int getIdLc() {
        return idLc;
    }

    public void setIdLc(int idLc) {
        this.idLc = idLc;
    }

    public int getIdcmd() {
        return idcmd;
    }

    public void setIdcmd(int idcmd) {
        this.idcmd = idcmd;
    }

    public float getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(float subtotal) {
        this.subtotal = subtotal;
    }

    
    
    @Override
    public String toString() {
        return "Panier{" + "id=" + idLc + ", designation=" + designation + ", prix=" + prix + ", nom=" + nom + ", qte=" + qte + "}\n";
    }

   
    
    
    
}

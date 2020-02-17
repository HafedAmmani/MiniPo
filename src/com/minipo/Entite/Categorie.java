/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.minipo.Entite;

/**
 *
 * @author yacin
 */
public class Categorie {
    private int idcateg;
    private String nom;

    public Categorie(int idcateg, String nom) {
        this.idcateg = idcateg;
        this.nom = nom;
    }

    public Categorie(String nom) {
        this.nom = nom;
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

    @Override
    public String toString() {
        return "Categorie{" + "idcateg=" + idcateg + ", nom=" + nom + '}';
    }
    
    
    
}

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
public class Produit {
    private int idprod;
    private String designation;
    private int qtestock;
    private float prix;
    private int idcateg;
    private int idf;

    public Produit(String designation, int qtestock, float prix, int idcateg, int idf) {
        this.designation = designation;
        this.qtestock = qtestock;
        this.prix = prix;
        this.idcateg = idcateg;
        this.idf = idf;
    }

    public Produit(int idprod, String designation, int qtestock, float prix, int idcateg, int idf) {
        this.idprod = idprod;
        this.designation = designation;
        this.qtestock = qtestock;
        this.prix = prix;
        this.idcateg = idcateg;
        this.idf = idf;
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

    public int getQtestock() {
        return qtestock;
    }

    public void setQtestock(int qtestock) {
        this.qtestock = qtestock;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public int getIdcateg() {
        return idcateg;
    }

    public void setIdcateg(int idcateg) {
        this.idcateg = idcateg;
    }

    public int getIdf() {
        return idf;
    }

    public void setIdf(int idf) {
        this.idf = idf;
    }

    @Override
    public String toString() {
        return "Produit{" + "idprod=" + idprod + ", designation=" + designation + ", qtestock=" + qtestock + ", prix=" + prix + ", idcateg=" + idcateg + ", idf=" + idf + '}';
    }
    
    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.esprit.Entite;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Lenovo
 */
public class Produits {
    
    private SimpleStringProperty idprod;
    private SimpleStringProperty photo;
    private SimpleStringProperty designation;
    private SimpleStringProperty prix;
    private SimpleStringProperty qtestock;
    private SimpleStringProperty categ ;

    public Produits(int idprod, String photo, String designation, int prix, int qtestock, String categ) {
        this.idprod = new SimpleStringProperty(String.valueOf(idprod));
        this.photo = new SimpleStringProperty(photo);
        this.designation = new SimpleStringProperty(designation);
        this.prix = new SimpleStringProperty(String.valueOf(prix));
        this.qtestock = new SimpleStringProperty(String.valueOf(qtestock));
        this.categ = new SimpleStringProperty(categ);
    }

    
    
    
    public Produits() {
    }

    public String getIdprod() {
        return idprod.get();
    }

    public void setIdprod(String idprod) {
        this.idprod.set(idprod);
    }

    public String getPhoto() {
        return photo.get();
    }

    public void setPhoto(String photo) {
        this.photo.set(photo);
    }

    public String getDesignation() {
        return designation.get();
    }

    public void setDesignation(String designation) {
        this.designation.set(designation);
    }

    public String getPrix() {
        return prix.get();
    }

    public void setPrix(String prix) {
        this.prix.set(prix);
    }

    public String getQtestock() {
        return qtestock.get();
    }

    public void setQtestock(String qtestock) {
        this.qtestock.set(qtestock);
    }

    public String getCateg() {
        return categ.get();
    }

    public void setCateg(String categ) {
        this.categ.set(categ);
    }


    
    
    
    
}

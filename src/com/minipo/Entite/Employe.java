/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.minipo.Entite;

import java.sql.Date;

/**
 *
 * @author hafed
 */
public class Employe {
    private int idemp;
    private String nom;
    private String prenom;
    private String adresse;
    private String tel;
    private String email;
    private String salaire;
    private Date date;

    public Employe(int idemp, String nom, String prenom, String adresse, String tel, String email, String salaire) {
        this.idemp = idemp;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.tel = tel;
        this.email = email;
        this.salaire = salaire;
    }

    public Employe(String nom, String prenom, String adresse, String tel, String email, String salaire,Date date) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.tel = tel;
        this.email = email;
        this.salaire = salaire;
        this.date = date;
    }

    public Employe(int idemp, String nom, String prenom, String adresse, String tel, String email, String salaire ,Date date) {
        this.idemp = idemp;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.tel = tel;
        this.email = email;
        this.salaire = salaire;
        this.date=date;
    }

  
    

    public int getIdemp() {
        return idemp;
    }

    public void setIdemp(int idemp) {
        this.idemp = idemp;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
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

    public String getSalaire() {
        return salaire;
    }

    public void setSalaire(String salaire) {
        this.salaire = salaire;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Employe{" + "idemp=" + idemp + ", nom=" + nom + ", prenom=" + prenom + ", adresse=" + adresse + ", tel=" + tel + ", email=" + email + ", salaire=" + salaire + ", date=" + date + '}';
    }

    
    

    
}

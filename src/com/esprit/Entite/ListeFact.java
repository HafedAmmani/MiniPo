/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.Entite;

import java.sql.Date;

/**
 *
 * @author Lenovo
 */
public class ListeFact {
    
    private int idFact;
    private Date dateFact;
    private String etatFact;
    private String nom;
    private String prenom;
    private int idclt;
    private int idcmd;

    public ListeFact(int idFact, Date dateFact, String etatFact, String nom, String prenom, int idclt,int idcmd) {
        this.idFact = idFact;
        this.dateFact = dateFact;
        this.etatFact = etatFact;
        this.nom = nom;
        this.prenom = prenom;
        this.idclt = idclt;
        this.idcmd=idcmd;
    }

    public int getIdFact() {
        return idFact;
    }

    public void setIdFact(int idFact) {
        this.idFact = idFact;
    }

    public Date getDateFact() {
        return dateFact;
    }

    public void setDateFact(Date dateFact) {
        this.dateFact = dateFact;
    }

    public String getEtatFact() {
        return etatFact;
    }

    public void setEtatFact(String etatFact) {
        this.etatFact = etatFact;
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

    public int getIdclt() {
        return idclt;
    }

    public void setIdclt(int idclt) {
        this.idclt = idclt;
    }

    public int getIdcmd() {
        return idcmd;
    }

    public void setIdcmd(int idcmd) {
        this.idcmd = idcmd;
    }

    @Override
    public String toString() {
        return "ListeFact{" + "idFact=" + idFact + ", dateFact=" + dateFact + ", etatFact=" + etatFact + ", nom=" + nom + ", prenom=" + prenom + ", idclt=" + idclt + ", idcmd=" + idcmd + '}';
    }
    
    
   
    
    
    
    
}

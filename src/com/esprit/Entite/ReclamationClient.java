package com.esprit.Entite;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.Date;

/**
 *
 * @author darra
 */
public class ReclamationClient {
     private int idR;
    //private int idcmd;
   // private int id;
    private String type;
    private String objet;
    private String description;
    private String  etatr;
    private String firstname;
    private String lastname;
    private String reponse;
    private Date dateR;

    public ReclamationClient(int idR,  String type, String objet, String description, String etatr, String firstname, String lastname, Date dateR) {
        this.idR = idR;
       // this.idcmd = idcmd;
        this.type = type;
        this.objet = objet;
        this.description = description;
        this.etatr = etatr;
        this.firstname = firstname;
        this.lastname = lastname;
        this.dateR = dateR;
    }

    public ReclamationClient(int idR, String type, String objet, String description, String etatr, String firstname, String lastname, String reponse, Date dateR) {
        this.idR = idR;
        this.type = type;
        this.objet = objet;
        this.description = description;
        this.etatr = etatr;
        this.firstname = firstname;
        this.lastname = lastname;
        this.reponse = reponse;
        this.dateR = dateR;
    }

    public ReclamationClient() {
    }

    public String getReponse() {
        return reponse;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }
     
    public int getIdR() {
        return idR;
    }

    public void setIdR(int idR) {
        this.idR = idR;
    }

   

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getObjet() {
        return objet;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEtatr() {
        return etatr;
    }

    public void setEtatr(String etatr) {
        this.etatr = etatr;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Date getDateR() {
        return dateR;
    }

    public void setDateR(Date dateR) {
        this.dateR = dateR;
    }

    @Override
    public String toString() {
        return "ReclamationClient{" + "idR=" + idR +  ", type=" + type + ", objet=" + objet + ", description=" + description + ", etatr=" + etatr + ", firstname=" + firstname + ", lastname=" + lastname + ", dateR=" + dateR + '}';
    }
    
    
    
}

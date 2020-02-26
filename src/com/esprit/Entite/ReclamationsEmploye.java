/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.Entite;

import java.sql.Date;

/**
 *
 * @author darra
 */
public class ReclamationsEmploye {
    
     private int idRemp ;
    private int id;
    private String objet;
    private String description;
    private Date dateRemp;
    private String etatRemp;
    private String firstname;
    private String lastname ;
    private String reponse;

    public ReclamationsEmploye(int idRemp, int id, String objet, String description, Date dateRemp, String etatRemp, String firstname, String lastname) {
        this.idRemp = idRemp;
        this.id = id;
        this.objet = objet;
        this.description = description;
        this.dateRemp = dateRemp;
        this.etatRemp = etatRemp;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public ReclamationsEmploye(String objet, String description,  String etatRemp, Date dateRemp,String reponse) {
        this.objet = objet;
        this.description = description;
        this.dateRemp = dateRemp;
        this.etatRemp = etatRemp;
        this.reponse = reponse;
    }

    public ReclamationsEmploye(int idRemp, String objet, String description, Date dateRemp, String etatRemp, String firstname, String lastname) {
        this.idRemp = idRemp;
        this.objet = objet;
        this.description = description;
        this.dateRemp = dateRemp;
        this.etatRemp = etatRemp;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public ReclamationsEmploye(int idRemp, String objet, String description, Date dateRemp, String firstname, String lastname) {
        this.idRemp = idRemp;
        this.objet = objet;
        this.description = description;
        this.dateRemp = dateRemp;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public ReclamationsEmploye(int idRemp, String objet, String description, String etatr, String firstname, String lastname, Date dateRemp) {
        this.idRemp = idRemp;
        this.objet = objet;
        this.description = description;
        this.etatRemp = etatRemp;
        this.firstname = firstname;
        this.lastname = lastname;
        this.dateRemp = dateRemp;
    }

    public ReclamationsEmploye() {
        
    }

    public ReclamationsEmploye(int id, String sujet, String Description) {
        this.id = id;
        this.objet = sujet;
        this.description= Description;
    }



    public String getReponse() {
        return reponse;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }

    public int getIdRemp() {
        return idRemp;
    }

    public void setIdRemp(int idRemp) {
        this.idRemp = idRemp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Date getDateRemp() {
        return dateRemp;
    }

    public void setDateRemp(Date dateRemp) {
        this.dateRemp = dateRemp;
    }

    public String getEtatRemp() {
        return etatRemp;
    }

    public void setEtatRemp(String etatRemp) {
        this.etatRemp = etatRemp;
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

    
    
    
    
    
    
}

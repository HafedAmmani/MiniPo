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
public class ReclamationClient {
     private int idR;
    private int idcmd;
    private int id;
    private int idcatrec;
    private String nom;
    private String objet;
    private String description;
    private String  etatr;
    private String firstname;
    private String lastname;
    private String reponse;
    private Date dateR;

    public ReclamationClient(int idR,  String nom, String objet, String description, String etatr, String firstname, String lastname, Date dateR) {
        this.idR = idR;
       // this.idcmd = idcmd;
        this.nom = nom;
        this.objet = objet;
        this.description = description;
        this.etatr = etatr;
        this.firstname = firstname;
        this.lastname = lastname;
        this.dateR = dateR;
    }

    public ReclamationClient(int idR, String nom, String objet, String description, String etatr, String firstname, String lastname, String reponse, Date dateR) {
        this.idR = idR;
        this.nom = nom;
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
 public ReclamationClient(int idcatrec, String objet, String description, int id) {
        this.idcatrec =idcatrec;
        this.objet = objet;
        this.description = description;
        this.id=id;
        
    }
  public ReclamationClient( String objet, String description,int idcmd, int id) {
        this.idcmd =idcmd;
        this.objet = objet;
        this.description = description;
        this.id=id;
        
    }
    public ReclamationClient(String nom, String objet, String description, String etatr, Date dateR, String reponse) {
         this.nom = nom;
        this.objet = objet;
        this.description = description;
        this.etatr = etatr;
        this.dateR = dateR;
        this.reponse = reponse;
    }

    public ReclamationClient(int id, String nom, String text, String description) {
       this.id=id;
       this.nom = nom;
       this.objet = text;
       this.description = description;
       
    }
       public ReclamationClient(int idr,String nom, String objet, String description, String etatr, String firstname, String lastname, Date dateR, String reponse) {
                 this.idR=idr;
                 this.nom=nom;
                 this.objet=objet;
                 this.description=description;
                 this.etatr=etatr;
                 this.firstname=firstname;
                 this.lastname=lastname;
                 this.dateR=dateR;
                 this.reponse=reponse;
    }
 


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getIdcmd() {
        return idcmd;
    }

    public void setIdcmd(int idcmd) {
        this.idcmd = idcmd;
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

    public int getIdcatrec() {
        return idcatrec;
    }

    public void setIdcatrec(int idcatrec) {
        this.idcatrec = idcatrec;
    }
    

    @Override
    public String toString() {
        return "ReclamationClient{" + "idR=" + idR +  ", categorie=" + nom + ", objet=" + objet + ", description=" + description + ", etatr=" + etatr + ", firstname=" + firstname + ", lastname=" + lastname + ", dateR=" + dateR + '}';
    }
    
    
    
}

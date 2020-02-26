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
public class Reclamationemploye {
    private int idRemp ;
    private int id;
    private String objet;
    private String description;
    private Date dateRemp;
    private String etatRemp;

    public Reclamationemploye() {
    }

    public Reclamationemploye(int idRemp, String objet, String description,String etatRemp, Date dateRemp ) {
        this.idRemp = idRemp;
        this.objet = objet;
        this.description = description;
        this.etatRemp = etatRemp;
        this.dateRemp = dateRemp;
        
    }

    public Reclamationemploye(int id, String objet, String description, Date dateRemp, String etatRemp) {
        this.id= id;
        this.objet = objet;
        this.description = description;
        this.dateRemp = dateRemp;
        this.etatRemp = etatRemp;
    }

    

    public Reclamationemploye(int idRemp,int idemp, String description, Date dateRemp, String etatRemp) {
        this.idRemp = idRemp;
        this.id=id;
        this.description = description;
        this.dateRemp = dateRemp;
        this.etatRemp = etatRemp;
    }

    public Reclamationemploye(int idRemp, int idemp, String description, String etatRemp) {
        this.idRemp = idRemp;
        this.id= id;
        this.description = description;
        this.etatRemp = etatRemp;
    }

    public Reclamationemploye(int idemp,String description, Date dateRemp, String etatRemp) {
        this.id=id;
        this.description = description;
        this.dateRemp = dateRemp;
        this.etatRemp = etatRemp;
    }

    public Reclamationemploye(int idemp, String objet, String description) {
        this.id = id;
        this.objet = objet;
        this.description = description;
    }

    

    public void setIdemp(int idemp) {
        this.id = id;
    }

    public int getIdemp() {
        return id;
    }

    public int getIdRemp() {
        return idRemp;
    }

    public String getDescription() {
        return description;
    }

    public Date getDateRemp() {
        return dateRemp;
    }

    public String getEtatRemp() {
        return etatRemp;
    }

    public void setIdRemp(int idRemp) {
        this.idRemp = idRemp;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDateRemp(Date dateRemp) {
        this.dateRemp = dateRemp;
    }

    public void setEtatRemp(String etatRemp) {
        this.etatRemp = etatRemp;
    }
    
       public String getObjet() {
        return objet;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }
    
    
    
}

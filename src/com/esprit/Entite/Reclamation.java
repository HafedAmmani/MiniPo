/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.Entite;

import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author House
 */
public class Reclamation {
    private int idR;
    private int idclt;
    private int idcmd;
    private String description;
    private Date dateR;
    private String  etatr;

    public Reclamation(int idR, int idclt, int idcmd, String description, Date dateR, String etat) {
        this.idR = idR;
        this.idclt = idclt;
        this.idcmd = idcmd;
        this.description = description;
        this.dateR = dateR;
        this.etatr = etat;
    }

    public Reclamation(int idclt, int idcmd, String description, Date dateR, String etat) {
        this.idclt = idclt;
        this.idcmd = idcmd;
        this.description = description;
        this.dateR = dateR;
        this.etatr = etat;
    }

    public Reclamation(int idR, int idclt, int idcmd, String description, String etatr) {
        this.idR = idR;
        this.idclt = idclt;
        this.idcmd = idcmd;
        this.description = description;
        this.etatr = etatr;
    }
    

    public Reclamation() {
    }

    public Reclamation(int idr, String description, java.util.Date dater, String etatr, int idclt, int idcmd) {
        this.idR = idR;
        this.description = description;
        this.dateR = dateR;
        this.etatr = etatr;
        this.idclt = idclt;
        this.idcmd = idcmd;
        
        
        
       
    }

    public int getIdR() {
        return idR;
    }

    public int getIdclt() {
        return idclt;
    }

    public int getIdcmd() {
        return idcmd;
    }

    public String getDescription() {
        return description;
    }

    public Date getDateR() {
        return dateR;
    }

    public String getEtatr() {
        return etatr;
    }

    public void setIdR(int idR) {
        this.idR = idR;
    }

    public void setIdclt(int idclt) {
        this.idclt = idclt;
    }

    public void setIdcmd(int idcmd) {
        this.idcmd = idcmd;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDateR(Date dateR) {
        this.dateR = dateR;
    }

    public void setEtat(String etat) {
        this.etatr = etat;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "idR=" + idR + ", idclt=" + idclt + ", idcmd=" + idcmd + ", description=" + description + ", dateR=" + dateR + ", etat=" + etatr + '}';
    }

    


    
}

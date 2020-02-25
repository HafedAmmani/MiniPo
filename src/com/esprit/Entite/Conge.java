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
 * @author hafed
 */
public class Conge {
    private int idcon;
    private String type;
    private String datedebut;
    private String datefin;
    private int nbrjrs;
    private String description;
    private boolean etat;
    private String etatc;

    public Conge(int idcon, String type, String datedebut, String datefin, int nbrjrs, String description, boolean etat) {
        this.idcon = idcon;
        this.type = type;
        this.datedebut = datedebut;
        this.datefin = datefin;
        this.nbrjrs = nbrjrs;
        this.description = description;
        this.etat = etat;
    }

    public Conge(String type, String datedebut, String datefin, int nbrjrs, String description, boolean etat) {
        this.type = type;
        this.datedebut = datedebut;
        this.datefin = datefin;
        this.nbrjrs = nbrjrs;
        this.description = description;
        this.etat = etat;
    }
    public Conge(String type, String datedebut, String datefin, int nbrjrs, String description, boolean etat, String etatc) {
        this.type = type;
        this.datedebut = datedebut;
        this.datefin = datefin;
        this.nbrjrs = nbrjrs;
        this.description = description;
        this.etat = etat;
        this.etatc = etatc;
    }

    public Conge(int idc, String type, String datedebut, String datefin, int nbrjrs, String description, Boolean etat, String etatc) {
        this.idcon=idc;
        this.type = type;
        this.datedebut = datedebut;
        this.datefin = datefin;
        this.nbrjrs = nbrjrs;
        this.description = description;
        this.etat = etat;
        this.etatc = etatc;
    }



   
    public int getIdcon() {
        return idcon;
    }

    public void setIdcon(int idcon) {
        this.idcon = idcon;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDatedebut() {
        return datedebut;
    }

    public void setDatedebut(String datedebut) {
        this.datedebut = datedebut;
    }

    public String getDatefin() {
        return datefin;
    }

    public void setDatefin(String datefin) {
        this.datefin = datefin;
    }

    public int getNbrjrs() {
        return nbrjrs;
    }

    public void setNbrjrs(int nbrjrs) {
        this.nbrjrs = nbrjrs;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isEtat() {
        return etat;
    }

    public void setEtat(boolean etat) {
        this.etat = etat;
    }
        public String getEtatc() {
        return etatc;
    }

    public void setEtatc(String etatc) {
        this.etatc = etatc;
    }

    @Override
    public String toString() {
        return "Conge{" + "idcon=" + idcon + ", type=" + type + ", datedebut=" + datedebut + ", datefin=" + datefin + ", nbrjrs=" + nbrjrs + ", description=" + description + ", etat=" + etat + '}';
    }
    
    
    
}

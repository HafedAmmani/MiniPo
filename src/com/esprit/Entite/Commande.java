/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.Entite;

import java.sql.Date;

/**
 *
 * @author House
 */
public class Commande {
    private int idcmd;
    private float total;
    private Date datec;
    private String etatc;
    private int idfact;

    public Commande(int idcmd, float total, Date datec, String etatc, int idfact) {
        this.idcmd = idcmd;
        this.total = total;
        this.datec = datec;
        this.etatc = etatc;
        this.idfact = idfact;
    }

    public Commande(float total, Date datec, String etatc, int idfact) {
        this.total = total;
        this.datec = datec;
        this.etatc = etatc;
        this.idfact = idfact;
    }

    public Commande(Integer idc) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getIdcmd() {
        return idcmd;
    }

    public void setIdcmd(int idcmd) {
        this.idcmd = idcmd;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public Date getDatec() {
        return datec;
    }

    public void setDatec(Date datec) {
        this.datec = datec;
    }

    public String getEtatc() {
        return etatc;
    }

    public void setEtatc(String etatc) {
        this.etatc = etatc;
    }

    public int getIdfact() {
        return idfact;
    }

    public void setIdfact(int idfact) {
        this.idfact = idfact;
    }

    @Override
    public String toString() {
        return "Commande{" + "idcmd=" + idcmd + ", total=" + total + ", datec=" + datec + ", etatc=" + etatc + ", idfact=" + idfact + '}';
    }


    
}

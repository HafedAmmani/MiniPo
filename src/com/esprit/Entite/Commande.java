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
    private int idclt;
    private String refC;

    public Commande(int idcmd, float total, Date datec, String etatc, int idclt, String refC) {
        this.idcmd = idcmd;
        this.total = total;
        this.datec = datec;
        this.etatc = etatc;
        this.idclt = idclt;
        this.refC = refC;
    }

    public Commande(float total, Date datec, String etatc, int idclt, String refC) {
        this.total = total;
        this.datec = datec;
        this.etatc = etatc;
        this.idclt = idclt;
        this.refC = refC;
    }

    public Commande(int idcmd) {
        this.idcmd = idcmd;
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

    public int getIdclt() {
        return idclt;
    }

    public void setIdclt(int idclt) {
        this.idclt = idclt;
    }

    public String getRefC() {
        return refC;
    }

    public void setRefC(String refC) {
        this.refC = refC;
    }

    @Override
    public String toString() {
        return "Commande{" + "idcmd=" + idcmd + ", total=" + total + ", datec=" + datec + ", etatc=" + etatc + ", idclt=" + idclt + ", refC=" + refC + '}';
    }

    


    
}

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

enum EtatCommande {Validée, NonValidée} ;  
    


public class Commande {
    
    private int idcmd;
    private Date datec ;
    private float total;
    //private EtatCommande etatc;
    private String etatc;
    private Client client;

    public Commande(int idcmd, Date datec, float total, String etatc, Client client) {
        this.idcmd = idcmd;
        this.datec = datec;
        this.total = total;
        this.etatc = etatc;
        this.client = client;
    }
    
    
  
    public Commande(Date datec, float total, String etatc, Client client) {
        this.datec = datec;
        this.total = total;
        this.etatc = etatc;
        this.client = client;
    }

    public Commande(Date datec, float total, Client client) {
        this.datec = datec;
        this.total = total;
        this.client = client;
    }

    public Commande(int idcmd, String etatc) {
        this.idcmd = idcmd;
        this.etatc = etatc;
    }
    
    public Commande() {
    }


    public int getIdcmd() {
        return idcmd;
    }

    public void setIdc(int idc) {
        this.idcmd = idc;
    }

    public Date getDatec() {
        return datec;
    }

    public void setDatec(Date datec) {
        this.datec = datec;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getEtatc() {
        return etatc;
    }

    public void setEtatcmd(String etatcmd) {
        this.etatc = etatcmd;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
    

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Commande other = (Commande) obj;
        if (this.idcmd != other.idcmd) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "\nid commande= " + idcmd +
                "\tdate du commande=" + datec +
                "\ttotal=" + total +
                "\tetat du commande=" + etatc +
                "\nClient=" + client ;
                
    }


    
    
    
    
}
 
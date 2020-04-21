/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.Entite;

/**
 *
 * @author House
 */
public class Livraison {
   
    private Integer idliv;
  
    private String destination;
 
    private String etatl;
    
    private String dateliv;
    
    private String matriculeL;
 
    private User id;

    private Commande idc;

    public Livraison(Integer idliv, String destination, String etatl, String dateliv, String matriculeL, User id, Commande idc) {
        this.idliv = idliv;
        this.destination = destination;
        this.etatl = etatl;
        this.dateliv = dateliv;
        this.matriculeL = matriculeL;
        this.id = id;
        this.idc = idc;
    }

    public Livraison(String destination, String etatl, String dateliv, User id, Commande idc) {
        this.destination = destination;
        this.etatl = etatl;
        this.dateliv = dateliv;
        this.id = id;
        this.idc = idc;
    }

    public Livraison() {
    }

    public Livraison(Integer idliv) {
        this.idliv = idliv;
    }

    public Livraison(Integer idliv, String destination, String etatl, String dateliv) {
        this.idliv = idliv;
        this.destination = destination;
        this.etatl = etatl;
        this.dateliv = dateliv;
    }

    public Integer getIdliv() {
        return idliv;
    }

    public void setIdliv(Integer idliv) {
        this.idliv = idliv;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getEtatl() {
        return etatl;
    }

    public void setEtatl(String etatl) {
        this.etatl = etatl;
    }

    public String getDateliv() {
        return dateliv;
    }

    public void setDateliv(String dateliv) {
        this.dateliv = dateliv;
    }

    public String getMatriculeL() {
        return matriculeL;
    }

    public void setMatriculeL(String matriculeL) {
        this.matriculeL = matriculeL;
    }

    public User getId() {
        return id;
    }

    public void setId(User id) {
        this.id = id;
    }

    public Commande getIdc() {
        return idc;
    }

    public void setIdc(Commande idc) {
        this.idc = idc;
    }

    public String getLivreur() {
        return this.id.getUsername();
    }

    public String getCommandeRefC() {
        return this.idc.getRefC();
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idliv != null ? idliv.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Livraison)) {
            return false;
        }
        Livraison other = (Livraison) object;
        if ((this.idliv == null && other.idliv != null) || (this.idliv != null && !this.idliv.equals(other.idliv))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Livraison{" + "idliv=" + idliv + ", destination=" + destination + ", etatl=" + etatl + ", dateliv=" + dateliv + ", matriculeL=" + matriculeL + ", id=" + id + ", idc=" + idc + '}';
    }
}

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
enum EtatFacture {Payée, NonPayée} ;  
public class Facture {
    
    private int idFact;
    private Date datef;
    private String etatf;
    private Commande commande;

    public Facture(int idfact, Date dateFact, String etatFact, Commande commande) {
        this.idFact = idfact;
        this.datef = dateFact;
        this.etatf = etatFact;
        this.commande = commande;
    }

    public Facture(Date dateFact, String etatFact, Commande commande) {
        this.datef = dateFact;
        this.etatf = etatFact;
        this.commande = commande;
    }

    public Facture() {
    }

    public int getIdfact() {
        return idFact;
    }

    public void setIdfact(int idfact) {
        this.idFact = idfact;
    }

    public Date getDateFact() {
        return datef;
    }

    public void setDateFact(Date dateFact) {
        this.datef = dateFact;
    }

    public String getEtatFact() {
        return etatf;
    }

    public void setEtatFact(String etatFact) {
        this.etatf = etatFact;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Facture other = (Facture) obj;
        if (this.idFact != other.idFact) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "idfact=" + idFact +
               "\tdateFact=" + datef + 
               "\tetatFact=" + etatf + 
               "\tcommande=" + commande ;
    }
    
    
    
    
    
}

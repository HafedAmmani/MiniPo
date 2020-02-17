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
    private int idliv;
    private String destination;
    private String etatl;
    private int idc;
    private int idl;

    public Livraison(int idliv, String destination, String etatl, int idc, int idl) {
        this.idliv = idliv;
        this.destination = destination;
        this.etatl = etatl;
        this.idc = idc;
        this.idl = idl;
    }

    public Livraison(String destination, String etatl, int idc, int idl) {
        this.destination = destination;
        this.etatl = etatl;
        this.idc = idc;
        this.idl = idl;
    }

    public int getIdliv() {
        return idliv;
    }

    public void setIdliv(int idliv) {
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

    public int getIdc() {
        return idc;
    }

    public void setIdc(int idc) {
        this.idc = idc;
    }

    public int getIdl() {
        return idl;
    }

    public void setIdl(int idl) {
        this.idl = idl;
    }

    @Override
    public String toString() {
        return "Livraison{" + "idliv=" + idliv + ", destination=" + destination + ", etatl=" + etatl + ", idc=" + idc + ", idl=" + idl + '}';
    }

   


    
}

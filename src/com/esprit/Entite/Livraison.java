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

    @Override
    public String toString() {
        return "Livraison{" + "idliv=" + idliv + ", destination=" + destination + ", etatl=" + etatl + ", idc=" + idc + ", idl=" + idl + ", dateliv=" + dateliv + ", matriculeL=" + matriculeL + '}';
    }
    private int idliv;
    private String destination;
    private String etatl;
    private int idc;
    private int idl;
    private String dateliv;
    private String matriculeL;

    public Livraison(int idliv, String destination, String etatl, int idc, int idl, String dateliv, String matriculeL) {
        this.idliv = idliv;
        this.destination = destination;
        this.etatl = etatl;
        this.idc = idc;
        this.idl = idl;
        this.dateliv = dateliv;
        this.matriculeL = matriculeL;
    }

   

    public Livraison(String destination, String etatl, int idc, int idl, String dateliv, String matriculeL) {
        this.destination = destination;
        this.etatl = etatl;
        this.idc = idc;
        this.idl = idl;
        this.dateliv = dateliv;
        this.matriculeL = matriculeL;
    }

    public Livraison(String destination, String etatl, int idc, int idl) {
        this.destination = destination;
        this.etatl = etatl;
        this.idc = idc;
        this.idl = idl;
    }
 
  public Livraison(String destination, String etatl, int idc, int idl, String matriculeL) {
        this.destination = destination;
        this.etatl = etatl;
        this.idc = idc;
        this.idl = idl;
        this.matriculeL = matriculeL;
    }

    public Livraison(int idliv, String destination, String etatl, int idc, int idl) {
        this.idliv = idliv;
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

    public String getDateliv() {
        return dateliv;
    }

    public void setDateliv(String dateliv) {
        this.dateliv = dateliv;
    }

    public void setMatriculeL(String matriculeL) {
        this.matriculeL = matriculeL;
    }

  public String getMatriculeL() {
        return matriculeL;
    }
   


    
}

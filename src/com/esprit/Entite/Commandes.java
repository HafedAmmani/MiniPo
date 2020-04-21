/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.Entite;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Lenovo
 */
public class Commandes {
    
    private SimpleStringProperty idCmd;
    private SimpleStringProperty refC;
    private SimpleStringProperty nomClt;
    private SimpleStringProperty prenomClt;
    private SimpleStringProperty datec;
    private SimpleStringProperty etatc;
    private SimpleStringProperty total;

    public Commandes(int idCmd, String nomClt, String prenomClt, String datec, String etatc,float total,String refC) {

        this.idCmd = new SimpleStringProperty(String.valueOf(idCmd));
        this.nomClt = new SimpleStringProperty(nomClt);
        this.prenomClt = new SimpleStringProperty(prenomClt);
        this.datec = new SimpleStringProperty(datec);
        this.etatc = new SimpleStringProperty(etatc);
        this.total = new SimpleStringProperty(String.valueOf(total));
        this.refC = new SimpleStringProperty(refC);
    }
    

    public String getIdCmd() {
        return idCmd.get();
        
    }

    public void setIdCmd(String idCmd) {
        this.idCmd.set(idCmd);
    }

    public String getNomClt() {
        
        return nomClt.get();
    }

    public void setNomClt(String nomClt) {
        this.nomClt.set(nomClt);
    }

    public String getPrenomClt() {
        return prenomClt.get();
    }

    public void setPrenomClt(String prenomClt) {
        this.prenomClt.set(prenomClt);
    }

    public String getDatec() {
        return datec.get();
    }

    public void setDatec(String datec) {
        this.datec.set(datec);
    }

    public String getEtatc() {
        return etatc.get();
    }

    public void setEtatc(String etatc) {
        this.etatc.set(etatc);
    }
    
    public float getTotal(){
    return Float.valueOf(total.get());
    }
    
    public void setTotal(){
        this.total.set(this.total.get());
    }
    
    public String getRefC() {
        return refC.get();
    }
    
    public void setRefC(String refC) {
        this.refC.set(refC);
    }

}
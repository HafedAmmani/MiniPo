/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.minipo.Entite;

/**
 *
 * @author hafed
 */
public class Affectation {
    private int idaff;
    private String NomEq;
    private String nom;

    public Affectation(int idaff, String ideq, String idemp) {
        this.idaff = idaff;
        this.NomEq = ideq;
        this.nom = idemp;
    }
    public Affectation( String NomEq, String nom) {
        
        this.NomEq = NomEq;
        this.nom = nom;
    }
    

    @Override
    public String toString() {
        return "Affectation{" + "idaff=" + idaff + ", NomEq=" + NomEq + ", nom=" + nom + '}';
    }
    

    public int getIdaff() {
        return idaff;
    }

    public void setIdaff(int idaff) {
        this.idaff = idaff;
    }

    public String getNomEq() {
        return NomEq;
    }

    public void setNomEq(String NomEq) {
        this.NomEq = NomEq;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }



    
}

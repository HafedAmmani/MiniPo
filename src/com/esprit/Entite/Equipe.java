/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.Entite;

/**
 *
 * @author hafed
 */
public class Equipe {
    private int ideq;
    private String NomEq;
    private int nombre;

    public Equipe(int ideq, String nomeq, int nombre) {
        this.ideq = ideq;
        this.NomEq = nomeq;
        this.nombre = nombre;
    }

    public Equipe(String nomeq, int nombre) {
        this.NomEq = nomeq;
        this.nombre = nombre;
    }

    public Equipe() {
       
    }

    public int getIdeq() {
        return ideq;
    }

    public void setIdeq(int ideq) {
        this.ideq = ideq;
    }

    public String getNomeq() {
        return NomEq;
    }

    public void setNomeq(String nomeq) {
        this.NomEq = nomeq;
    }

    public int getNombre() {
        return nombre;
    }

    public void setNombre(int nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Equipe{" + "ideq=" + ideq + ", nomeq=" + NomEq + ", nombre=" + nombre + '}';
    }
    
}

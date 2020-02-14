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
public class Equipe {
    private int ideq;
    private String nomeq;
    private int nombre;

    public Equipe(int ideq, String nomeq, int nombre) {
        this.ideq = ideq;
        this.nomeq = nomeq;
        this.nombre = nombre;
    }

    public Equipe(String nomeq, int nombre) {
        this.nomeq = nomeq;
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
        return nomeq;
    }

    public void setNomeq(String nomeq) {
        this.nomeq = nomeq;
    }

    public int getNombre() {
        return nombre;
    }

    public void setNombre(int nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Equipe{" + "ideq=" + ideq + ", nomeq=" + nomeq + ", nombre=" + nombre + '}';
    }
    
}

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
    private int ideq;
    private int idemp;

    public Affectation(int idaff, int ideq, int idemp) {
        this.idaff = idaff;
        this.ideq = ideq;
        this.idemp = idemp;
    }

    public Affectation(int ideq, int idemp) {
        this.ideq = ideq;
        this.idemp = idemp;
    }

    public int getIdaff() {
        return idaff;
    }

    public void setIdaff(int idaff) {
        this.idaff = idaff;
    }

    public int getIdeq() {
        return ideq;
    }

    public void setIdeq(int ideq) {
        this.ideq = ideq;
    }

    public int getIdemp() {
        return idemp;
    }

    public void setIdemp(int idemp) {
        this.idemp = idemp;
    }

    @Override
    public String toString() {
        return "Affectation{" + "idaff=" + idaff + ", ideq=" + ideq + ", idemp=" + idemp + '}';
    }
    
}

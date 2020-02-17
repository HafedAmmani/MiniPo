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
public class Livreur {
    private int idl;
    private String login;
    private String password;
    private String nom;
    private String prenom;
    private float salaire;
    private String email;
    private String tel;

    public Livreur(int idl, String login, String password, String nom, String prenom, float salaire, String email, String tel) {
        this.idl = idl;
        this.login = login;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.salaire = salaire;
        this.email = email;
        this.tel = tel;
    }

    public Livreur(String login, String password, String nom, String prenom, float salaire, String email, String tel) {
        this.login = login;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.salaire = salaire;
        this.email = email;
        this.tel = tel;
    }

    public int getIdl() {
        return idl;
    }

    public void setIdl(int idl) {
        this.idl = idl;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public float getSalaire() {
        return salaire;
    }

    public void setSalaire(float salaire) {
        this.salaire = salaire;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Override
    public String toString() {
        return "Livreur{" + "idl=" + idl + ", login=" + login + ", password=" + password + ", nom=" + nom + ", prenom=" + prenom + ", salaire=" + salaire + ", email=" + email + ", tel=" + tel + '}';
    }



    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.Entite;
import java.util.ArrayList;

/**
 *
 * @author Lenovo
 */
public class Client {
    
    private int id;
    private String login;
    private String password;
    private String cin;
    private String nom;
    private String prenom;
    private String adresse;
    private String tel;
    private String email;
    private ArrayList<Commande> commandes=new ArrayList<Commande>();

    public Client(int id, String login, String password, String cin, String nom, String prenom, String adresse, String tel, String email) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.tel = tel;
        this.email = email;
    }

    public Client(String login, String password, String cin, String nom, String prenom, String adresse, String tel, String email) {
        this.login = login;
        this.password = password;
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.tel = tel;
        this.email = email;
    }

     public Client() {
    
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public ArrayList<Commande> getCommandes() {
        return commandes;
    }

    public void setCommandes(ArrayList<Commande> commandes) {
        this.commandes = commandes;
    }

    @Override
    public String toString() {
        return  "id =" + id + 
                "\tnom=" + nom +
                "\tprenom=" + prenom + 
                "\tadresse=" + adresse + 
                "\ttel=" + tel + 
                "\temail=" + email ;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Client other = (Client) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

  
    
    
     
    
    
    
    
    
    
}

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
public class User {
    
    private int id;
    private String username;
    private String username_canonical;
    private String password;
    private String Firstname;
    private String Lastname;
    private String adresse;
    private String email;
    private String email_canonical;
    private int enabled;
    private String salt;
    private Date las_log;
    private String confirmation_token;
    private String password_requested_at;
    private String roles;
    private String Genre;


    public User(int id, String login, String password, String nom, String prenom, String adresse, String email) {
        this.id = id;
        this.username = login;
        this.password = password;
        this.Firstname = nom;
        this.Lastname = prenom;
        this.adresse = adresse;
        this.email = email;
    }

    public User(String login, String password, String nom, String prenom, String adresse, String tel, String email) {
        this.username = login;
        this.password = password;
        this.Firstname = nom;
        this.Lastname = prenom;
        this.adresse = adresse;
        this.email = email;
    }

    public User(int id, String username, String username_canonical, String password, String Firstname, String Lastname, String adresse, String email, String email_canonical, int enabled, String salt, Date las_log, String confirmation_token, String password_requested_at, String roles, String Genre) {
        this.id = id;
        this.username = username;
        this.username_canonical = username_canonical;
        this.password = password;
        this.Firstname = Firstname;
        this.Lastname = Lastname;
        this.adresse = adresse;
        this.email = email;
        this.email_canonical = email_canonical;
        this.enabled = enabled;
        this.salt = salt;
        this.las_log = las_log;
        this.confirmation_token = confirmation_token;
        this.password_requested_at = password_requested_at;
        this.roles = roles;
        this.Genre = Genre;
    }
    
    

     public User() {
    
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return Firstname;
    }

    public void Firstname(String nom) {
        this.Firstname = nom;
    }

    public String getLastname() {
        return Lastname;
    }

    public void setLastname(String prenom) {
        this.Lastname = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername_canonical() {
        return username_canonical;
    }

    public void setUsername_canonical(String username_canonical) {
        this.username_canonical = username_canonical;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail_canonical() {
        return email_canonical;
    }

    public void setEmail_canonical(String email_canonical) {
        this.email_canonical = email_canonical;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Date getLas_log() {
        return las_log;
    }

    public void setLas_log(Date las_log) {
        this.las_log = las_log;
    }

    public String getConfirmation_token() {
        return confirmation_token;
    }

    public void setConfirmation_token(String confirmation_token) {
        this.confirmation_token = confirmation_token;
    }

    public String getPassword_requested_at() {
        return password_requested_at;
    }

    public void setPassword_requested_at(String password_requested_at) {
        this.password_requested_at = password_requested_at;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getGenre() {
        return Genre;
    }

    public void setGenre(String Genre) {
        this.Genre = Genre;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", username=" + username + ", username_canonical=" + username_canonical 
                + ", password=" + password + ", Firstname=" + Firstname + ", Lastname=" + Lastname + ", adresse="
                + adresse + ", email=" + email + ", email_canonical=" + email_canonical + ", enabled=" + enabled 
                + ", salt=" + salt + ", las_log=" + las_log + ", confirmation_token=" + confirmation_token +
                ", password_requested_at=" + password_requested_at + ", roles=" + roles + ", Genre=" + Genre +
                '}';
    }
    
   
   

   

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

}

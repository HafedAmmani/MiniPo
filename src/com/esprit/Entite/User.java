/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.Entite;

import java.util.Date;

/**
 *
 * @author House
 */
public class User {
    private Integer id;
    private String username;
    private String usernameCanonical;
    private String email;
    private String emailCanonical;
    private short enabled;
    private String salt;
    private String password;
    private Date lastLogin;
    private String confirmationToken;
    private Date passwordRequestedAt;
    private String roles;
    private String firstname;
    private String lastname;
    private String genre;

    public User() {
    }

    public User(String username, String email, String password, String firstname, String lastname) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public User(String username, String email, String password, String firstname, String lastname, String genre) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.genre = genre;
    }

    public User(Integer id, String username, String usernameCanonical, String email, String emailCanonical, short enabled, String salt, String password, Date lastLogin, String confirmationToken, Date passwordRequestedAt, String roles, String firstname, String lastname, String genre) {
        this.id = id;
        this.username = username;
        this.usernameCanonical = usernameCanonical;
        this.email = email;
        this.emailCanonical = emailCanonical;
        this.enabled = enabled;
        this.salt = salt;
        this.password = password;
        this.lastLogin = lastLogin;
        this.confirmationToken = confirmationToken;
        this.passwordRequestedAt = passwordRequestedAt;
        this.roles = roles;
        this.firstname = firstname;
        this.lastname = lastname;
        this.genre = genre;
    }

    public User(Integer id, String username, String email, String password, String roles, String firstname, String lastname, String genre) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.firstname = firstname;
        this.lastname = lastname;
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", username=" + username + ", usernameCanonical=" + usernameCanonical + ", email=" + email + ", emailCanonical=" + emailCanonical + ", enabled=" + enabled + ", salt=" + salt + ", password=" + password + ", lastLogin=" + lastLogin + ", confirmationToken=" + confirmationToken + ", passwordRequestedAt=" + passwordRequestedAt + ", roles=" + roles + ", firstname=" + firstname + ", lastname=" + lastname + ", genre=" + genre + '}';
    }

    public User(String username, String email, String password, String roles, String firstname, String lastname, String genre) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.firstname = firstname;
        this.lastname = lastname;
        this.genre = genre;
    }

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getUsernameCanonical() {
        return usernameCanonical;
    }

    public String getEmail() {
        return email;
    }

    public String getEmailCanonical() {
        return emailCanonical;
    }

    public short getEnabled() {
        return enabled;
    }

    public String getSalt() {
        return salt;
    }

    public String getPassword() {
        return password;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public String getConfirmationToken() {
        return confirmationToken;
    }

    public Date getPasswordRequestedAt() {
        return passwordRequestedAt;
    }

    public String getRoles() {
        return roles;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getGenre() {
        return genre;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUsernameCanonical(String usernameCanonical) {
        this.usernameCanonical = usernameCanonical;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEmailCanonical(String emailCanonical) {
        this.emailCanonical = emailCanonical;
    }

    public void setEnabled(short enabled) {
        this.enabled = enabled;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public void setConfirmationToken(String confirmationToken) {
        this.confirmationToken = confirmationToken;
    }

    public void setPasswordRequestedAt(Date passwordRequestedAt) {
        this.passwordRequestedAt = passwordRequestedAt;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

   

    
    
}

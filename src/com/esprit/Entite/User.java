/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.Entite;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author sinda
 */
@Entity
@Table(name = "user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User_1.findAll", query = "SELECT u FROM User_1 u")
    , @NamedQuery(name = "User_1.findById", query = "SELECT u FROM User_1 u WHERE u.id = :id")
    , @NamedQuery(name = "User_1.findByFirstname", query = "SELECT u FROM User_1 u WHERE u.firstname = :firstname")
    , @NamedQuery(name = "User_1.findByLastname", query = "SELECT u FROM User_1 u WHERE u.lastname = :lastname")
    , @NamedQuery(name = "User_1.findByGenre", query = "SELECT u FROM User_1 u WHERE u.genre = :genre")
    , @NamedQuery(name = "User_1.findByMatricule", query = "SELECT u FROM User_1 u WHERE u.matricule = :matricule")
    , @NamedQuery(name = "User_1.findByAdresse", query = "SELECT u FROM User_1 u WHERE u.adresse = :adresse")
    , @NamedQuery(name = "User_1.findByTel", query = "SELECT u FROM User_1 u WHERE u.tel = :tel")
    , @NamedQuery(name = "User_1.findBySalaire", query = "SELECT u FROM User_1 u WHERE u.salaire = :salaire")
    , @NamedQuery(name = "User_1.findByDate", query = "SELECT u FROM User_1 u WHERE u.date = :date")
    , @NamedQuery(name = "User_1.findByUsername", query = "SELECT u FROM User_1 u WHERE u.username = :username")
    , @NamedQuery(name = "User_1.findByUsernameCanonical", query = "SELECT u FROM User_1 u WHERE u.usernameCanonical = :usernameCanonical")
    , @NamedQuery(name = "User_1.findByEmail", query = "SELECT u FROM User_1 u WHERE u.email = :email")
    , @NamedQuery(name = "User_1.findByEmailCanonical", query = "SELECT u FROM User_1 u WHERE u.emailCanonical = :emailCanonical")
    , @NamedQuery(name = "User_1.findByEnabled", query = "SELECT u FROM User_1 u WHERE u.enabled = :enabled")
    , @NamedQuery(name = "User_1.findBySalt", query = "SELECT u FROM User_1 u WHERE u.salt = :salt")
    , @NamedQuery(name = "User_1.findByPassword", query = "SELECT u FROM User_1 u WHERE u.password = :password")
    , @NamedQuery(name = "User_1.findByLastLogin", query = "SELECT u FROM User_1 u WHERE u.lastLogin = :lastLogin")
    , @NamedQuery(name = "User_1.findByConfirmationToken", query = "SELECT u FROM User_1 u WHERE u.confirmationToken = :confirmationToken")
    , @NamedQuery(name = "User_1.findByPasswordRequestedAt", query = "SELECT u FROM User_1 u WHERE u.passwordRequestedAt = :passwordRequestedAt")})
public class User implements Serializable {

    @OneToMany(mappedBy = "id")
    private Collection<Livraison> livraisonCollection;
    @OneToMany(mappedBy = "id")
    private Collection<Commande> commandeCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "Firstname")
    private String firstname;
    @Column(name = "Lastname")
    private String lastname;
    @Column(name = "Genre")
    private String genre;
    @Column(name = "matricule")
    private String matricule;
    @Column(name = "adresse")
    private String adresse;
    @Column(name = "tel")
    private String tel;
    @Column(name = "salaire")
    private String salaire;
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Basic(optional = false)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @Column(name = "username_canonical")
    private String usernameCanonical;
    @Basic(optional = false)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @Column(name = "email_canonical")
    private String emailCanonical;
    @Basic(optional = false)
    @Column(name = "enabled")
    private boolean enabled;
    @Column(name = "salt")
    private String salt;
    @Basic(optional = false)
    @Column(name = "password")
    private String password;
    @Column(name = "last_login")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLogin;
    @Column(name = "confirmation_token")
    private String confirmationToken;
    @Column(name = "password_requested_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date passwordRequestedAt;
    @Basic(optional = false)
    @Lob
    @Column(name = "roles")
    private String roles;

    public User() {
    }

    public User(Integer id) {
        this.id = id;
    }

    public User(Integer id, String username, String usernameCanonical, String email, String emailCanonical, boolean enabled, String password, String roles) {
        this.id = id;
        this.username = username;
        this.usernameCanonical = usernameCanonical;
        this.email = email;
        this.emailCanonical = emailCanonical;
        this.enabled = enabled;
        this.password = password;
        this.roles = roles;
    }

    public User(Integer id, String firstname, String lastname, String adresse, String username, String email, String password) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.adresse = adresse;
        this.username = username;
        this.email = email;
        this.password = password;
    }
    
    

    public User(Integer id, String username) {
        this.id = id;
        this.username = username;
    }

    public User(String username, String Lastname, String Firstname, String email, String password, String Genre, String roles) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
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

    public String getSalaire() {
        return salaire;
    }

    public void setSalaire(String salaire) {
        this.salaire = salaire;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsernameCanonical() {
        return usernameCanonical;
    }

    public void setUsernameCanonical(String usernameCanonical) {
        this.usernameCanonical = usernameCanonical;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmailCanonical() {
        return emailCanonical;
    }

    public void setEmailCanonical(String emailCanonical) {
        this.emailCanonical = emailCanonical;
    }

    public boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getConfirmationToken() {
        return confirmationToken;
    }

    public void setConfirmationToken(String confirmationToken) {
        this.confirmationToken = confirmationToken;
    }

    public Date getPasswordRequestedAt() {
        return passwordRequestedAt;
    }

    public void setPasswordRequestedAt(Date passwordRequestedAt) {
        this.passwordRequestedAt = passwordRequestedAt;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.esprit.Entite.User_1[ id=" + id + " ]";
    }

    @XmlTransient
    public Collection<Livraison> getLivraisonCollection() {
        return livraisonCollection;
    }

    public void setLivraisonCollection(Collection<Livraison> livraisonCollection) {
        this.livraisonCollection = livraisonCollection;
    }

    @XmlTransient
    public Collection<Commande> getCommandeCollection() {
        return commandeCollection;
    }

    public void setCommandeCollection(Collection<Commande> commandeCollection) {
        this.commandeCollection = commandeCollection;
    }

}

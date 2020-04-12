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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "commande")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Commande_1.findAll", query = "SELECT c FROM Commande_1 c")
    , @NamedQuery(name = "Commande_1.findByIdcmd", query = "SELECT c FROM Commande_1 c WHERE c.idcmd = :idcmd")
    , @NamedQuery(name = "Commande_1.findByTotal", query = "SELECT c FROM Commande_1 c WHERE c.total = :total")
    , @NamedQuery(name = "Commande_1.findByDatec", query = "SELECT c FROM Commande_1 c WHERE c.datec = :datec")
    , @NamedQuery(name = "Commande_1.findByEtatc", query = "SELECT c FROM Commande_1 c WHERE c.etatc = :etatc")
    , @NamedQuery(name = "Commande_1.findByRefC", query = "SELECT c FROM Commande_1 c WHERE c.refC = :refC")})
public class Commande implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcmd")
    private Integer idcmd;
    @Basic(optional = false)
    @Column(name = "total")
    private float total;
    @Basic(optional = false)
    @Column(name = "datec")
    @Temporal(TemporalType.DATE)
    private Date datec;
    @Basic(optional = false)
    @Column(name = "etatc")
    private String etatc;
    @Column(name = "refC")
    private String refC;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idc")
    private Collection<Livraison> livraisonCollection;
    @JoinColumn(name = "id", referencedColumnName = "id")
    @ManyToOne
    private User id;

    public Commande() {
    }

    public Commande(Integer idcmd) {
        this.idcmd = idcmd;
    }

    public Commande(Integer idcmd, float total, Date datec, String etatc) {
        this.idcmd = idcmd;
        this.total = total;
        this.datec = datec;
        this.etatc = etatc;
    }

    public Commande(Integer idcmd, String refC) {
        this.idcmd = idcmd;
        this.refC = refC;
    }

    public Integer getIdcmd() {
        return idcmd;
    }

    public void setIdcmd(Integer idcmd) {
        this.idcmd = idcmd;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public Date getDatec() {
        return datec;
    }

    public void setDatec(Date datec) {
        this.datec = datec;
    }

    public String getEtatc() {
        return etatc;
    }

    public void setEtatc(String etatc) {
        this.etatc = etatc;
    }

    public String getRefC() {
        return refC;
    }

    public void setRefC(String refC) {
        this.refC = refC;
    }

    @XmlTransient
    public Collection<Livraison> getLivraisonCollection() {
        return livraisonCollection;
    }

    public void setLivraisonCollection(Collection<Livraison> livraisonCollection) {
        this.livraisonCollection = livraisonCollection;
    }

    public User getId() {
        return id;
    }

    public void setId(User id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcmd != null ? idcmd.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Commande)) {
            return false;
        }
        Commande other = (Commande) object;
        if ((this.idcmd == null && other.idcmd != null) || (this.idcmd != null && !this.idcmd.equals(other.idcmd))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.esprit.Entite.Commande_1[ idcmd=" + idcmd + " ]";
    }

}

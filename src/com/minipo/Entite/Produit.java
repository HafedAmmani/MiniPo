/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.minipo.Entite;

/**
 *
 * @author yacin
 */
public class Produit {
    private int idprod;
    private String designation;
    private String qtestock;
    private String prix;
    private String categorie;
    private String fournisseur;
    
    
    
	public Produit(int idprod, String designation, String qtestock, String prix, String categorie, String fournisseur) {
		this.idprod = idprod;
		this.designation = designation;
		this.qtestock = qtestock;
		this.prix = prix;
		this.categorie = categorie;
		this.fournisseur = fournisseur;
	}
	
	public Produit( String designation, String qtestock, String prix, String categorie, String fournisseur) {
		this.designation = designation;
		this.qtestock = qtestock;
		this.prix = prix;
		this.categorie = categorie;
		this.fournisseur = fournisseur;
	}
	
	public int getIdprod() {
		return idprod;
	}
	public void setIdprod(int idprod) {
		this.idprod = idprod;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getQtestock() {
		return qtestock;
	}
	public void setQtestock(String qtestock) {
		this.qtestock = qtestock;
	}
	public String getPrix() {
		return prix;
	}
	public void setPrix(String prix) {
		this.prix = prix;
	}
	public String getCategorie() {
		return categorie;
	}
	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}
	public String getFournisseur() {
		return fournisseur;
	}
	public void setFournisseur(String fournisseur) {
		this.fournisseur = fournisseur;
	}
	@Override
	public String toString() {
		return "Produit [idprod=" + idprod + ", designation=" + designation + ", qtestock=" + qtestock + ", prix="
				+ prix + ", categorie=" + categorie + ", fournisseur=" + fournisseur + "]";
	}
    
    

    
    
    
    
    
}

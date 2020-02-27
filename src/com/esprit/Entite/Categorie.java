package com.esprit.Entite;

public class Categorie {

	private int idcateg;
	private String nom;
	
	
	
	public Categorie(String nom) {
		this.nom = nom;
	}
	public Categorie(int idcateg, String nom) {
		this.idcateg = idcateg;
		this.nom = nom;
	}
	
	public int getIdcateg() {
		return idcateg;
	}
	public void setIdcateg(int idcateg) {
		this.idcateg = idcateg;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	@Override
	public String toString() {
		return "Categorie [idcateg=" + idcateg + ", nom=" + nom + "]";
	}
	
	
	
}

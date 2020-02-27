package com.esprit.Entite;

public class Fournisseur {

	private int idf;
    private String nom;
    private String adresse;
    private String tel;
    private String email;
    
    
	public Fournisseur(int idf, String nom, String adresse, String tel, String email) {
		this.idf = idf;
		this.nom = nom;
		this.adresse = adresse;
		this.tel = tel;
		this.email = email;
	}

	public Fournisseur(String nom, String adresse, String tel, String email) {
		this.nom = nom;
		this.adresse = adresse;
		this.tel = tel;
		this.email = email;
	}

	public int getIdf() {
		return idf;
	}

	public void setIdf(int idf) {
		this.idf = idf;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
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

	@Override
	public String toString() {
		return "Fournisseur [idf=" + idf + ", nom=" + nom + ", adresse=" + adresse + ", tel=" + tel + ", email=" + email
				+ "]";
	}

	
    
    
}

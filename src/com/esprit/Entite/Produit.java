package com.esprit.Entite;

public class Produit {
	private int idprod;
	private String designation;
	private int qtestock;
	private int prix;
	private int idcateg;
	private String nomcateg;
	private int idf;
	private String nomfour;
        private Categorie categorie ;
	
	
	public Produit(int idprod, String designation, int qtestock, int prix, int idcateg, String nomcateg, int idf,
			String nomfour) {
		super();
		this.idprod = idprod;
		this.designation = designation;
		this.qtestock = qtestock;
		this.prix = prix;
		this.idcateg = idcateg;
		this.nomcateg = nomcateg;
		this.idf = idf;
		this.nomfour = nomfour;
	}


	public Produit(String designation, int qtestock, int prix, int idcateg, String nomcateg, int idf, String nomfour) {
		super();
		this.designation = designation;
		this.qtestock = qtestock;
		this.prix = prix;
		this.idcateg = idcateg;
		this.nomcateg = nomcateg;
		this.idf = idf;
		this.nomfour = nomfour;
	}
	
	public Produit(int idprod, String designation, float prix, int qtestock, Categorie categorie) {
        this.idprod = idprod;
        this.designation = designation;
        this.prix = (int) prix;
        this.qtestock = qtestock;
        this.categorie = categorie;
    }

    public Produit() {
    }


	public Produit(int idprod) {
		super();
		this.idprod = idprod;
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


	public int getQtestock() {
		return qtestock;
	}


	public void setQtestock(int qtestock) {
		this.qtestock = qtestock;
	}


	public int getPrix() {
		return prix;
	}


	public void setPrix(int prix) {
		this.prix = prix;
	}


	public int getIdcateg() {
		return idcateg;
	}


	public void setIdcateg(int idcateg) {
		this.idcateg = idcateg;
	}


	public String getNomcateg() {
		return nomcateg;
	}


	public void setNomcateg(String nomcateg) {
		this.nomcateg = nomcateg;
	}


	public int getIdf() {
		return idf;
	}


	public void setIdf(int idf) {
		this.idf = idf;
	}


	public String getNomfour() {
		return nomfour;
	}


	public void setNomfour(String nomfour) {
		this.nomfour = nomfour;
	}

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }


	@Override
	public String toString() {
		return "Produit [idprod=" + idprod + ", designation=" + designation + ", qtestock=" + qtestock + ", prix="
				+ prix + ", idcateg=" + idcateg + ", nomcateg=" + nomcateg + ", idf=" + idf + ", nomfour=" + nomfour
				+ "]";
	}
	
	
	
	
}

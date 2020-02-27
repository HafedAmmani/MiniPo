package com.esprit.Service;

import com.esprit.Entite.Categorie;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.esprit.Entite.Produit;
import com.esprit.IService.IService;
import com.esprit.Utils.DataBase;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ServiceProduit implements IService<Produit> {

	
	private Connection con;
	private Statement ste;
	private PreparedStatement pste;
	
	public ServiceProduit() {
		con = DataBase.getInstance().getConnection();
	}
	
	@Override
	public void ajouter(Produit t) throws SQLException {
		PreparedStatement pre = con.prepareStatement("INSERT INTO `produit`(`designation`, `qtestock`, `prix`, `idcateg`, `idf`) VALUES (?,?,?,?,?)");

		pre.setString(1, t.getDesignation());
		pre.setInt(2, t.getQtestock());
		pre.setInt(3, t.getPrix());
		pre.setInt(4, t.getIdcateg());
		pre.setInt(5, t.getIdf());
		System.out.println("before execute update");
		pre.executeUpdate();
		System.out.println("after execute update");
		
		
	
	}

	@Override
	public boolean delete(Produit t) throws SQLException {
		PreparedStatement pre=con.prepareStatement("DELETE FROM `minipot`.`produit` WHERE idprod=?;");
		pre.setInt(1, t.getIdprod());
		int ex=pre.executeUpdate();
		System.out.println("delete succes");

		return ex!=0;
	}

	@Override
	public boolean update(Produit t) throws SQLException {
		System.out.println("i'm in update produit Service not working");
		PreparedStatement pre=con.prepareStatement("UPDATE `produit` SET `designation`=?,`qtestock`=?,`prix`=?,`idcateg`=?,`idf`=? WHERE `idprod` = ?;");
		System.out.println("i'm in update produit Service is workingggggggg");

		pre.setString(1, t.getDesignation());
		System.out.println(t.getDesignation());
		pre.setInt(2, t.getQtestock());
		System.out.println(t.getQtestock());
		pre.setInt(3, t.getPrix());
		System.out.println(t.getPrix());
		pre.setInt(4, t.getIdcateg());
		System.out.println(t.getIdcateg());
		pre.setInt(5, t.getIdf());
		System.out.println(t.getIdf());
		pre.setInt(6, t.getIdprod());
		System.out.println(t.getIdprod());
		int ex=pre.executeUpdate();
		return ex==1;
	}

	@Override
	public List<Produit> readAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public ObservableList<Produit> getAllProduit(){
		ObservableList oblist = FXCollections.observableArrayList();
		
		try {
			PreparedStatement st = con.prepareStatement("SELECT p.idprod, p.designation, p.qtestock, p.prix,c.idcateg, c.nom,f.idf, f.nom FROM produit p JOIN categorie c ON p.idcateg=c.idcateg JOIN fournisseur f ON f.idf=p.idf");
			ResultSet res =st.executeQuery();
			while(res.next()) {
				int idprod=res.getInt("p.idprod");
				String designation=res.getString("p.designation");
				int qtestock=res.getInt("p.qtestock");
				int prix=res.getInt("p.prix");
				int idcateg=res.getInt("c.idcateg");
				String nomcateg=res.getString("c.nom");
				int idf=res.getInt("f.idf");
				String nomfour=res.getString("f.nom");
				
				oblist.add(new Produit(idprod, designation, qtestock, prix, idcateg, nomcateg, idf, nomfour));
				
			}
			st.close();
		
		}catch(SQLException ex) {
			
		}
		return oblist;
	
	}
	
public Produit getProduit(int idprod) {
        Produit pp;
        pp = null;
        try {
        ste=con.createStatement();
        ResultSet rsp=ste.executeQuery("select * from produit where idprod="+idprod);
        
        while (rsp.next()) {  
            
            ServiceCategorie sc=new ServiceCategorie();
            Categorie c=sc.getCategorie(rsp.getInt("idcateg"));
            
            
               
            pp=new Produit(rsp.getInt("idprod"),rsp.getString("designation"),rsp.getFloat("prix"),rsp.getInt("qtestock"),c);  
            return pp;
        }
        
        
    
    }  catch (SQLException ex) {
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
            pp=null;
        }
        
        return pp;
    
    }
	
}

package com.esprit.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.esprit.Entite.Fournisseur;
import com.esprit.IService.IService;
import com.esprit.Utils.DataBase;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ServiceFournisseur implements IService<Fournisseur> {

    private Connection con;
    private Statement ste;
    private PreparedStatement pste;
    
    public ServiceFournisseur() {
        con = DataBase.getInstance().getConnection();
    }
	
	@Override
	public void ajouter(Fournisseur t) throws SQLException {
		PreparedStatement pre = con.prepareStatement("INSERT INTO `minipot`.`fournisseur` (`nom`, `adresse`, `tel`, `email`) VALUES (?, ?, ?, ?);");
        pre.setString(1, t.getNom());
        pre.setString(2, t.getAdresse());
        pre.setString(3, t.getTel());
        pre.setString(4, t.getEmail());
        pre.executeUpdate();
		
	}

	@Override
	public boolean delete(Fournisseur t) throws SQLException {
		PreparedStatement pre=con.prepareStatement("DELETE FROM `minipot`.`fournisseur` WHERE idf=?;");
        pre.setInt(1, t.getIdf());
        int ex=pre.executeUpdate();
        return ex!=0;
	}

	@Override
	public boolean update(Fournisseur t) throws SQLException {
PreparedStatement pre=con.prepareStatement("UPDATE `fournisseur` SET nom = ? , adresse = ? , tel = ?, email = ? where idf=?");
        
        pre.setString(1, t.getNom());
        pre.setString(2, t.getAdresse());
        pre.setString(3, t.getTel());
        pre.setString(4, t.getEmail());
        pre.setInt(5, t.getIdf());
        int ex=pre.executeUpdate();
        return ex==1;
	}

	@Override
	public List<Fournisseur> readAll() throws SQLException {
		List<Fournisseur> arr=new ArrayList<>();
        ste=con.createStatement();
        ResultSet rs=ste.executeQuery("select * from fournisseur");
        while (rs.next()){
                int idf = rs.getInt("idf");
                String nom=rs.getString("nom");
                String adresse=rs.getString("adresse");
                String tel=rs.getString("tel");
                String email=rs.getString("email");
                Fournisseur p=new Fournisseur(idf, nom, adresse, tel, email);
        arr.add(p);    
        }
        return arr;
	}
	
	public ObservableList<Fournisseur> getAllFournisseur(){

		ObservableList oblist = FXCollections.observableArrayList();
		
		try {
			PreparedStatement st = con.prepareStatement("select * from fournisseur");
			ResultSet res = st.executeQuery();
			while(res.next()) {
				int idf=res.getInt("idf");
				String nom = res.getString("nom");
				String adresse = res.getString("adresse");
				String tel = res.getString("tel");
				String email = res.getString("email");
				
				oblist.add(new Fournisseur(idf,nom,adresse,tel,email));
			}
			st.close();
		} catch(SQLException ex) {
			
		}
		return oblist;
	}

	
	public ObservableList<String> getFourNames() throws SQLException{
	    ObservableList<String> oblist = FXCollections.observableArrayList();
	    PreparedStatement st= con.prepareStatement("select nom from fournisseur");
	    ResultSet res= st.executeQuery();
	    while (res.next()) {        
	   	 
	        String nomF=res.getString("nom");
	              
	               oblist.add(nomF);
	               
	             
	    }
	    return oblist;

	}
	
	public int getIdByName (String n) throws SQLException {
		pste =con.prepareStatement("Select idf from fournisseur where nom=?");
		pste.setString(1, n);
		ResultSet rs= pste.executeQuery();
		while(rs.first()) {
			int id=rs.getInt("idf");
			
			return id;
		}
		return 0;
	}
	
	
	public ObservableList<String> getNames() throws SQLException{
	    ObservableList<String> oblist = FXCollections.observableArrayList();
	    PreparedStatement st= con.prepareStatement("select nom from fournisseur");
	    ResultSet res= st.executeQuery();
	    while (res.next()) {        
	   	 
	        String nomF=res.getString("nom");
	              
	               oblist.add(nomF);
	               
	             
	    }
	    return oblist;

	}
	
	
	
	public Fournisseur getFournisseur(int idf) {
        
        Fournisseur f=null;
        try {
        ste=con.createStatement();
        ResultSet rs=ste.executeQuery("select * from fournisseur where idf="+idf);
        
        while (rs.next()) {  
  
            f=new Fournisseur(rs.getInt("idf"),rs.getString("nom"),rs.getString("adresse"),rs.getString("tel"),rs.getString("email"));  
            return f;
        }
        
        
    
    }  catch (SQLException ex) {
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
            f=null;
        }
        
        return f;
    
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

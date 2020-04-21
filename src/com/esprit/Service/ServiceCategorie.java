package com.esprit.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.esprit.Entite.Categorie;
import com.esprit.IService.IService;
import com.esprit.Utils.DataBase;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ServiceCategorie implements IService<Categorie> {

	private Connection con;
        private Statement ste;
	private PreparedStatement pste;
    
    public ServiceCategorie(){
        con = DataBase.getInstance().getConnection();
    }

	@Override
	public void ajouter(Categorie t) throws SQLException {

		PreparedStatement pre = con.prepareStatement("INSERT INTO `minipot`.`categorie` (`nom`) VALUES ( ?);");
        pre.setString(1, t.getNom());
        pre.executeUpdate();
	}

	@Override
	public boolean delete(Categorie t) throws SQLException {
		PreparedStatement pre=con.prepareStatement("DELETE FROM `minipot`.`categorie` WHERE idcateg=?;");
        pre.setInt(1, t.getIdcateg());
        int ex=pre.executeUpdate();
        return ex!=0;
	}

	@Override
	public boolean update(Categorie t) throws SQLException {
		PreparedStatement pre=con.prepareStatement("UPDATE `categorie` SET nom = ? where idcateg=?");
        pre.setString(1, t.getNom());
        pre.setInt(2, t.getIdcateg());
        int rows=pre.executeUpdate();
        return rows!=0;
	}

	@Override
	public List<Categorie> readAll() throws SQLException {
		List<Categorie> arr = new ArrayList<>();
        ste=con.createStatement();
        ResultSet rs=ste.executeQuery("select * from categorie");
        while (rs.next()){
                    int idcateg=rs.getInt("idcateg");
                    String nom=rs.getString("nom");
                    Categorie p=new Categorie(idcateg, nom);
         arr.add(p);
        }
        return arr;
	}
	
	
public ObservableList<Categorie> getAllCategorie() {
        
        ObservableList<Categorie> oblist = FXCollections.observableArrayList();
        
         try {
	    PreparedStatement st= con.prepareStatement("select * from categorie");
	    ResultSet res= st.executeQuery();
     while (res.next()) {        
    	 int id=res.getInt("idcateg");
         String nomC=res.getString("nom");
               
                oblist.add(new Categorie(id,nomC));
               
     }
     st.close();
      } catch (SQLException ex) {
        }
         return oblist;
    }

	public ObservableList<String> getCatNames() throws SQLException{
    ObservableList<String> oblist = FXCollections.observableArrayList();
    PreparedStatement st= con.prepareStatement("select nom from categorie");
    ResultSet res= st.executeQuery();
    while (res.next()) {        
   	 
        String nomC=res.getString("nom");
              
               oblist.add(nomC);
               
             
    }
    return oblist;

}

public int getIdByName (String n) throws SQLException {
	pste =con.prepareStatement("Select idcateg from categorie where nom=?");
	pste.setString(1, n);
	ResultSet rs= pste.executeQuery();
	while(rs.first()) {
		int id=rs.getInt("idcateg");

		return id;
	}
	return 0;
}


public Categorie getCategorie(int idcateg){
    Categorie categ=null;
        try {
        ste=con.createStatement();
        ResultSet rs=ste.executeQuery("select * from categorie where idcateg="+idcateg);
        
        while (rs.next()) {  
  
            categ=new Categorie(rs.getInt("idcateg"),rs.getString("nom"));  
            return categ;
        }
        
        
    
    }  catch (SQLException ex) {
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
            categ=null;
        }
        
        return categ;
    
    }












}

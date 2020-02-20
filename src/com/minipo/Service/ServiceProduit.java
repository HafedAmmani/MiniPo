/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.minipo.Service;

import com.minipo.Entite.Produit;
import com.minipo.IService.IService;
import com.minipo.Utils.DataBase;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author yacin
 */
public class ServiceProduit implements IService<Produit>{

    private Connection con;
    private Statement ste;

    //lina bech tsire el connexion ta3 service lel bd
    public ServiceProduit() {
        con = DataBase.getInstance().getConnection();

    }
    
    @Override
    public void ajouter(Produit t) throws SQLException {
        PreparedStatement pre=con.prepareStatement("INSERT INTO `minipot`.`produit` ( `idprod`, `designation`, `qtestock`, `prix`, `idcateg`, `idf`) VALUES ( ?, ?, ?, ?, ?, ?);");
        pre.setInt(1, t.getIdprod());
        pre.setString(2, t.getDesignation());
        pre.setString(3, t.getQtestock());
        pre.setString(4, t.getPrix());
        pre.setString(5, t.getCategorie());
        pre.setString(6, t.getFournisseur());
        pre.executeUpdate();
    }

    @Override
    public boolean delete(Produit t) throws SQLException {
        PreparedStatement pre= con.prepareStatement("DELETE FROM `minipot`.`produit` WHERE idprod=?;");
        pre.setInt(1, t.getIdprod());
        int ex=pre.executeUpdate();
        return ex!=0;
    }

    @Override
    public boolean update(Produit t) throws SQLException {
        
        PreparedStatement pre=con.prepareStatement("UPDATE `produit` SET designation = ? , qtestock = ? , prix = ?, idcateg = ?, idf= ?");
        
        pre.setString(1, t.getDesignation());
        pre.setString(2, t.getQtestock());
        pre.setString(3, t.getPrix());
        pre.setString(4, t.getCategorie());
        pre.setString(5, t.getFournisseur());
        int ex=pre.executeUpdate();
        return ex==1;
        
    }

    @Override
    public List<Produit> readAll() throws SQLException {
        List<Produit> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from produit");
     while (rs.next()) {                
               int idprod=rs.getInt("idprod");
               String designation=rs.getString("designation");
               String qtestock=rs.getString("qtestock");
               String prix=rs.getString("prix");
               String categorie=rs.getString("categorie");
               String fournisseur =rs.getString("fournisseur");
               Produit p=new Produit(idprod, designation, qtestock, prix,categorie,fournisseur);
     arr.add(p);
     }
    return arr;
    }
    
    public ObservableList<Produit> getAllProduit() {
        List<Produit> array= new ArrayList<>();
        ObservableList obList = FXCollections.observableArrayList();
        ResultSet rs;//   obList.clear();
         try {
	    PreparedStatement st= con.prepareStatement("select * from produit");
	    ResultSet res= st.executeQuery();
     while (res.next()) {        
               int idprod=res.getInt("idprod");
               String designation=res.getString("designation");
               String qtestock=res.getString("qtestock");
               String prix=res.getString("prix");
               String categorie=res.getString("categorie");
               String fournisseur=res.getString("fournisseur");
                obList.add(new Produit(designation, qtestock, prix, categorie, fournisseur));
                System.out.println("dattttt = "+obList);
     }
     st.close();
      } catch (SQLException ex) {
        }
         return obList;
    }
    
}

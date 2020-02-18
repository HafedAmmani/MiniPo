/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.minipo.Service;

import com.minipo.Entite.Produit;
import com.minipo.IService.IService;
import com.minipo.Utils.DataBase;
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
    
    public boolean existeProduit(Produit t)  throws SQLException {
        ste=con.createStatement();
        ResultSet rs=ste.executeQuery("select * from produit where `idcateg`="+t.getIdcateg()+";");
        return rs.isBeforeFirst();
    }
    
    @Override
    public void ajouter(Produit t) throws SQLException {
        PreparedStatement pre=con.prepareStatement("INSERT INTO `minipot`.`produit` ( `idprod`, `designation`, `qtestock`, `prix`, `idcateg`, `idf`) VALUES ( ?, ?, ?, ?, ?, ?);");
        pre.setInt(1, t.getIdprod());
        pre.setString(2, t.getDesignation());
        pre.setInt(3, t.getQtestock());
        pre.setFloat(4, t.getPrix());
        pre.setInt(5, t.getIdcateg());
        pre.setInt(6, t.getIdf());
        pre.executeUpdate();
    }

    @Override
    public boolean delete(Produit t) throws SQLException {
        if (existeProduit(t)) return false;
       else {
           ste= con.createStatement();
           String deleteRequest ="DELETE FROM produit WHERE idprod="+t.getIdprod()+";";
           ste.executeUpdate(deleteRequest);
           return true;
       }
    }

    @Override
    public boolean update(Produit t) throws SQLException {
        /*
        PreparedStatement pre=con.prepareStatement("UPDATE `produit` SET designation = ? , qtestock = ? , prix = ?, idcateg = ?, idf= ?");
        
        pre.setString(1, t.getDesignation());
        pre.setInt(2, t.getQtestock());
        pre.setFloat(3, t.getPrix());
        pre.setInt(4, t.getIdcateg());
        pre.setInt(5, t.getIdf());
        int ex=pre.executeUpdate();
        return ex==1;
        */
        
        PreparedStatement pre=con.prepareStatement("UPDATE `produit` SET designation = ? , qtestock = ? , prix = ?, idcateg = ?, idf= ?");
        pre.setString(1, t.getDesignation());
        pre.setInt(2, t.getQtestock());
        pre.setFloat(3, t.getPrix());
        pre.setInt(4, t.getIdcateg());
        pre.setInt(5, t.getIdf());
        int rows=pre.executeUpdate();
        return rows!=0;
    }

    @Override
    public List<Produit> readAll() throws SQLException {
        List<Produit> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from produit");
     while (rs.next()) {                
               int idprod=rs.getInt("idprod");
               String designation=rs.getString("designation");
               int qtestock=rs.getInt("qtestock");
               float prix=rs.getFloat("prix");
               int idcateg=rs.getInt("idcateg");
               int idf=rs.getInt("idf");
               Produit p=new Produit(idprod, designation, qtestock, prix,idcateg,idf);
     arr.add(p);
     }
    return arr;
    }
    
}

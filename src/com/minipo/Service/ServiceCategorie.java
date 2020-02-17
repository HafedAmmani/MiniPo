/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.minipo.Service;

import com.minipo.Entite.Categorie;
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
public class ServiceCategorie implements IService<Categorie> {

    private Connection con;
    private Statement ste;
    
    public ServiceCategorie(){
        con = DataBase.getInstance().getConnection();
    }
    
    @Override
    public void ajouter(Categorie t) throws SQLException {
        PreparedStatement pre = con.prepareStatement("INSERT INTO `minipot`.`categorie` ( `idcateg`, `nom`) VALUES ( ?, ?);");
        pre.setInt(1, t.getIdcateg());
        pre.setString(2, t.getNom());
        pre.executeUpdate();
    }

    @Override
    public boolean delete(Categorie t) throws SQLException {
        PreparedStatement pre=con.prepareStatement("DELETE FROM `minipot`.`categorie` WHERE idcateg=?");
        pre.setInt(1, t.getIdcateg());
        int  ex=pre.executeUpdate();
        return ex!=0;    }

    @Override
    public boolean update(Categorie t) throws SQLException {
        PreparedStatement pre=con.prepareStatement("UPDATE `categorie` SET nom = ?");
        
        pre.setString(1, t.getNom());
        int ex=pre.executeUpdate();
        return ex==1;
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
    
}

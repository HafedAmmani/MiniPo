/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.minipo.Service;

import com.minipo.Entite.Employe;
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
 * @author hafed
 */
public class ServiceEmploye implements IService<Employe>{
    
    private Connection con;
    private Statement ste;

    public ServiceEmploye() {
        con = DataBase.getInstance().getConnection();

    }

    @Override
    public void ajouter(Employe t) throws SQLException {
        PreparedStatement pre=con.prepareStatement("INSERT INTO `minipot`.`employe` ( `nom`, `prenom`,`adresse`,`tel`,`email`,`salaire`) VALUES ( ?, ?, ?, ?, ?, ?);");
        pre.setString(1, t.getNom());
        pre.setString(2, t.getPrenom());
        pre.setString(3, t.getAdresse());
        pre.setInt(4, t.getTel());
        pre.setString(5,t.getEmail());
        pre.setString(6,t.getSalaire());
        pre.executeUpdate();
    }

    @Override
    public boolean delete(Employe t) throws SQLException {
        PreparedStatement pre=con.prepareStatement("DELETE FROM `minipot`.`employe` WHERE nom='hafed';");
        int ex=pre.executeUpdate();
        return ex!=0;
    }

    @Override
    public boolean update(Employe t) throws SQLException {
        PreparedStatement pre=con.prepareStatement("UPDATE `employe` SET nom = ?, prenom = ?, adresse = ?, tel = ?, email = ?, salaire = ? WHERE idemp = ?");
        pre.setString(1, t.getNom());
        pre.setString(2, t.getPrenom());
        pre.setString(3, t.getAdresse());
        pre.setInt(4, t.getTel());
        pre.setString(5,t.getEmail());
        pre.setString(6,t.getSalaire());
        int ex=pre.executeUpdate();
        return ex==1;
    }

    @Override
    public List<Employe> readAll() throws SQLException {
        List<Employe> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from employe");
     while (rs.next()) {                
               int idemp=rs.getInt(1);
               String nom=rs.getString("nom");
               String prenom=rs.getString("prenom");
               String adresse=rs.getString("adresse");
               int tel=rs.getInt("tel");
               String email=rs.getString("email");
               String salaire=rs.getString("salaire");
               Employe p=new Employe(idemp, nom, prenom, adresse, tel, email, salaire);
     arr.add(p);
     }
    return arr;
    }
    
}

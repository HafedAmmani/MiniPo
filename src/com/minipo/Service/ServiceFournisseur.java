/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.minipo.Service;

import com.minipo.Entite.Fournisseur;
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
public class ServiceFournisseur implements IService<Fournisseur>{

    private Connection con;
    private Statement ste;
    
    public ServiceFournisseur() {
        con = DataBase.getInstance().getConnection();
    }
    
    public boolean existeFournisseur(Fournisseur r)  throws SQLException {
        ste=con.createStatement();
        ResultSet rs=ste.executeQuery("select * from fournisseur where `idh`="+r.getIdf()+";");
        return rs.isBeforeFirst();
    }
    
    @Override
    public void ajouter(Fournisseur t) throws SQLException {
        PreparedStatement pre = con.prepareStatement("INSERT INTO `minipot`.`fournisseur` ( `idf`, `nom`, `adresse`, `tel`, `email`) VALUES ( ?, ?, ?, ?, ?);");
        pre.setInt(1, t.getIdf());
        pre.setString(2, t.getNom());
        pre.setString(3, t.getEmail());
        pre.setString(4, t.getTel());
        pre.setString(5, t.getEmail());
        pre.executeUpdate();
        
    }

    @Override
    public boolean delete(Fournisseur t) throws SQLException {
        PreparedStatement pre=con.prepareStatement("DELETE FROM `minipot`.`fournisseur` WHERE idf='1'");
        int ex=pre.executeUpdate();
        return ex!=0;
    }

    @Override
    public boolean update(Fournisseur t) throws SQLException {
        PreparedStatement pre=con.prepareStatement("UPDATE `fournisseur` SET nom = ? , adresse = ? , tel = ?, email = ?");
        
        pre.setString(1, t.getNom());
        pre.setString(2, t.getAdresse());
        pre.setString(3, t.getTel());
        pre.setString(4, t.getEmail());
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
    
}

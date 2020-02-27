/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.Service;

import com.esprit.Entite.Livreur;
import java.sql.SQLException;
import java.util.List;
import java.sql.*;
import com.esprit.Utils.DataBase;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.esprit.IService.IServiceLivreur;

/**
 *
 * @author House
 */
public class ServiceLivreur implements IServiceLivreur<Livreur> {

    private Connection con;
    private Statement ste;

    public ServiceLivreur() {
        con = DataBase.getInstance().getConnection();

    }

    public void ajouterLivreur(Livreur l) throws SQLException {
        ste = con.createStatement();
        String requeteInsert = "INSERT INTO `minipot`.`livreur` (`login`,`password`, `nom`, `prenom`,`salaire`, `email`, `tel`) VALUES ( '" + l.getLogin() + "', '" + l.getPassword() + "', '" + l.getNom() + "', '" + l.getPrenom() + "', '" + l.getSalaire() + "', '" + l.getEmail()+ "', '" + l.getTel() + "');";
        ste.executeUpdate(requeteInsert);
    }
    public void ajouter1(Livreur l) throws SQLException
    {
    PreparedStatement pre=con.prepareStatement("INSERT INTO `minipot`.`livreur` (`login`,`password`, `nom`, `prenom`,`salaire`, `email`, `tel`) VALUES ( ?, ?, ?, ?, ?, ?, ?);");
    pre.setString(1, l.getLogin());
    pre.setString(2, l.getPassword());
    pre.setString(3, l.getNom());
    pre.setString(4, l.getPrenom());
    pre.setFloat(5, l.getSalaire());
    pre.setString(6, l.getEmail());
    pre.setString(7, l.getTel());
    pre.executeUpdate();
    }
            

    public boolean deleteLivreur(Livreur l) throws SQLException {
        PreparedStatement pre=con.prepareStatement("DELETE FROM `minipot`.`livreur` WHERE `nom` = 'boudaya'");
        pre.executeUpdate();
        return true;
    }

    public boolean updateLivreur(Livreur l) throws SQLException {
        PreparedStatement pre=con.prepareStatement("UPDATE livreur SET login ='login' WHERE login='yyyyy'");
        pre.executeUpdate();
        return true;
    }

    public List<Livreur> readAllLivreur() throws SQLException {
    List<Livreur> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from livreur");
     while (rs.next()) {                
               Integer idl=rs.getInt("idl");
               String nom=rs.getString("nom");
               String login=rs.getString("login");
               String password=rs.getString("password");
               String prenom=rs.getString("prenom");
               Float salaire=rs.getFloat("salaire");
               String email=rs.getString("email");
               String tel=rs.getString("tel");
               Livreur p=new Livreur(idl, login,password,nom, prenom, salaire, email, tel);
     arr.add(p);
     }
    return arr;
    }
}

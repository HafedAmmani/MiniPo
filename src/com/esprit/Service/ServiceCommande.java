/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.esprit.Service;

import com.esprit.Entite.Commande;

import java.sql.SQLException;
import java.util.List;
import java.sql.*;
import com.minipo.Utils.DataBase;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.esprit.IService.IServiceCommande;

/**
 *
 * @author House
 */

public class ServiceCommande implements IServiceCommande<Commande> {

    private Connection con;
    private Statement ste;

    public ServiceCommande() {
        con = DataBase.getInstance().getConnection();

    }


    @Override
    public void ajouterCommande(Commande c) throws SQLException {
        ste = con.createStatement();
        String requeteInsert = "INSERT INTO `minipot`.`commande` (`total`,`datec`, `etatc`, `idfact`) VALUES ( '" + c.getTotal() + "', '" + c.getDatec() + "', '" + c.getEtatc() + "', '" + c.getIdfact() +"');";
        ste.executeUpdate(requeteInsert);
    }

    @Override
    public boolean deleteCommande(Commande c) throws SQLException {
        PreparedStatement pre=con.prepareStatement("DELETE FROM `minipot`.`commande` WHERE `idc` = '5'");
        pre.executeUpdate();
        return true;
    }

    @Override
    public boolean updateCommande(Commande c) throws SQLException {
        PreparedStatement pre=con.prepareStatement("UPDATE commande SET etatc ='validée' WHERE idc='1'");
        pre.executeUpdate();
        return true ;
    }

    @Override
    public List<Commande> readAllCommande() throws SQLException {
             List<Commande> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from commande");
     while (rs.next()) {                
               Integer idc=rs.getInt("idc");
               Float total=rs.getFloat("total");
               Date datec=rs.getDate("datec");
               String etatc=rs.getString("etatc");
               Integer idfact=rs.getInt("idfact");
               Commande p=new Commande(idc, total, datec, etatc, idfact);
     arr.add(p);
     }
    return arr;
    }
}

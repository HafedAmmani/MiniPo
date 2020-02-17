/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.esprit.Service;

import com.esprit.Entite.Livraison;
import java.sql.SQLException;
import java.util.List;
import java.sql.*;
import com.minipo.Utils.DataBase;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.esprit.IService.IServiceLivraison;

/**
 *
 * @author House
 */

public class ServiceLivraison implements IServiceLivraison<Livraison> {

    private Connection con;
    private Statement ste;

    public ServiceLivraison() {
        con = DataBase.getInstance().getConnection();

    }

    @Override
    public void ajouterLivraison(Livraison liv) throws SQLException {
        ste = con.createStatement();
        String requeteInsert = "INSERT INTO `minipot`.`livraison` (`destination`,`etatl`, `idc`, `idl`) VALUES ( '" + liv.getDestination() + "', '" + liv.getEtatl() + "', '" + liv.getIdc() + "', '" + liv.getIdl() +"');";
        ste.executeUpdate(requeteInsert);
    }

    @Override
    public boolean deleteLivraison(Livraison liv) throws SQLException {
        PreparedStatement pre=con.prepareStatement("DELETE FROM `minipot`.`livraison` WHERE `idl` = '5'");
        pre.executeUpdate();
        return true;
    }

    @Override
    public boolean updateLivraison(Livraison liv) throws SQLException {
        PreparedStatement pre=con.prepareStatement("UPDATE livraison SET destination ='bizerte' WHERE idliv='1'");
        pre.executeUpdate();
        return true;
    }

    @Override
    public List<Livraison> readAllLivraison() throws SQLException {
        List<Livraison> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from livraison liv,livreur l WHERE l.idl=liv.idl");
     while (rs.next()) {                
               Integer idliv=rs.getInt("idliv");
               String destination=rs.getString("destination");
               String etatl=rs.getString("etatl");
               Integer idc=rs.getInt("idc");
               Integer idl=rs.getInt("idl");
               Livraison p=new Livraison(idliv, destination, etatl, idc, idl);
     arr.add(p);
     }
    return arr;
    }

    @Override
    public List<Livraison> RechercheParCommande() throws SQLException {
        List<Livraison> list=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from livraison liv,commande c WHERE c.idcmd=liv.idc ORDER BY c.datec ASC");
     while (rs.next()) {                
               Integer idliv=rs.getInt("idliv");
               String destination=rs.getString("destination");
               String etatl=rs.getString("etatl");
               Integer idc=rs.getInt("idc");
               Integer idl=rs.getInt("idl");
               Livraison p=new Livraison(idliv, destination, etatl, idc, idl);
     list.add(p);
     }
    return list; 
    }
}

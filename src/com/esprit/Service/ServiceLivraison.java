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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
        PreparedStatement pre=con.prepareStatement("DELETE FROM `minipot`.`livraison` WHERE `idl` = ? ");
        pre.setInt(1, liv.getIdl());
        int ex=pre.executeUpdate();
        return ex!=0;
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
    public List<Livraison> RechercheLivraisonParDate() throws SQLException {
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
    public ObservableList<Livraison> getAllLivraison() {
        
        ObservableList obList = FXCollections.observableArrayList();
        
         try {
             PreparedStatement st=con.prepareStatement("select * from livraison");
//	    PreparedStatement st= con.prepareStatement("select * from livraison liv,commande c WHERE c.idcmd=liv.idc ORDER BY c.datec ASC");
	    ResultSet res= st.executeQuery();
     while (res.next()) {        
               Integer idliv=res.getInt("idliv");
               String destination=res.getString("destination");
               String etatl=res.getString("etatl");
               Integer idc=res.getInt("idc");
               Integer idl=res.getInt("idl");
                obList.add(new Livraison(idliv, destination, etatl, idc, idl));
                
     }
     st.close();
      } catch (SQLException ex) {
        }
         return obList;
    }
    public ObservableList<String> getIdLivreur() {
        ObservableList list = FXCollections.observableArrayList();
        ResultSet rs;//   obList.clear();
         try {
	    PreparedStatement st= con.prepareStatement("select idliv from livraison");
	    ResultSet res= st.executeQuery();
     while (res.next()) {        
               int idliv=res.getInt("idliv");
                list.add(String.valueOf(idliv));
     }
     st.close();
      } catch (SQLException ex) {
        }
         return list;
    }
    
}

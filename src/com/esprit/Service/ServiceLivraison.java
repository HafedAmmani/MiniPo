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

    
  /*  public void ajouterLivraison(Livraison liv) throws SQLException {
        ste = con.createStatement();
        String requeteInsert = "INSERT INTO `minipot`.`livraison` (`destination`,`etatl`, `idc`, `idl`) VALUES ( '" + liv.getDestination() + "', '" + liv.getEtatl() + "', '" + liv.getIdc() + "', '" + liv.getIdl() +"');";
        ste.executeUpdate(requeteInsert);
    }*/
    @Override
    public void ajouterLivraison(Livraison liv) throws SQLException
    {
    PreparedStatement pre=con.prepareStatement("INSERT INTO `minipot`.`livraison` (`destination`,`etatl`, `idc`, `idl`,`dateliv`, `matriculeL`) VALUES ( ?, ?, ?, ?, ?, ?);",Statement.RETURN_GENERATED_KEYS);
    pre.setString(1, liv.getDestination());
    pre.setString(2, liv.getEtatl());
    pre.setInt(3, liv.getIdc());
    pre.setInt(4, liv.getIdl());
    pre.setString(5, liv.getDateliv());
    pre.setString(6, "X"+liv.getIdliv());
    pre.executeUpdate();
     try (ResultSet generatedKeys = pre.getGeneratedKeys()){
         if (generatedKeys.next()){
             PreparedStatement stmt=con.prepareStatement("UPDATE `minipot`.`livraison` SET `matriculeL` =? WHERE `idliv`=?");
             stmt.setString(1,"X"+ generatedKeys.getLong(1));
             stmt.setInt(2, (int) generatedKeys.getLong(1));
             stmt.executeUpdate();
             try {
                 JavaEmailUtil.sendEmail("projetminipo@gmail.com", generatedKeys.getInt(1));
             } catch (Exception ex) {
                 Logger.getLogger(ServiceLivraison.class.getName()).log(Level.SEVERE, null, ex);
             }
         } else {
         throw new SQLException("Creating user failed, no ID obtained");
         }
         
     }
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
               String destination=res.getString("destination");
               String etatl=res.getString("etatl");
               Integer idc=res.getInt("idc");
               Integer idl=res.getInt("idl");
               String matriculeL=res.getString("matriculeL");
                obList.add(new Livraison(destination, etatl, idc, idl,matriculeL));
                
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
	    PreparedStatement st= con.prepareStatement("select idl from livreur");
	    ResultSet res= st.executeQuery();
     while (res.next()) {        
               int idl=res.getInt("idl");
                list.add(String.valueOf(idl));
     }
     st.close();
      } catch (SQLException ex) {
        }
         return list;
    }
      public ObservableList<Livraison> getLiv() {
        
        ObservableList obList = FXCollections.observableArrayList();
        
         try {
             PreparedStatement st=con.prepareStatement("select *, c.refC from livraison l , commande c WHERE l.idc=c.idcmd ORDER BY idliv");
//	    PreparedStatement st= con.prepareStatement("select * from livraison liv,commande c WHERE c.idcmd=liv.idc ORDER BY c.datec ASC");
	    ResultSet res= st.executeQuery();
     while (res.next()) {        
               String matriculeL=res.getString("matriculeL");
               String destination=res.getString("destination");
               String etatl=res.getString("etatl");
               Integer idc=res.getInt("idc");
               Integer idl=res.getInt("idl");
                obList.add(new Livraison(destination, etatl, idc, idl, matriculeL));      
     }
     st.close();
      } catch (SQLException ex) {
        }
         return obList;
    }
}

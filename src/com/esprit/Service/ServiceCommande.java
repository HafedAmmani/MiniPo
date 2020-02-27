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
import com.esprit.IService.IServiceCommande;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author House
 */

public class ServiceCommande implements IServiceCommande<Commande> {

    private final Connection con;
    private Statement ste;

    public ServiceCommande() {
        con = DataBase.getInstance().getConnection();

    }


    @Override
    public void ajouterCommande(Commande c) throws SQLException {
        ste = con.createStatement();
        String requeteInsert = "INSERT INTO `minipot`.`commande` (`total`,`datec`, `etatc`, `idfact`) VALUES ( '" + c.getTotal() + "', '" + c.getDatec() + "', '" + c.getEtatc() + "', '" + c.getIdclt() +"','" + c.getRefC() +"');";
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
               Integer idclt=rs.getInt("idclt");
               String refC=rs.getString("refC");
               Commande p=new Commande(idc, total, datec, etatc, idclt, refC);
     arr.add(p);
     }
    return arr;
    }

    @Override
    public List<Commande> readIdCommande() throws SQLException {
         List<Commande> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select idcmd from commande where etatc='validéé'");
     while (rs.next()) {                
               Integer idcmd=rs.getInt("idcmd");
               Commande p=new Commande(idcmd);
     arr.add(p);
     }
    return arr;
    }
    
    public ObservableList<String> getIdCommande() {
        ObservableList list = FXCollections.observableArrayList();
        //   obList.clear();
         try {
	    PreparedStatement st= con.prepareStatement("select idcmd from commande");
	    ResultSet res= st.executeQuery();
     while (res.next()) {        
               int idcmd=res.getInt("idcmd");
                list.add(String.valueOf(idcmd));
     }
     st.close();
      } catch (SQLException ex) {
        }
         return list;
    }
    public String getAdr(int id){
        String adr="";
        try {
            PreparedStatement st= con.prepareStatement("select u.adresse from user u ,commande c where u.id=c.idclt and c.idcmd="+id);
            ResultSet res= st.executeQuery();
            while(res.next()){
                adr=res.getString("adresse");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
        }
      //  System.out.println("error");
        return adr;
    }
    
}

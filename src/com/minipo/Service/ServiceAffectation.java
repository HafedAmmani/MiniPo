/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.minipo.Service;

import com.minipo.IService.IService;
import com.minipo.Utils.DataBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.minipo.Entite.Affectation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 *
 * @author hafed
 */
public class ServiceAffectation implements IService<Affectation>{
    private Connection con;
    private Statement ste;

    public ServiceAffectation() {
        con = DataBase.getInstance().getConnection();

    }

    @Override
    public void ajouter(Affectation a) throws SQLException {
        PreparedStatement pre= con.prepareStatement("INSERT INTO `minipot`.`affectation` ( `NomEq`, `nom`) VALUES ( ?, ?);");
        pre.setString(1, a.getNomEq());
        pre.setString(2, a.getNom());
        pre.executeUpdate();
    }

    @Override
    public boolean delete(Affectation a) throws SQLException {
        PreparedStatement pre=con.prepareStatement("DELETE FROM `minipot`.`affectation` WHERE idaff= ?;");
        pre.setInt(1, a.getIdaff());
        int ex=pre.executeUpdate();
        return ex!=0;
    }

    @Override
    public boolean update(Affectation a) throws SQLException {
        PreparedStatement pre=con.prepareStatement("UPDATE `equipe` SET idaff = ?, NomEq = ?, nom = ? WHERE idaffect = ?");
        pre.setInt(1, a.getIdaff());
        pre.setString(2, a.getNomEq());
        pre.setString(3, a.getNom());
        int ex=pre.executeUpdate();
        return ex==1;
    }

    @Override
    public List<Affectation> readAll() throws SQLException {
        List<Affectation> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from affectation");
     while (rs.next()) {                
               int idaff=rs.getInt("idaffect");
               String ideq=rs.getString("NomEq");
               String idemp=rs.getString("nom");
               Affectation p=new Affectation(idaff, ideq, idemp);
     arr.add(p);
     }
    return arr;
    }
     public ObservableList<Affectation> getAllAffectation() {
        
        ObservableList obList = FXCollections.observableArrayList();
        ResultSet rs;//   obList.clear();
         try {
	    PreparedStatement st= con.prepareStatement("select * from affectation");
	    ResultSet res= st.executeQuery();
     while (res.next()) {        
               int idaff=res.getInt("idaffect");
               String nom=res.getString("nom");
               String NomEq=res.getString("NomEq");
               
                obList.add(new Affectation(NomEq,nom));
               
     }
     st.close();
      } catch (SQLException ex) {
        }
         return obList;
    }
    
}

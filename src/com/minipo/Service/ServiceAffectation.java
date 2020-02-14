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
        PreparedStatement pre= con.prepareStatement("INSERT INTO `minipot`.`affectation` ( `ideq`, `idemp`) VALUES ( ?, ?);");
        pre.setInt(1, a.getIdeq());
        pre.setInt(2, a.getIdemp());
        pre.executeUpdate();
    }

    @Override
    public boolean delete(Affectation a) throws SQLException {
        PreparedStatement pre=con.prepareStatement("DELETE FROM `minipot`.`affectation` WHERE idaff='1';");
        int ex=pre.executeUpdate();
        return ex!=0;
    }

    @Override
    public boolean update(Affectation a) throws SQLException {
        PreparedStatement pre=con.prepareStatement("UPDATE `equipe` SET idaff = ?, ideq = ?, idemp = ? WHERE idaffect = ?");
        pre.setInt(1, a.getIdaff());
        pre.setInt(2, a.getIdeq());
        pre.setInt(3, a.getIdemp());
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
               int ideq=rs.getInt("ideq");
               int idemp=rs.getInt("idemp");
               Affectation p=new Affectation(idaff, ideq, idemp);
     arr.add(p);
     }
    return arr;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.Service;

import com.esprit.Entite.Conge;
import com.esprit.IService.Iconge;
import com.esprit.Utils.DataBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 *
 * @author hafed
 */
public class ServiceConge implements Iconge<Conge> {
    
    private Connection con;
    private Statement ste;

    public ServiceConge() {
        con = DataBase.getInstance().getConnection();

    }
    @Override
    public void ajouter(Conge t) throws SQLException {
        PreparedStatement pre= con.prepareStatement("INSERT INTO `minipot`.`conge` ( `type`, `datedebut`, `datefin`, `nbrjrs`,`description`, `etat`) VALUES ( ?, ?, ?, ?, ?,?);");
        pre.setString(1, t.getType());
        pre.setString(2, t.getDatedebut());
        pre.setString(3, t.getDatefin());
        pre.setInt(4, t.getNbrjrs());
        pre.setString(5, t.getDescription());
        pre.setBoolean(6, t.isEtat());
        pre.executeUpdate();
    }

    @Override
    public List<Conge> readAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.Service;


import com.esprit.Entite.Reclamationemploye;
import com.esprit.Utils.DataBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.util.Date; 
import java.sql.Time;

/**
 *
 * @author darra
 */
public class ServiceReclamationEmploye {
    
    private Connection con;
    private Statement ste;

    public ServiceReclamationEmploye() {
        con = DataBase.getInstance().getConnection();

    }
    public void ajouterReclamationEmploye(Reclamationemploye r) throws SQLException
    {
    PreparedStatement ps=con.prepareStatement("INSERT INTO `minipot`.`reclamationemploye` ( `idRemp`,`idemp`, `description`, `dateRemp`,`etatRemp`) VALUES ( ?,?, ?, ?, ?);");
    ps.setInt(1, r.getIdRemp());
    ps.setInt(2, r.getIdemp());
    ps.setString(3,r.getDescription());
    ps.setDate(4, r.getDateRemp());
    ps.setString(5, r.getEtatRemp());
    ps.executeUpdate();
    }
    
    public void Delete(int idemp ) throws SQLException {
        
         PreparedStatement ps = con.prepareStatement("DELETE FROM `reclamationemploye` where idemp=?");
         ps.setInt(1,idemp);
         ps.executeUpdate();
        
    }
    
    public void updateEmploye(int idemp,String description  ) throws SQLException {
         PreparedStatement ps=con.prepareStatement("UPDATE `reclamationemploye` set description=? where "
                 + "idemp=?");
         ps.setString(1, description);
         ps.setInt(2, idemp);
         ps.executeUpdate();
    }
    
     public void updateAdmin(int idemp ,String etatRemp) throws SQLException {
         PreparedStatement ps=con.prepareStatement("UPDATE `reclamationemploye` set etatRemp=? where idemp=?");
         ps.setString(1, etatRemp);
         ps.setInt(2, idemp);
         ps.executeUpdate();
     }
     
       public List<Reclamationemploye> readAll() throws SQLException {

           List<Reclamationemploye> rec=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from reclamationemploye");
     while (rs.next()) {   
         Reclamationemploye re=new Reclamationemploye();
               re.setIdRemp(rs.getInt(1));
               re.setIdemp(rs.getInt(2));
               re.setDescription(rs.getString(3));
               re.setDateRemp(rs.getDate(4));
               re.setEtatRemp(rs.getString(5));
               rec.add(re);
     }
    return rec;
    }
       
       public List<Reclamationemploye> ChercherReclamationParIdId(int idemp ) throws SQLException {
    List<Reclamationemploye> rec=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from reclamation where idemp="+idemp);
     while (rs.next()) {   
         Reclamationemploye re=new Reclamationemploye();
               re.setIdRemp(rs.getInt(1));
               re.setIdemp(rs.getInt(2));
               re.setDescription(rs.getString(3));
               re.setDateRemp(rs.getDate(4));
               re.setEtatRemp(rs.getString(5));
               rec.add(re);
     }
    return rec;
    }
       
       public List<Reclamationemploye> ChercherReclamationParEtat(String etatr ) throws SQLException {
    List<Reclamationemploye> rec=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from reclamation where etatr like '%"+etatr+"%'");
     while (rs.next()) {   
         Reclamationemploye re=new Reclamationemploye();
               re.setIdRemp(rs.getInt(1));
               re.setIdemp(rs.getInt(2));
               re.setDescription(rs.getString(3));
               re.setDateRemp(rs.getDate(4));
               re.setEtatRemp(rs.getString(5));
               rec.add(re);
     }
    return rec;
    }
       
       public List<Reclamationemploye> ChercherReclamationParIdcltetEtat(int idclt,String etatr ) throws SQLException {
    List<Reclamationemploye> rec=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from reclamation where etatr like '%"+etatr+"%'and idclt="+idclt);
     while (rs.next()) {   
         Reclamationemploye re=new Reclamationemploye();
               re.setIdRemp(rs.getInt(1));
               re.setIdemp(rs.getInt(2));
               re.setDescription(rs.getString(3));
               re.setDateRemp(rs.getDate(4));
               re.setEtatRemp(rs.getString(5));
               rec.add(re);
               
               rec.add(re);
     }
    return rec;
    }
    
    
    
}

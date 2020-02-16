/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.Service;

import com.esprit.Entite.Reclamation;
import com.esprit.IService.IService;
import java.sql.SQLException;
import java.util.List;
import java.sql.*;
import com.esprit.Utils.DataBase;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date; 
import java.sql.Time;

/**
 *
 * @author House
 */
public class ServiceReclamation  {

    private Connection con;
    private Statement ste;

    public ServiceReclamation() {
        con = DataBase.getInstance().getConnection();

    }

    /**@Override
    public void ajouter(Reclamation t) throws SQLException {
        ste = con.createStatement();
        String requeteInsert = "INSERT INTO `esprit1`.`personne` (`id`, `nom`, `prenom`, `age`) VALUES (NULL, '" + t.getNom() + "', '" + t.getPrenom() + "', '" + t.getAge() + "');";
        ste.executeUpdate(requeteInsert);
    }*/
    public void ajouterReclamation(Reclamation r) throws SQLException
    {
    PreparedStatement ps=con.prepareStatement("INSERT INTO `minipot`.`reclamation` ( `description`, `dater`, `etatr`,`idclt`,`idcmd`) VALUES ( ?, ?, ?, ?, ?);");
    ps.setString(1, r.getDescription());
    ps.setDate(2,r.getDateR());
    ps.setString(3, r.getEtatr());
    ps.setInt(4, r.getIdclt());
    ps.setInt(5, r.getIdcmd());
    ps.executeUpdate();
    }
            
    public void Delete(Integer r ) throws SQLException {
        
         PreparedStatement ps = con.prepareStatement("DELETE FROM `reclamation` where idclt=?");
         ps.setInt(1,r);
         ps.executeUpdate();
        
    }

    public void updateClient(int idclt,String description  ) throws SQLException {
         PreparedStatement ps=con.prepareStatement("UPDATE `reclamation` set description=? where "
                 + "idclt=?");
         ps.setString(1, description);
         ps.setInt(2, idclt);
         ps.executeUpdate();
    }
    
     public void updateAdmin(int idclt ,String etatr) throws SQLException {
         PreparedStatement ps=con.prepareStatement("UPDATE `reclamation` set etatr=? where idclt=?");
         ps.setString(1, etatr);
         ps.setInt(2, idclt);
         ps.executeUpdate();
     }
     
     
       public List<Reclamation> readAll() throws SQLException {
    List<Reclamation> rec=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from reclamation");
     while (rs.next()) {   
         Reclamation r=new Reclamation();
               r.setIdR(rs.getInt(1));
               r.setDescription(rs.getString(2));
               r.setDateR(rs.getDate(3));
               r.setEtat(rs.getString(4));
               r.setIdclt(rs.getInt(5));
               r.setIdcmd(rs.getInt(6));
               
               rec.add(r);
     }
    return rec;
    }
          public List<Reclamation> ChercherReclamationParIdId(int idclt ) throws SQLException {
    List<Reclamation> rec=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from reclamation where idclt="+idclt);
     while (rs.next()) {   
         Reclamation r=new Reclamation();
               r.setIdR(rs.getInt(1));
               r.setDescription(rs.getString(2));
               r.setDateR(rs.getDate(3));
               r.setEtat(rs.getString(4));
               r.setIdclt(rs.getInt(5));
               r.setIdcmd(rs.getInt(6));
               
               rec.add(r);
     }
    return rec;
    }
          
           public List<Reclamation> ChercherReclamationParEtat(String etatr ) throws SQLException {
    List<Reclamation> rec=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from reclamation where etatr like '%"+etatr+"%'");
     while (rs.next()) {   
         Reclamation r=new Reclamation();
               r.setIdR(rs.getInt(1));
               r.setDescription(rs.getString(2));
               r.setDateR(rs.getDate(3));
               r.setEtat(rs.getString(4));
               r.setIdclt(rs.getInt(5));
               r.setIdcmd(rs.getInt(6));
               
               rec.add(r);
     }
    return rec;
    }
           public List<Reclamation> ChercherReclamationParIdCommande(int idcmd ) throws SQLException {
    List<Reclamation> rec=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from reclamation where idclt="+idcmd);
     while (rs.next()) {   
         Reclamation r=new Reclamation();
               r.setIdR(rs.getInt(1));
               r.setDescription(rs.getString(2));
               r.setDateR(rs.getDate(3));
               r.setEtat(rs.getString(4));
               r.setIdclt(rs.getInt(5));
               r.setIdcmd(rs.getInt(6));
               
               rec.add(r);
     }
    return rec;
    }
           
         public List<Reclamation> ChercherReclamationParIdcltetEtat(int idclt,String etatr ) throws SQLException {
    List<Reclamation> rec=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from reclamation where etatr like '%"+etatr+"%'and idclt="+idclt);
     while (rs.next()) {   
         Reclamation r=new Reclamation();
               r.setIdR(rs.getInt(1));
               r.setDescription(rs.getString(2));
               r.setDateR(rs.getDate(3));
               r.setEtat(rs.getString(4));
               r.setIdclt(rs.getInt(5));
               r.setIdcmd(rs.getInt(6));
               
               rec.add(r);
     }
    return rec;
    }
     
  
         
         
         

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.Service;


import com.esprit.Entite.Reclamation;
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
import java.sql.Time;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
    PreparedStatement ps=con.prepareStatement("INSERT INTO `minipot`.`reclamationemploye` ( `id`, `objet`, `description`) VALUES ( ?,?,?);");
    //ps.setInt(1, r.getIdRemp());
    ps.setInt(1, r.getIdemp());
    ps.setString(2,r.getObjet());
    ps.setString(3,r.getDescription());
    //ps.setDate(5, r.getDateRemp());
    //ps.setString(6, r.getEtatRemp());
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
       
        /*public ObservableList<Object> ListertoutesLesReclamations() throws SQLException{
             //List<Reclamation> rec=new ArrayList<>();
             ObservableList oblist = FXCollections.observableArrayList();
             ste=con.createStatement();
             
             
             //ResultSet rs=ste.executeQuery("select r.idr,r.type,r.objet,r.description,r.etatr,r.dater,u.Firstname,u.Lastname from reclamation r ,user u where r.id=u.id;");
             ResultSet rs=ste.executeQuery("select r.idRemp,r.objet,r.objet,r.description,r.etatRemp,r.dateRemp,u.firstname,u.lastname from reclamation r inner join user u on  (r.id=u.id);");
             while (rs.next()) {
                //ResultSet rsu=ste.executeQuery("select * from user where id= "+rs.getInt("id"));
                //String nom="";
                //String prenom="";
                /*while (rsu.next()) {
                    nom=rsu.getString("firstname");
                    prenom=rsu.getString("lastname");
                }
                
         //Reclamation r=new Reclamation();
             int idr= rs.getInt("idr");
             //String type= rs.getString("type");
             String objet= rs.getString("objet");
             String description= rs.getString("description");
             String etatr= rs.getString("etatr");
             String nom=rs.getString("firstname");
             String prenom=rs.getString("lastname");
             java.sql.Date dateR=rs.getDate("dateRemp");
             oblist.add(new Reclamationemploye(idr, objet, description, etatr, dateR));
  
     }
            
        return oblist;
            
        }*/
       
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

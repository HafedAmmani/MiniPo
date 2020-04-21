/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.Service;

import com.esprit.Entite.User;
import com.esprit.Entite.Reclamation;
import com.esprit.Entite.ReclamationClient;
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
import java.sql.Time;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
    /*public void ajouterReclamation(Reclamation r) throws SQLException
    {
    PreparedStatement ps=con.prepareStatement("INSERT INTO `minipot`.`reclamation` ( `description`, `dater`, `etatr`,`id`,`idcmd`) VALUES ( ?, ?, ?, ?, ?);");
    ps.setString(1, r.getDescription());
    ps.setDate(2,r.getDateR());
    ps.setString(3, r.getEtatr());
    //ps.setInt(4, r.getIdclt());
    ps.setInt(5, r.getIdcmd());
    ps.executeUpdate();
    }*/
   public void  ajouterReclamation(ReclamationClient r) throws SQLException
    {
       // int st=0; 
    PreparedStatement ps=con.prepareStatement("INSERT INTO `minipot`.`reclamation` ( `idcatrec`, `objet`, `description`,`dater`,`etatr`,`id`) VALUES ( ?, ?, ?,sysdate(),'non traiter',?);");
    ps.setInt(1, r.getIdcatrec());
    ps.setString(2, r.getObjet());
    ps.setString(3, r.getDescription());
    ps.setInt(4, r.getId());
   
   // ps.setString(3, r.getEtatr());
    //ps.setInt(4, r.getIdclt());
   // ps.setInt(5, r.getIdcmd());
    ps.executeUpdate();
        System.out.println("jhsgdfjsdgjgfsdjgfsjgf");
    }
    public void  ajouterReclamationCommande(ReclamationClient r) throws SQLException
    {
       // int st=0; 
    PreparedStatement ps=con.prepareStatement("INSERT INTO `minipot`.`reclamation` ( `idcmd`,`idcatrec`, `objet`, `description`,`dater`,`etatr`,`id`) VALUES ( ?,2, ?, ?,sysdate(),'non traiter',?);");
    ps.setInt(1, r.getIdcmd());
    ps.setString(2, r.getObjet());
    ps.setString(3, r.getDescription());
    ps.setInt(4, r.getId());
   
   // ps.setString(3, r.getEtatr());
    //ps.setInt(4, r.getIdclt());
   // ps.setInt(5, r.getIdcmd());
    ps.executeUpdate();
        System.out.println("jhsgdfjsdgjgfsdjgfsjgf");
    }
     public void  ajouterReclamationAvecImage(Reclamation r) throws SQLException
    {
       // int st=0;
    PreparedStatement ps=con.prepareStatement("INSERT INTO `minipot`.`reclamation` ( `type`, `objet`, `description`,`image`,`dater`) VALUES ( ?,?, ?, ?,sysdate());");
    ps.setString(1, r.getType());
    ps.setString(2, r.getObjet());
    ps.setString(3, r.getDescription());
   
   // ps.setString(3, r.getEtatr());
    //ps.setInt(4, r.getIdclt());
   // ps.setInt(5, r.getIdcmd());
    ps.executeUpdate();
    }
            
    public void Delete(Integer r ) throws SQLException {
        
         PreparedStatement ps = con.prepareStatement("DELETE FROM `reclamation` where id=?");
         ps.setInt(1,r);
         ps.executeUpdate();
        
    }

    public void updateClient(int idr,String description  ) throws SQLException {
         PreparedStatement ps=con.prepareStatement("UPDATE `reclamation` set description=? where idr=?");
         ps.setString(1, description);
         ps.setInt(2, idr);
         ps.executeUpdate();
    }
    
     public void updateAdmin(String etatr,int idr,String reponse) throws SQLException {
         PreparedStatement ps=con.prepareStatement("UPDATE `reclamation` set etatr=? ,reponse=? where idr=?");
         ps.setString(1, etatr);
         ps.setString(2, reponse);
         ps.setInt(3, idr);
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
               //r.setIdclt(rs.getInt(5));
               r.setIdcmd(rs.getInt(6));
               
               rec.add(r);
     }
    return rec;
    }
       
        public ObservableList<Reclamation> ListertoutesLesReclamations() throws SQLException{
             //List<Reclamation> rec=new ArrayList<>();
             ObservableList oblist = FXCollections.observableArrayList();
             ste=con.createStatement();
             
             
             //ResultSet rs=ste.executeQuery("select r.idr,r.type,r.objet,r.description,r.etatr,r.dater,u.Firstname,u.Lastname from reclamation r ,user u where r.id=u.id;");
             ResultSet rs=ste.executeQuery("select r.idr,r.type,r.objet,r.description,r.etatr,r.dater,u.firstname,u.lastname from reclamation r inner join user u on  (r.id=u.id) order by dater asc;");
             while (rs.next()) {
                //ResultSet rsu=ste.executeQuery("select * from user where id= "+rs.getInt("id"));
                //String nom="";
                //String prenom="";
                /*while (rsu.next()) {
                    nom=rsu.getString("firstname");
                    prenom=rsu.getString("lastname");
                }*/
                
         //Reclamation r=new Reclamation();
             int idr= rs.getInt("idr");
             String type= rs.getString("type");
             String objet= rs.getString("objet");
             String description= rs.getString("description");
             String etatr= rs.getString("etatr");
             String firstname=rs.getString("firstname");
             String lastname=rs.getString("lastname");
             Date dateR=rs.getDate("dater");
             oblist.add(new Reclamation(idr,type, objet, description, etatr, firstname, lastname, dateR));
  //7elili controlleur ay ija ay 
     }
            
        return oblist;
            
        }
        public ObservableList<ReclamationClient> ListerReclamationsById(int id) throws SQLException{
             //List<Reclamation> rec=new ArrayList<>();
             
             ste=con.createStatement();
             List<ReclamationClient> listRec = new ArrayList<ReclamationClient>();
             //ResultSet rs=ste.executeQuery("select r.idr,r.type,r.objet,r.description,r.etatr,r.dater,u.Firstname,u.Lastname from reclamation r ,user u where r.id=u.id;");
             ResultSet rs=ste.executeQuery("select c.nom,r.objet,r.description,r.etatr,r.dater,r.reponse,r.idr from reclamation r join categorie_reclamation c on (r.idcatrec=c.idcatrec) where id="+id+" order by dater asc");
             while (rs.next()) {
                 ReclamationClient rc=new ReclamationClient();
                 rc.setNom(rs.getString("nom"));
                 rc.setObjet(rs.getString("objet"));
                 rc.setDescription(rs.getString("description"));
                 rc.setEtatr(rs.getString("etatr"));
                 rc.setDateR(rs.getDate("dater"));
                 rc.setReponse(rs.getString("reponse"));
                 rc.setIdR(rs.getInt("idr"));
                 listRec.add(rc);}
                ObservableList oblist = FXCollections.observableArrayList(listRec);
                return oblist;
                //ResultSet rsu=ste.executeQuery("select * from user where id= "+rs.getInt("id"));
                //String nom="";
                //String prenom="";
                /*while (rsu.next()) {
                    nom=rsu.getString("firstname");
                    prenom=rsu.getString("lastname");
                }*/
                
         //Reclamation r=new Reclamation();
             //int idr= rs.getInt("idr");
             //String type= rs.getString("type");
             //String objet= rs.getString("objet");
             //String description= rs.getString("description");
             //String etatr= rs.getString("etatr");
             //String firstname=rs.getString("firstname");
             //String lastname=rs.getString("lastname");
             //Date dateR=rs.getDate("dater");
            // oblist.add(new Reclamation(type, objet, description, etatr,dateR));
  
     //}
            
        //return oblist;
            
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
               //r.setIdclt(rs.getInt(5));
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
               //r.setIdclt(rs.getInt(5));
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
               //r.setIdclt(rs.getInt(5));
               r.setIdcmd(rs.getInt(6));
               
               rec.add(r);
     }
    return rec;
    }
           
//         public List<ReclamationClient> ChercherReclamationParIdcltetEtat(int idclt,String etatr ) throws SQLException {
//    List<ReclamationClient> rec=new ArrayList<>();
//    ste=con.createStatement();
//    ResultSet rs=ste.executeQuery("select * from reclamation where etatr like '%"+etatr+"%'and idclt="+idclt);
//     while (rs.next()) {   
//         ReclamationClient r=new ReclamationClient();
//               r.setIdR(rs.getInt(1));
//               r.setDescription(rs.getString(2));
//               r.setDateR(rs.getDate(3));
//               r.setEtatr(rs.getString(4));
//              // r.setIdclt(rs.getInt(5));
//               r.setIdcmd(rs.getInt(6));
//               
//               rec.add(r);
//     }
//    return rec;
//    }
     
  
     public ObservableList<ReclamationClient> Lister() throws SQLException{
             //List<Reclamation> rec=new ArrayList<>();
             ObservableList oblist = FXCollections.observableArrayList();
             ste=con.createStatement();
             
             //select r.idr,c.nom,r.objet,r.description,r.etatr,r.dater,u.firstname,u.lastname from reclamation r join user u on  (r.id=u.id)   join categorie_reclamation c on (r.idcatrec=c.idcatrec) where id="+id+" order by dater asc
             
             //ResultSet rs=ste.executeQuery("select r.idr,r.type,r.objet,r.description,r.etatr,r.dater,u.Firstname,u.Lastname from reclamation r ,user u where r.id=u.id;");
             ResultSet rs=ste.executeQuery("select r.idr,c.nom,r.objet,r.description,r.etatr,r.dater,r.reponse,u.firstname,u.lastname from reclamation r join user u on  (r.id=u.id) join categorie_reclamation c on (r.idcatrec=c.idcatrec) order by dater asc;");
             while (rs.next()) {
                //ResultSet rsu=ste.executeQuery("select * from user where id= "+rs.getInt("id"));
                //String nom="";
                //String prenom="";
                /*while (rsu.next()) {
                    nom=rsu.getString("firstname");
                    prenom=rsu.getString("lastname");
                }*/
                
                //ResultSet rsc=ste.executeQuery("select r.idr,r.type,r.objet,r.description,r.etatr,r.dater,u.firstname,u.lastname from reclamation r inner join user u on  (r.id=u.id) order by dater asc;");
                
         //Reclamation r=new Reclamation();
             int idr= rs.getInt("idr");
             String nom= rs.getString("nom");
             String objet= rs.getString("objet");
             String description= rs.getString("description");
             String etatr= rs.getString("etatr");
             String firstname=rs.getString("firstname");
             String lastname=rs.getString("lastname");
             Date dateR=rs.getDate("dater");
             String reponse=rs.getString("reponse");
             oblist.add(new ReclamationClient( idr,nom, objet, description, etatr, firstname, lastname, dateR,reponse));
  
     }
            
        return oblist;
            
        }
      public ObservableList<ReclamationClient> ListerById(int id ) throws SQLException{
             //List<Reclamation> rec=new ArrayList<>();
             ObservableList oblist = FXCollections.observableArrayList();
             ste=con.createStatement();
             
             
             //ResultSet rs=ste.executeQuery("select r.idr,r.type,r.objet,r.description,r.etatr,r.dater,u.Firstname,u.Lastname from reclamation r ,user u where r.id=u.id;");
             ResultSet rs=ste.executeQuery("select type,objet,description,etatr,dater,reponse from reclamation where id="+id+" order by dater asc;");
             while (rs.next()) {
                //ResultSet rsu=ste.executeQuery("select * from user where id= "+rs.getInt("id"));
                //String nom="";
                //String prenom="";
                /*while (rsu.next()) {
                    nom=rsu.getString("firstname");
                    prenom=rsu.getString("lastname");
                }*/
                
                //ResultSet rsc=ste.executeQuery("select r.idr,r.type,r.objet,r.description,r.etatr,r.dater,u.firstname,u.lastname from reclamation r inner join user u on  (r.id=u.id) order by dater asc;");
                
         //Reclamation r=new Reclamation();
            // int idr= rs.getInt("idr");
             String type= rs.getString("type");
             String objet= rs.getString("objet");
             String description= rs.getString("description");
             String etatr= rs.getString("etatr");
             String reponse=rs.getString("reponse");
             //String lastname=rs.getString("lastname");
             Date dateR=rs.getDate("dater");
             oblist.add(new ReclamationClient(  type, objet, description, etatr, dateR,reponse));
  
     }
            
        return oblist;
            
        }
    public ResultSet Stat() throws SQLException{
        
        ste=con.createStatement();
        ResultSet rs;
        return rs=ste.executeQuery("select count(*),count(etatr='nontraité') from reclamation");
    }
    
         
         

}

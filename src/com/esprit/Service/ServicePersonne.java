/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.Service;

import com.esprit.Entite.User;
import com.esprit.IService.IService;
import java.sql.SQLException;
import java.util.List;
import java.sql.*;
import com.esprit.Utils.DataBase;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author House
 */
public class ServicePersonne  {

    private Connection con;
    private Statement ste;

    public ServicePersonne() {
        con = DataBase.getInstance().getConnection();

    }

 
    public void ajouter(User u) throws SQLException
    {
    PreparedStatement pre=con.prepareStatement("INSERT INTO `user` (`id`,`username`,`email`,`password`, `roles`, `firstname`, `lastname`, `genre`)  VALUES ( NULL, ?, ?, ?, ?, ?, ?, ?);");
    
    pre.setString(1, u.getUsername());
    pre.setString(2, u.getEmail());
    pre.setString(3, u.getPassword());
    pre.setString(4, u.getRoles());
    pre.setString(5, u.getFirstname());
    pre.setString(6, u.getLastname());
    pre.setString(7, u.getGenre());
    
    pre.executeUpdate();
    }
            
    
    
        public void Delete(Integer id ) throws SQLException {
        
         PreparedStatement ps = con.prepareStatement("DELETE FROM `user` where id=?");
         ps.setInt(1,id);
         ps.executeUpdate();
        
    }
  
    
  
    
    public void listerClt() throws SQLException{
        ste = con.createStatement();
        String requeteList = "SELECT * FROM user";
        ResultSet rs = ste.executeQuery(requeteList);
        while (rs.next()) {
           // client c = new client();
            System.out.println(rs.getInt("id"));
            System.out.println(rs.getString("firstname"));
            System.out.println(rs.getString("lastname"));
            System.out.println(rs.getInt("email"));
            System.out.println(rs.getInt("role"));
            System.out.println(rs.getInt("username"));
            System.out.println(rs.getString("genre"));
            
            System.out.println("**************");
            

            

        }
    }
    
    
   public void Modifier(String firstname, String lastname, int id,String genre,String email,String username,String role,String password) throws SQLException
    {
        ste = con.createStatement();
        //String requeteModify= "UPDATE client SET login="+login+", password="+password+", cin="+cin+", nom="+nom+", prenom="+prenom+", adresse="+adresse+", tel="+tel+", email="+email+" WHERE idclt="+idclt;
        String requeteModify="UPDATE user SET "
                                                + "id = '" + id +"',"
						+ "username = '" + username +"',"
						+ "password='" + password +"',"
						+ "firstname='" +  firstname +"',"
						+ "lastname='" + lastname +"',"
                                                + "email='" + email +"',"
                                                + "role='" + role +"',"
						+ "genre='" + genre +";"; 
        
        ste.executeUpdate(requeteModify);
        System.out.println("Données bien modifier");
    }
    
     public ResultSet listerUser1(String name) throws SQLException{
        ste = con.createStatement();
        String requeteList = "SELECT * FROM user where username='"+name+"'";
        ResultSet rs = ste.executeQuery(requeteList);
        return rs;

    }
     public ResultSet listerUserpass(String pass) throws SQLException{
        ste = con.createStatement();
        String requeteList = "SELECT * FROM user where password='"+pass+"'";
        ResultSet rs = ste.executeQuery(requeteList);
        return rs;
    }
    
    
    
    
     public void RechercheById(int id) throws SQLException{
        ste = con.createStatement();
        String requeteList = "SELECT * FROM user WHERE id="+id;
        ResultSet rs = ste.executeQuery(requeteList);
        while (rs.next()) {
           // client c = new client();
            System.out.println(rs.getString("id"));
            System.out.println(rs.getString("username"));
            System.out.println(rs.getInt("email"));
            System.out.println(rs.getString("password"));
            System.out.println(rs.getString("role"));
            System.out.println(rs.getString("firstname"));
            System.out.println(rs.getString("lastname"));
            System.out.println(rs.getString("genre"));
            System.out.println("**************");
            

            

        }
    }
     public void Updatepass(String pass,String nom ) throws SQLException {
        PreparedStatement pre=con.prepareStatement ("UPDATE user  SET password='"+pass+"' where username='"+nom+"';");
        pre.executeUpdate();
    }
}

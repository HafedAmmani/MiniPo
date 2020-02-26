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
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


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
    PreparedStatement pre=con.prepareStatement("INSERT INTO `minipot`.`user` (`lastname`,`firstname`,`email`,`username`,`password`,  `genre`, `roles`)  VALUES (  ?, ?, ?, ?, ?, ?, ?);");
    pre.setString(1, u.getLastname());
    pre.setString(2, u.getFirstname());
    pre.setString(3, u.getEmail());
    pre.setString(4, u.getUsername());
    pre.setString(5, u.getPassword());
    pre.setString(6, u.getGenre());
    pre.setString(7, u.getRoles());
    pre.executeUpdate();
    }
    public void ajouterBD(User u) throws SQLException
    {    
    PreparedStatement pre=con.prepareStatement("INSERT INTO `user` (`lastname`,`firstname`,`email`,`username`,`password`,  `genre`, `roles`)  VALUES (  ?, ?, ?, ?, ?, ?, ?);");
    pre.setString(1, u.getLastname());
    pre.setString(2, u.getFirstname());
    pre.setString(3, u.getEmail());
    pre.setString(4, u.getUsername());
    pre.setString(5, u.getPassword());
    pre.setString(6, u.getGenre());
    pre.setString(7, u.getRoles());
    pre.executeUpdate();
    }



     /*   public void Delete(Integer id ) throws SQLException {

         PreparedStatement ps = con.prepareStatement("DELETE FROM `user` where id=?");
         ps.setInt(1,id);
         ps.executeUpdate();

    }*/




    public void listerUser() throws SQLException{
        ste = con.createStatement();
        String requeteList = "SELECT * FROM user";
        ResultSet rs = ste.executeQuery(requeteList);
        while (rs.next()) {
           // client c = new client();
            System.out.println(rs.getInt("id"));
            System.out.println(rs.getString("firstname"));
            System.out.println(rs.getString("lastname"));
            System.out.println(rs.getString("email"));
            System.out.println(rs.getString("roles"));
            System.out.println(rs.getString("username"));
            System.out.println(rs.getString("genre"));

            System.out.println("**************");




        }
    }
    public ObservableList<User> listerUserOB(){
//        ste = con.createStatement();
         List<User> array= new ArrayList<>();
        ObservableList obList = FXCollections.observableArrayList();
         try {
             PreparedStatement se = con.prepareStatement("SELECT * FROM user");
             ResultSet rs = se.executeQuery();
//        String requeteList = "SELECT * FROM user";
//        ResultSet rs = ste.executeQuery(requeteList);
        while (rs.next()) {
           // client c = new client();
            int id = rs.getInt("id");
            String Firstname = rs.getString("Firstname");
            String Lastname = rs.getString("Lastname");
            String email = rs.getString("email");
            String roles = rs.getString("roles");
            String username = rs.getString("username");
            String password = rs.getString("password");
            String Genre = rs.getString("Genre");
            obList.add(new User( username, Lastname, Firstname,   email,   password,  Genre,  roles));
            


                        }
     se.close();
          } catch (SQLException ex) {
        }
         return obList;
    }

    public ResultSet listerUser1(String name) throws SQLException{
        ste = con.createStatement();
        String requeteList = "SELECT * FROM user where username='"+name+"'";
        ResultSet rs = ste.executeQuery(requeteList);
        User u = new User();
        while (rs.next())
        {
            int id = rs.getInt("id");
            String Firstname = rs.getString("Firstname");
            String Lastname = rs.getString("Lastname");
            String email = rs.getString("email");
            String roles = rs.getString("roles");
            String username = rs.getString("username");
            String password = rs.getString("password");
            String Genre = rs.getString("Genre");
        }
        return rs;
        
        

    }
     public ResultSet listerUserpass(String pass) throws SQLException{
        ste = con.createStatement();
        String requeteList = "SELECT * FROM user where password='"+pass+"'";
        ResultSet rs = ste.executeQuery(requeteList);
        return rs;
    }

   /* public void Modifier(String firstname, String lastname, int id,String genre,String email,String username,String role,String password) throws SQLException
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
    }*/

public boolean Modifier(User u ) throws SQLException
    {
        ste = con.createStatement();
             PreparedStatement pre=con.prepareStatement("UPDATE `User` SET username = ?,password = ?, firstname = ?, lastname = ?, email = ?, roles = ?, genre= ? where username = ?;");

        //String requeteModify= "UPDATE client SET login="+login+", password="+password+", cin="+cin+", nom="+nom+", prenom="+prenom+", adresse="+adresse+", tel="+tel+", email="+email+" WHERE idclt="+idclt;
       /* String requeteModify="UPDATE user SET "
                                                + "id = '" + id +"',"
						+ "username = '" + username +"',"
						+ "password='" + password +"',"
						+ "firstname='" +  firstname +"',"
						+ "lastname='" + lastname +"',"
                                                + "email='" + email +"',"
                                                + "role='" + role +"',"
						+ "genre='" + genre +";"; */
        pre.setString(1, u.getUsername());
        pre.setString(2, u.getPassword());
        pre.setString(3, u.getFirstname());
        pre.setString(4, u.getLastname());
        pre.setString(5,u.getEmail());
        pre.setString(6,u.getRoles());
        pre.setString(7, u.getGenre());
        pre.setString(8, u.getUsername());
        pre.executeUpdate();
        System.out.println("Données bien modifier");
        int ex=pre.executeUpdate();
        return ex==1;
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

    public void Delete(int id) throws SQLException {
        PreparedStatement ps = con.prepareStatement("DELETE  FROM `user` where id=?");
         ps.setInt(1,id);
         int ex=ps.executeUpdate();
        
    }
     public void Delete(String username) throws SQLException {
        PreparedStatement ps = con.prepareStatement("DELETE  FROM `user` where username ='"+username+"';");
         //ps.setString(1, username);
         int ex=ps.executeUpdate();
        
    }
    public String getRoleUser(String name) throws SQLException{
       String role="";
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select roles from user where username='"+name+"' ;");
        while (rs.next())
        {
            
           role = rs.getString("roles");
          
            
        }
        return role;
       

    }
    private int rechercherID(String name) throws SQLException{
        int id = 0 ;
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select id from user where username='"+name+"' ;");
        while (rs.next())
        {
            
           id = rs.getInt("id");
          
            
        }
        return id;
    }

}

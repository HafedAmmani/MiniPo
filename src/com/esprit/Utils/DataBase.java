/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.Utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author hafed
 */
public class DataBase {
    String url = "jdbc:mysql://localhost:3306/minipot?useTimezone=true&serverTimezone=UTC";
    String login = "root";
    String pwd = "";
    public  static DataBase db;
    public Connection con;
    public DataBase() {
         try {
             con=DriverManager.getConnection(url, login, pwd);
             System.out.println("connexion etablie");
         } catch (SQLException ex) {
             System.out.println(ex);
         }
    }
    
    public Connection  getConnection()
    {
    return con;
    }     
    public static DataBase getInstance()
    {if(db==null)
        db=new DataBase();
    return db;
    }
    private ResultSet rs;
    private PreparedStatement pstmt;
    public InputStream getReport(String report_name, String column_name) {
		InputStream input = null;
		String query = "SELECT "+column_name+" FROM report WHERE report_name='"+report_name+"'";
		try {
			
			con = db.getConnection();
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				input = rs.getBinaryStream(1);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return input;
	}
}

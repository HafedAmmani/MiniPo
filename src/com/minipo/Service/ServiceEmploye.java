/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.minipo.Service;

import com.minipo.Entite.Employe;
import com.minipo.IService.IService;
import com.minipo.Utils.DataBase;
import com.minipo.gui.Report;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;



/**
 *
 * @author hafed
 */
public class ServiceEmploye implements IService<Employe>{
    
    private Connection con;
    private Statement ste;

    public ServiceEmploye() {
        con = DataBase.getInstance().getConnection();

    }

    @Override
    public void ajouter(Employe t) throws SQLException {
        PreparedStatement pre=con.prepareStatement("INSERT INTO `minipot`.`employe` ( `nom`, `prenom`,`adresse`,`tel`,`email`,`salaire`) VALUES ( ?, ?, ?, ?, ?, ?);");
        pre.setString(1, t.getNom());
        pre.setString(2, t.getPrenom());
        pre.setString(3, t.getAdresse());
        pre.setString(4, t.getTel());
        pre.setString(5,t.getEmail());
        pre.setString(6,t.getSalaire());
        pre.executeUpdate();
    }

    @Override
    public boolean delete(Employe t) throws SQLException {
        PreparedStatement pre=con.prepareStatement("DELETE FROM `minipot`.`employe` WHERE idemp=?;");
        pre.setInt(1, t.getIdemp());
        int ex=pre.executeUpdate();
        return ex!=0;
    }

    @Override
    public boolean update(Employe t) throws SQLException {
        PreparedStatement pre=con.prepareStatement("UPDATE `employe` SET nom = ?, prenom = ?, adresse = ?, tel = ?, email = ?, salaire = ? WHERE idemp = ?");
        pre.setString(1, t.getNom());
        pre.setString(2, t.getPrenom());
        pre.setString(3, t.getAdresse());
        pre.setString(4, t.getTel());
        pre.setString(5,t.getEmail());
        pre.setString(6,t.getSalaire());
        pre.setInt(7, t.getIdemp());
        int ex=pre.executeUpdate();
        return ex==1;
    }

    @Override
    public List<Employe> readAll() throws SQLException {
        List<Employe> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from employe");
     while (rs.next()) {                
               int idemp=rs.getInt(1);
               String nom=rs.getString("nom");
               String prenom=rs.getString("prenom");
               String adresse=rs.getString("adresse");
               String tel=rs.getString("tel");
               String email=rs.getString("email");
               String salaire=rs.getString("salaire");
               Employe p=new Employe(idemp, nom, prenom, adresse, tel, email, salaire);
     arr.add(p);
     }
    return arr;
    }
   
    private Map<String, Object> map;
    DataBase dao = new DataBase();
    public void printReport() {
		
		map = new HashMap<String, Object>();
		
		Report.createReport(con, map, dao.getReport("Emlpoye_report", "report_jasper"));
		Report.showReport();
	}
//    private ResultSet rs;
//    private PreparedStatement pstmt;
//    public ObservableList<Employe> getAllEmployeData(){
//                List<Employe> array= new ArrayList<>();
//		ObservableList obList = FXCollections.observableArrayList();
//		try {
//			
//			ste = con.prepareStatement("select * from employe");
//			rs = pstmt.executeQuery();
//			while(rs.next()) {
//				list.add(new Employe(rs.getString("nom"), rs.getString("prenom"), rs.getString("adresse"), rs.getString("tel"), rs.getString("email"), rs.getString("salaire")));
//			}
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//		
//		return list;
//	}
    

    public ObservableList<Employe> getAllEmploye() {
        List<Employe> array= new ArrayList<>();
        ObservableList obList = FXCollections.observableArrayList();
        ResultSet rs;//   obList.clear();
         try {
	    PreparedStatement st= con.prepareStatement("select * from employe");
	    ResultSet res= st.executeQuery();
     while (res.next()) {        
               int idemp=res.getInt("idemp");
               String nom=res.getString("nom");
               String prenom=res.getString("prenom");
               String adresse=res.getString("adresse");
               String tel=res.getString("tel");
               String email=res.getString("email");
               String salaire=res.getString("salaire");
                obList.add(new Employe(idemp,nom,prenom,adresse,tel,email,salaire));
                System.out.println("dattttt = "+obList);
     }
     st.close();
      } catch (SQLException ex) {
        }
         return obList;
    }
    
    
    
}

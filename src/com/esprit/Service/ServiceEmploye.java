/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.Service;

import com.esprit.Entite.Employe;
import com.esprit.IService.IService;
import com.esprit.Utils.DataBase;
import com.esprit.gui.Report;
import java.sql.Connection;
import java.sql.Date;
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
import javafx.scene.chart.PieChart;



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
        PreparedStatement pre=con.prepareStatement("INSERT INTO `minipot`.`user` ( `Lastname`, `Firstname`,`adresse`,`tel`,`email`,`salaire`,`date`,`roles`) VALUES ( ?, ?, ?, ?, ?,? , ?,'employe');");
        pre.setString(1, t.getNom());
        pre.setString(2, t.getPrenom());
        pre.setString(3, t.getAdresse());
        pre.setString(4, t.getTel());
        pre.setString(5,t.getEmail());
        pre.setString(6,t.getSalaire());
        pre.setDate(7,t.getDate());
        
        pre.executeUpdate();
    }

    @Override
    public boolean delete(Employe t) throws SQLException {
        PreparedStatement pre=con.prepareStatement("DELETE FROM `minipot`.`user` WHERE id=?;");
        pre.setInt(1, t.getIdemp());
        int ex=pre.executeUpdate();
        return ex!=0;
    }

    @Override
    public boolean update(Employe t) throws SQLException {
        PreparedStatement pre=con.prepareStatement("UPDATE `user` SET Lastname = ?, Firstname = ?, adresse = ?, tel = ?, email = ?, salaire = ?  WHERE id = ?");
        pre.setString(1, t.getNom());
        pre.setString(2, t.getPrenom());
        pre.setString(3, t.getAdresse());
        pre.setString(4, t.getTel());
        pre.setString(5,t.getEmail());
        pre.setString(6,t.getSalaire());
//        pre.setDate(7,t.getDate());
        pre.setInt(7, t.getIdemp());
        int ex=pre.executeUpdate();
        return ex==1;
    }

    @Override
    public List<Employe> readAll() throws SQLException {
        List<Employe> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from user where roles = a:1:{i:0;s:7:\"EMPLOYE\";}");
     while (rs.next()) {                
               int idemp=rs.getInt(1);
               String nom=rs.getString("Lastname");
               String prenom=rs.getString("Firstname");
               String adresse=rs.getString("adresse");
               String tel=rs.getString("tel");
               String email=rs.getString("email");
               String salaire=rs.getString("salaire");
               java.sql.Date date = rs.getDate("date");
               Employe p=new Employe(idemp, nom, prenom, adresse, tel, email, salaire,date);
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

    

    public ObservableList<Employe> getAllEmploye() {
        List<Employe> array= new ArrayList<>();
        ObservableList obList = FXCollections.observableArrayList();
        ResultSet rs;//   obList.clear();
         try {
	    PreparedStatement st= con.prepareStatement("select * from user where roles='a:1:{i:0;s:7:\"EMPLOYE\";}'");
	    ResultSet res= st.executeQuery();
     while (res.next()) {        
               int idemp=res.getInt("id");
               String nom=res.getString("Lastname");
               String prenom=res.getString("Firstname");
               String adresse=res.getString("adresse");
               String tel=res.getString("tel");
               String email=res.getString("email");
               String salaire=res.getString("salaire");
              Date date = res.getDate("date");
                obList.add(new Employe(idemp,nom,prenom,adresse,tel,email,salaire,date)); 
               
     }
     st.close();
      } catch (SQLException ex) {
        }
         return obList;
    }
    
    public ObservableList<String> getNomEmp() {
        ObservableList list = FXCollections.observableArrayList();
        ResultSet rs;//   obList.clear();
         try {
	    PreparedStatement st= con.prepareStatement("select Lastname from user where roles ='a:1:{i:0;s:7:\"EMPLOYE\";}'");
	    ResultSet res= st.executeQuery();
     while (res.next()) {        
               String NomEq=res.getString("Lastname");
                list.add(String.valueOf(NomEq));
     }
     st.close();
      } catch (SQLException ex) {
        }
         return list;
    }
    
    
    public List<Employe> getAllEmlpoyeByID(int id) throws SQLException {
        List<Employe> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from ratings where `idh`="+id+";");
    
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
    
    
    
    public long countEmploye() throws SQLException {
        List<Employe> cont = getAllEmploye();
        if(cont.isEmpty()) return -1;
        else return cont.stream().count();
        
    }
    public ObservableList<String> getAllSalaire() {
        ObservableList list = FXCollections.observableArrayList();
        ResultSet rs;//   obList.clear();
         try {
	    PreparedStatement st= con.prepareStatement("select salaire from user where roles ='a:1:{i:0;s:7:\"EMPLOYE\";}'");
	    ResultSet res= st.executeQuery();
     while (res.next()) {        
               String sal=res.getString("salaire");
                list.add(String.valueOf(sal));
     }
     st.close();
      } catch (SQLException ex) {
        }
         return list;
    }
    
   public int sumSalaire(){
        List<Employe> sum = getAllEmploye();
        if(sum.isEmpty()) return -1;
        else return sum.stream().mapToInt(e->Integer.parseInt(e.getSalaire())).sum();
   }
   
}

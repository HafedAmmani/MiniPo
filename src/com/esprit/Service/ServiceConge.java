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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

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
    public boolean update(Conge t) throws SQLException {
        PreparedStatement pre=con.prepareStatement("UPDATE `conge` SET  type =?, datedebut = ?, datefin = ?, nbrjrs = ?, description = ?, etat = ? WHERE idcon = ?");
        pre.setString(1, t.getType());
        pre.setString(2, t.getDatedebut());
        pre.setString(3, t.getDatefin());
        pre.setInt(4, t.getNbrjrs());
        pre.setString(5, t.getDescription());
        pre.setBoolean(6, t.isEtat());
        pre.setInt(7, t.getIdcon());
        int ex=pre.executeUpdate();
        return ex==1;
    }

    @Override
    public List<Conge> readAll() throws SQLException {
        List<Conge> arr=new ArrayList<>();
        ste=con.createStatement();
        ResultSet rs=ste.executeQuery("select * from conge");
         while (rs.next()) {                
                   int idcon=rs.getInt("idcon");
                   String typec=rs.getString("type");
                   String dateD=rs.getString("datedebut");
                   String datef=rs.getString("datefin");
                   int nbr=rs.getInt("nbrjrs");
                   String descr=rs.getString("description");
                   Boolean etatC = rs.getBoolean("etat");
                   
                   Conge p=new Conge(idcon,typec,dateD,datef,nbr,descr,etatC);
         arr.add(p);
         }
        return arr;
    }

     public long countDemande() throws SQLException {
        List<Conge> cont = readAll();
       
        if(cont.isEmpty()) return -1;
        else return cont.stream().filter(e->e.isEtat()==false).count();
       }
        
     public ObservableList<Conge> getAllDemande() {
        String etatc;
        ObservableList obList = FXCollections.observableArrayList();
        ResultSet rs;//   obList.clear();
         try {
	    PreparedStatement st= con.prepareStatement("select * from conge");
	    ResultSet res= st.executeQuery();
     while (res.next()) {        
               int idcon=res.getInt(1);
               String type=res.getString("type");
               String datedebut=res.getString("datedebut");
               String datefin=res.getString("datefin");
               int nbrjrs=res.getInt("nbrjrs");
               String description=res.getString("description");
               Boolean etat=res.getBoolean("etat");
               if(etat==false)
                   etatc = "Non confirmer";
               else
                   etatc = "Confirmer";
               
                obList.add(new Conge(idcon,type,datedebut,datefin,nbrjrs,description,etat,etatc));
               
     }
     st.close();
      } catch (SQLException ex) {
        }
         return obList;
    }

    public ResultSet loadData() throws SQLException
    {
        ObservableList list = FXCollections.observableArrayList();
         con = DataBase.getInstance().getConnection();
            ste=con.createStatement();
            ResultSet rs;
           return  rs=ste.executeQuery("select type,nbrjrs from conge ");
           
            }
}
    
    
    



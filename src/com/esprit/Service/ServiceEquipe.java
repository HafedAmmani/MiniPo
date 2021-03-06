/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.Service;

import com.esprit.Entite.Equipe;
import com.esprit.IService.IService;
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


/**
 *
 * @author hafed
 */
public class ServiceEquipe implements IService<Equipe>{
    private Connection con;
    private Statement ste;

    public ServiceEquipe() {
        con = DataBase.getInstance().getConnection();

    }

    @Override
    public void ajouter(Equipe e) throws SQLException {
        PreparedStatement pre= con.prepareStatement("INSERT INTO `minipot`.`equipe` ( `NomEq`, `Nombre`) VALUES ( ?, ?);");
        pre.setString(1, e.getNomeq());
        pre.setInt(2, e.getNombre());
        pre.executeUpdate();
    }

    @Override
    public boolean delete(Equipe e) throws SQLException {
        PreparedStatement pre=con.prepareStatement("DELETE FROM `minipot`.`equipe` WHERE idEq=?;");
        pre.setInt(1, e.getIdeq());
        int ex=pre.executeUpdate();
        return ex!=0;
    }

    @Override
    public boolean update(Equipe e) throws SQLException {
        PreparedStatement pre=con.prepareStatement("UPDATE `equipe` SET  NomEq = ?, Nombre = ? WHERE idEq = ?");
        
        pre.setString(1, e.getNomeq());
        pre.setInt(2, e.getNombre());
        pre.setInt(3, e.getIdeq());
        int ex=pre.executeUpdate();
        return ex==1;
    }
    
    @Override
    public List<Equipe> readAll() throws SQLException {
        List<Equipe> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from equipe");
     while (rs.next()) {                
               int ideq=rs.getInt(1);
               String nom=rs.getString("NomEq");
               int nombre=rs.getInt("Nombre");
               Equipe p=new Equipe(ideq, nom, nombre);
     arr.add(p);
     }
    return arr;
    }
    
    //find by id
    public Equipe find(int id) {
    Equipe equipe = new Equipe();      
      
    try {
      ResultSet result = this.con.createStatement(
        ResultSet.TYPE_SCROLL_INSENSITIVE,
        ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM equipe WHERE idEq = " + id);
      if(result.first())
        equipe = new Equipe(
          id,
          result.getString("NomEq"),
          result.getInt("Nombre"));         
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return equipe;
  }
    
    public ObservableList<String> getNomEq() {
        ObservableList list = FXCollections.observableArrayList();
        ResultSet rs;//   obList.clear();
         try {
	    PreparedStatement st= con.prepareStatement("select NomEq from equipe");
	    ResultSet res= st.executeQuery();
     while (res.next()) {        
               String NomEq=res.getString("NomEq");
                list.add(String.valueOf(NomEq));
     }
     st.close();
      } catch (SQLException ex) {
        }
         return list;
    }
    public ObservableList<Equipe> getAllEquipe() {
        
        ObservableList obList = FXCollections.observableArrayList();
        ResultSet rs;//   obList.clear();
         try {
	    PreparedStatement st= con.prepareStatement("select * from equipe");
	    ResultSet res= st.executeQuery();
     while (res.next()) {        
               int ideq=res.getInt("idEq");
               String Nomeq=res.getString("NomEq");
               int Nombre=res.getInt("Nombre");
               
                obList.add(new Equipe(ideq,Nomeq,Nombre));
               
     }
     st.close();
      } catch (SQLException ex) {
        }
         return obList;
    }
   
}

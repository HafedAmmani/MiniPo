/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.Service;

import com.esprit.Entite.Commande;
import com.esprit.Entite.Livraison;
import com.esprit.Entite.User;
import java.sql.SQLException;
import java.util.List;
import java.sql.*;
import com.esprit.Utils.DataBase;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.esprit.IService.IServiceLivraison;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author House
 */
public class ServiceLivraison implements IServiceLivraison<Livraison> {

    private Connection con;
    private Statement ste;

    public ServiceLivraison() {
        con = DataBase.getInstance().getConnection();

    }

    /*  public void ajouterLivraison(Livraison liv) throws SQLException {
        ste = con.createStatement();
        String requeteInsert = "INSERT INTO `minipot`.`livraison` (`destination`,`etatl`, `idc`, `idl`) VALUES ( '" + liv.getDestination() + "', '" + liv.getEtatl() + "', '" + liv.getIdc() + "', '" + liv.getIdl() +"');";
        ste.executeUpdate(requeteInsert);
    }*/
    @Override
    public void ajouterLivraison(Livraison liv) throws SQLException {
        PreparedStatement pre = con.prepareStatement("INSERT INTO livraison (`destination`,`etatl`, `idc`, `id`,`dateliv`, `matriculeL`) VALUES ( ?, ?, ?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
        pre.setString(1, liv.getDestination());
        pre.setString(2, liv.getEtatl());
        pre.setString(3, String.valueOf(liv.getIdc().getIdcmd()));
        pre.setString(4, String.valueOf(liv.getId().getId()));
        pre.setString(5, liv.getDateliv());
        pre.setString(6, "X" + liv.getIdliv());
        System.out.println(pre);
        pre.executeUpdate();
        try (ResultSet generatedKeys = pre.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                PreparedStatement stmt = con.prepareStatement("UPDATE livraison SET `matriculeL` =? WHERE `idliv`=?");
                stmt.setString(1, "X" + generatedKeys.getLong(1));
                stmt.setInt(2, (int) generatedKeys.getLong(1));
                stmt.executeUpdate();
                try {
                    JavaEmailUtil.sendEmail("projetminipo@gmail.com", generatedKeys.getInt(1));
                } catch (Exception ex) {
                    Logger.getLogger(ServiceLivraison.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                throw new SQLException("Creating user failed, no ID obtained");
            }

        }
    }

    @Override
    public boolean deleteLivraison(Livraison liv) throws SQLException {
        PreparedStatement pre = con.prepareStatement("DELETE FROM livraison WHERE `idliv` = ? ");
        pre.setInt(1, liv.getIdliv());
        int ex = pre.executeUpdate();
        return ex != 0;
    }

    @Override
    public boolean updateLivraison(Livraison liv) throws SQLException {
        PreparedStatement pre = con.prepareStatement("UPDATE livraison SET destination=? WHERE idliv=?");
        pre.setInt(1, liv.getIdliv());
        pre.setInt(2, liv.getIdliv());
        pre.executeUpdate();
        return true;
    }

    @Override
    public List<Livraison> readAllLivraison() throws SQLException {
        List<Livraison> arr = new ArrayList<>();
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from livraison liv,user u WHERE liv.id=u.id and u.roles LIKE %ROLE_LIVREUR%");
        while (rs.next()) {
            Integer idliv = rs.getInt("idliv");
            String destination = rs.getString("destination");
            String etatl = rs.getString("etatl");
            String dateliv = rs.getString("dateliv");
            String matriculeL = rs.getString("matriculeL");
            Integer idc = rs.getInt("idc");
            Integer id = rs.getInt("id");
            Livraison p = new Livraison(idliv, destination, etatl, dateliv, matriculeL, new User(id), new Commande(idc));
            arr.add(p);
        }
        return arr;
    }

    @Override
    public List<Livraison> RechercheLivraisonParDate() throws SQLException {
        List<Livraison> list = new ArrayList<>();
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from livraison liv,commande c WHERE c.idcmd=liv.idc ORDER BY c.datec ASC");
        while (rs.next()) {
            Integer idliv = rs.getInt("idliv");
            String destination = rs.getString("destination");
            String etatl = rs.getString("etatl");
            String dateliv = rs.getString("dateliv");
            String matriculeL = rs.getString("matriculeL");
            Integer idc = rs.getInt("idc");
            Integer id = rs.getInt("id");
            Livraison p = new Livraison(idliv, destination, etatl, dateliv, matriculeL, new User(id), new Commande(idc));
            list.add(p);
        }
        return list;
    }

    public ObservableList<Livraison> getAllLivraison() {

        ObservableList obList = FXCollections.observableArrayList();

        try {
            PreparedStatement st = con.prepareStatement("select * from livraison");
//	    PreparedStatement st= con.prepareStatement("select * from livraison liv,commande c WHERE c.idcmd=liv.idc ORDER BY c.datec ASC");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Integer idliv = rs.getInt("idliv");
                String destination = rs.getString("destination");
                String etatl = rs.getString("etatl");
                String dateliv = rs.getString("dateliv");
                String matriculeL = rs.getString("matriculeL");
                Integer idc = rs.getInt("idc");
                Integer id = rs.getInt("id");
                Livraison p = new Livraison(idliv, destination, etatl, dateliv, matriculeL, new User(id), new Commande(idc));
                obList.add(p);

            }
            st.close();
        } catch (SQLException ex) {
        }
        return obList;
    }

    public ObservableList<Livraison> getLivraisonRelatedToLivreur(int idLivreur) {

        ObservableList obList = FXCollections.observableArrayList();

        try {
            PreparedStatement st = con.prepareStatement("select * from livraison l where l.id=?");
            st.setInt(1, idLivreur);
//	    PreparedStatement st= con.prepareStatement("select * from livraison liv,commande c WHERE c.idcmd=liv.idc ORDER BY c.datec ASC");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Integer idliv = rs.getInt("idliv");
                String destination = rs.getString("destination");
                String etatl = rs.getString("etatl");
                String dateliv = rs.getString("dateliv");
                String matriculeL = rs.getString("matriculeL");
                Integer idc = rs.getInt("idc");
                Integer id = rs.getInt("id");
                Livraison p = new Livraison(idliv, destination, etatl, dateliv, matriculeL, new User(id), new Commande(idc));
                obList.add(p);

            }
            st.close();
        } catch (SQLException ex) {
        }
        return obList;
    }

    public ObservableList<String> getListLivreur() {
        ObservableList list = FXCollections.observableArrayList();
        ResultSet rs;//   obList.clear();
        try {
            PreparedStatement st = con.prepareStatement("select u.username from user u WHERE u.roles LIKE '%ROLE_LIVREUR%'");
            ResultSet res = st.executeQuery();
            while (res.next()) {
                String username = res.getString("username");
                list.add(username);
            }
            st.close();
        } catch (SQLException ex) {
        }
        return list;
    }

    public ObservableList<Livraison> getLiv() {

        ObservableList obList = FXCollections.observableArrayList();

        try {
            PreparedStatement st = con.prepareStatement("select *, c.refC from livraison l , commande c WHERE l.idc=c.idcmd ORDER BY idliv");
//	    PreparedStatement st= con.prepareStatement("select * from livraison liv,commande c WHERE c.idcmd=liv.idc ORDER BY c.datec ASC");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Integer idliv = rs.getInt("idliv");
                String destination = rs.getString("destination");
                String etatl = rs.getString("etatl");
                String dateliv = rs.getString("dateliv");
                String refC = rs.getString("refC");
                String username = "";
                String matriculeL = rs.getString("matriculeL");
                Integer idc = rs.getInt("idc");
                Integer id = rs.getInt("id");
                ResultSet rs1 = con.createStatement().executeQuery("select username from user where id=" + id + " ;");
                while (rs1.next()) {
                    username = rs1.getString("username");
                }
                Livraison p = new Livraison(idliv, destination, etatl, dateliv, matriculeL, new User(id, username), new Commande(idc, refC));
                obList.add(p);
            }
            st.close();
        } catch (SQLException ex) {
        }
        return obList;
    }

    public boolean updateLiv(String etat, String id, int idc) throws SQLException {
        PreparedStatement pre = con.prepareStatement("UPDATE livraison SET etatl='" + etat + "' WHERE matriculeL='" + id + "';");
        int i = pre.executeUpdate();
        if (etat == "livree") {
            PreparedStatement prefact = con.prepareStatement("UPDATE facture SET etatf=? where idcmd=? ;");
            prefact.setString(1, "Payee");
            prefact.setInt(2, idc);
            prefact.executeUpdate();
        } else {
            PreparedStatement precmd = con.prepareStatement("UPDATE commande SET etatc=? where idcmd=? ;");
            precmd.setString(1, "Refusee");
            precmd.setInt(2, idc);
            precmd.executeUpdate();
        }
        System.out.println(i);
        return true;
    }

    public User getLivreurFromUsername(String username) throws SQLException {
        PreparedStatement pre = con.prepareStatement("select * from user u where u.username=?;");
        pre.setString(1, username);
        ResultSet rs = pre.executeQuery();
        User livreur = new User();
        while (rs.next()) {
            Integer id = rs.getInt("id");
            livreur.setId(id);
        }
        return livreur;
    }

    public Commande getComandeFromReference(String reference) throws SQLException {
        PreparedStatement pre = con.prepareStatement("select * from commande c where c.refC=?;");
        pre.setString(1, reference);
        ResultSet rs = pre.executeQuery();
        Commande commande = new Commande();
        while (rs.next()) {
            Integer id = rs.getInt("idcmd");
            commande.setIdcmd(id);
        }
        return commande;
    }

    public ObservableList<String> getListRefCommande() {
        ObservableList list = FXCollections.observableArrayList();
        //   obList.clear();
        try {
            PreparedStatement st = con.prepareStatement("select refc from commande");
            ResultSet res = st.executeQuery();
            while (res.next()) {
                String refc = res.getString("refc");
                list.add(String.valueOf(refc));
            }
            st.close();
        } catch (SQLException ex) {
        }
        return list;
    }

    public boolean updateLivreur(String livreur, String id) throws SQLException {
        Integer idUser = 0;
        ResultSet rs = con.createStatement().executeQuery("select id from user where username='" + livreur + "' ;");
        while (rs.next()) {
            idUser = rs.getInt("id");
        }
        PreparedStatement pre = con.prepareStatement("UPDATE livraison SET id=?, etatl=? WHERE matriculeL=?;");
        pre.setInt(1, idUser);
        pre.setString(2, "en cours");
        pre.setString(3, id);
        int i = pre.executeUpdate();
        System.out.println(i);
        return true;
    }
}

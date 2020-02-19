/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.IService;


import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author House
 */
public interface IServiceCommande<T> {
    void ajouterCommande(T t) throws SQLException;
    boolean deleteCommande(T t) throws SQLException;
    boolean updateCommande(T t) throws SQLException;
    List<T> readAllCommande() throws SQLException;
    List<T> readIdCommande() throws SQLException;
}


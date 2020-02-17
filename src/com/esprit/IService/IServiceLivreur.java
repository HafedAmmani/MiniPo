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
public interface IServiceLivreur<T> {
    void ajouterLivreur(T t) throws SQLException;
    boolean deleteLivreur(T t) throws SQLException;
    boolean updateLivreur(T t) throws SQLException;
    List<T> readAllLivreur() throws SQLException;
}


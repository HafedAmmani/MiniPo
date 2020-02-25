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
 * @author hafed
 */
public interface Iconge<C> {
    void ajouter(C t) throws SQLException;
    List<C> readAll() throws SQLException;
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minipo;

import com.minipo.Entite.Categorie;
import com.minipo.Entite.Fournisseur;
import com.minipo.Entite.Produit;
import com.minipo.Service.ServiceCategorie;
import com.minipo.Service.ServiceFournisseur;
import com.minipo.Service.ServiceProduit;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author hafed
 */
public class MiniPo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //TEST ajout produit
        //ServiceProduit ser = new ServiceProduit();
        /*
        Produit p1 = new Produit("designation", 44, 10,5,5);
        
        try {
       
            ser.ajouter(p1);
            List<Produit> list = ser.readAll();
            System.out.println(list);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        */
        ServiceProduit ser = new ServiceProduit();
        
        Produit p1 = new Produit("une autre designation", 40, 50, 1, 1);
        try {
            ser.ajouter(p1);
            List<Produit> list = ser.readAll();
            System.out.println(list);
            ser.delete(p1);
            System.out.println(list);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
        
    }
}
    


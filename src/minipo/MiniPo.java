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
        ServiceFournisseur ser = new ServiceFournisseur();
        Fournisseur p1= new Fournisseur("Nasus", "Soussa", "97412365", "Soussa@esprit.tn");
        Fournisseur p2= new Fournisseur(4, "dsfds", "sdfsd", "dfsd", "fsdfs");

        try {
            ser.ajouter(p1);
            ser.delete(p2);
            List<Fournisseur> listf = ser.readAll();
            System.out.println(listf);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        System.out.println("helloooo");
    }
}
    


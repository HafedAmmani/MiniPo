/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minipo;

import com.esprit.Entite.Livraison;
import com.esprit.Entite.Livreur;
import com.esprit.Service.JavaEmailUtil;
import com.esprit.Service.ServiceLivreur;
import com.esprit.Service.ServiceLivraison;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author sinda
 */
public class MAIN {

    public static void main(String[] args) throws Exception {
        ServiceLivreur ser = new ServiceLivreur();
        ServiceLivraison serliv = new ServiceLivraison();
        Livreur l1 = new Livreur("xxxxx", "yyyyy","blabla","blabla",1400,"wwwww","ttttt");
        Livreur l2 = new Livreur("yyyyy", "zzzzzz","blabla","blabla",1850,"wwwww","ttttt");
        
        try {
//         
            //ser.ajouter1(l2);
            //ser.ajouterLivreur(l1);
            List<Livreur> list = ser.readAllLivreur();
            List<Livraison> listliv = serliv.readAllLivraison();
            System.out.println(list);
            //Email
            JavaEmailUtil.sendEmail("projetminipo@gmail.com");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
}

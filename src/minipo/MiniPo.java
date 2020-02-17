/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minipo;

import com.minipo.Entite.Employe;
import com.minipo.Entite.Equipe;
import com.minipo.Service.ServiceEmploye;
import com.minipo.Service.ServiceEquipe;
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
               ServiceEmploye ser = new ServiceEmploye();
        Employe p1 = new Employe("hafed", "Ammani", "Tunis","2334","hafed.ammani@esprit.tn","2344");
          
          //pour l'equipe
          ServiceEquipe sereq = new ServiceEquipe();
          Equipe E1 = new Equipe(1,"Caisse",200);
          Equipe E2 = new Equipe("Equipe2",43);
        
        try {
            
            //sereq.ajouter(E2);
            List<Equipe> listeq = sereq.readAll();
            System.out.println(listeq);
            //sereq.update(E1);
            //sereq.delete(E2)


            
            /***** find by id *****/
            Equipe equipe = sereq.find(4);
            System.out.println("Equipe N°"+equipe.getIdeq()+ "  Nom d'equipe : " + equipe.getNomeq() + "  Nombre : " + equipe.getNombre());
            
            
            //pour Table  Employe
           ser.ajouter(p1);
//            List<Employe> list = ser.readAll();
//            System.out.println(list);
//            ser.delete(p1);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
}
    


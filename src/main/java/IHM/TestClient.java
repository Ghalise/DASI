/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM;

//import entite.Client;

import dao.JpaUtil;
import entite.Astrologer;
import entite.Client;
import entite.Employee;
import entite.FortuneTeller;
import entite.Information;
import entite.Medium;
import entite.Voyance;
import service.Service;



//import DaoClient;

/**
 *
 * @author erouille
 */
public class TestClient  {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JpaUtil.init();
        
        Service s=new Service();
//        
//        Information info1=new Information("elghali.benchekroun@gmail.com","elghali","0664170706","ben");
//        Client c = new Client("M","benchekroun","el ghali","20/01/1997",info1);
//        s.createClient(c);
//        
//        Information info=new Information("elghali.benchekroun@gmail.com","elghali","0664170706","ben");
//        Employee e = new Employee("M", "Lapin", "Jeannot","01/01/2018", info);
//        Information info2=new Information("random@gmail.com","ran","0664170706","dom");
//        Employee e2 = new Employee("M", "Ran", "Dom","05/01/2008", info2);
//        s.createEmployee(e);
//        s.createEmployee(e2);
//        
//        FortuneTeller i = new FortuneTeller("Irma", "Raconte n'importe quoi", "Poissons morts");
//        FortuneTeller b = new FortuneTeller("Bouldingue", "Invente des mots pour paraitre intelligent", "Kinder Bueno");
//        Astrologer a = new Astrologer("As", "Se croit superieur a tout le monde", "AstroMag", "1945");
//        s.createMedium(i);
//        s.createMedium(b);
//        s.createMedium(a);
//        s.affectEmployee(e2, a);
//        s.affectEmployee(e2, b);
//        s.affectEmployee(e, a);
//        s.affectEmployee(e, i);
        Medium a = s.findMedium(5);
        
        Client cl= s.connectClient("elghali.benchekroun@gmail.com","ben");
        if(cl!=null){
            Voyance v=s.askForVoyance(cl,a);
            v=s.beginVoyance(v);
            s.closeVoyance(v, "caca");
        }
        JpaUtil.destroy();
        
    }
    
}

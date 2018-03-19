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
import java.util.logging.Level;
import java.util.logging.Logger;
import service.EmployeeIsNotFree;
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
        
        Information info1=new Information("elghali.benchekroun@gmail.com","elghali","0664170706","ben");
        Client c = new Client("M","benchekroun","el ghali","20/01/1997",info1);
        s.createClient(c);
        
        Client cl= s.connectClient("elghali.benchekroun@gmail.com","ben");
        
        Employee emp= s.connectEmployee("poe.dameron@gmail.com", "resistance");
        
        Medium m=s.findMedium(13);
        
        if(cl!=null){
            Voyance v1;
            try {
                v1 = s.askForVoyance(cl,m);
                v1=s.beginVoyance(v1.getIdVoyance());
                v1=s.endVoyance(v1.getIdVoyance());
                s.closeVoyance(v1.getIdVoyance(), "Facile a arnaquer !");
            } catch (EmployeeIsNotFree ex) {
                Logger.getLogger(TestClient.class.getName()).log(Level.SEVERE, null, ex);
            }
           
            System.out.println(s.voyanceByEmployee());
            System.out.println(s.voyanceByMedium());
        }
        JpaUtil.destroy();
        
    }
    
}

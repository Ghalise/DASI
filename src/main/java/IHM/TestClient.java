/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM;

//import entite.Client;

import dao.JpaUtil;
import entite.Client;
import entite.Employee;
import entite.Information;
import entite.Medium;
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
        Information info1=new Information("elghali.benchekroun@gmail.com","elghali","0664170706","ben");
        Client c = new Client("M","benchekroun","el ghali","20/01/1997",info1);
        Service s=new Service();
        Employee e = s.findEmployee(1);
        Employee e2= s.findEmployee(2);
        Medium m= s.findMedium(53);
        //s.createClient(c);
        Client cl= s.connectClient("elghali.benchekroun@gmail.com","ben");
        if(cl!=null){
            s.askForVoyance(cl, m);
        }
        JpaUtil.destroy();
    }
    
}

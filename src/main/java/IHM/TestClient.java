/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM;

//import entite.Client;

import dao.JpaUtil;
import entite.Client;
import entite.Information;
import service.Service;



//import DaoClient;

/**
 *
 * @author erouille
 */
public class TestClient  {

    /**
     * @param args the command line arguments
     * @throws java.text.ParseException
     */
    public static void main(String[] args) {
        JpaUtil.init();
        //Information info=new Information("elghali.benchekroun@gmail.com","elghali","0664170706","ben");
        //Client c = new Client("M","benchekroun","el ghali","20/01/1997",info);
        Service s=new Service();
        s.connectClient("el.benchekroun@gmail.com", "che");
        //s.createClient(c);
        JpaUtil.destroy();
    }
    
}

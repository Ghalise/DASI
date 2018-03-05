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
        Information info=new Information("spar.row@gmail.com","caraibes","06666666","jackie chan");
        Client c = new Client("M","sparrow","jack","02/01/1400",info);
        Service s=new Service();
        s.createClient(c);
        JpaUtil.destroy();
    }
    
}

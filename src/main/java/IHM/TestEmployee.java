/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM;

import dao.JpaUtil;
import entite.Employee;
import entite.Information;
import service.Service;

/**
 *
 * @author erouille
 */
public class TestEmployee {

    public static void main(String[] args) {
        JpaUtil.init();
        
        Service s=new Service();
        
        Employee emp= s.connectEmployee("poe.dameron@gmail.com", "resistance");
        
       
        JpaUtil.destroy();
    }
    
}

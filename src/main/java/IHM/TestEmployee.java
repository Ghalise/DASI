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
    Information info=new Information("elghali.benchekroun@gmail.com","elghali","0664170706","ben");
    Employee e = new Employee("M", "Lapin", "Jeannot","01/01/2018", info);
    Information info2=new Information("random@gmail.com","ran","0664170706","dom");
    Employee e2 = new Employee("M", "Ran", "Dom","05/01/2008", info2);
    Service s=new Service();
    s.createEmployee(e);
    s.createEmployee(e2);
    JpaUtil.destroy();
    }
    
}

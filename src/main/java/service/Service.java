/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entite.Client;
import dao.ClientDao;
import dao.EmployeeDao;
import dao.JpaUtil;
import dao.MediumDao;
import entite.Employee;
import entite.Medium;
/**
 *
 * @author erouille
 */
public class Service {
    
    // utile de penser la classe service comme un objet car on pourrait avoir des attributs à l'avenir (ex : langue utilisateur)
    public Service(){}
    
    //**************** SERVICES CLIENT ****************
    public void createClient(Client c)
    {
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        ClientDao cd= new ClientDao();
        try{
            cd.create(c);
            JpaUtil.validerTransaction();
        }catch(Exception e){
            JpaUtil.annulerTransaction();
        }finally{ 
            JpaUtil.fermerEntityManager();
        }  
       
    }
    
    public Client connectClient(String mail, String password){
        JpaUtil.creerEntityManager();
        ClientDao cd= new ClientDao();
        Client c=cd.findByMail(mail);
        if(c.getInformation().getPassword().equals(password)){
            c=null;
        }
        JpaUtil.fermerEntityManager();
        return c;
    }
    
    //service utilisé par un employé pour avoir accès aux informations concernant un client
    public Client findClient (long id){
        //pas de try catch ?
        JpaUtil.creerEntityManager();
        ClientDao cd= new ClientDao();
        Client c=cd.find(id);
        JpaUtil.fermerEntityManager();
        return c;
    }
    
    //**************** SERVICES EMPLOYE ****************
    public void createEmployee(Employee emp)
    {
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        EmployeeDao cd= new EmployeeDao();
        try{
            cd.create(emp);
            JpaUtil.validerTransaction();
        }catch(Exception e){
            JpaUtil.annulerTransaction();
        }finally{ 
            JpaUtil.fermerEntityManager();
        }  
       
    }
    
    public Employee findEmployee (long id){
        //pas de try catch ?
        JpaUtil.creerEntityManager();
        EmployeeDao cd= new EmployeeDao();
        Employee emp=cd.find(id);
        JpaUtil.fermerEntityManager();
        return emp;
    }
    
    
   //**************** SERVICES MEDIUM ****************
    public void createMedium(Medium m)
    {
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        MediumDao md= new MediumDao();
        try{
            md.create(m);
            JpaUtil.validerTransaction();
        }catch(Exception e){
            JpaUtil.annulerTransaction();
        }finally{ 
            JpaUtil.fermerEntityManager();
        }  
       
    }
    
    
    
    
    
    
    
    
    
}

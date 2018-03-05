/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entite.Client;
import dao.ClientDao;
import dao.EmployeDao;
import dao.JpaUtil;
import entite.Employe;
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
    public void createEmploye(Employe emp)
    {
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        EmployeDao cd= new EmployeDao();
        try{
            cd.create(emp);
            JpaUtil.validerTransaction();
        }catch(Exception e){
            JpaUtil.annulerTransaction();
        }finally{ 
            JpaUtil.fermerEntityManager();
        }  
       
    }
    
    public Employe findEmploye (long id){
        //pas de try catch ?
        JpaUtil.creerEntityManager();
        EmployeDao cd= new EmployeDao();
        Employe emp=cd.find(id);
        JpaUtil.fermerEntityManager();
        return emp;
    }
   
    
    
    
    
    
    
    
    
    
}

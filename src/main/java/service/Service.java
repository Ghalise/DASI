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
import javax.persistence.RollbackException;
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
            sendMailSuccess(c);
        }catch(RollbackException e){
            JpaUtil.annulerTransaction();
            sendMailFail(c);
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
        }catch(RollbackException e){
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
    
    public Employee connectEmployee(String mail, String password){
        JpaUtil.creerEntityManager();
        EmployeeDao ed= new EmployeeDao();
        Employee e=ed.findByMail(mail);
        if(e.getInformation().getPassword().equals(password)){
            e=null;
        }
        JpaUtil.fermerEntityManager();
        return e;
    }
    
    private void sendMailSuccess(Client c){
        System.out.println("Expediteur: contact@posit.if");
        System.out.println("Pour: "+c.getInformation().getMail());
        System.out.println("Sujet: Bienvenue chez POSIT'IF");
        System.out.println("Corps:");
        System.out.println("Bonjour "+c.getFirstname()+",");
        System.out.println("Nous vous confirmons votre inscription au service POSIT'IF. Votre numéro de client est : "+c.getIdClient());        
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
    
    public Medium findMedium (long id){
        //pas de try catch ?
        JpaUtil.creerEntityManager();
        MediumDao cd= new MediumDao();
        Medium m=cd.find(id);
        JpaUtil.fermerEntityManager();
        return m;
    }
    
    public void affectEmployee(Employee emp, Medium m)
    {
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        MediumDao md= new MediumDao();
        try{
            md.affect(emp,m);
            JpaUtil.validerTransaction();
        }catch(Exception e){
            JpaUtil.annulerTransaction();
        }finally{ 
            JpaUtil.fermerEntityManager();
        }  
    }
    
    public void getEmployee(Medium m)
    {
        JpaUtil.creerEntityManager();
        MediumDao md= new MediumDao();
        
        System.out.println(md.attributeEmployee(m));
            
       
        JpaUtil.fermerEntityManager();
         
    }

    
    private void sendMailFail(Client c){
        System.out.println("Expediteur: contact@posit.if");
        System.out.println("Pour: "+c.getInformation().getMail());
        System.out.println("Sujet: Bienvenue chez POSIT'IF");
        System.out.println("Corps:");
        System.out.println("Bonjour "+c.getFirstname()+",");
        System.out.println("Votre inscription au service POSIT'IF a malencontreusement échoué... Merci de recommencer ultérieurement.");        
    }
     
}

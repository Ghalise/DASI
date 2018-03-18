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
import dao.VoyanceDao;
import entite.AstroProfile;
import entite.ClientVoyance;
import entite.Employee;
import entite.Medium;
import entite.Voyance;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import javax.persistence.RollbackException;
import util.AstroTest;
/**
 *
 * @author erouille
 */
public class Service {
    
    // utile de penser la classe service comme un objet car on pourrait avoir 
    //des attributs à l'avenir (ex : langue utilisateur)
    public Service(){}
    
   /*-------------------------------------------------------------------------*/ 
   //********************** SERVICES CLIENTS *********************************//
   /*-------------------------------------------------------------------------*/
    
    //Service pour enregistrer un nouveau client dans l'application
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
    
    //Service pour qu'un client puisse se connecter dans l'application
    public Client connectClient(String mail, String password){
        JpaUtil.creerEntityManager();
        ClientDao cd= new ClientDao();
        Client c=cd.findByMail(mail);
        if(!c.getInformation().getPassword().equals(password)){
            System.out.println(c.getInformation().getPassword());
            c=null;
        }
        JpaUtil.fermerEntityManager();
        return c;
    }
    
    //Service pour que le client puisse consulter la liste des Médiums
    public Collection<Medium> displayMediums (Client c){
        JpaUtil.creerEntityManager();
        MediumDao md = new MediumDao();
        Collection<Medium> m = md.findAll();
        JpaUtil.fermerEntityManager();
        return m;
    }
    
    //Service pour le client puisse faire une demande de voyance avec le médium
    //qu'il a choisi (Gestion de la concurrence, on laisse la possibilité de 
    //recommencer la transaction 3 fois)
    public Voyance askForVoyance(Client c, Medium m){
        JpaUtil.creerEntityManager();
        if (c==null || m==null){
            return null;
        }
        boolean repeatTransaction= true;
        int nbRepeat=0;
        Voyance v= null;
        VoyanceDao vdao=new VoyanceDao();
        while ((repeatTransaction)&&(nbRepeat<3)){
            try{
                Employee emp = getBetterEmployee(m);
                v= new Voyance (emp,m,c);
                JpaUtil.ouvrirTransaction();
                vdao.create(v);
                //v.getEmployee().addVoyance(v);
                v.getMedium().addEmployee(emp);
                JpaUtil.validerTransaction();
                sendNotification(c,m);
                repeatTransaction=false;
            }catch(RollbackException e){
                JpaUtil.annulerTransaction();
                repeatTransaction=true;
                nbRepeat++;
            }
        }
        JpaUtil.fermerEntityManager();  
        return v;
    }
    
    //Service pour le client pour qu'il puisse consulter l'historique des demandes
    //de voyances qu'il a fait
    public List<Voyance> historicForClient (Client c){
        c.getHistorique().isEmpty();
        return c.getHistorique();
    }
        
   /*-------------------------------------------------------------------------*/ 
   //********************* SERVICES EMPLOYES *********************************//
   /*-------------------------------------------------------------------------*/
   
    
     //Service pour creation (en dur seulement) d'un employé
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
    
    //Service pour trouver (en dur seulement) un employé à partir de son id
    public Employee findEmployee (long id){
        JpaUtil.creerEntityManager();
        EmployeeDao cd= new EmployeeDao();
        Employee emp=cd.find(id);
        JpaUtil.fermerEntityManager();
        return emp;
    }
    
    //Service pour qu'un client puisse se connecter dans l'application
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
    
    //Service utilisé pour avoir accès aux informations concernant un client
    public Client findClient (long id){
        JpaUtil.creerEntityManager();
        ClientDao cd= new ClientDao();
        Client c=cd.find(id);
        JpaUtil.fermerEntityManager();
        return c;
    }
    
    //Service pour obtenir automatiquement des prédictions pour l'employe
    public List<String> getPredictions(Client c) throws IOException{
        AstroTest at=new AstroTest("ASTRO-01-M0lGLURBU0ktQVNUUk8tQjAx");
        int amour= (int) (5*Math.random());
        int sante= (int) (5*Math.random());
        int travail= (int) (5*Math.random());
        return at.getPredictions(c.getAstroProfile().getColor(),c.getAstroProfile().getColor(),amour, sante, travail);
    }
    
    //Service pour pouvoir débuter une voyance
    public Voyance beginVoyance(Voyance v){
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        VoyanceDao vdao= new VoyanceDao();
        EmployeeDao edao= new EmployeeDao();
        ClientDao cdao=new ClientDao();
        Employee emp= v.getEmployee();
        Voyance newVoyance=null;
        try{
            v.setBegin();
            newVoyance=vdao.update(v);
            emp.setFree(false);
            edao.update(emp);
            v.getClient().addVoyance(v);
            cdao.update(v.getClient());
            JpaUtil.validerTransaction();
        }catch(RollbackException e){
            JpaUtil.annulerTransaction();
        }finally{ 
            JpaUtil.fermerEntityManager();
        } 
        return newVoyance;
    }
    
    //Service pour cloturer une voyance
    public void closeVoyance(Voyance v, String comments){
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        VoyanceDao vdao= new VoyanceDao();
        EmployeeDao edao= new EmployeeDao();
        Employee emp= v.getEmployee();
        try{
            v.setEnd();
            v.setComment(comments);
            vdao.update(v);
            emp.setFree(true);
            edao.update(emp);
            JpaUtil.validerTransaction();
        }catch(RollbackException e){
            JpaUtil.annulerTransaction();
        }finally{ 
            JpaUtil.fermerEntityManager();
        } 
    }
    
    //Service pour que l'employe puisse accéder au profil Astrologique d'un client
    public AstroProfile displayAstroprofile (Client c){
        return c.getAstroProfile();
    }
    
    //Service pour l'employé puisse consulter l'historique des voyances que le 
    //client a effectué
    public List<Voyance> getHistoricOfClient (Client c){
        JpaUtil.creerEntityManager();
        VoyanceDao vdao= new VoyanceDao();
        List<Voyance> lv= (List<Voyance>) vdao.getHistoriqueClient(c);
        JpaUtil.fermerEntityManager();
        return lv;
    }
    
   /*-------------------------------------------------------------------------*/ 
   //*********************** SERVICES MEDIUM *********************************//
   /*-------------------------------------------------------------------------*/
   
    //Service pour creation (en dur seulement) d'un médium
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
    
    //Service pour trouver (en dur seulement) un médium à partir de son id
    public Medium findMedium (long id){
        JpaUtil.creerEntityManager();
        MediumDao cd= new MediumDao();
        Medium m=cd.find(id);
        JpaUtil.fermerEntityManager();
        return m;
    }
    
    //Service qui permet de définir explicitement quels sont les médiums qu'un
    //employé peut incarner
    public void affectEmployee(Employee emp, Medium m)
    {
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        MediumDao md= new MediumDao();
        try{
            md.affectEmployee(emp,m);
            JpaUtil.validerTransaction();
        }catch(Exception e){
            JpaUtil.annulerTransaction();
        }finally{ 
            JpaUtil.fermerEntityManager();
        }  
    }

    //**************** SERVICES COMPLEMENTAIRES ****************
    
    private void sendMailFail(Client c){
        System.out.println("Expediteur: contact@posit.if");
        System.out.println("Pour: "+c.getInformation().getMail());
        System.out.println("Sujet: Bienvenue chez POSIT'IF");
        System.out.println("Corps:");
        System.out.println("Bonjour "+c.getFirstname()+",");
        System.out.println("Votre inscription au service POSIT'IF a malencontreusement échoué... Merci de recommencer ultérieurement.");        
    }
    
    private void sendMailSuccess(Client c){
        System.out.println("Expediteur: contact@posit.if");
        System.out.println("Pour: "+c.getInformation().getMail());
        System.out.println("Sujet: Bienvenue chez POSIT'IF");
        System.out.println("Corps:");
        System.out.println("Bonjour "+c.getFirstname()+",");
        System.out.println("Nous vous confirmons votre inscription au service POSIT'IF. Votre numéro de client est : "+c.getIdClient());        
    }
    
    private void sendNotification(Client c, Medium m){
        System.out.println("Voyance demandée pour client "+c.getFirstname()+" "+c.getSurname()+" (#"+c.getIdClient()+"), Médium : "+m.getName());
    }
    
    //Permet de récupérer l'employee qui a le moins de voyances
    private Employee getBetterEmployee(Medium m)
    {
        MediumDao md= new MediumDao();
        Employee e=md.attributeEmployee(m);
        return e;
    }
    
     
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entite.Employee;
import entite.Medium;
import java.util.Collection;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author erouille
 */
public class MediumDao {

    public MediumDao() {
    }
    
    public void create(Medium m){
        JpaUtil.obtenirEntityManager().persist(m);
    }
    
    public Medium update(Medium m){
        m=JpaUtil.obtenirEntityManager().merge(m);
        
        return  m;
    }
    
    // on va trouver un client donné grâce à sa clef (id)
    public Medium find(long id){
        Medium m=JpaUtil.obtenirEntityManager().find(Medium.class, id);
        
        return m;
    }
    
    // execution d'une requête qui va nous donner tous les clients contenus dans la BD POSITIF
    // utilisé?
     public Collection<Medium> findAll(){
        Query q=JpaUtil.obtenirEntityManager().createQuery("SELECT m FROM Medium m");
        
        return (Collection<Medium>)q.getResultList();
    }
     
    public List<Employee> attributeEmployee(Medium m){
        
        Query q = JpaUtil.obtenirEntityManager().createQuery("SELECT e FROM Medium med JOIN med.employees e WHERE med = :m AND e.free = true");
        q.setParameter("m", m);
        
        return (List<Employee>) q.getResultList();
    }
    
    public void affect(Employee e, Medium m){
        m.addEmployee(e);
        JpaUtil.obtenirEntityManager().merge(m);
    }
}

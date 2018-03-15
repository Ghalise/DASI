/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entite.Employee;
import entite.Medium;
import java.util.Collection;
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
     
    public Employee attributeEmployee(Medium m){
        
        Query q = JpaUtil.obtenirEntityManager().createQuery("SELECT med.employees FROM Medium med WHERE med.=:m");
        q.setParameter("m", m);
        
        return (Employee)q.getSingleResult();
    }
    
    public void affect(Employee e, Medium m){
        m.addEmployee(e);
        JpaUtil.obtenirEntityManager().merge(m);
    }
}

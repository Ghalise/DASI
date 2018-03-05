/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entite.Employee;
import java.util.Collection;
import javax.persistence.Query;
/**
 *
 * @author erouille
 */
public class EmployeeDao { 
    
    public EmployeeDao()
    {}
    
    // pour creer un client dans la BD POSITIF il faut le persister grâce à notre entityManager
    public void create(Employee e){
        JpaUtil.obtenirEntityManager().persist(e);
    }
    
    // le client existe déjà et on veut changer ses attributs, on va donc utiliser la fonction merge de l'entityManager
    // utilisé ?
    public Employee update(Employee e){
        e=JpaUtil.obtenirEntityManager().merge(e);
        
        return  e;
    }
    
    // on va trouver un client donné grâce à sa clef (id)
    public Employee find(long id){
        Employee e=JpaUtil.obtenirEntityManager().find(Employee.class, id);
        
        return e;
    }
    
    // execution d'une requête qui va nous donner tous les clients contenus dans la BD POSITIF
    // utilisé?
     public Collection<Employee> findAll(){
        Query q=JpaUtil.obtenirEntityManager().createQuery("SELECT e FROM Employe e");
        
        return (Collection<Employee>)q.getResultList();
    }
}

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
     public Collection<Medium> findAll(){
        Query q=JpaUtil.obtenirEntityManager().createQuery("SELECT m FROM Medium m");
        return (Collection<Medium>)q.getResultList();
    }
     
    public Employee attributeEmployee(Medium m){
        Query q = JpaUtil.obtenirEntityManager().createQuery("SELECT e FROM Medium med JOIN med.employees e WHERE med = :m AND e.free = true");
        q.setParameter("m", m);
        List<Employee> result = q.getResultList();
        return bestEmployee(result,m);
    }
    
    // Permet de déterminer automatiquement l'employee disponible qui a le moins 
    //d'affectation à partir d'une liste d'employés disponibles 
    private Employee bestEmployee(List<Employee> l, Medium m){
        if(!l.isEmpty()){
            Employee best= l.get(0);
            int nbVoyance=best.getNumberVoyance();
            Employee curr;
            for(int i=1; i<l.size(); i++){
                curr=l.get(i);
                if(curr.getNumberVoyance()<nbVoyance){
                    best=curr;
                    nbVoyance=curr.getNumberVoyance();
                }
            }
            return best;
        }else{ return null;}
    }
    
    
     // execution d'une requête qui va nous donner tous les employes contenus dans la BD POSITIF
     private List<Employee> findAllFreeEmployee(){
        Query q=JpaUtil.obtenirEntityManager().createQuery("SELECT e FROM Employee e WHERE e.free = true");
        return (List<Employee>)q.getResultList();
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entite.Employee;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import javafx.util.Pair;
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
        JpaUtil.obtenirEntityManager().merge(e);
        long id=e.getIdEmployee();
        Employee emp = find(id);
        return  emp;
    }
    
    // on va trouver un client donné grâce à sa clef (id)
    public Employee find(long id){
        Employee e=JpaUtil.obtenirEntityManager().find(Employee.class, id);
        return e;
    }
    
    public Employee findByMail(String email){
        Query q=JpaUtil.obtenirEntityManager().createQuery("SELECT e FROM Employee e WHERE e.information.mail = :email");
        q.setParameter("email", email);
        if(q.getResultList().isEmpty()){
            return null;
        }else{
            return (Employee) q.getResultList().get(0);
        }        
    }
    
    // execution d'une requête qui va nous donner tous les employes contenus dans la BD POSITIF
     public Collection<Employee> findAll(){
        Query q=JpaUtil.obtenirEntityManager().createQuery("SELECT e FROM Employee e");
        return (Collection<Employee>)q.getResultList();
    }
    
    public HashMap<Employee,Pair<Long,Float>> statEmployee(List<Employee> emp){
        HashMap<Employee,Pair<Long,Float>> mapRes = new HashMap<>();
        long total = 0;
        Query q = JpaUtil.obtenirEntityManager().createQuery("select count(v) from Voyance v");
        total = (Long) q.getSingleResult();
        if(total != 0){
            for(Employee empe : emp){
                q = JpaUtil.obtenirEntityManager().createQuery("select v from Voyance v where v.employee = :emp");
                q.setParameter("emp", empe);
                long nbConsultation = (long)q.getResultList().size();
                float pourcentage = (float)(100*(nbConsultation))/total;
                Pair<Long,Float> paire = new Pair<>(nbConsultation,pourcentage);
                mapRes.putIfAbsent(empe, paire);
            }
        }
        return mapRes;
    }
     
}

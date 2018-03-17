/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entite.Client;
import java.util.Collection;
import javax.persistence.Query;
/**
 *
 * @author erouille
 */
public class ClientDao { 
    
    public ClientDao()
    {}
    
    public void create(Client c){
        JpaUtil.obtenirEntityManager().persist(c);
    }
    
    // on va trouver un client donné grâce à sa clef (id)
    public Client find(long id){
        Client c=JpaUtil.obtenirEntityManager().find(Client.class, id);
        return c;
    }
    
    public Client findByMail(String email){
        Query q=JpaUtil.obtenirEntityManager().createQuery("SELECT c FROM Client c WHERE c.information.mail = :email");
        q.setParameter("email", email);
        if(q.getResultList().isEmpty()){
            return null;
        }else{
            return (Client) q.getSingleResult();
        }        
    }
    
    public Client update(Client c){
        c=JpaUtil.obtenirEntityManager().merge(c);
        return  c;
    }
    
     public Collection<Client> findAll(){
        Query q=JpaUtil.obtenirEntityManager().createQuery("SELECT c FROM Client c");
        
        return (Collection<Client>)q.getResultList();
    }
}

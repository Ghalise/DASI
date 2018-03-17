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
    
    // execution d'une requête qui va nous donner tous les clients contenus dans la BD POSITIF
    // utilisé?
     public Collection<Client> findAll(){
        Query q=JpaUtil.obtenirEntityManager().createQuery("SELECT c FROM Client c");
        
        return (Collection<Client>)q.getResultList();
    }
}

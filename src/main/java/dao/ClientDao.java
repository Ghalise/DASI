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
    
    // pour creer un client dans la BD POSITIF il faut le persister grâce à notre entityManager
    public void create(Client c){
        JpaUtil.obtenirEntityManager().persist(c);
    }
    
    // le client existe déjà et on veut changer ses attributs, on va donc utiliser la fonction merge de l'entityManager
    // utilisé ?
    public Client update(Client c){
        c=JpaUtil.obtenirEntityManager().merge(c);
        return  c;
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

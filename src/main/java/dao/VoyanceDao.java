/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entite.Client;
import entite.Voyance;
import java.util.Collection;
import javax.persistence.Query;

/**
 *
 * @author erouille
 */
public class VoyanceDao {

    public VoyanceDao() {
    }
    
    public void create(Voyance v){
        JpaUtil.obtenirEntityManager().persist(v);
    }
    
    public Voyance update(Voyance v){
        v=JpaUtil.obtenirEntityManager().merge(v);
        return  v;
    }
    
    public Voyance find(long id){
        Voyance v=JpaUtil.obtenirEntityManager().find(Voyance.class, id);
        return v;
    }
    
    public Collection<Voyance> getHistoriqueClient(Client c){
        Query q=JpaUtil.obtenirEntityManager().createQuery("SELECT v FROM Voyance v WHERE v.client =:client ");
        q.setParameter("client", c);
        return (Collection<Voyance>)q.getResultList();
    }
}

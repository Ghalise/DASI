/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entite.Voyance;

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
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.persistence;

import co.edu.uniandes.csw.mascotas.entities.MascotaEncontradaEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Johan E. Vivas Sepulveda (je.vivas)
 */

@Stateless
public class MascotaEncontradaPersistence {
    
    @PersistenceContext (unitName = "mascotasPU")
    protected EntityManager em;
    
    public MascotaEncontradaEntity create(MascotaEncontradaEntity mascotaEncontradaEntity){
        
        em.persist(mascotaEncontradaEntity);
        
        return mascotaEncontradaEntity;
    }
    
    public MascotaEncontradaEntity find(Long mascotaEncontradaId){
        
        return em.find(MascotaEncontradaEntity.class, mascotaEncontradaId);
    }
    
    public List<MascotaEncontradaEntity> findAll(){
        
        TypedQuery<MascotaEncontradaEntity> query = em.createQuery("select u from MascotaEncontradaEntity u", MascotaEncontradaEntity.class);
        
        return query.getResultList();
    }
}

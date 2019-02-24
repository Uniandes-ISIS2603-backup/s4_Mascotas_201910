/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.persistence;

import co.edu.uniandes.csw.mascotas.entities.EventoEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Daniela Gonzalez
 */
@Stateless
public class EventoPersistence {

    @PersistenceContext(unitName = "mascotasPU")
    protected EntityManager em;

    public EventoEntity create(EventoEntity eventoEntity) {

        em.persist(eventoEntity);
        return eventoEntity;
    }

    public EventoEntity find(Long eventoId) {

        return em.find(EventoEntity.class, eventoId);
    }

    public List<EventoEntity> findAll(){
        
        TypedQuery query = em.createQuery("select u from EventoEntity u", EventoEntity.class);
        return query.getResultList();
    }
    
   // public EventoEntity 
    
}
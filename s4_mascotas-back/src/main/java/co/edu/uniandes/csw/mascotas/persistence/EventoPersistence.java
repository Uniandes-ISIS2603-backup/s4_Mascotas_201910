/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.persistence;

import co.edu.uniandes.csw.mascotas.entities.EventoEntity;
import java.util.Date;
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
    
    public EventoEntity actualizarNombre(Long eventoId, String nombre){
        EventoEntity evento = em.find(EventoEntity.class, eventoId);
         evento.setNombre(nombre);
         em.refresh(evento);
         return evento;
    }
    
    public EventoEntity actualizarDescripcion(Long eventoId, String descripcion){
        EventoEntity evento = em.find(EventoEntity.class, eventoId);
         evento.setDescripcion(descripcion);
         em.refresh(evento);
         return evento;
    }
      
    public EventoEntity actualizarImagen(Long eventoId, String imagen){
        EventoEntity evento = em.find(EventoEntity.class, eventoId);
         evento.setImagen(imagen);
         em.refresh(evento);
         return evento;
    }
        
    public EventoEntity actualizarFechaInicio(Long eventoId, Date fecha){
        EventoEntity evento = em.find(EventoEntity.class, eventoId);
         evento.setFechaInicio(fecha);
         em.refresh(evento);
         return evento;
    }
    
    public EventoEntity actualizarFechaFin(Long eventoId, Date fecha){
        EventoEntity evento = em.find(EventoEntity.class, eventoId);
         evento.setFechaFin(fecha);
         em.refresh(evento);
         return evento;
    }
}
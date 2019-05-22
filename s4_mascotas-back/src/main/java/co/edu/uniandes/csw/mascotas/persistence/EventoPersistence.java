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

    /**
     * Método para persisitir (crear) la entidad en la base de datos.
     *
     * @param eventoEntity objeto evento que se creará en la base de datos
     * @return devuelve la entidad creada con un id generado por la base de datos.
     */
    public EventoEntity create(EventoEntity eventoEntity) {

        em.persist(eventoEntity);
        return eventoEntity;
    }

    /**
     * Busca un evento con el id enviado por parametro.
     *
     * @param eventoId: id correspondiente al evento buscado.
     * @return una entidad de evento.
     */
    public EventoEntity find(Long eventoId) {

        return em.find(EventoEntity.class, eventoId);
    }

     /**
     * Devuelve todos los eventos de la base de datos.
     *
     * @return una lista con todos los eventos que encuentre en la base de
     * datos.
     */
    public List<EventoEntity> findAll(){
        
        TypedQuery query = em.createQuery("select u from EventoEntity u", EventoEntity.class);
        return query.getResultList();
    }
    
    /**
     * Actualiza un evento.
     *
     * @param nuevaEntidad: la entidad que viene con los nuevos cambios.
     * Por ejemplo el nombre pudo cambiar. 
     * @return un evento con los cambios aplicados.
     */
     public EventoEntity actualizarEvento(EventoEntity nuevaEntidad){
         
         return em.merge(nuevaEntidad);
     }
    
     /**
     *
     * Borra un evento de la base de datos recibiendo como argumento el id
     * del evento
     *
     * @param id: id correspondiente al evento a borrar.
     */
     public void delete(Long id){
         
         EventoEntity entity = em.find(EventoEntity.class, id);
         em.remove(entity);
     }
     
}
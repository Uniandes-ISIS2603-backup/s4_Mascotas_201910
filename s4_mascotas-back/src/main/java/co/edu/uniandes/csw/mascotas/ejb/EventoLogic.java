/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.ejb;

import co.edu.uniandes.csw.mascotas.entities.EventoEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.mascotas.persistence.EventoPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Daniela Gonzalez
 */

@Stateless
public class EventoLogic {
    
    @Inject
    private EventoPersistence persistence;
    
    
     /**
     * Crea un evento a partir de la entidad ingresada por parámetro
     * @param evento
     * @return entidad del evento creado
     * @throws BusinessLogicException 
     */
    public EventoEntity crearEvento(EventoEntity evento) throws BusinessLogicException{
        
        //Validacion reglas de negocio
        if(evento.getNombre()== null){
             throw new BusinessLogicException("Un evento debe tener un nombre");
        }
        
         if(evento.getDescripcion()== null){
             throw new BusinessLogicException("Un evento debe tener una descripcion");
        }
        if(evento.getFechaInicio().compareTo(evento.getFechaFin())<0){
           throw new BusinessLogicException("La fecha de inicio debe ser antes de la fecha final");
        }
         
        evento = persistence.create(evento);
        return evento;
    }
    
     /**
     * Busca un evento por su id
     * @param eventoId Llave del evento
     * @return elEvento
     */
    public EventoEntity encontrarEventoPorId(Long eventoId){
        
        EventoEntity elEvento = persistence.find(eventoId);
        return elEvento;
    }
    
      /**
     * Busca todos los eventos
     * @return eventos
     */
    public List<EventoEntity> encontrarTodosEventos(){
       
        List<EventoEntity> eventos = persistence.findAll();
        return eventos;
    }
    
    /**
     *
     * Actualizar un evento.
     *
     * @param id: id del evento que se quiere actualizar para buscarlo en la base de
     * datos.
     * @param nuevo: evento con los cambios para ser actualizado.
     * Por ejemplo el titulo.
     * @throws BusinessLogicException si la informacion nueva no cumple las reglas de negocio.
     * @return el evento con los cambios actualizados en la base de datos.
     */
    public EventoEntity actualizarEvento(Long id, EventoEntity nuevo) throws BusinessLogicException{
        
        //Validacion reglas de negocio
        if(nuevo.getNombre()== null){
             throw new BusinessLogicException("El nuevo nombre debe ser valido");
        }
        if(nuevo.getDescripcion()== null){
             throw new BusinessLogicException("La nueva descripcion debe ser valida");
        }
        if(nuevo.getFechaInicio().compareTo(nuevo.getFechaFin())<0){
            throw new BusinessLogicException("La fecha de inicio debe ser antes de la fecha final");
        }
        
        EventoEntity cambiado = persistence.actualizarEvento(nuevo);
        
        return cambiado;
    }
    
    /**
     * Borrar un evento
     *
     * @param id: id del evento a borrar.
     */
    public void eliminarEvento(Long id) {
        
        persistence.delete(id);
    } 
    
    /**
     * Busca un articulo por su nombre
     *
     * @param nombre: nombre del evento que se busca
     * @return el evento con el nombre enviado por parametro.
     * @throws BusinessLogicException Si el nombre que se busca no es  valido.
     */
    public EventoEntity buscarEventoPorNombre(String nombre) throws BusinessLogicException{
        
        if(nombre == null){
            throw new BusinessLogicException("El nombre buscado debe ser valido");
        }
        
        EventoEntity evento = persistence.findByName(nombre);
       
        return evento;    
    }
    
}

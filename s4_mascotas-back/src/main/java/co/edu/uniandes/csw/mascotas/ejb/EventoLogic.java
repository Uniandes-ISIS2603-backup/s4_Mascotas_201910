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
    
    public EventoEntity crearEvento(EventoEntity evento) throws BusinessLogicException{
        
        if(evento.getNombre()== null){
             throw new BusinessLogicException("Un evento debe tener un nombre");
        }
        
         if(evento.getDescripcion()== null){
             throw new BusinessLogicException("Un evento debe tener una de4scripcion");
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
    
    public EventoEntity cambiarNombre(Long eventoId, String nuevoNombre) throws BusinessLogicException{
        
        //Validacion reglas de negocio
        if(nuevoNombre == null){
            throw new BusinessLogicException("Un evento debe tener un nombre");
        }
        
        EventoEntity cambiada = persistence.actualizarNombre(eventoId, nuevoNombre);
        return cambiada;
    }
 
    public EventoEntity cambiarDescripcion(Long eventoId, String nuevaDescripcion) throws BusinessLogicException{
        
        //Validacion reglas de negocio
        if(nuevaDescripcion == null){
            throw new BusinessLogicException("Un evento debe tener una descripcion");
        }
        
        EventoEntity cambiada = persistence.actualizarDescripcion(eventoId, nuevaDescripcion);
        return cambiada;
    }
    
    public EventoEntity cambiarImagen(Long eventoId, String nuevaImagen){
        
        EventoEntity cambiada = persistence.actualizarImagen(eventoId, nuevaImagen);
        return cambiada;
    }
    
}

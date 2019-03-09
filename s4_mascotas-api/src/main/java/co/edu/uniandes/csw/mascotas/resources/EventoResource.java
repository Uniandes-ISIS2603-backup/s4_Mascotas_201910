/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.resources;

import co.edu.uniandes.csw.mascotas.dtos.EventoDTO;
import co.edu.uniandes.csw.mascotas.ejb.EventoLogic;
import co.edu.uniandes.csw.mascotas.entities.EventoEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author Daniela Gonzalez
 */

@Path("eventos")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped

public class EventoResource {
    
    private static final Logger LOGGER = Logger.getLogger(EventoResource.class.getName());
    
    @Inject
    private EventoLogic eventoLogic;
    
    /**
     * Crea un nuevo evento con la informacion que se recibe en el cuerpo de
     * la petición y se regresa un objeto identico con un id auto-generado por
     * la base de datos.
     *
     * @param evento {@link EventoDTO} - El evento que se desea crear.
     * @return JSON {@link EventoDTO} - El evento guardado con el id generado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se cumplen las reglas de negocio.
     */
     @POST
    public EventoDTO crearEvento(EventoDTO evento) throws BusinessLogicException{
     
        EventoEntity entity = evento.toEntity();
        EventoEntity nuevoEntity = eventoLogic.crearEvento(entity);
        EventoDTO nuevoEvento = new EventoDTO(nuevoEntity);
        
        return nuevoEvento;
    }
    
    /**
     * Busca y devuelve todos los eventos que existen en la aplicacion.
     *
     * @return JSONArray {@link EventoDTO} - Los eventos de la aplicacion.
     */
    @GET 
    public List<EventoDTO> darEventos(){
        
        List<EventoDTO> listaEventos = listaEntidadesADTO(eventoLogic.encontrarTodosEventos());
        
        return listaEventos;
    }
   
    /**
     * Convierte una lista de entidades a DTO.
     *
     * @param listaEntidad corresponde a la lista de eventos de tipo Entity
     * que se va a convertir a DTO.
     * @return la lista de eventos en forma DTO (json)
     */
    private List<EventoDTO> listaEntidadesADTO(List<EventoEntity> listaEntidad){
        
        List<EventoDTO> lista = new ArrayList();
        for(EventoEntity entity : listaEntidad){
            lista.add(new EventoDTO(entity));
        }
        
        return lista;
    }
    
      /**
     * Busca el evento con el id asociado y lo devuelve.
     *
     * @param id Identificador del evento que se esta buscando.
     * @return EventoDTO - El evento buscado.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el evento.
     */
     @GET 
     @Path("{id: \\d+}")
    public EventoDTO darEventoPorId(@PathParam("id") Long id) throws WebApplicationException{
        
         EventoEntity eventoEntity = eventoLogic.encontrarEventoPorId(id);
        
        if(eventoEntity == null){
            throw new WebApplicationException("No existe un evento con ese identificador", 404);
        }
        
        EventoDTO evento = new EventoDTO(eventoEntity);

        return evento;
    }
    
    @GET
    @Path("/{nombre}")
    public EventoDTO darArticuloPorNombre(@PathParam("nombre") String nombre) throws WebApplicationException, BusinessLogicException {
        
        EventoEntity eventoEntity = eventoLogic.buscarEventoPorNombre(nombre);
        
        if(eventoEntity == null){
            throw new WebApplicationException("No existe un evento con ese nombre", 404);
        }
        
        EventoDTO evento = new EventoDTO(eventoEntity);

        return evento;
    }
    
    /**
     * Actualiza el evento con el id recibido por parametro con la informacion
     * que se recibe en el cuerpo de la petición.
     *
     * @param id Identificador del evento que se desea
     * actualizar. 
     * @param eventoNuevo {@link EventoDTO} El evento con la nueva informacion. 
     * @return JSON {@link EventoDTO} - El evento actualizado y guardado.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el evento a
     * actualizar.
     */
    @PUT
    @Path("{id: \\d+}")
    public EventoDTO actualizarEvento(@PathParam("id") Long id, EventoDTO eventoNuevo) throws WebApplicationException, BusinessLogicException{
        
        eventoNuevo.setId(id);
        
        if(eventoLogic.encontrarEventoPorId(id) == null){
            throw new WebApplicationException("No existe un evento con ese identificador", 404);
        }
        
        EventoDTO evento = new EventoDTO(eventoLogic.actualizarEvento(id, eventoNuevo.toEntity()) );
        
        return evento;
    }
    
    
     /**
     * Borra un evento con el id enviado por parametro.
     *
     * @param id Identificador del evento que se quiere borrar.
     * Este debe ser una cadena de dígitos.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el evento.
     */
    @DELETE
     @Path("{id: \\d+}")
    public void eliminarEventoPorId(@PathParam("id") Long id){
        
        if(eventoLogic.encontrarEventoPorId(id) == null){
            throw new WebApplicationException("No existe un evento con ese identificador", 404);
        }
        
        eventoLogic.eliminarEvento(id);
        
    }
    
    
    
    
}
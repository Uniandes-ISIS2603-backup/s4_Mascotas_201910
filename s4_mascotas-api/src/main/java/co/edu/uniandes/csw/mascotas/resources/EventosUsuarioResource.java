/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.resources;

import co.edu.uniandes.csw.mascotas.dtos.EventoDTO;
import co.edu.uniandes.csw.mascotas.ejb.EventoLogic;
import co.edu.uniandes.csw.mascotas.ejb.EventosUsuarioLogic;
import co.edu.uniandes.csw.mascotas.entities.EventoEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

/**
 * Clase que implementa el recurso usuario/{id}/eventos
 * @authorNatalia Sanabria Forero
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("usuario/{id}/eventos")
public class EventosUsuarioResource 
{
    /**
     * Variable para acceder a la lógica del usuario
     */
    @Inject
   private EventosUsuarioLogic logica;
    
    /**
     * Variable para acceder a la lógica de los eventos de la aplicación
     */
    @Inject
    private EventoLogic eventoLogic;
    
    /**
     * Le agrega un evento a un usuario con la información que recibe de la URL
     * @param eventoId identificador del evento
     * @param usuarioId identificador del usuario
     * @throws WebApplicationException si no se encuentra el evento
     * @return evento
     */
    @POST
    @Path("{eventoId}")
    public EventoDTO agregarEvento(@PathParam("usuarioId") Long usuarioId, @PathParam("eventoId") Long eventoId) throws WebApplicationException
    {
        if(eventoLogic.encontrarEventoPorId(eventoId)== null)
            throw new WebApplicationException("El recurso /eventos/"+eventoId + " no existe.", 404);
        EventoDTO eventoDTO = new EventoDTO(logica.agregarEventoAUsuario(eventoId, usuarioId));
        return eventoDTO;
    }
    
    /**
     * Retorna una lista con todos los eventos que ha organizado un usuario dado su id
     * @param usuarioId
     * @return respuesta
     */
    @GET
    public List<EventoDTO> darEventosUsuario(@PathParam("usuarioId")Long usuarioId)
    {
        List<EventoEntity> listaEventos = logica.darEventosUsuario(usuarioId);
        List<EventoDTO> respuesta = new ArrayList<>();
        for(EventoEntity entity: listaEventos)
            respuesta.add(new EventoDTO(entity));
        return respuesta;
    }
    
    /**
     * Busca el evento asociacido a un usuario con ambos identificadores
     * @param usuarioId id del usuario
     * @param eventoId id del evento
     * @return evento asoaciado al usuario con el id eventoId
     * @throws BusinessLogicException Si no encuentra el evento en la lista de eventos del usuario
     */
    @GET
    @Path("{eventoId}")
    public EventoDTO darEventoAsociadoAUsuario(@PathParam("usuarioId") Long usuarioId, @PathParam("eventoId") Long eventoId) throws BusinessLogicException
    {
        if (eventoLogic.encontrarEventoPorId(eventoId) == null) {
            throw new WebApplicationException("El recurso /usuario/" + usuarioId + "/eventos/" + eventoId + " no existe.", 404);
        }
        EventoDTO eventoDTO = new EventoDTO(logica.buscarEventoEnUsuario(eventoId, usuarioId));
        return eventoDTO;
    }
   
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.resources;

import co.edu.uniandes.csw.mascotas.dtos.EventoDTO;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author estudiante
 */

@Path("eventos")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped

public class EventoResource {
    
    private static final Logger LOGGER = Logger.getLogger(EventoResource.class.getName());
    
     @POST
    public EventoDTO crearEvento(EventoDTO evento){
     
        return evento;
    }
    
    @GET 
    public List<EventoDTO> darEventos(){
        //TODO
        return null;
    }
   
     @GET 
     @Path("{id: \\d+}")
    public EventoDTO darEventoPorId(@PathParam("id") Long id){
        //TODO
        
        return null;
    }
    
    @DELETE
     @Path("{id: \\d+}")
    public void eliminarEventoPorId(@PathParam("id") Long id){
        
    }
    
    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.resources;

import co.edu.uniandes.csw.mascotas.dtos.CalificacionDTO;
import co.edu.uniandes.csw.mascotas.ejb.CalificacionLogic;
import co.edu.uniandes.csw.mascotas.entities.CalificacionEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.swing.text.html.HTML;
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
 * @author s.canales
 */

@Path("calificaciones")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped

public class CalificacionResource {
    
    private static final Logger LOGGER = Logger.getLogger(ArticuloResource.class.getName());
    
    /**
     * la lógica de las calificaciones
     */
    @Inject
    private CalificacionLogic logic;
    
    /**
     * crea una nueva calificación asociada a un proceso
     * @param calificacion
     * @return
     * @throws BusinessLogicException 
     */
    @POST
    public CalificacionDTO createCalificacion(CalificacionDTO calificacion) throws BusinessLogicException{
        
        CalificacionDTO nueva = new CalificacionDTO();
        logic.createCalificacion(calificacion.toEntity());
        return nueva;
    }
    /**
     * devuelve todas las calificaciones
     * @return 
     */
    @GET
    public List<CalificacionDTO> getCalificaciones(){
        
        List<CalificacionDTO> calificaciones = new ArrayList<>();
        List<CalificacionEntity> entidades = logic.getCalificaciones();
        
        
        for(CalificacionEntity actual : entidades){
            
            
            calificaciones.add(new CalificacionDTO(actual));
        }
        
        return calificaciones;        
    }
    /**
     * devuelve una calificación por id
     * @param id
     * @return
     * @throws BusinessLogicException 
     */
    @GET
    @Path("{id: \\d+}")
    public CalificacionDTO getCalificacion(@PathParam("id") Long id) throws BusinessLogicException{
        
      CalificacionEntity entity = logic.getCalificacion(id);
      if(entity == null) throw new WebApplicationException("no se encontró una calificacion con id " + id, 404);
      CalificacionDTO dto = new CalificacionDTO(entity);
     
      
      return dto;
    }
    /**
     * borra una calificación por id
     * @param id
     * @throws BusinessLogicException 
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteCalificacion(@PathParam("id") Long id) throws BusinessLogicException{
        CalificacionEntity entity = logic.getCalificacion(id);
        if(entity == null) throw new WebApplicationException("no se encontró una calificacion con id " + id, 404);
        logic.deleteCalificacion(id);
    }

    /**
     * Modifica una calificacion
     * @param id
     * @return asd
     */
    @PUT
    @Path("{id: \\d+}")
    public CalificacionDTO updateCalificacionDTO(@PathParam("id") Long id, CalificacionDTO dto) throws Exception{
        
        CalificacionEntity entity = dto.toEntity();
        entity.setId(id);
        if(logic.getCalificacion(id) == null) throw new WebApplicationException("no se encontró una calificación con ese id");        
        return new CalificacionDTO(logic.updateCalificacion(id,entity));
    }
    
    
    
}

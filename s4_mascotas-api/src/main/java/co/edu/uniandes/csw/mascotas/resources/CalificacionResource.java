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
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

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
    
    @Inject
    private CalificacionLogic logic;
    
    @POST
    public CalificacionDTO createCalificacion(CalificacionDTO calificacion) throws BusinessLogicException{
        
        CalificacionDTO nueva = new CalificacionDTO();
        logic.createCalificacion(calificacion.toEntity());
        return nueva;
    }
    /**
     * como la lista entidades, toca pasarlas a dto, meterlas a la nueva lista y retornarla
     * @return 
     */
    @GET
    public List<CalificacionDTO> getCalificaciones(){
        
        List<CalificacionDTO> calificaciones = new ArrayList<>();
        List<CalificacionEntity> entidades = logic.getCalificaciones();
        CalificacionDTO dto = new CalificacionDTO();
        
        for(CalificacionEntity actual : entidades){
            
            dto.setCalificacion(actual.getCalificacion());
            dto.setComentario(actual.getComentario());
            calificaciones.add(dto);
        }
        
        return calificaciones;        
    }
    
    @GET
    @Path("{id: \\d+}")
    public CalificacionDTO getCalificacion(@PathParam("id") Long id) throws BusinessLogicException{
        
      CalificacionEntity entity = logic.getCalificacion(id);
      CalificacionDTO dto = new CalificacionDTO();
      dto.setCalificacion(entity.getCalificacion());
      dto.setComentario(entity.getComentario());
      
      return dto;
    }
    
    @DELETE
    @Path("{id: \\d+}")
    public void deleteCalificacion(@PathParam("id") Long id) throws BusinessLogicException{
        
        logic.deleteCalificacion(id);
    }

    /**
     * No pueden existir parámetros sin anotaciones (salvo los DTO´s)
     */
//    @PUT
//    @Path("{id: \\d+}")
//    public CalificacionDTO uptaCalificacionDTO(@PathParam("id") Long id , String comentario, Integer calificacion) throws BusinessLogicException{
//        
//        CalificacionDTO dto = new CalificacionDTO();
//        CalificacionEntity entity = logic.updateCalificacion(id, comentario, calificacion);
//        dto.setCalificacion(entity.getCalificacion());
//        dto.setComentario(entity.getComentario());
//        
//        return dto;
//    }
    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.resources;
import co.edu.uniandes.csw.mascotas.dtos.CalificacionDTO;
import co.edu.uniandes.csw.mascotas.dtos.MascotaEnAdopcionDTO;
import co.edu.uniandes.csw.mascotas.ejb.CalificacionLogic;
import co.edu.uniandes.csw.mascotas.ejb.MascotaEnAdopcionLogic;
import co.edu.uniandes.csw.mascotas.ejb.MascotaExtraviadaLogic;
import co.edu.uniandes.csw.mascotas.entities.CalificacionEntity;
import co.edu.uniandes.csw.mascotas.entities.MascotaEnAdopcionEntity;
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
 * @author s.canales
 */
@Path("procesoMascotaEnAdopcion")
@Produces("application/json")
@Consumes("application/json")
public class MascotaEnAdopcionResource {
    
    /**
     * la lógica de los procesos de adopción
     */
    private MascotaEnAdopcionLogic logic;
    
    private static final Logger LOGGER = Logger.getLogger(MascotaEnAdopcionLogic.class.getName());
    
    /**
     * crea una mascota nueva
     * @param dto
     * @return
     * @throws BusinessLogicException 
     */
    @POST
    public MascotaEnAdopcionDTO createMascotaEnAdopcion(MascotaEnAdopcionDTO dto)throws BusinessLogicException{
        
        return new MascotaEnAdopcionDTO(logic.createMascotaEnAdopcion(dto.toEntity()));
    }
    /**
     * deuelve un proceso de adopción por id
     * @param id
     * @return
     * @throws Exception 
     */
    @GET
    @Path("{processId}")
    public MascotaEnAdopcionDTO getMascotaEnAdopcion(@PathParam("processId")Long id) throws Exception{
        
        return new MascotaEnAdopcionDTO(logic.getMascotaEnAdopcion(id));
    }
    /**
     * devuelve todos los procesos de adopción 
     * @return
     * @throws Exception 
     */
    @GET
    public List<MascotaEnAdopcionDTO> getMascotasEnAdopcion() throws Exception{
        
        List<MascotaEnAdopcionDTO> listaDTOs = new ArrayList<>();
        List<MascotaEnAdopcionEntity> listaEntities = logic.getMascotasEnAdopcion();
                
        
        for(MascotaEnAdopcionEntity actual : listaEntities){
            
         listaDTOs.add(new MascotaEnAdopcionDTO(actual));
        }
        
        return listaDTOs;
    }
    /**
     * borra un proceso de adopción por id
     * @param id
     * @throws Exception 
     */
    @DELETE
    public void deleteMascotaEnAdopcion(Long id) throws Exception{
        
        logic.deleteMascotaEnAdopcion(id);
    }
    /**
     * modifica un proceso de adopción
     * @param entity
     * @param id
     * @return
     * @throws Exception 
     */
    @PUT
    @Path("{id: \\d+}")
    public MascotaEnAdopcionDTO updateMascotaEnAdopcion(MascotaEnAdopcionEntity entity , @PathParam("id") Long id) throws Exception{
        
        MascotaEnAdopcionEntity e = logic.getMascotaEnAdopcion(id);
        if(e == null) throw new WebApplicationException("no se encontró un proceso de Mascota En adopción con ese id");
        
        return new MascotaEnAdopcionDTO(logic.updateMascotaEnAdopcion(entity , id));
    }
    
}

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
/**
 *
 * @author s.canales
 */
public class MascotaEnAdopcionResource {
    
    private MascotaEnAdopcionLogic logic;
    
    private static final Logger LOGGER = Logger.getLogger(MascotaEnAdopcionLogic.class.getName());
    
    
    @POST
    public MascotaEnAdopcionDTO createMascotaEnAdopcion(MascotaEnAdopcionDTO dto)throws BusinessLogicException{
        
        return new MascotaEnAdopcionDTO(logic.createMascotaEnAdopcion(dto.toEntity()));
    }
    
    @GET
    public MascotaEnAdopcionDTO getMascotaEnAdopcion(Long id) throws Exception{
        
        return new MascotaEnAdopcionDTO(logic.getMascotaEnAdopcion(id));
    }
    
    @GET
    public List<MascotaEnAdopcionDTO> getMascotasEnAdopcion() throws Exception{
        
        List<MascotaEnAdopcionDTO> listaDTOs = new ArrayList<>();
        List<MascotaEnAdopcionEntity> listaEntities = logic.getMascotasEnAdopcion();
                
        
        for(MascotaEnAdopcionEntity actual : listaEntities){
            
         listaDTOs.add(new MascotaEnAdopcionDTO(actual));
        }
        
        return listaDTOs;
    }
    @DELETE
    public void deleteMascotaEnAdopcion(Long id) throws Exception{
        
        logic.deleteMascotaEnAdopcion(id);
    }
    @PUT
    @Path("{id: \\d+}")
    public MascotaEnAdopcionDTO updateMascotaEnAdopcion(MascotaEnAdopcionEntity entity , @PathParam("id") Long id) throws Exception{
        
        return new MascotaEnAdopcionDTO(logic.updateMascotaEnAdopcion(entity , id));
    }
    
}

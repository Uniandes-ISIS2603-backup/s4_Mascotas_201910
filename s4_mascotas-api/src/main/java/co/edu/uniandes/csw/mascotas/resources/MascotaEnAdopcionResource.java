/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.resources;
import co.edu.uniandes.csw.mascotas.dtos.CalificacionDTO;
import co.edu.uniandes.csw.mascotas.dtos.MascotaEnAdopcionDTO;
import co.edu.uniandes.csw.mascotas.dtos.UsuarioDTO;
import co.edu.uniandes.csw.mascotas.ejb.CalificacionLogic;
import co.edu.uniandes.csw.mascotas.ejb.MascotaEnAdopcionLogic;
import co.edu.uniandes.csw.mascotas.ejb.MascotaExtraviadaLogic;
import co.edu.uniandes.csw.mascotas.entities.CalificacionEntity;
import co.edu.uniandes.csw.mascotas.entities.MascotaEnAdopcionEntity;
import co.edu.uniandes.csw.mascotas.entities.UsuarioEntity;
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
@Path("procesosMascotaEnAdopcion")
@Produces("application/json")
@Consumes("application/json")
public class MascotaEnAdopcionResource {
    
    /**
     * la lógica de los procesos de adopción
     */
    @Inject
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
    @Path("{id: \\d+}")
    public MascotaEnAdopcionDTO getMascotaEnAdopcion(@PathParam("id")Long id) throws Exception{
        MascotaEnAdopcionEntity entity = logic.getMascotaEnAdopcion(id);
        if(entity == null)
            throw new WebApplicationException("no existe la mascota con id " + id, 404);
        
        return new MascotaEnAdopcionDTO(logic.getMascotaEnAdopcion(id));
    }
    /**
     * devuelve la lista de las mascotas que están sin adoptar
     * @return
     * @throws Exception 
     */
    @GET
    @Path("/sinAdoptar")
    public List<MascotaEnAdopcionDTO> getMascotasSinAdoptar() throws Exception{
        List<MascotaEnAdopcionEntity> listaEntities = logic.getMascotasSinAdoptar();
      
        return toDTO(listaEntities);
    }
    /**
     * devuele los postulados del proceso asociado
     * @param id
     * @return
     * @throws Exception 
     */
    @GET
    @Path("/postulados/{id}")
    public List<UsuarioDTO> getPostuladosByProceso(@PathParam("id")Long id) throws Exception{
        List<UsuarioEntity> listaEntities = logic.getPostuladosByProceso(id);
        List<UsuarioDTO> listaDTOs = new ArrayList<>();
        for(UsuarioEntity actual : listaEntities){
            
         listaDTOs.add(new UsuarioDTO(actual));
        }
        
        return listaDTOs;
    }
    
    /**
     * devuelve la lista de las mascotas filtradas por el tipo
     * @param raza
     * @return
     * @throws Exception 
     */
    @GET
    @Path("/filtrar/{tipo}")
    public List<MascotaEnAdopcionDTO> getProcesosPorRaza(@PathParam("tipo")String tipo) throws Exception{
        List<MascotaEnAdopcionEntity> listaEntities = logic.getProcesosPorTipo(tipo);
      
        return toDTO(listaEntities);
    }
    /**
     * devuelve todos los procesos de adopción 
     * @return
     * @throws Exception 
     */
    @GET
    public List<MascotaEnAdopcionDTO> getMascotasEnAdopcion() throws Exception{
        
        
        List<MascotaEnAdopcionEntity> listaEntities = logic.getMascotasEnAdopcion();
      
        return toDTO(listaEntities);
    }
    /**
     * converts a list from entities to dto
     * @param listaEntities
     * @return 
     */
    public List<MascotaEnAdopcionDTO> toDTO(List<MascotaEnAdopcionEntity> listaEntities){
        List<MascotaEnAdopcionDTO> listaDTOs = new ArrayList<>();
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
    @Path("{id: \\d+}")
    public void deleteMascotaEnAdopcion(@PathParam("id") Long id) throws Exception{
        MascotaEnAdopcionEntity entity = logic.getMascotaEnAdopcion(id);
        if(entity == null)
            throw new WebApplicationException("no existe la mascota con id " + id, 404);
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
    public MascotaEnAdopcionDTO updateMascotaEnAdopcion(MascotaEnAdopcionDTO dto , @PathParam("id") Long id) throws Exception{
        
        MascotaEnAdopcionEntity e = dto.toEntity();
        e.setId(id);
        if(logic.getMascotaEnAdopcion(id) == null) throw new WebApplicationException("no se pudo modificar la mascota con id " + id, 404);
        
        return new MascotaEnAdopcionDTO(logic.updateMascotaEnAdopcion(e , id));
    }
    
}

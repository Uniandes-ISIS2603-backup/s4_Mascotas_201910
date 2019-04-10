/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.resources;
import javax.ws.rs.*;
import co.edu.uniandes.csw.mascotas.dtos.UsuarioDTO;
import co.edu.uniandes.csw.mascotas.dtos.UsuarioDetailDTO;
import co.edu.uniandes.csw.mascotas.ejb.UsuarioLogic;
import co.edu.uniandes.csw.mascotas.entities.UsuarioEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * 
 *
 * @author Maria Ana Ortiz ma.ortiz1
 */
@Path("usuario")
@Produces("application/json")
@Consumes("application/json")
public class UsuarioResource {
    private static final Logger LOGGER = Logger.getLogger(UsuarioResource.class.getName());
    
    @Inject
    private UsuarioLogic logica;
    
    
    @POST
    public UsuarioDTO crearUsuario(UsuarioDTO usuario) throws BusinessLogicException{
        UsuarioEntity entidad = usuario.toEntity();
        entidad = logica.crearUsuario(entidad);
        return new UsuarioDTO(entidad);
    }
    
    @GET
    @Path("{id: \\d+}")
    public UsuarioDTO darUsuuario(@PathParam("id") Long id) throws BusinessLogicException
    {      

               UsuarioEntity usuario = logica.getUsuario(id);
               return  new UsuarioDTO(usuario);

    }
    
    
    
    @PUT
    @Path("{id: \\d+}")
    public UsuarioDTO actualizar(@PathParam("id") Long id,  UsuarioDTO usuario)throws BusinessLogicException
    {      
            usuario.setId(id);
            UsuarioEntity usr = usuario.toEntity();
           
            UsuarioEntity resp= logica.actualizarInformacion(id, usr);
            UsuarioDTO fo = new UsuarioDTO(resp);
            
        return fo;
    }
    
    @DELETE
    @Path("{id: \\d+}")
    public void eliminarUsuario(@PathParam("id") Long id ) throws BusinessLogicException{      
      UsuarioEntity usuario = logica.getUsuario(id);
      logica.deleteUsuario(id);     
    
    }
//    /**
//     * Metodo que retorna todos los usuarios
//     * @return todos los usuarios
//     */
//    @GET
//    public List<UsuarioDTO> darUsuario(){
//        List<UsuarioEntity> usuarios = logica.getUsuarios();
//                List<UsuarioDTO> newList = new ArrayList<>();
//        for(UsuarioEntity x : usuarios){
//            newList.add(new UsuarioDTO(x));
//        }
//        return newList;
//        
//    }
//    
//    
    /**
     * Conexión con el servicio de articulos para un usuario.
     * {@link UsuarioArticulosResource}
     *
     * Este método conecta la ruta de /usuarios con las rutas de /articulos que
     * dependen del usuario, es una redirección al servicio que maneja el
     * segmento de la URL que se encarga de los articulos del usuario.
     *
     * @param usuarioId El ID del usuario con respecto a la cual se
     * accede al servicio.
     * @return El servicio de libros para esta editorial en paricular.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la editorial.
     */
    @Path("{usuarioId: \\d+}/articulos")
    public Class<UsuarioArticulosResource> getUsuarioArticulosResource(@PathParam("usuarioId") Long usuarioId) {
        //if (usuarioLogic.getUsuario(usuarioId) == null) {
        //    throw new WebApplicationException("El recurso /usuarios/" + usuarioId + " no existe.", 404);
        //}
        return UsuarioArticulosResource.class;
    }
    
        
    
        /**
     * Busca y devuelve todas las editoriales que existen en la aplicacion.
     *
     * @return JSONArray {@link EditorialDetailDTO} - Las editoriales
     * encontradas en la aplicación. Si no hay ninguna retorna una lista vacía.
     */
    @GET
    public List<UsuarioDetailDTO> getUsuarios() {
      
        List<UsuarioDetailDTO>listaEditoriales = listEntity2DetailDTO(logica.getUsuarios());

        return listaEditoriales;
    }

    
    
     /**
     * Convierte una lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos EditorialEntity a una lista de
     * objetos UsuarioDetailDTO (json)
     *
     * @param entityList corresponde a la lista de editoriales de tipo Entity
     * que vamos a convertir a DTO.
     * @return la lista de editoriales en forma DTO (json)
     */
    private List<UsuarioDetailDTO> listEntity2DetailDTO(List<UsuarioEntity> entityList) {
        List<UsuarioDetailDTO> list = new ArrayList<>();
        for (UsuarioEntity entity : entityList) {
            list.add(new UsuarioDetailDTO(entity));
        }
        return list;
    }
}

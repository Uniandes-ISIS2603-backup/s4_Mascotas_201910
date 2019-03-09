/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.resources;
import javax.ws.rs.*;
import co.edu.uniandes.csw.mascotas.dtos.UsuarioDTO;
import java.util.logging.Logger;
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
    
    @POST
    public UsuarioDTO crearUsuario(UsuarioDTO usuario){
        return usuario;
    }
    
    @GET
    @Path("{id: \\d+}")
    public UsuarioDTO darUsuuario(@PathParam("id") Long id){      
        
        return null;
    }
    
    
// Realizar solo un mètodo put para todas las posibles actualizaciones del usuario   
//    @PUT
//    @Path("{id: \\d+}")
//    public UsuarioDTO actualizarContraseñaUsuario(@PathParam("id") Long id, String contrasenha){      
//        
//        // esta operaciòn debe ser manejada por la lògica y la persistencia no por las entidaddes
//        // Corregir.
//        //usuario.setContrasenha(contrasenha);
//        
//        //return usuario;
//        return null;
//    }
//    
//    @PUT
//    @Path("{id: \\d+}")
//    public UsuarioDTO actualizarTelefono(@PathParam("id") Long id, int telefono){      
//        // esta operaciòn debe ser manejada por la lògica y la persistencia no por las entidaddes
//        // Corregir.
//        //usuario.setTelefono(telefono);
//        
//        //return usuario;
//        return null;
//    }
//    
//    @PUT
//    @Path("{id: \\d+}")
//    public UsuarioDTO actualizarNombre(@PathParam("id") Long id, String nombre){      
//        // esta operaciòn debe ser manejada por la lògica y la persistencia no por las entidaddes
//        // Corregir.
//        //usuario.setNombre(nombre);
//        
//        return null;
//    }
    
    
    
    @PUT
    @Path("{id: \\d+}")
    public UsuarioDTO actualizar(@PathParam("id") Long id,  boolean recibeNotificaciones){      
        // esta operaciòn debe ser manejada por la lògica y la persistencia no por las entidaddes
        // Corregir.
        //usuario.setRecibeNotificaciones(recibeNotificaciones);
        
        return null;
    }
    
    @DELETE
    @Path("{id: \\d+}")
    public void eliminarUsuario(@PathParam("id") Long id ){      
      
    
    }
    
    
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
    
    
}

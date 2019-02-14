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
    public UsuarioDTO darUsuuario(@PathParam("usuario") String usuario){      
        
        return null;
    }
    
    
    @PUT
    public UsuarioDTO actualizarContraseñaUsuario(UsuarioDTO usuario,@PathParam("contrasenha") String contrasenha){      
        usuario.setContrasenha(contrasenha);
        
        return usuario;
    }
    
    @PUT
    public UsuarioDTO actualizarTelefono(UsuarioDTO usuario,@PathParam("telefono") int telefono){      
        usuario.setTelefono(telefono);
        
        return usuario;
    }
    
    @PUT
    public UsuarioDTO actualizarNombre(UsuarioDTO usuario,@PathParam("nombre") String nombre){      
        usuario.setNombre(nombre);
        
        return usuario;
    }
    
    
    
    @PUT
    public UsuarioDTO actualizar(UsuarioDTO usuario,@PathParam("recibeNotificaciones") boolean recibeNotificaciones){      
        usuario.setRecibeNotificaciones(recibeNotificaciones);
        
        return usuario;
    }
    
     @DELETE
    public void eliminarUsuario(@PathParam("usuario") String usuario ){      
      
    
    }
    
    
    
}

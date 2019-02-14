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
    @Path("{usuario: {[a-zA-Z][a-zA-Z]*}}")
    public UsuarioDTO (@PathParam("usuario") String usuario){
        return  null;
    }

    
}

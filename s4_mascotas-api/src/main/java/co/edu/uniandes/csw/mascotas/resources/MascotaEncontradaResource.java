/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.resources;

import co.edu.uniandes.csw.mascotas.dtos.MascotaEncontradaDTO;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author Johan E. Vivas Sepulveda (je.vivas)
 */
@Path("procesosMascotaEncontrada")
@Consumes("application/json")
@Produces("application/json")
@RequestScoped

public class MascotaEncontradaResource {
    
    public static final Logger LOGGER = Logger.getLogger(MascotaEncontradaResource.class.getName());
    
    @POST
    public MascotaEncontradaDTO crearMascotaEncontrada (MascotaEncontradaDTO mascotaEncontrada)
    {
        return mascotaEncontrada;
    }
    
    
    
}

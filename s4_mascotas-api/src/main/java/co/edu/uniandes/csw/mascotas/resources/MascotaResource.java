/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.resources;

import co.edu.uniandes.csw.mascotas.dtos.MascotaDTO;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;

/**
 *
 * @author Natalia Sanabria Forero
 */
@Path("mascotas")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class MascotaResource {
    
    private static final Logger LOGGER = Logger.getLogger(MascotaResource.class.getName());
    
    @POST
    public MascotaDTO crearMascota(MascotaDTO mascota) 
    {
        return mascota;
    }
    
 //   @GET
 //   public MascotaDTO darMascota( MascotaDTO mascota )
 //   {
 //       return mascota;
 //   }
    
 //   @GET
 //   public MascotaDTO darMascotasPorEstado( )
 //   {
 //       
 //   }
    
    //@PUT
    //public void actualizarEstadoMascota( MascotaDTO mascota, String nuevoEstado )
    //{
    //    mascota.setEstado_mascota(nuevoEstado);
    //}
}

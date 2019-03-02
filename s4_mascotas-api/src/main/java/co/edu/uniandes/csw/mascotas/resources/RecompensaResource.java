/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.resources;

import co.edu.uniandes.csw.mascotas.dtos.RecompensaDTO;
import java.util.List;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author Sebastián Lemus Cadena (s.lemus)
 */
@Path("recompensas")
@Produces("application/json")
@Consumes("application/json")
public class RecompensaResource {
    
    private static final Logger LOGGER = Logger.getLogger(RecompensaResource.class.getName());
    
    /**
     * Realiza la creación de una recompensa de acuerdo
     * a la información contenida en el DTO dado
     * @param r - DTO de la recompensa
     * @return DTO con la información respectiva
     */
    @POST
    public RecompensaDTO createRecompensa(RecompensaDTO r){
        return r;
    }
    
    /**
     * 
     * @return Todas las recompensas existentes
     */
    @GET
    public List<RecompensaDTO> getRecompensas(){
        return null;
    }
    
    /**
     * 
     * @param id - id de la recompensa
     * @return La recompensa correspondiente al id
     */
    @GET
    @Path("{id: \\d+}")
    public RecompensaDTO getRecompensa(Long id){
        return null;
    }
    
    /**
     * Elimina la recompensa asociada al id dado
     * @param id - id de la recompensa
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteRecompensaPorId(Long id){
        
    }
}

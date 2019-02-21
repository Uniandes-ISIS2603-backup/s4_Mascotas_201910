/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.resources;

import co.edu.uniandes.csw.mascotas.dtos.MascotaExtraviadaDTO;
import co.edu.uniandes.csw.mascotas.entities.MascotaExtraviadaEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import java.util.List;
import java.util.logging.Logger;
import javax.inject.Inject;
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
@Path("procesosMascotaExtraviada")
@Produces("application/json")
@Consumes("application/json")
public class MascotaExtraviadaResource {
    
    private static final Logger LOGGER = Logger.getLogger(MascotaExtraviadaResource.class.getName());
    
    /**
     * Realiza la creación de un nuevo proceso de mascota
     * extraviada de acuerdo a la información del DTO dado
     * @param p - DTO del proceso
     * @return DTO con la información dada
     */
    @POST
    public MascotaExtraviadaDTO crearProcesoMascotaExtraviada(MascotaExtraviadaDTO p){
        return p;
    }
    
    /**
     * 
     * @return Todos los procesos de mascotas extraviadas existentes
     */
    @GET
    public List<MascotaExtraviadaDTO> darProcesosMascotaExtraviada(){
        return null;
    }
    
    /**
     * 
     * @param id - id del proceso a buscar
     * @return El proceso correspondiente al id
     */
    @GET
    @Path("{id: \\d+}")
    public MascotaExtraviadaDTO darProcesoMascotaExtraviadaPorId(Long id){
        return null;
    }
    
    /**
     * Elimina un proceso de mascota extraviada correspondiente
     * al id indicado
     * @param id - id del proceso
     */
    @DELETE
    @Path("{id: \\d+}")
    public void eliminarProcesoMascotaExtraviadaPorId(Long id){
        
    }
}

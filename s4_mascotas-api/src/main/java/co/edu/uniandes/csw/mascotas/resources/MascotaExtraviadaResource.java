/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.resources;

import co.edu.uniandes.csw.mascotas.dtos.MascotaExtraviadaDTO;
import co.edu.uniandes.csw.mascotas.ejb.MascotaExtraviadaLogic;
import co.edu.uniandes.csw.mascotas.ejb.MascotaExtraviadaRecompensaLogic;
import co.edu.uniandes.csw.mascotas.entities.MascotaExtraviadaEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
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
 * @author Sebastián Lemus Cadena (s.lemus)
 */
@Path("procesosMascotaExtraviada")
@Produces("application/json")
@Consumes("application/json")
public class MascotaExtraviadaResource {
    
    /**
     * La lógica de los procesos de mascota extraviada
     */
    @Inject
    private MascotaExtraviadaLogic mascotaExtraviadaLogic;
    
    /**
     * La lógica que maneja procesos de mascota extraviada y sus recompensas
     */
    @Inject
    private MascotaExtraviadaRecompensaLogic mascotaExtraviadaRecompensaLogic;
    
    private static final Logger LOGGER = Logger.getLogger(MascotaExtraviadaResource.class.getName());
    
    /**
     * Realiza la creación de un nuevo proceso de mascota
     * extraviada de acuerdo a la información del DTO dado
     * @param p - DTO del proceso
     * @return DTO con la información dada
     */
    @POST
    public MascotaExtraviadaDTO createProcesoMascotaExtraviada(MascotaExtraviadaDTO p)throws Exception{
        return new MascotaExtraviadaDTO(mascotaExtraviadaLogic.createMascotaExtraviada(p.toEntity()));
    }
    
    /**
     * 
     * @return Todos los procesos de mascotas extraviadas existentes
     */
    @GET
    public List<MascotaExtraviadaDTO> getProcesosMascotaExtraviada(){
        return listaEntidadesADTO(mascotaExtraviadaLogic.getProcesosMascotaExtraviada());
    }
    
    /**
     * 
     * @param id - id del proceso a buscar
     * @return El proceso correspondiente al id
     */
    @GET
    @Path("{id: \\d+}")
    public MascotaExtraviadaDTO getProcesoMascotaExtraviada(@PathParam("id") Long id) throws Exception{
        MascotaExtraviadaEntity entity = mascotaExtraviadaLogic.getProcesoMascotaExtraviada(id);
        if(entity == null){
            throw new WebApplicationException("El proceso de mascota extraviada con id = " + id + "no existe.", 404);
        }
        return new MascotaExtraviadaDTO(entity);
    }
    
    /**
     * Elimina un proceso de mascota extraviada correspondiente
     * al id indicado
     * @param id - id del proceso
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteProcesoMascotaExtraviada(@PathParam("id") Long id) throws Exception{
        MascotaExtraviadaEntity entity = mascotaExtraviadaLogic.getProcesoMascotaExtraviada(id);
        if(entity == null){
            throw new WebApplicationException("El proceso de mascota extraviada con id = " + id + "no existe.", 404);
        }
        if(entity.getRecompensa() != null){
            mascotaExtraviadaRecompensaLogic.removeRecompensaConProceso(id);
        }
        mascotaExtraviadaLogic.deleteProcesoMascotaExtraviada(id);
    }
    
    /**
     * Actualiza el proceso de mascota extraviada asociado al id
     * @param id
     * @param p - Proceso de mascota extraviada
     * @return El proceso actualizado
     * @throws Exception 
     */
    @PUT
    @Path("{id: \\d+}")
    public MascotaExtraviadaDTO updateProcesoMascotaExtraviada(@PathParam("id") Long id, MascotaExtraviadaDTO p) throws Exception{
        p.setId(id);
        if (mascotaExtraviadaLogic.getProcesoMascotaExtraviada(id) == null) {
            throw new WebApplicationException("El recurso /procesosMascotaExtraviada/" + id + " no existe.", 404);
        }
        return new MascotaExtraviadaDTO(mascotaExtraviadaLogic.updateMascotaExtraviada(id));
    }
    
    /**
     * Transforma una lista de entidades a lista de DTO's
     * @param xs - Lista de entidades
     * @return Lista de DTO's
     */
    private List<MascotaExtraviadaDTO> listaEntidadesADTO(List<MascotaExtraviadaEntity> xs){
        List<MascotaExtraviadaDTO> newList = new ArrayList<>();
        for(MascotaExtraviadaEntity x : xs){
            newList.add(new MascotaExtraviadaDTO(x));
        }
        return newList;
    }
}

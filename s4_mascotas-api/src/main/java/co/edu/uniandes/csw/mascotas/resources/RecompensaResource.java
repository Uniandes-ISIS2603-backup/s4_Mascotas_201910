/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.resources;

import co.edu.uniandes.csw.mascotas.dtos.MascotaExtraviadaDTO;
import co.edu.uniandes.csw.mascotas.dtos.RecompensaDTO;
import co.edu.uniandes.csw.mascotas.ejb.MascotaExtraviadaRecompensaLogic;
import co.edu.uniandes.csw.mascotas.ejb.RecompensaLogic;
import co.edu.uniandes.csw.mascotas.entities.MascotaExtraviadaEntity;
import co.edu.uniandes.csw.mascotas.entities.RecompensaEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
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
@Path("recompensas")
@Produces("application/json")
@Consumes("application/json")
public class RecompensaResource {
    
    private static final Logger LOGGER = Logger.getLogger(RecompensaResource.class.getName());
    
    /**
     * La logica de la recompensa
     */
    @Inject
    private RecompensaLogic recompensaLogic;
    
    /**
     * La lógica que maneja procesos de mascota extraviada y sus recompensas
     */
    @Inject
    private MascotaExtraviadaRecompensaLogic mascotaExtraviadaRecompensaLogic;
    
    /**
     * Realiza la creación de una recompensa de acuerdo
     * a la información contenida en el DTO dado
     * @param r - DTO de la recompensa
     * @return DTO con la información respectiva
     */
    @POST
    public RecompensaDTO createRecompensa(RecompensaDTO r) throws Exception{
        return new RecompensaDTO(recompensaLogic.createRecompensa(r.toEntity()));
    }
    
    /**
     * 
     * @return Todas las recompensas existentes
     */
    @GET
    public List<RecompensaDTO> getRecompensas(){
        return listaEntidadesADTO(recompensaLogic.getRecompensas());
    }
    
    /**
     * 
     * @param id - id de la recompensa
     * @return La recompensa correspondiente al id
     * @throws java.lang.Exception
     */
    @GET
    @Path("{id: \\d+}")
    public RecompensaDTO getRecompensa(Long id) throws Exception{
        RecompensaEntity r = recompensaLogic.getRecompensa(id);
        if(r == null){
            throw new WebApplicationException("la recompensa con id = " + id + "no existe.", 404);
        }
        return new RecompensaDTO(r);
    }
    
    /**
     * Elimina la recompensa asociada al id dado
     * @param id - id de la recompensa
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteRecompensaPorId(Long id) throws Exception{
        RecompensaEntity r = recompensaLogic.getRecompensa(id);
        if(r == null){
            throw new WebApplicationException("la recompensa con id = " + id + "no existe.", 404);
        }
        mascotaExtraviadaRecompensaLogic.removeRecompensa(id);
    }
    
    /**
     * Actualiza los valores de una recompensa de acuerdo a las reglas establecidas
     * en la lógica
     * @param id
     * @param r - nueva información de la recompensa
     * @return La recompensa actualizada
     * @throws Exception 
     */
    @PUT
    @Path("{id: \\d+}")
    public RecompensaDTO updateRecompensa(@PathParam("id") Long id, RecompensaDTO r) throws Exception{
        r.setId(id);
        if(recompensaLogic.getRecompensa(id) == null){
            throw new WebApplicationException("la recompensa con id = " + id + "no existe.", 404);
        }
        return new RecompensaDTO(recompensaLogic.updateRecompensa(id, r.toEntity()));
    } 
    
    /**
     * Transforma una lista de entidades a lista de DTO's
     * @param xs - Lista de entidades
     * @return Lista de DTO's
     */
    private List<RecompensaDTO> listaEntidadesADTO(List<RecompensaEntity> xs){
        List<RecompensaDTO> newList = new ArrayList<>();
        for(RecompensaEntity x : xs){
            newList.add(new RecompensaDTO(x));
        }
        return newList;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.resources;

import co.edu.uniandes.csw.mascotas.dtos.RecompensaDTO;
import co.edu.uniandes.csw.mascotas.ejb.MascotaExtraviadaLogic;
import co.edu.uniandes.csw.mascotas.ejb.MascotaExtraviadaRecompensaLogic;
import co.edu.uniandes.csw.mascotas.ejb.RecompensaLogic;
import co.edu.uniandes.csw.mascotas.entities.MascotaExtraviadaEntity;
import co.edu.uniandes.csw.mascotas.entities.RecompensaEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Sebastián Lemus Cadena (s.lemus)
 */
@Path("procesosMascotaExtraviada/{procesoId: \\d+}/recompensa")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MascotaExtraviadaRecompensaResource {
    
    /**
     * Instancia de la lògica de procesos de mascota extraviada
     */
    @Inject
    private MascotaExtraviadaLogic procesoLogic;
    
    /**
     * Instancia de la lògica de las recompensas
     */
    @Inject
    private RecompensaLogic recompensaLogic;
    
    @Inject
    private MascotaExtraviadaRecompensaLogic procesoRecompensaLogic;
    
    /**
     * Crea y asocia una nueva recompensa a un proceso de
     * mascota extraviada
     * @param procesoId - Id del proceso de mascota extraviada
     * @param recompensa
     * @return La Recompensa creada
     * @throws Exception 
     */
    @POST
    public RecompensaDTO createRecompensa(@PathParam("procesoId") Long procesoId, RecompensaDTO recompensa) throws Exception{
        System.out.println("entrando al post createRecompensa");
        RecompensaEntity r = recompensa.toEntity();
        MascotaExtraviadaEntity p = procesoLogic.getProcesoMascotaExtraviada(procesoId);
        if(p.getRecompensa() != null){
            throw new BusinessLogicException("El proceso con id = " + procesoId + " ya tiene asociado una recompensa");
        }
        r.setProcesoMascotaExtraviada(p);
        p.setRecompensa(r);
        System.out.println("creando recompensa createRecompensa");
        RecompensaEntity createdR = recompensaLogic.createRecompensa(r);
        System.out.println("terminando de crear recompensa createRecompensa");
        procesoLogic.updateMascotaExtraviada(procesoId, p);
        return new RecompensaDTO(createdR);
    }
    
    /**
     * Busca y retorna la recompensa asociada a un proceso de mascota extraviada
     * @param procesoId - Id del proceso de mascota extraviada
     * @return La recompensa asociada al proceso
     */
    @GET
    public RecompensaDTO getRecompensa(@PathParam("procesoId") Long procesoId) throws Exception{
        return new RecompensaDTO (procesoLogic.getProcesoMascotaExtraviada(procesoId).getRecompensa());
    }
    
    /**
     * Elimina la recompensa asociada a un proceso de mascota extraviada
     * @param procesoId - Id del proceso de mascota extraviada
     */
    @DELETE
    public void deleteRecompensa(@PathParam("procesoId") Long procesoId) throws Exception{
        procesoRecompensaLogic.removeRecompensaConProceso(procesoId);
    }
    
    /**
     * Actualiza la recompensa asociada a un proceso de mascota extraviada
     * @param procesoId - Id del proceso de mascota extraviada
     * @param r - El DTO con la nueva informaciòn de la recompensa
     * @return La recompensa actualizada
     * @throws Exception 
     */
    @PUT
    public RecompensaDTO updateRecompensa(@PathParam("procesoId") Long procesoId, RecompensaDTO r) throws Exception{
        MascotaExtraviadaEntity p = procesoLogic.getProcesoMascotaExtraviada(procesoId);
        RecompensaEntity currentR = p.getRecompensa();
        RecompensaEntity newR = r.toEntity();
        if(currentR == null){
            throw new BusinessLogicException("El proceso con id = " + procesoId + " no tiene una recompensa asociada");
        }
        newR.setId(currentR.getId());
        RecompensaEntity updatedR = recompensaLogic.updateRecompensa(currentR.getId(), newR);
        p.setRecompensa(updatedR);
        procesoLogic.updateMascotaExtraviada(procesoId, p);
        return new RecompensaDTO(updatedR);
    }
}

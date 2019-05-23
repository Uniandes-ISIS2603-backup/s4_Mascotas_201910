/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.resources;

import co.edu.uniandes.csw.mascotas.dtos.MascotaDTO;
import co.edu.uniandes.csw.mascotas.dtos.MascotaExtraviadaDTO;
import co.edu.uniandes.csw.mascotas.ejb.MascotaExtraviadaLogic;
import co.edu.uniandes.csw.mascotas.ejb.MascotaLogic;
import co.edu.uniandes.csw.mascotas.entities.MascotaEntity;
import co.edu.uniandes.csw.mascotas.entities.MascotaExtraviadaEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Sebastián Lemus Cadena (s.lemus)
 */
@Path("procesosMascotaExtraviada/{procesoId: \\d+}/mascota")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MascotaProcesoResource {
    
    /**
     * Instancia de la lògica de procesos de mascota extraviada
     */
    @Inject
    private MascotaExtraviadaLogic procesoLogic;
    
    /**
     * Instancia de la lógica de las mascotas 
     */
    @Inject
    private MascotaLogic mascotaLogic;
    
    /**
     * Crea una mascota para el proceso de mascota extraviada
     */
    @POST
    public MascotaDTO createMascota(@PathParam("procesoId") Long procesoId, MascotaDTO mascota) throws Exception{
        System.out.println("entrando al post createMascota");
        MascotaEntity m = mascota.toEntity();
        MascotaExtraviadaEntity p = procesoLogic.getProcesoMascotaExtraviada(procesoId);
        if(p.getRecompensa() != null){
            throw new BusinessLogicException("El proceso con id = " + procesoId + " ya tiene asociado una mascota");
        }
        m.setProcesoMascotaExtraviada(p);
        p.setMascota(m);
        System.out.println("creando mascota createMascota");
        MascotaEntity createdM = mascotaLogic.crearMascota(m);
        System.out.println("terminando de crear mascota createMascota");
        procesoLogic.updateMascotaExtraviada(procesoId, p);
        return new MascotaDTO(createdM);
    }
    
}

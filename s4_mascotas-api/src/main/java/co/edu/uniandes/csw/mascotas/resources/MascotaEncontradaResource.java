/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.resources;

import co.edu.uniandes.csw.mascotas.dtos.MascotaEncontradaDTO;
import co.edu.uniandes.csw.mascotas.ejb.MascotaEncontradaLogic;
import co.edu.uniandes.csw.mascotas.entities.MascotaEncontradaEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author Johan E. Vivas Sepulveda (je.vivas) / Natalia Sanabria Forero (n.sanabria)
 */
@Path("procesosMascotaEncontrada")
@Consumes("application/json")
@Produces("application/json")
@RequestScoped
public class MascotaEncontradaResource 
{
    
     /**
     * La lógica de los procesos de mascota encontrada
     */
    @Inject
    private MascotaEncontradaLogic logica;

    
    @POST
    public MascotaEncontradaDTO crearMascotaEncontrada (MascotaEncontradaDTO mascotaEncontrada) throws BusinessLogicException
    {
        MascotaEncontradaEntity e = logica.createMascotaEncontrada(mascotaEncontrada.toEntity());
        return new MascotaEncontradaDTO(e);   
    }
    
    /**
     * Retorna una lista con todas las mascotas encontradas
     * @return 
     */
    @GET
    public List<MascotaEncontradaDTO> listarMascotasEncontradas( )
    {
        List<MascotaEncontradaEntity> list = logica.getProcesosMascotaEncontrada();
        List<MascotaEncontradaDTO> respuesta = new ArrayList<>();
        list.forEach((entity) -> {
            respuesta.add(new MascotaEncontradaDTO(entity));
        });
        return respuesta;
    }
    
    /**
     * Retorna un proceso de mascota encontrada dado su id
     * @param id
     * @return
     * @throws BusinessLogicException 
     */
    @GET
    @Path("{id: \\d+}")
    public MascotaEncontradaDTO darMascotaEncontradaPorId(@PathParam("id") Long id) throws BusinessLogicException
    {
        return new MascotaEncontradaDTO(logica.getProcesoMascotaEncontrada(id));
    }
    
}

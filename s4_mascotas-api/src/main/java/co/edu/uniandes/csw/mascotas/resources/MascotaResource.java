/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.resources;

import co.edu.uniandes.csw.mascotas.dtos.MascotaDTO;
import co.edu.uniandes.csw.mascotas.ejb.MascotaLogic;
import co.edu.uniandes.csw.mascotas.entities.MascotaEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

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
    
    @Inject
    private MascotaLogic logica;
    
    @POST
    public MascotaDTO crearMascota(MascotaDTO mascota) throws BusinessLogicException
    {
       MascotaEntity entidad = mascota.toEntity();
       entidad = logica.crearMascota(entidad);
       return new MascotaDTO(entidad);
    }
    
    @GET
    public MascotaDTO darMascota( MascotaDTO mascota )
    {
        MascotaEntity entidad = mascota.toEntity();
        entidad = logica.buscarMascotaPorId(entidad.getId());
        return new MascotaDTO(entidad);
    }

// Esta operaciòn GET es ambigûa con respecto al GET de darMascota
// Para diferenciar las operaciones deben tener @Path diferente, si no no funciona
// Sugerencia: para las consultas del estado, se deberìa realiza la consulta (select) en la persistencia donde se busque el estado
//    @GET
//    public List<MascotaDTO> darMascotasPorEstado( int pEstado )
//    {
//        List<MascotaDTO> mascotas = new ArrayList<>();
//        List<MascotaEntity> entidades = logica.darMascotasPorEstado(pEstado);
//        entidades.forEach((entidad) -> { mascotas.add(new MascotaDTO(entidad));});
//        return mascotas;
//    }

    /**
     * Actualiza el estado de una mascota con el estado dado por parámetro
     * @param mascota Mascota a la cual se le actualizará el estado
     * @param nuevoEstado Nuevo estado de la mascota
     * @return mascotaDTO mascota con el estado actualizado
     */
    @PUT
    @Path("{id: \\d+}")
    public MascotaDTO actualizarEstadoMascota( @PathParam("id") Long id, MascotaDTO mascota /*, Integer nuevoEstado*/ )
    {
        // Mètodo necesita correcciones
        /**
         * El atributo de nuevoEstado por dònde se recibe? este atributo es inecesario
         * puesto a que esa informaciòn deberìa estar contenida en el DTO (JSON) que se le pasò desde 
         * la aplicaciòn; por tanto dicho atributo no tiene ningùn valor.
         * 
         * Adicionalmente cualquier atributo (que no sea un DTO) debe ir anotado (por ejemplo @PathParam),
         * de lo contrario no funciona.
         * 
         * 
         */
       MascotaEntity entidad = mascota.toEntity();
       //entidad = logica.cambiarEstadoMascota(entidad.getId(), nuevoEstado);
       return new MascotaDTO(entidad);
    }
}

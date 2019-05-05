/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.resources;

import co.edu.uniandes.csw.mascotas.dtos.ClasificadoDTO;
import co.edu.uniandes.csw.mascotas.ejb.ClasificadoLogic;
import co.edu.uniandes.csw.mascotas.entities.ClasificadoEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
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
 * @author Johan E. Vivas Sepulveda (je.vivas)
 */
@Path("clasificados")
@Consumes("application/json")
@Produces("application/json")
@RequestScoped

public class ClasificadoResource {
    
    private static final Logger LOGGER = Logger.getLogger(ClasificadoResource.class.getName());

    // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.
    @Inject
    private ClasificadoLogic clasificadoLogic;

    @POST
    public ClasificadoDTO crearClasificado(ClasificadoDTO clasificado) throws BusinessLogicException {
        ClasificadoEntity entity = clasificado.toEntity();
        ClasificadoEntity nuevoEntity = clasificadoLogic.createClasificado(entity);
        ClasificadoDTO nuevoClasificado = new ClasificadoDTO(nuevoEntity);

        return nuevoClasificado;
    }

    @GET
    public List<ClasificadoDTO> darClasificados() {

        List<ClasificadoDTO> listaClasificados = listaEntidadesADTO(clasificadoLogic.getClasificados());

        return listaClasificados;
    }
    
     /**
     * Convierte una lista de entidades a DTO.
     *
     * @param listaEntidad corresponde a la lista de clasificados de tipo Entity
     * que se va a convertir a DTO.
     * @return la lista de clasificados en forma DTO (json)
     */
    private List<ClasificadoDTO> listaEntidadesADTO(List<ClasificadoEntity> listaEntidad){
        
        List<ClasificadoDTO> lista = new ArrayList();
        for(ClasificadoEntity entity : listaEntidad){
            lista.add(new ClasificadoDTO(entity));
        }
        
        return lista;
    }
    
    @GET
    @Path("{id: \\d+}")
    public ClasificadoDTO darClasificadoPorId(@PathParam("id") Long id) throws WebApplicationException {
        
        ClasificadoEntity clasificadoEntity = clasificadoLogic.buscarClasificadoPorId(id);
        
        if(clasificadoEntity == null){
            throw new WebApplicationException("No existe un clasificado con ese identificador", 404);
        }
        
        ClasificadoDTO clasificado = new ClasificadoDTO(clasificadoEntity);

        return clasificado;
    }
    
    /**
     * Actualiza el clasificado con el id recibido por parametro con la informacion
     * que se recibe en el cuerpo de la petición.
     *
     * @param id Identificador del clasificado que se desea
     * actualizar. 
     * @param clasificadoNuevo {@link ClasificadoDTO} El clasificado con la nueva informacion. 
     * @return JSON {@link ClasificadoDTO} - El clasificado actualizado y guardado.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el clasificado a
     * actualizar.
     */
    @PUT
    @Path("{id: \\d+}")
    public ClasificadoDTO actualizarClasificado(@PathParam("id") Long id, ClasificadoDTO clasificadoNuevo) throws WebApplicationException, BusinessLogicException{
        
        clasificadoNuevo.setId(id);
        
        if(clasificadoLogic.buscarClasificadoPorId(id) == null){
            throw new WebApplicationException("No existe un clasificado con ese identificador", 404);
        }
        
        ClasificadoDTO clasificado = new ClasificadoDTO( clasificadoLogic.actualizarClasificado(id, clasificadoNuevo.toEntity()) );
        
        return clasificado;
    }
    
    /**
     * Borra un clasificado con el id enviado por parametro.
     *
     * @param id Identificador del clasificado que se quiere borrar.
     * Este debe ser una cadena de dígitos.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el clasificado.
     */
    @DELETE
    @Path("{id: \\d+}")
    public void eliminarClasificadoPorId(@PathParam("id") Long id) {

        if(clasificadoLogic.buscarClasificadoPorId(id) == null){
            throw new WebApplicationException("No existe un clasificado con ese identificador", 404);
        }
        
        clasificadoLogic.eliminarClasificado(id);
    }
    
    
}




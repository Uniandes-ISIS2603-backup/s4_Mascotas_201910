/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.resources;

import co.edu.uniandes.csw.mascotas.dtos.ArticuloDTO;
import co.edu.uniandes.csw.mascotas.ejb.ArticuloLogic;
import co.edu.uniandes.csw.mascotas.ejb.UsuarioArticulosLogic;
import co.edu.uniandes.csw.mascotas.entities.ArticuloEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

/**
 * Clase que implementa el recurso "usuarios/{id}/articulos"
 * @author Daniela Gonzalez
 */

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UsuarioArticulosResource {
    
     @Inject
    private UsuarioArticulosLogic usuarioArticulosLogic;

    @Inject
    private ArticuloLogic articuloLogic;
    
    /**
     * Guarda un articulo dentro de un  usuario con la informacion que recibe el
     * la URL. Se devuelve el articulo que se guarda en el usuario.
     *
     * @param usuarioId Identificador de la editorial que se esta
     * actualizando. Este debe ser una cadena de dígitos.
     * @param articuloId Identificador del libro que se desea guardar. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link BookDTO} - El libro guardado en la editorial.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro.
     */
    @POST
    @Path("{articuloId: \\d+}")
    public ArticuloDTO agregarArticulo(@PathParam("usuarioId") Long usuarioId, @PathParam("articuloId") Long articuloId) {
        if (articuloLogic.encontrarArticuloPorId(articuloId) == null) {
            throw new WebApplicationException("El recurso /articulos/" + articuloId + " no existe.", 404);
        }
        ArticuloDTO articuloDTO = new ArticuloDTO(usuarioArticulosLogic.agregarArticulo(articuloId, usuarioId));
        return articuloDTO;
    }
    
    
    /**
     * Busca y devuelve todos los articulos que existen en el usuario.
     *
     * @param usuarioId Identificador del usuario que se esta buscando.
     * Este debe ser una cadena de dígitos.
     * @return JSONArray {@link ArticuloDTO} - Los articulos encontrados en la
     * usuario. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<ArticuloDTO> getArticulos(@PathParam("usuarioId") Long usuarioId) {
        List<ArticuloDTO> listaDTOs = booksListEntity2DTO(usuarioArticulosLogic.darArticulos(usuarioId));
        return listaDTOs;
    }
    
    /**
     * Busca el libro con el id asociado dentro de la editorial con id asociado.
     *
     * @param usuarioId Identificador del usuario que se esta buscando.
     * Este debe ser una cadena de dígitos.
     * @param articuloId Identificador del articulo que se esta buscando. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link BookDetailDTO} - El libro buscado
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro en la
     * editorial.
     */
    @GET
    @Path("{articuloId: \\d+}")
    public ArticuloDTO getBook(@PathParam("usuarioId") Long usuarioId, @PathParam("articuloId") Long articuloId) throws BusinessLogicException {
        if (articuloLogic.encontrarArticuloPorId(articuloId) == null) {
            throw new WebApplicationException("El recurso /usuarios/" + usuarioId + "/articulos/" + articuloId + " no existe.", 404);
        }
        ArticuloDTO articuloDTO = new ArticuloDTO(usuarioArticulosLogic.darArticuloPorId(usuarioId, articuloId));
        return articuloDTO;
    }
    
    /**
     * Convierte una lista de BookEntity a una lista de BookDetailDTO.
     *
     * @param entityList Lista de BookEntity a convertir.
     * @return Lista de BookDTO convertida.
     */
    private List<ArticuloDTO> booksListEntity2DTO(List<ArticuloEntity> entityList) {
        List<ArticuloDTO> list = new ArrayList();
        for (ArticuloEntity entity : entityList) {
            list.add(new ArticuloDTO(entity));
        }
        return list;
    }
}

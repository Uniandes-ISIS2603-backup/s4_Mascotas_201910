/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.resources;

import co.edu.uniandes.csw.mascotas.dtos.ArticuloDTO;
import co.edu.uniandes.csw.mascotas.ejb.ArticuloLogic;
import co.edu.uniandes.csw.mascotas.entities.ArticuloEntity;
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
 * @author Daniela Gonzalez
 */
@Path("articulos")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped

public class ArticuloResource {

    private static final Logger LOGGER = Logger.getLogger(ArticuloResource.class.getName());

    // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.
    @Inject
    private ArticuloLogic articuloLogic;

    
    /**
     * Crea un nuevo articulo con la informacion que se recibe en el cuerpo de
     * la petición y se regresa un objeto identico con un id auto-generado por
     * la base de datos.
     *
     * @param articulo {@link ArticuloDTO} - El articulo que se desea crear.
     * @return JSON {@link ArticuloDTO} - El articulo guardado con el id generado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se cumplen las reglas de negocio.
     */
    @POST
    public ArticuloDTO crearArticulo(ArticuloDTO articulo) throws BusinessLogicException {

        ArticuloEntity entity = articulo.toEntity();
        ArticuloEntity nuevoEntity = articuloLogic.crearArticulo(entity);
        ArticuloDTO nuevoArticulo = new ArticuloDTO(nuevoEntity);
        
        return nuevoArticulo;
    }

    
    /**
     * Busca y devuelve todos los articulos que existen en la aplicacion.
     *
     * @return JSONArray {@link ArticuloDTO} - Los articulos de la aplicacion.
     */
    @GET
    public List<ArticuloDTO> darArticulos() {
        
        List<ArticuloDTO> listaArticulos = listaEntidadesADTO(articuloLogic.encontrarTodosArticulos());
        
        return listaArticulos;
    }
    
     /**
     * Convierte una lista de entidades a DTO.
     *
     * @param listaEntidad corresponde a la lista de articulos de tipo Entity
     * que se va a convertir a DTO.
     * @return la lista de articulos en forma DTO (json)
     */
    private List<ArticuloDTO> listaEntidadesADTO(List<ArticuloEntity> listaEntidad){
        
        List<ArticuloDTO> lista = new ArrayList();
        for(ArticuloEntity entity : listaEntidad){
            lista.add(new ArticuloDTO(entity));
        }
        
        return lista;
    }

    
    /**
     * Busca el articulo con el id asociado y lo devuelve.
     *
     * @param id Identificador del articulo que se esta buscando.
     * @return ArticuloDTO - El articulo buscado.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el articulo.
     */
    @GET
    @Path("{id: \\d+}")
    public ArticuloDTO darArticuloPorId(@PathParam("id") Long id) throws WebApplicationException {
        
        ArticuloEntity articuloEntity = articuloLogic.encontrarArticuloPorId(id);
        
        if(articuloEntity == null){
            throw new WebApplicationException("No existe un articulo con ese identificador", 404);
        }
        
        ArticuloDTO articulo = new ArticuloDTO(articuloEntity);

        return articulo;
    }
    
    
     /**
     * Devuelve todos los articulos con el titulo dado por parametro
     *
     * @param titulo Titulo que se busca
     * @return lista de articulos con ese titulo
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error que se genera cuando no se encuentran articulos con ese titulo.
     */
    @GET
    @Path("/{titulo}")
    public List<ArticuloDTO> darArticulosPorTitulo(@PathParam("titulo") String titulo) throws WebApplicationException {
        
        List<ArticuloDTO> todos = darArticulos();
        List<ArticuloDTO> articulos = new ArrayList();
        
        for (int i = 0; i < todos.size(); i++) {
            if(todos.get(i).getTitulo().equals(titulo)){
                articulos.add(todos.get(i));
            }
        }
       
        if(articulos.isEmpty()){
            throw new WebApplicationException("No existe ningun articulo con ese nombre", 404);
        }

        return articulos;
    }
    
    /**
     * Devuelve todos los articulos con el tema por parametro
     *
     * @param tema Tema con el que se busca filtrar
     * @return lista de articulos con ese tema
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error que se genera cuando no se encuentran articulos con ese tema.
     */ 
    @GET
    @Path("filtrar/{tema}")
    public List<ArticuloDTO> filtrarPorTema(@PathParam("tema") String tema) throws WebApplicationException {
        
        List<ArticuloDTO> todos = darArticulos();
        List<ArticuloDTO> articulos = new ArrayList();
        
        for (int i = 0; i < todos.size(); i++) {
            if(todos.get(i).getTema().equals(tema)){
                articulos.add(todos.get(i));
            }
        }
       
        if(articulos.isEmpty()){
            throw new WebApplicationException("No existe ningun articulo con ese tema", 404);
        }

        return articulos;
    }

    /**
     * Actualiza el articulo con el id recibido por parametro con la informacion
     * que se recibe en el cuerpo de la petición.
     *
     * @param id Identificador del articulo que se desea
     * actualizar. 
     * @param articuloNuevo {@link ArticuloDTO} El articulo con la nueva informacion. 
     * @return JSON {@link ArticuloDTO} - El articulo actualizado y guardado.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el articulo a
     * actualizar.
     */
    @PUT
    @Path("{id: \\d+}")
    public ArticuloDTO actualizarArticulo(@PathParam("id") Long id, ArticuloDTO articuloNuevo) throws WebApplicationException, BusinessLogicException{
        
        articuloNuevo.setId(id);
        
        if(articuloLogic.encontrarArticuloPorId(id) == null){
            throw new WebApplicationException("No existe un articulo con ese identificador", 404);
        }
        
        ArticuloDTO articulo = new ArticuloDTO(articuloLogic.actualizarArticulo(id, articuloNuevo.toEntity()) );
        
        return articulo;
    }
    
    /**
     * Borra un articulo con el id enviado por parametro.
     *
     * @param id Identificador del articulo que se quiere borrar.
     * Este debe ser una cadena de dígitos.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el articulo.
     */
    @DELETE
    @Path("{id: \\d+}")
    public void eliminarArticuloPorId(@PathParam("id") Long id) {

        if(articuloLogic.encontrarArticuloPorId(id) == null){
            throw new WebApplicationException("No existe un articulo con ese identificador", 404);
        }
        
        articuloLogic.eliminarArticulo(id);
    }

}

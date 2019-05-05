/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.resources;

import javax.ws.rs.*;
import co.edu.uniandes.csw.mascotas.dtos.UsuarioDTO;
import co.edu.uniandes.csw.mascotas.dtos.UsuarioDetailDTO;
import co.edu.uniandes.csw.mascotas.ejb.UsuarioLogic;
import co.edu.uniandes.csw.mascotas.entities.UsuarioEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 *
 * @author Maria Ana Ortiz ma.ortiz1
 */
@Path("usuario")
@Produces("application/json")
@Consumes("application/json")
public class UsuarioResource {

    private static final Logger LOGGER = Logger.getLogger(UsuarioResource.class.getName());

    @Inject
    private UsuarioLogic logica;

    @POST
    public UsuarioDTO crearUsuario(UsuarioDTO usuario) throws BusinessLogicException,WebApplicationException {
        UsuarioEntity entidad = usuario.toEntity();
        entidad = logica.crearUsuario(entidad);
        if(entidad == null){
            throw new WebApplicationException("El usuario no se pudo crear",404);
        }
        return new UsuarioDTO(entidad);
    }

    @GET
    @Path("{id: \\d+}")
    public UsuarioDetailDTO darUsuario(@PathParam("id") Long id) throws BusinessLogicException, WebApplicationException {

        if (id == null) {
            throw new WebApplicationException("No se ingreso ningun id");

        }
        UsuarioEntity usuario = logica.getUsuario(id);
        if (usuario == null) {
            throw new WebApplicationException("El recurso /usuario/"+id+ " no existe", 404);
        }
        return new UsuarioDetailDTO(usuario);

    }

    @PUT
    @Path("{id: \\d+}")
    public UsuarioDTO actualizar(@PathParam("id") Long id, UsuarioDTO usuario) throws BusinessLogicException {
        usuario.setId(id);
        UsuarioEntity usr = usuario.toEntity();

        UsuarioEntity resp = logica.actualizarInformacion(id, usr);
        if(resp==null){
            throw new WebApplicationException("El recurso /usuario/"+id+" no existe",404);
        }
        UsuarioDTO fo = new UsuarioDTO(resp);

        return fo;
    }

    @DELETE
    @Path("{id: \\d+}")
    public void eliminarUsuario(@PathParam("id") Long id) throws BusinessLogicException,WebApplicationException {
        UsuarioEntity usuario = logica.getUsuario(id);
        if(usuario == null){
            throw new WebApplicationException("El usuario con el id: "+ id + " no existe");
        }
        logica.deleteUsuario(id);

    }

    /**
     * Busca y los usuarios.
     *
     * @return JSONArray {@link EditorialDetailDTO} - Las editoriales
     * encontradas en la aplicación. Si no hay ninguna retorna una lista vacía.
     */
    @GET
    public List<UsuarioDetailDTO> getUsuarios() throws WebApplicationException {
        try {
            List<UsuarioDetailDTO> listaUsuarioDetailDTO = listEntity2DetailDTO(logica.getUsuarios());
            if (listaUsuarioDetailDTO == null) {
                throw new WebApplicationException("No hay usuarios");
            }
            return listaUsuarioDetailDTO;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * Retorna el Usuario segun su nombre de usuario 
     *
     * @param usuario
     * @return retorna un detailDto de usuario
     * @throws BusinessLogicException
     * @throws WebApplicationException
     */
    @GET
    @Path("/buscar/{usuario}")
    public UsuarioDetailDTO buscarPorUsuario(@PathParam("usuario") String usuario) throws BusinessLogicException, WebApplicationException {
        UsuarioEntity usuarioBuscad = logica.buscarUsuarioXUsuario(usuario);
        if (usuarioBuscad == null) {
            throw new WebApplicationException("El recurso /usuario/buscar/"+ usuario + " no existe",404);
        }
        return new UsuarioDetailDTO(usuarioBuscad);
    }

    /**
     * Conexión con el servicio de articulos para un usuario.
     * {@link UsuarioArticulosResource}
     *
     * Este método conecta la ruta de /usuarios con las rutas de /articulos que
     * dependen del usuario, es una redirección al servicio que maneja el
     * segmento de la URL que se encarga de los articulos del usuario.
     *
     * @param usuarioId El ID del usuario con respecto a la cual se accede al
     * servicio.
     * @return El servicio de libros para esta editorial en paricular.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la editorial.
     */
    @Path("{usuarioId: \\d+}/articulos")
    public Class<UsuarioArticulosResource> getUsuarioArticulosResource(@PathParam("usuarioId") Long usuarioId) throws WebApplicationException{
        //if (usuarioLogic.getUsuario(usuarioId) == null) {
        //    throw new WebApplicationException("El recurso /usuarios/" + usuarioId + " no existe.", 404);
        //}
        UsuarioEntity usuarioArt = logica.getUsuario(usuarioId);
        if(usuarioArt==null){
            throw new WebApplicationException("El recurso /usuario/"+usuarioId+"/articulos no existe",404);
        }
        
        return UsuarioArticulosResource.class;
    }

    /**
     * Convierte una lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos EditorialEntity a una lista de
     * objetos UsuarioDetailDTO (json)
     *
     * @param entityList corresponde a la lista de editoriales de tipo Entity
     * que vamos a convertir a DTO.
     * @return la lista de editoriales en forma DTO (json)
     */
    private List<UsuarioDetailDTO> listEntity2DetailDTO(List<UsuarioEntity> entityList) {
        List<UsuarioDetailDTO> list = new ArrayList<>();
        for (UsuarioEntity entity : entityList) {
            list.add(new UsuarioDetailDTO(entity));
        }
        return list;
    }

}

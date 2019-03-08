/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.ejb;

import co.edu.uniandes.csw.mascotas.entities.ArticuloEntity;
import co.edu.uniandes.csw.mascotas.entities.UsuarioEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.mascotas.persistence.ArticuloPersistence;
import co.edu.uniandes.csw.mascotas.persistence.UsuarioPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Daniela Gonzalez
 */
@Stateless
public class UsuarioArticulosLogic {
    
    @Inject
    private ArticuloPersistence articuloPersistence;
    
     @Inject
    private UsuarioPersistence usuarioPersistence;
    
     /**
     * Agregar un articulo a la usuario
     *
     * @param articuloId El id articulo a guardar
     * @param usuarioId El id del usuario en la cual se va a guardar el
     * evento.
     * @return El articulo creado.
     */
    public ArticuloEntity agregarArticulo(Long articuloId, Long usuarioId) {
        UsuarioEntity usuarioEntity = usuarioPersistence.find(usuarioId);
        ArticuloEntity articuloEntity = articuloPersistence.find(articuloId);
        articuloEntity.setAutor(usuarioEntity);
        return articuloEntity;
    } 
     
    
    /**
     * Retorna todos los articulos asociados a un usuario
     *
     * @param usuarioId El ID del usuario buscado
     * @return La lista de articulos del usuario 
     */
    public List<ArticuloEntity> darArticulos(Long usuarioId) {
        return  usuarioPersistence.find(usuarioId).getArticulos();
    }
     
     /**
     * Retorna un articulo asociado a un usuario
     *
     * @param usuarioId El id de la usuario a buscar.
     * @param articuloId El id del articulo a buscar
     * @return El articulo encontrado dentro del usuario.
     * @throws BusinessLogicException Si el articulo no se encuentra en 
     * el usuario
     */
    public ArticuloEntity darArticuloPorId(Long usuarioId, Long articuloId) throws BusinessLogicException {
        List<ArticuloEntity> articulos = usuarioPersistence.find(usuarioId).getArticulos();
        ArticuloEntity articuloEntity = articuloPersistence.find(articuloId);
        int index = articulos.indexOf(articuloEntity);
        if (index >= 0) {
            return articulos.get(index);
        }
        throw new BusinessLogicException("El articulo no está asociado al usuario");
    }

    
    
}

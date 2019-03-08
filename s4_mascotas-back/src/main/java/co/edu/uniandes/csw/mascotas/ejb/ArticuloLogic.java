/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.ejb;

import co.edu.uniandes.csw.mascotas.entities.ArticuloEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.mascotas.persistence.ArticuloPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Daniela Gonzalez
 */

@Stateless
public class ArticuloLogic {
    
    // ---------------------------------------
    // Constantes
    // ---------------------------------------
    
    /**
     * Constante que define el tema de cuidado del animal  
     */
    public final static String CUIDADO = "CUIDADO";
    
     /**
     * Constante que define el tema de salud del animal  
     */
    public final static String SALUD = "SALUD";
    
     /**
     * Constante que define el tema de entrenamiento del animal  
     */
    public final static String ENTRENAMIENTO = "ENTRENAMIENTO";
    
     /**
     * Constante que define el tema de tenencia responsable del animal  
     */
    public final static String TENENCIA_RESPONSABLE = "TENENCIA RESPONSABLE";
    
    
    @Inject
    private ArticuloPersistence persistence;
    
    /**
     * Crea un articulo a partir de la entidad ingresada por parámetro
     * @param articulo
     * @return entidad del articulo creado
     * @throws BusinessLogicException 
     */
    public ArticuloEntity crearArticulo(ArticuloEntity articulo) throws BusinessLogicException{
        
        
        //Validacion reglas de negocio
        if(articulo.getTitulo() == null){
            throw new BusinessLogicException("Un articulo debe tener un titulo");
        }
        
        if(articulo.getContenido() == null){
            throw new BusinessLogicException("Un articulo debe tener contenido");
        }
        
        //if(!articulo.getTema().equals(CUIDADO) && !articulo.getTema().equals(SALUD) && !articulo.getTema().equals(ENTRENAMIENTO) && !articulo.getTema().equals(TENENCIA_RESPONSABLE)){
          //  throw new BusinessLogicException("Un articulo debe tener un tema valido");
        //}
        
        
        articulo = persistence.create(articulo);
        return articulo;
    }
    
    /**
     * Busca un articulo por su id
     * @param articuloId Llave del articulo
     * @return elArticulo
     */
    public ArticuloEntity encontrarArticuloPorId(Long articuloId) {
        
        ArticuloEntity elArticulo = persistence.find(articuloId);
        return elArticulo;
    }
    
    /**
     * Busca todos los articulos
     * @return articulos
     */
    public List<ArticuloEntity> encontrarTodosArticulos(){
        
        List<ArticuloEntity> articulos = persistence.findAll();
        return articulos;
    }
    
    
    /**
     *
     * Actualizar un articulo.
     *
     * @param id: id del articulo que se quiere actualizar para buscarlo en la base de
     * datos.
     * @param nuevo: articulo con los cambios para ser actualizado.
     * por ejemplo el titulo.
     * @throws BusinessLogicException si la informacion nueva no cumple las reglas de negocio.
     * @return el articulo con los cambios actualizados en la base de datos.
     */
    public ArticuloEntity actualizarArticulo(Long id, ArticuloEntity nuevo) throws BusinessLogicException{
        
        //Validacion reglas de negocio
        if(nuevo.getTitulo() == null){
            throw new BusinessLogicException("Un articulo debe tener un titulo");
        }
        if(nuevo.getContenido() == null){
            throw new BusinessLogicException("Un articulo debe tener un contenido");
        }
        
        ArticuloEntity cambiada = persistence.actualizarArticulo(nuevo);
        
        return cambiada;
    }
    
    /**
     * Borrar un articulo
     *
     * @param id: id del articulo a borrar.
     */
    public void eliminarArticulo(Long id) {
        
        persistence.delete(id);
    } 
    
    /**
     * Busca un articulo por su titulo
     *
     * @param titulo: titulo del articulo que se busca
     * @return el articulo con el titulo enviado por parametro.
     * @throws BusinessLogicException Si el titulo que se busca no es  valido.
     */
    public ArticuloEntity buscarArticuloPorTitulo(String titulo) throws BusinessLogicException{
        
        if(titulo == null){
            throw new BusinessLogicException("El titulo buscado debe ser valido");
        }
        
        ArticuloEntity articulo = persistence.findByName(titulo);
       
        return articulo;    
    }
}

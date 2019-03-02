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
     * @return
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
        
        if(!articulo.getTema().equals(CUIDADO) && !articulo.getTema().equals(SALUD) && !articulo.getTema().equals(ENTRENAMIENTO) && !articulo.getTema().equals(TENENCIA_RESPONSABLE)){
            throw new BusinessLogicException("Un articulo debe tener un tema valido");
        }
        
        
        articulo = persistence.create(articulo);
        return articulo;
    }
    
    /**
     * Busca un articulo por su id
     * @param articuloId Llave del articulo
     * @return elArticulo
     */
    public ArticuloEntity encontrarArticuloPorId(Long articuloId){
        
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
    
    
    public ArticuloEntity cambiarTitulo(Long articuloId, String nuevoTitulo) throws BusinessLogicException{
        
        //Validacion reglas de negocio
        if(nuevoTitulo == null){
            throw new BusinessLogicException("Un articulo debe tener un titulo");
        }
        
        ArticuloEntity cambiada = persistence.actualizarTitulo(articuloId, nuevoTitulo);
        return cambiada;
    }
    
    
     public ArticuloEntity cambiarContenido(Long articuloId, String nuevoContenido) throws BusinessLogicException{
        
        //Validacion reglas de negocio
        if(nuevoContenido == null){
            throw new BusinessLogicException("Un articulo debe tener un contenido");
        } 
         
        ArticuloEntity cambiada = persistence.actualizarContenido(articuloId, nuevoContenido);
        return cambiada;
    }
    
}

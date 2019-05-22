/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.ejb;

import co.edu.uniandes.csw.mascotas.entities.ClasificadoEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.mascotas.persistence.ClasificadoPersistence;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

/**
 *
 * @author Johan E. Vivas Sepulveda (je.vivas)
 */

@Stateless
public class ClasificadoLogic {
    
    @Inject
    private ClasificadoPersistence persistence;
    
    /**
     * Crea un clasificado a partir de la entidad ingresada por parámetro
     * @param clasif
     * @return entidad del clasificado creado
     * @throws BusinessLogicException 
     */
    public ClasificadoEntity createClasificado(ClasificadoEntity clasif) throws BusinessLogicException{
        
        if(clasif.getNombre() ==  null){
            throw new BusinessLogicException("El clasificado debe tener un nombre");
        }
        
        if( clasif.getContenido() == null){
            throw new BusinessLogicException("El contenido del clasificado no puede estar vacío");
        }
        
        return persistence.create(clasif);
    }
    
    /**
     * Busca todos los clasificados
     * @return clasificados
     */
    public List<ClasificadoEntity> getClasificados(){
        
        List<ClasificadoEntity> clas = persistence.findAll();
        return clas;
    }
    
    /**
     * Busca un clasificado por su id
     * @param clasifId Llave del clasificado
     * @return elClasificado
     */
    public ClasificadoEntity buscarClasificadoPorId(Long clasifId){
        
        ClasificadoEntity elClasificado = persistence.find(clasifId);
        return elClasificado;
    }
    
    /**
     *
     * Actualizar un clasificado.
     *
     * @param id: id del clasificado que se quiere actualizar para buscarlo en la base de
     * datos.
     * @param nuevo: clasificado con los cambios para ser actualizado.
     * por ejemplo el nombre.
     * @throws BusinessLogicException si la informacion nueva no cumple las reglas de negocio.
     * @return el clasificado con los cambios actualizados en la base de datos.
     */
    public ClasificadoEntity actualizarClasificado(Long id, ClasificadoEntity nuevo) throws BusinessLogicException{
        
        //Validacion reglas de negocio
       if(nuevo.getNombre() ==  null){
            throw new BusinessLogicException("El clasificado debe tener un nombre");
        }
        
        if(nuevo.getContenido() == null){
            throw new BusinessLogicException("El contenido del clasificado no puede estar vacío");
        }
        
        nuevo.setId(id);
        ClasificadoEntity cambiada = persistence.actualizarClasificado(nuevo);
        
        return cambiada;
    }
    
    /**
     * Borrar un clasificado
     *
     * @param id: id del clasificado a borrar.
     */
    public void eliminarClasificado(Long id) {
        
        persistence.delete(id);
    } 
}

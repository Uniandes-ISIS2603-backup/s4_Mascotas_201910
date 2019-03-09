/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.ejb;

import co.edu.uniandes.csw.mascotas.entities.CalificacionEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.mascotas.persistence.CalificacionPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author s.canales
 */
@Stateless
public class CalificacionLogic {
     /**
     * Instancia de la clase que maneja la persistencia de calificación
     * 
     */
    @Inject
    private CalificacionPersistence persistence;
    /**
     * le pasa una nueva calificación a la persistenia, validando que se cumplan las reglas de negocio
     * @param calificacion
     * @return
     * @throws BusinessLogicException 
     */
    public CalificacionEntity createCalificacion(CalificacionEntity calificacion)throws BusinessLogicException{
        
        if(calificacion.getCalificacion() < 0 ||calificacion.getCalificacion() > 5 ){
            
            throw new BusinessLogicException ("la calificación tiene que ser entre 0 y 5");
        }
        if(calificacion.getComentario() == null) throw new BusinessLogicException("el comentario de la calificación no puede ser null");
        calificacion = persistence.create(calificacion);
        return calificacion;        
    }
    /**
     * 
     * @return todas las calificaciones
     */
    public List<CalificacionEntity> getCalificaciones(){
        
        return persistence.findAll();
    }
    /**
     * devuelve una calificacion si existe en la base de datos
     * @param id
     * @return
     * @throws BusinessLogicException 
     */
    public CalificacionEntity getCalificacion(Long id) throws BusinessLogicException{
        
        CalificacionEntity e = persistence.find(id);
        if(e == null) throw new BusinessLogicException("No exist la calificación con id " + id);
        
        return e;
            
        
    }
    /**
     * borra una valificación si existe en la base de datos
     * @param id
     * @throws BusinessLogicException 
     */
    public void deleteCalificacion(Long id ) throws BusinessLogicException{
        
        CalificacionEntity e = persistence.find(id);
        if(e == null) throw new BusinessLogicException("No exist la calificación con id " + id);
        
        persistence.delete(id);
    }
    /**
     * modifica una calificación si existe en la base de datos
     * @param id
     * @param entity
     * @return
     * @throws BusinessLogicException 
     */
    public CalificacionEntity updateCalificacion(Long id, CalificacionEntity entity) throws BusinessLogicException{
        
        CalificacionEntity e = persistence.find(id);
        if(entity.getCalificacion() > 5 || entity.getCalificacion() < 0) throw new BusinessLogicException("La calificacion solo puede tener valores entre 0 y 5");
        if(entity.getComentario() == null ||entity.getComentario().equals("")) throw new BusinessLogicException("el comentario no puede estar vacío");
        
        return persistence.update(e);
    }
    
}

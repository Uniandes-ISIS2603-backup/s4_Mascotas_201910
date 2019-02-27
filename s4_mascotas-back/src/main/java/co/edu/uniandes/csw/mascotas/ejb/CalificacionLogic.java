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
    
    @Inject
    private CalificacionPersistence persistence;
    
    public CalificacionEntity createCalificacion(CalificacionEntity calificacion)throws BusinessLogicException{
        
        if(calificacion.getCalificacion() < 0 ||calificacion.getCalificacion() > 5 ){
            
            throw new BusinessLogicException ("la calificación tiene que ser entre 0 y 5");
        }
        calificacion = persistence.create(calificacion);
        return calificacion;        
    }
    
    public List<CalificacionEntity> getCalificaciones(){
        
        return persistence.findAll();
    }
    
    public CalificacionEntity getCalificacion(Long id) throws BusinessLogicException{
        
        CalificacionEntity e = persistence.find(id);
        if(e == null) throw new BusinessLogicException("No exist la calificación con id " + id);
        
        return e;
            
        
    }
    
    public void deleteCalificacion(Long id ) throws BusinessLogicException{
        
        CalificacionEntity e = persistence.find(id);
        if(e == null) throw new BusinessLogicException("No exist la calificación con id " + id);
        
        persistence.delete(id);
    }
    
    public CalificacionEntity updateCalificacion(Long id, String comentario, Integer calificacion) throws BusinessLogicException{
        
        CalificacionEntity e = persistence.find(id);
        if(e == null) throw new BusinessLogicException("No exist la calificación con id " + id);
        e.setCalificacion(calificacion);
        e.setComentario(comentario);
        return persistence.update(e);
    }
    
}

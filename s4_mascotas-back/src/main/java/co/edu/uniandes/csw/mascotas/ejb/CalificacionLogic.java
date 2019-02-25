/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.ejb;

import co.edu.uniandes.csw.mascotas.entities.CalificacionEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.mascotas.persistence.CalificacionPersistence;
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
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.ejb;

import co.edu.uniandes.csw.mascotas.entities.MascotaEnAdopcionEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.mascotas.persistence.MascotaEnAdopcionPersistence;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author s.canales
 */
@Stateless
public class MascotaEnAdopcionLogic {
    
    @Inject
    private MascotaEnAdopcionPersistence persistence;
    
    public MascotaEnAdopcionEntity createMascotaEnAdopcion(MascotaEnAdopcionEntity entity) throws BusinessLogicException{
        
        if(entity.isAdoptada()) throw new BusinessLogicException("el proceso se tiene que inicializar en false");
        if(entity.getFechaInicio() == null) throw new BusinessLogicException("tiene que inicializar el proceso con una fcha");
        if(entity.getPasado() == null) throw new BusinessLogicException("el pasado no puede ser nulo");
        if(entity.getRazonAdopcion() == null) throw new BusinessLogicException("tiene que tener una razón para dejarla en adopción");
        
        return persistence.create(entity);
    }
    
}

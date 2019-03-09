/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.ejb;

import co.edu.uniandes.csw.mascotas.entities.MascotaEncontradaEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.mascotas.persistence.MascotaEncontradaPersistence;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Johan E. Vivas Sepulveda (je.vivas)
 */

@Stateless
public class MascotaEncontradaLogic {
    
    private static final Logger LOGGER = Logger.getLogger(MascotaExtraviadaLogic.class.getName());
    
    @Inject
    private MascotaEncontradaPersistence persistence;
    
    
    public MascotaEncontradaEntity createMascotaEncontrada (MascotaEncontradaEntity mascota) throws BusinessLogicException {
        
        if(!mascota.getEstado().equals(MascotaEncontradaEntity.PENDIENTE)){
            throw new BusinessLogicException("El nuevo proceso de mascota encontrada deberia tener un estado 'PENDIENTE'");
        }
        
        persistence.create(mascota);
        
        return mascota;
    }
    
    public List<MascotaEncontradaEntity> getProcesosMascotaEncontrada( ){
        
        return persistence.findAll();
    }
    
    public MascotaEncontradaEntity getProcesoMascotaEncontrada( Long id ) throws BusinessLogicException{
        
        MascotaEncontradaEntity mascotaX = persistence.find(id);
        
        if(mascotaX == null){
            throw new BusinessLogicException("No existe un proceso de mascota encontrada con el id " + id );
        }
        
        return mascotaX;
    }
    
    public void deleteProcesoMascotaEncontrada( Long id ) throws BusinessLogicException{
        
        MascotaEncontradaEntity mascotaD = getProcesoMascotaEncontrada(id);
        if(!mascotaD.getEstado().equals(MascotaEncontradaEntity.ENTREGADA)){
            throw new BusinessLogicException("El proceso no se puede borrar. El valor del estado deberia ser igual a 'ENTREGADA'");
        }
        
        persistence.delete(id);
    }
    
    
    
    
    
}

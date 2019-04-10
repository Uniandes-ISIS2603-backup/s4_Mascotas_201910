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
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Johan E. Vivas Sepulveda (je.vivas) / Natalia Sanabria Forero (n.sanabria)
 */

@Stateless
public class MascotaEncontradaLogic {
    
    @Inject
    private MascotaEncontradaPersistence persistence;
    
    /**
     * Crea un nuevo proceso de mascota encontrada
     * @param mascota
     * @return
     * @throws BusinessLogicException 
     */
    public MascotaEncontradaEntity createMascotaEncontrada (MascotaEncontradaEntity mascota) throws BusinessLogicException {
        
        if(!mascota.getEstado().equals(MascotaEncontradaEntity.PENDIENTE)){
            throw new BusinessLogicException("El nuevo proceso de mascota encontrada deberia tener un estado 'PENDIENTE'");
        }
        
        persistence.create(mascota);
        
        return mascota;
    }
    
    /**
     * Retorna todas las entidades de tipo mascota encontrada almacenadas en la base de datos
     * @return 
     */
    public List<MascotaEncontradaEntity> getProcesosMascotaEncontrada( ){
        
        return persistence.findAll();
    }
    
    /**
     * Retorna un proceso de mascota encontrada dado su id
     * @return 
     */
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
    
    public MascotaEncontradaEntity updateMascotaEncontrada( Long id, MascotaEncontradaEntity mascota ) throws BusinessLogicException
    {
        
        MascotaEncontradaEntity mascotaEntity = getProcesoMascotaEncontrada(id);
        if(!mascota.getEstado().equals(MascotaEncontradaEntity.ENTREGADA) && (!mascota.getEstado().equals(MascotaEncontradaEntity.PENDIENTE))){
            throw new BusinessLogicException("El proceso solo puede tener el estado 'ENTREGADA' o 'PENDIENTE'");
            
        }
        
        return persistence.update(mascota);
        
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.ejb;

import co.edu.uniandes.csw.mascotas.entities.MascotaEnAdopcionEntity;
import co.edu.uniandes.csw.mascotas.entities.UsuarioEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.mascotas.persistence.MascotaEnAdopcionPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author s.canales
 */
@Stateless
public class MascotaEnAdopcionLogic {
    
     /**
     * Instancia de la clase que maneja la persistencia de los procesos
     * de mascota en adopción
     */
    @Inject
    private MascotaEnAdopcionPersistence persistence;
    
    /**
     * manda un nuevo proceso a la persistencia, validando que se cumplan las reglas de negocio
     * @param entity
     * @return
     * @throws BusinessLogicException 
     */
    public MascotaEnAdopcionEntity createMascotaEnAdopcion(MascotaEnAdopcionEntity entity) throws BusinessLogicException{
        
        if(entity.isAdoptada() == null){
            entity.setAdoptada(false);
        }
        //if(entity.getFechaInicio() == null) throw new BusinessLogicException("tiene que inicializar el proceso con una fcha");
        if(entity.getPasado() == null) throw new BusinessLogicException("el pasado no puede ser nulo");
        if(entity.getRazonAdopcion() == null) throw new BusinessLogicException("tiene que tener una razón para dejarla en adopción");
        
        
        persistence.create(entity);
        return entity;
    }
    /**
     * devuelve los procesos filtrados por el tipo
     * @param tipo
     * @return 
     */
    public List<MascotaEnAdopcionEntity> getProcesosPorTipo(String tipo){
        return persistence.findByType(tipo);
    }
    /**
     * devuelve los postulados del proceso en adopcion
     * @param id
     * @return 
     */
    public List<UsuarioEntity> getPostuladosByProceso(Long id){
        return persistence.postuladosByProceso(id);
    }
    /**
     * devuelve un proceso siempre y cuando exista en la base de datos
     * @param id
     * @return
     * @throws Exception 
     */
    public MascotaEnAdopcionEntity getMascotaEnAdopcion(long id)throws Exception{
        
        MascotaEnAdopcionEntity buscado = persistence.find(id);
        if(buscado == null) throw new BusinessLogicException(" no se encontró el proceso de mascota en adopción con id: " + id);
        
        return buscado;
    }
    /**
     * devuelve la lista e todos los procesos de mascota en adopción
     * @return
     * @throws BusinessLogicException 
     */
    public List<MascotaEnAdopcionEntity> getMascotasEnAdopcion() throws BusinessLogicException{
        
        return persistence.findAll();
    }
    /**
     * modifica un proceso de adopción validando que exista en la base de datos
     * @param mascotaCambiada
     * @param id
     * @return
     * @throws Exception 
     */
    public MascotaEnAdopcionEntity updateMascotaEnAdopcion(MascotaEnAdopcionEntity mascotaCambiada , Long id) throws Exception{
        
        if(persistence.find(id) == null) throw new BusinessLogicException("Error al modificar, no se encontró la mascota con id: " + id);
        return persistence.update(mascotaCambiada);
    }
    /**
     * borra un proceso de adopción
     * @param id
     * @throws BusinessLogicException 
     */
    public void deleteMascotaEnAdopcion(Long id) throws BusinessLogicException{
        
        persistence.delete(id);
    }
    /**
     * devuelve los procesos no terminados
     * @return 
     */
    public List<MascotaEnAdopcionEntity> getMascotasSinAdoptar(){
        return persistence.findSinAdoptar();
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.ejb;

import co.edu.uniandes.csw.mascotas.entities.MascotaExtraviadaEntity;
import co.edu.uniandes.csw.mascotas.entities.RecompensaEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.mascotas.persistence.MascotaExtraviadaPersistence;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Sebastián Lemus Cadena (s.lemus)
 */
@Stateless
public class MascotaExtraviadaLogic {
    
    private static final Logger LOGGER = Logger.getLogger(MascotaExtraviadaLogic.class.getName());
    
    /**
     * Instancia de la clase que maneja la persistencia de los procesos
     * de mascota extraviada
     */
    @Inject
    private MascotaExtraviadaPersistence persistence;
    
    /**
     * Crea un nuevo proceso de mascota estraviada de acuerdo a la información de la entidad dada
     * @param p - Entidad del proceso de mascota extraviada
     * @return Entidad del proceso de mascota extraviada
     * @throws Exception 
     */
    public MascotaExtraviadaEntity createMascotaExtraviada(MascotaExtraviadaEntity  p) throws Exception{
        
        if(p.getEstado() == null){
            p.setEstado(MascotaExtraviadaEntity.PENDIENTE);
        }else if(!p.getEstado().equals(MascotaExtraviadaEntity.PENDIENTE)){
            throw new BusinessLogicException("Un nuevo proceso de mascota extraviada debería estar en 'PENDIENTE'");
        }
        
        persistence.create(p);
        return p;
    }
    
    /**
     * 
     * @return La lista de todos los procesos de mascota extraviada existentes 
     */
    public List<MascotaExtraviadaEntity> getProcesosMascotaExtraviada(){
        return persistence.findAll();
    }
    
    /**
     * 
     * @param id - Id del proceso a buscar
     * @return El proceso de mascota extraviada asociado al id
     * @throws Exception 
     */
    public MascotaExtraviadaEntity getProcesoMascotaExtraviada(Long id) throws Exception{
        MascotaExtraviadaEntity p = persistence.find(id);
        if(p == null){
            throw new BusinessLogicException("El proceso de mascota extraviada con id " + id + " no existe");
        }
        return p;
    }
    
    /**
     * Elimina el proceso de mascota extraviada asociado al id
     * @param id - id del proceso a borrar
     * @throws Exception 
     */
    public void deleteProcesoMascotaExtraviada(Long id) throws Exception{
        RecompensaEntity recompensa = getProcesoMascotaExtraviada(id).getRecompensa();
        if (recompensa != null) {
            throw new BusinessLogicException("No de puede borrar el proceso de mascota extraviada con id = " + id + " ya que tiene una recompensa asociada");
        }
        persistence.delete(id);
    }
    
    /**
     * Acutaliza un proceso de mascota extraviada de acuerdo a la informaciòn dada
     * @param id - Identificador del proceso a actualizar
     * @param p - Entity con la nueva informaciòn
     * @return El proceso con los valores actualizados
     * @throws Exception 
     */
    public MascotaExtraviadaEntity updateMascotaExtraviada(Long id, MascotaExtraviadaEntity p) throws Exception{
        MascotaExtraviadaEntity entity = getProcesoMascotaExtraviada(id);
        setIfNull(p, entity);
        if(!p.getEstado().equals(MascotaExtraviadaEntity.ENCONTRADO) && !p.getEstado().equals(MascotaExtraviadaEntity.PENDIENTE)){
            throw new BusinessLogicException("El proceso de mascota extraviada solo puede estar en estado 'ENCONTRADO' o 'PENDIENTE'");
        }
        return persistence.update(p);
    }
    
    public void setIfNull(MascotaExtraviadaEntity entityToSet, MascotaExtraviadaEntity entityToCopy){
        if(entityToSet.getDireccion() == null){
            entityToSet.setDireccion(entityToCopy.getDireccion());
        }
        if(entityToSet.getCiudad() == null){
            entityToSet.setCiudad(entityToCopy.getCiudad());
        }
        
        if(entityToSet.getEstado() == null){
            entityToSet.setEstado(entityToCopy.getEstado());
        }
    }
}

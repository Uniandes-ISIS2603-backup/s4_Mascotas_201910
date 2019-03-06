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
import co.edu.uniandes.csw.mascotas.persistence.RecompensaPersistence;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Sebastián Lemus Cadena (s.lemus)
 */
@Stateless
public class MascotaExtraviadaRecompensaLogic {
    
    private static final Logger LOGGER = Logger.getLogger(MascotaExtraviadaRecompensaLogic.class.getName());
    
    /**
     * Persistencia de los procesos de mascota extraviada
     */
    @Inject
    private MascotaExtraviadaPersistence  mascotaExtraviadaPersistence;
    
    /**
     * Persistencia de las recompensas de los procesos de mascota extraviada
     */
    @Inject
    private RecompensaPersistence recompensaPersistence;
    
    /**
     * Remueve la recompensa asociada a un proceso de mascota extraviada
     * @param id - Id del proceso de mascota extraviada
     */
    public void removeRecompensaConProceso(Long id) throws Exception{
        MascotaExtraviadaEntity p = mascotaExtraviadaPersistence.find(id);
        if(p == null){
            throw new BusinessLogicException("El proceso de mascota extraviada asociado a la recompensa no exista");
        }
        RecompensaEntity r = p.getRecompensa();
        if(r == null){
            throw new BusinessLogicException("La recompensa que se intenta borrar no existe dentro del proceso de mascota extraviada especificado");
        }
        recompensaPersistence.delete(r.getId());
        p.setRecompensa(null);
        mascotaExtraviadaPersistence.update(p);
    }
    
    /**
     * Remueve una recompensa
     * @param id - el id de la recompensa
     * @throws Exception 
     */
    public void removeRecompensa(Long id) throws Exception{
        RecompensaEntity r = recompensaPersistence.find(id);
        if(r == null){
            throw new BusinessLogicException("La recompensa no existe");
        }
        MascotaExtraviadaEntity p = r.getProcesoMascotaExtraviada();
        if(p != null){
            p.setRecompensa(null);
            mascotaExtraviadaPersistence.update(p);
        }
        recompensaPersistence.delete(id);
    }

}

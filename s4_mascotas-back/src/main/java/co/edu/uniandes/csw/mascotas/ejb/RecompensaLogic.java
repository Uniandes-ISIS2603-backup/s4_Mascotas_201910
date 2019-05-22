/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.ejb;

import co.edu.uniandes.csw.mascotas.ejb.MascotaExtraviadaLogic;
import co.edu.uniandes.csw.mascotas.entities.MascotaExtraviadaEntity;
import co.edu.uniandes.csw.mascotas.entities.RecompensaEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.mascotas.persistence.RecompensaPersistence;
import java.util.List;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ejb.Stateless;
/**
 *
 * @author Sebastián Lemus Cadena (s.lemus)
 */
@Stateless
public class RecompensaLogic {
    
    private static final Logger LOGGER = Logger.getLogger(RecompensaLogic.class.getName());
    
    /**
     * Instancia de la clase que maneja la persistencia las recompensas
     */
    @Inject
    private RecompensaPersistence persistence;
    
    /**
     * Realiza la creación de una recompensa de acuerdo 
     * a los valores de la entidad dada
     * @param r - Entidad de la recompensa
     * @return Entidad de la recompensa
     * @throws Exception 
     */
    public RecompensaEntity createRecompensa(RecompensaEntity r) throws Exception{
        System.out.println("entrando a RecompensaLogic createRecompensa");
        if (r.getValor() < 0) {
            throw new BusinessLogicException("El valor de la recompensa no puede ser negativo");
        }
        if (!r.getEstado().equals(RecompensaEntity.PENDIENTE)) {
            throw new BusinessLogicException("Una nueva recompensa debería estar en estado 'PENDIENTE'");
        }
        if(r.getProcesoMascotaExtraviada() == null){
            throw new BusinessLogicException("Un proceso no debería existir sin un proceso de mascota extraviada");
        }
        System.out.println("reglas de negocio verificadas");
        persistence.create(r);
        return r;
    }
    
    /**
     * 
     * @return La lista de todas las recompensas existentes
     */
    public List<RecompensaEntity> getRecompensas(){
        return persistence.findAll();
    }
    
    /**
     * 
     * @param id - Id de la recompensa a buscar
     * @return Recompensa asociada al Id
     * @throws Exception 
     */
    public RecompensaEntity getRecompensa(Long id) throws Exception{
        RecompensaEntity r = persistence.find(id);
        if(r == null){
            throw new BusinessLogicException("La recompensa con id " + id + " no existe");
        }
        return r;
    }
    
    /**
     * Actualiza los valores de una recompensa de acuerdo a la entidad dada por parámetro
     * @param id
     * @param entity
     * @return La recompensa actualizada
     * @throws Exception 
     */
    public RecompensaEntity updateRecompensa(Long id, RecompensaEntity entity) throws Exception{
        RecompensaEntity r = getRecompensa(id);
        if (entity.getValor() < 0) {
            throw new BusinessLogicException("El valor nuevo de la recompensa no es correcto; no puede ser negativo");
        }
        if(!entity.getEstado().equals(RecompensaEntity.PAGADO) && !entity.getEstado().equals(RecompensaEntity.PENDIENTE)){
           throw new BusinessLogicException("El estado de la recompensa solo puede ser 'PENDIENTE' o 'PAGADO'");
        }
        if(!r.getProcesoMascotaExtraviada().equals(entity.getProcesoMascotaExtraviada())){
           throw new BusinessLogicException("El proceso de mascota extraviada asociada debe ser el mismo que antes para la recompensa con id " + id);
        }
        return persistence.update(entity);
    }

}

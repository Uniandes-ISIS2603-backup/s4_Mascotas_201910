/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.persistence;

import co.edu.uniandes.csw.mascotas.entities.MascotaEncontradaEntity;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Johan E. Vivas Sepulveda (je.vivas)
 */

@Stateless
public class MascotaEncontradaPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(MascotaEncontradaPersistence.class.getName());
    
    /**
     * Contexto de persistencia
     */
    @PersistenceContext (unitName = "mascotasPU")
    protected EntityManager em;
    
    /**
     * Crea un nuevo proceso de mascota encontrada en la base de datos
     * @param mascotaEncontradaEntity
     * @return 
     */
    public MascotaEncontradaEntity create(MascotaEncontradaEntity mascotaEncontradaEntity)
    {
        LOGGER.info("Persistiendo nuevo proceso de mascota encontrada");
        em.persist(mascotaEncontradaEntity);
        LOGGER.info("Proceso persistido satisfactoriamente");
        return mascotaEncontradaEntity;
    }
    
    /**
     * Retorna un proceso de mascota encontrada dado su id
     * @param mascotaEncontradaId
     * @return 
     */
    public MascotaEncontradaEntity find(Long mascotaEncontradaId)
    {
        LOGGER.info("Buscando proceso de mascota encontrada con identificador " + mascotaEncontradaId);
        return em.find(MascotaEncontradaEntity.class, mascotaEncontradaId);
    }
    
    /**
     * Retorna una lista con todos los procesos de mascotas encontradas
     * @return 
     */
    public List<MascotaEncontradaEntity> findAll()
    {
        LOGGER.info("Listando todos los procesos de mascota encontrada en la base de datos");
        TypedQuery<MascotaEncontradaEntity> query = em.createQuery("select u from MascotaEncontradaEntity u", MascotaEncontradaEntity.class);
        List<MascotaEncontradaEntity> list = query.getResultList();
        if(list != null)
            LOGGER.info("Listando procesos de mascota encontrada: Listo!");
        return list;
    }
    
    /**
     * Elimina el registro de una mascota encontrada de la base de datos
     * @param id 
     */
    public void delete(Long id)
    {
        LOGGER.info("Borrando el proceso de mascota encontrada con id " + id);
        MascotaEncontradaEntity entity = em.find(MascotaEncontradaEntity.class, id);
        em.remove(entity);
        LOGGER.info("Proceso removido satisfactoriamente.");
    }
    
    /**
     * Actualiza el registro de una mascota encontrada
     * @param mascota
     * @return 
     */
    public MascotaEncontradaEntity update( MascotaEncontradaEntity mascota )
    {
        LOGGER.info("Modificando la información del proceso de mascota encontrada " + mascota.getId());
        return em.merge(mascota);
    }
}

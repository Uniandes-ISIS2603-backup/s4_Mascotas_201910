/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.persistence;

import co.edu.uniandes.csw.mascotas.entities.RecompensaEntity;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Sebastián Lemus Cadena (s.lemus)
 */
@Stateless
public class RecompensaPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(RecompensaPersistence.class.getName());
    
    /**
     * Manejador de la persistencia
     */
    @PersistenceContext(unitName = "mascotasPU")
    protected EntityManager em;
    
    /**
     * Persiste en la base de datos la información contenida en el entity dado
     * @param entity - información para crear una nueva entrada en la base de datos
     * @return Objeto con id
     */
    public RecompensaEntity create(RecompensaEntity entity){
        em.persist(entity);
        return entity;
    }
    
    /**
     * Encuentra la entidad asociado al identificador dado.
     * Si no existe devuelve null.
     * @param id - identificador
     * @return instancia de RecompensaEntity asociada
     */
    public RecompensaEntity find(Long id){
        return em.find(RecompensaEntity.class, id);
    }
    
    /**
     * Retorna todas las recompensas existentes
     * @return Lista con todas las entidades existentes
     */
    public List<RecompensaEntity> findAll(){
        TypedQuery<RecompensaEntity> q = em.createQuery("select u from RecompensaEntity u", RecompensaEntity.class);
        return q.getResultList();
    }
    
    /**
     * Actualiza una recompensa de acuerdo a los valores
     * dados por el Entity
     * @param entity - Entity de la recompensa
     * @return Entity de la recompensa
     */
    public RecompensaEntity update(RecompensaEntity entity){
        return em.merge(entity);
    }
    
    /**
     * Borra una tupla de una recompensa en la base de datos
     * de acuerdo al id dado
     * @param id - Id de la recompensa a eliminar 
     */
    public void delete(Long id){
        RecompensaEntity r = em.find(RecompensaEntity.class, id);
        em.remove(r);
    }
}

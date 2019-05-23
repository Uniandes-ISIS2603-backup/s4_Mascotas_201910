/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.persistence;

import co.edu.uniandes.csw.mascotas.entities.MascotaExtraviadaEntity;
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
public class MascotaExtraviadaPersistence{
    
    private static final Logger LOGGER = Logger.getLogger(MascotaExtraviadaPersistence.class.getName());
    
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
    public MascotaExtraviadaEntity create(MascotaExtraviadaEntity entity){
        
        em.persist(entity);
        return entity;
    }
    
    /**
     * Encuentra la entidad asociado al identificador dado.
     * Si no existe devuelve null.
     * @param id - identificador
     * @return instancia de MascotaExtraviadaEntity asociada
     */
    public MascotaExtraviadaEntity find(Long id){
        return em.find(MascotaExtraviadaEntity.class, id);
    }
    
    /**
     * Retorna todas los procesos de mascota extraviada existentes
     * @return Lista con todas las entidades existentes
     */
    public List<MascotaExtraviadaEntity> findAll(){
        TypedQuery<MascotaExtraviadaEntity> q = em.createQuery("select u from MascotaExtraviadaEntity u", MascotaExtraviadaEntity.class);
        return q.getResultList();
    }
    
    /**
     * Actualiza una proceso de mascota extraviada de acuerdo a los valores
     * dados por el Entity
     * @param entity - Entity del proceso de mascota extraviada
     * @return Entity del proceso de mascota extraviada
     */
    public MascotaExtraviadaEntity update(MascotaExtraviadaEntity entity)
    {
        return em.merge(entity);
    }
    
    /**
     * Elimina el proceso de mascota extraviada asociado al id
     * @param id - El id del proceso a borrar
     */
    public void delete(Long id)
    {
        MascotaExtraviadaEntity entity = em.find(MascotaExtraviadaEntity.class, id);
        em.remove(entity);
    }
}

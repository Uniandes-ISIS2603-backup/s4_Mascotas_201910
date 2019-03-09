/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.persistence;


import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import co.edu.uniandes.csw.mascotas.entities.UsuarioEntity;


/**
 *
 * @author estudiante
 */
@Stateless
public class UsuarioPersistence {
     private static final Logger LOGGER = Logger.getLogger(UsuarioPersistence.class.getName());
     
     @PersistenceContext(unitName="mascotasPU")
     protected EntityManager em;
     
     
     public UsuarioEntity create(UsuarioEntity entityU){
         em.persist(entityU);
         return entityU;
     }
     
   
    public UsuarioEntity find(long id){
    return em.find(UsuarioEntity.class, id);
    }
    
    public List<UsuarioEntity> findAll()
    {
        TypedQuery<UsuarioEntity> qu = em.createQuery("select u from UsuarioEntity u",UsuarioEntity.class );
       return qu.getResultList();
    }
    
    /**
     * Actualiza un usuario de acuerdo a los valores dados en el entity
     *
     * @param entity - Entity del usuario
     * @return Entity del usuario
     */
    public UsuarioEntity update(UsuarioEntity entity) {
        return em.merge(entity);
    }

    /**
     * Elimina el usuario segun su id
     *
     * @param id
     */
    public void delete(Long id) {
        UsuarioEntity entity = em.find(UsuarioEntity.class, id);
        em.remove(entity);
    }

}
            
            
            
       

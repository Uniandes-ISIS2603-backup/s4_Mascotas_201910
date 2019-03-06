/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.persistence;

import co.edu.uniandes.csw.mascotas.entities.ClasificadoEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 *
 * @author je.vivas
 */

@Stateless
public class ClasificadoPersistence {
    
   @PersistenceContext(unitName = "mascotasPU")
   
   protected EntityManager em;
   
   public ClasificadoEntity create(ClasificadoEntity clasifEntity){
       
       em.persist(clasifEntity);
       
       return clasifEntity;
   }
   
   public ClasificadoEntity find(Long id){
       
       return em.find(ClasificadoEntity.class, id);
   }
   
   public List<ClasificadoEntity> findAll(){
       TypedQuery<ClasificadoEntity> query = em.createQuery("select u from ClasificadoEntity u", ClasificadoEntity.class);
       
       return query.getResultList();
   }
    
}

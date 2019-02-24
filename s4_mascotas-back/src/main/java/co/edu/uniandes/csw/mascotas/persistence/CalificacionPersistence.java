/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.persistence;
import co.edu.uniandes.csw.mascotas.entities.CalificacionEntity;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
/**
 *
 * @author s.canales
 */
@Stateless
public class CalificacionPersistence {
    
    @PersistenceContext(unitName="mascotasPU")
    protected EntityManager em;
    
   public CalificacionEntity create(CalificacionEntity calificacionEntity){
       
       em.persist(calificacionEntity);
       return calificacionEntity;
   }
   
   public CalificacionEntity find(Long calificacionEntity){
       
       return em.find(CalificacionEntity.class,calificacionEntity);
   }
   public List<CalificacionEntity> findAll(){
       
       TypedQuery<CalificacionEntity> query = em.createQuery("select u from UsuarioEntity u",CalificacionEntity.class );
       return query.getResultList();
   }
    
}

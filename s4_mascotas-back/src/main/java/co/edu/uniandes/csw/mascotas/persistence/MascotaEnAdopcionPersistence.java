/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.persistence;


import co.edu.uniandes.csw.mascotas.entities.MascotaEnAdopcionEntity;
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
public class MascotaEnAdopcionPersistence {
    
     @PersistenceContext(unitName="mascotasPU")
    protected EntityManager em;
    
   public MascotaEnAdopcionEntity create(MascotaEnAdopcionEntity mascotaEnAdopcionEntity){
       
       em.persist(mascotaEnAdopcionEntity);
       return mascotaEnAdopcionEntity;
   }
   
   public MascotaEnAdopcionEntity find(Long id){
       
       return em.find(MascotaEnAdopcionEntity.class, id);
   }
   public List<MascotaEnAdopcionEntity> findAll(){
       
       TypedQuery<MascotaEnAdopcionEntity> query = em.createQuery("select u from UsuarioEntity u",MascotaEnAdopcionEntity.class );
       return query.getResultList();
   }
   public MascotaEnAdopcionEntity update(MascotaEnAdopcionEntity entity){
       
       return em.merge(entity);
   }
   
   public void delete(long id){
       
         em.remove(em.find(MascotaEnAdopcionEntity.class , id));
   }

}

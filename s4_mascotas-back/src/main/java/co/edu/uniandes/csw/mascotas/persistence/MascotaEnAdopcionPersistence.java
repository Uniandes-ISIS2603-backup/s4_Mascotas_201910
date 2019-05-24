/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.persistence;


import co.edu.uniandes.csw.mascotas.entities.MascotaEnAdopcionEntity;
import co.edu.uniandes.csw.mascotas.entities.UsuarioEntity;
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
    
    /**
     * para acceder a la base de datos
     */
     @PersistenceContext(unitName="mascotasPU")
    protected EntityManager em;
    
     /**
      * crea un nuevo proceso de adopción
      * @param mascotaEnAdopcionEntity
      * @return 
      */
   public MascotaEnAdopcionEntity create(MascotaEnAdopcionEntity mascotaEnAdopcionEntity){
       
       em.persist(mascotaEnAdopcionEntity);
       return mascotaEnAdopcionEntity;
   }
   /**
    * encuentra un proceso por su id
    * @param id
    * @return 
    */
   public MascotaEnAdopcionEntity find(Long id){
       
       return em.find(MascotaEnAdopcionEntity.class, id);
   }
   /**
    * devuelve todos los procesos de adopción
    * @return 
    */
   public List<MascotaEnAdopcionEntity> findAll(){
       
       TypedQuery<MascotaEnAdopcionEntity> query = em.createQuery("select u from MascotaEnAdopcionEntity u",MascotaEnAdopcionEntity.class );
       return query.getResultList();
   }
   /**
    * devuelve la lista de los postulados al proceso asociado
    * @param id Long del proceso buscado
    * @return 
    */
   
   public List<UsuarioEntity> postuladosByProceso(Long id){
       TypedQuery<UsuarioEntity> query = em.createQuery("Select u from UsuarioEntity u where u.id = :id " ,UsuarioEntity.class );
       return query.setParameter("id", 200).getResultList();
   }
   /**
    * devuelve todos los procesos sin adoptar
    * @return 
    */
   public List<MascotaEnAdopcionEntity> findSinAdoptar(){
       
       TypedQuery<MascotaEnAdopcionEntity> query = em.createQuery("Select u from MascotaEnAdopcionEntity u where u.adoptada = false" ,MascotaEnAdopcionEntity.class );
       return query.getResultList();
   }
   /**
    * modifica el proceso
    * @param entity
    * @return 
    */
   public MascotaEnAdopcionEntity update(MascotaEnAdopcionEntity entity){
       
       return em.merge(entity);
   }
   /**
    * devuelve los procesos por tipo
    * @param type
    * @return 
    */
   public List<MascotaEnAdopcionEntity> findByType(String type){
       TypedQuery<MascotaEnAdopcionEntity> query = em.createQuery("Select u from MascotaEnAdopcionEntity u where u.mascota.tipo = :type" ,MascotaEnAdopcionEntity.class );
       return query.setParameter("type", type).getResultList();
   }
   
   /**
    * borra un proceso por su id
    * @param id 
    */
   public void delete(long id){
       
         em.remove(em.find(MascotaEnAdopcionEntity.class , id));
   }

}

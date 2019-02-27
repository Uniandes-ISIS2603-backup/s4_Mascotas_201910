/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.persistence;

import co.edu.uniandes.csw.mascotas.entities.PostuladoEntity;

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
public class PostuladoPersistence {
    
    @PersistenceContext(unitName="mascotasPU")
    protected EntityManager em;
    
   public PostuladoEntity create(PostuladoEntity postuladoEntity){
       
       em.persist(postuladoEntity);
       return postuladoEntity;
   }
   
   public PostuladoEntity find(Long postuladoEntity){
       
       return em.find(PostuladoEntity.class,postuladoEntity);
   }
   public List<PostuladoEntity> findAll(){
       
       TypedQuery<PostuladoEntity> query = em.createQuery("select u from UsuarioEntity u",PostuladoEntity.class );
       return query.getResultList();
   }
           
}

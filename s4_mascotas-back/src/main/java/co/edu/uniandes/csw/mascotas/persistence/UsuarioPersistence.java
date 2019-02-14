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
     
     
     public UsuarioEntity crete(UsuarioEntity entityU){
         em.persist(entityU);
         return entityU;
     }
     
   
    public UsuarioEntity find(String usuario){
        return em.find(UsuarioEntity.class, usuario);
    }
}

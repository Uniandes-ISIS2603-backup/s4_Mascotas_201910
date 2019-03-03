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
 * @author Maria Ana Ortiz (ma.ortiz1)
 */
@Stateless
public class UsuarioPersistence {
     private static final Logger LOGGER = Logger.getLogger(UsuarioPersistence.class.getName());
     
     /**
      * Manejador de persitencia
      */
     @PersistenceContext(unitName="mascotasPU")
     protected EntityManager em;
     
     /**
      * Persiste en la base de datos informacion contenida en el entity de usario
      * dado por parametro
      * @param entityU - informacion para crear la entrada a la base de datos
      * @return Objeto Entity correspondiente
      */
     public UsuarioEntity create(UsuarioEntity entityU){
         em.persist(entityU);
         return entityU;
     }
     
    /**
     * Encuentra la entidad que esta asociada al id dada por parametro
     * @param id - idenfiticador de la entidad
     * @return la instancca de UsuarioEntity asociada al id
     */
    public UsuarioEntity find(long id){
    return em.find(UsuarioEntity.class, id);
    }
    
    /**
     * Retorna todos los usuarios que existen
     * @return lista de todos los usuarios
     */
    public List<UsuarioEntity> findAll()
    {
        TypedQuery<UsuarioEntity> qu = em.createQuery("select u from UsuarioEntity u",UsuarioEntity.class );
       return qu.getResultList();
    }

    /**
     * Actualiza  un usuario de acuerdo a los valores dados en el entity
     * @param entity - Entity del usuario
     * @return Entity del usuario 
     */
    public UsuarioEntity update(UsuarioEntity entity){
        return em.merge(entity);
    }
    /**
     * Elimina el usuario segun su id
     * @param id 
     */
    public void delete(Long id)
    {
        UsuarioEntity entity = em.find(UsuarioEntity.class, id);
        em.remove(entity);
    }
    
    /**
     * Cambia el correo del usuario
     * @param correo - cadena de caracteres respectiva al correo
     * @param id -  identificador
     * @return la instancia de usuario modificada
     */
    public UsuarioEntity actualizarCorreoUsuario(String correo,Long id){
        UsuarioEntity usuario = em.find(UsuarioEntity.class,id);
        usuario.setCorreo(correo);
        em.refresh(usuario);
        return usuario;              
    }
    
    /**
     * Cambia la conttraseña del usuario
     * @param contrasenha - cadena dde caracteres respectiva a la contrasenha
     * @param id - identificador
     * @return la instancia de usuario modificada
     */
    public UsuarioEntity actualizarContrasenhaUsuario(String contrasenha,Long id){
        UsuarioEntity usuario = em.find(UsuarioEntity.class, id);
        usuario.setContrasenha(contrasenha);
        em.refresh(usuario);
        return usuario;
    }

    /**
     * Cambia ell nombre del usuario
     * @param nombre - cadaena de caracteres con el nuevo nombre
     * @param id - identificador 
     * @return la instancia de usuario modificada
     */
    public UsuarioEntity actualizarNombre(String nombre,Long id){
        UsuarioEntity usuario = em.find(UsuarioEntity.class, id);
        usuario.setNombre(nombre);
        em.refresh(usuario);
        return usuario;
    }


}



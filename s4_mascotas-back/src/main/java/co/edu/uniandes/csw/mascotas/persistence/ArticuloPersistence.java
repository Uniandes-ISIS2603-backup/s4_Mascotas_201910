/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.persistence;

import co.edu.uniandes.csw.mascotas.entities.ArticuloEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Daniela Gonzalez
 */

@Stateless
public class ArticuloPersistence {
    
    @PersistenceContext (unitName = "mascotasPU")
    protected EntityManager em;
    
    
    /**
     * Método para persisitir (crear) la entidad en la base de datos.
     *
     * @param articuloEntity objeto articulo que se creará en la base de datos
     * @return devuelve la entidad creada con un id generado por la base de datos.
     */
     public ArticuloEntity create(ArticuloEntity articuloEntity){
        
        em.persist(articuloEntity);
        return articuloEntity;
    }
     
     /**
     * Busca un articulo con el id enviado por parametro.
     *
     * @param articuloId: id correspondiente al articulo buscado.
     * @return una entidad de articulo.
     */
     public ArticuloEntity find(Long articuloId){
        
        return em.find(ArticuloEntity.class, articuloId);
    }
     
     /**
     * Devuelve todos los articulos de la base de datos.
     *
     * @return una lista con todos los articulos que encuentre en la base de
     * datos.
     */
     public List<ArticuloEntity> findAll(){
        
        TypedQuery<ArticuloEntity> query = em.createQuery("select u from ArticuloEntity u", ArticuloEntity.class);
        return query.getResultList();
    }
     
     /**
     * Actualiza un articulo.
     *
     * @param nuevaEntidad: la entidad que viene con los nuevos cambios.
     * Por ejemplo el nombre pudo cambiar. 
     * @return un articulo con los cambios aplicados.
     */
     public ArticuloEntity actualizarArticulo(ArticuloEntity nuevaEntidad){
         
         return em.merge(nuevaEntidad);
     }
     
     
     /**
     *
     * Borra un articulo de la base de datos recibiendo como argumento el id
     * del articulo
     *
     * @param id: id correspondiente al articulo a borrar.
     */
     public void delete(Long id){
         
         ArticuloEntity entity = em.find(ArticuloEntity.class, id);
         em.remove(entity);
     }
     
     /**
     * Busca si hay algun articulo con el titulo que se envía de argumento
     *
     * @param nombre: titulo del articulo que se está buscando
     * @return null si no existe ningun articulo con el nombre del argumento.
     * Si existe alguno devuelve el primero.
     */
     public ArticuloEntity findByName(String nombre){
         
         TypedQuery query = em.createQuery("Select e From ArticuloEntity e where e.titulo = :titulo", ArticuloEntity.class);
         
         query = query.setParameter("titulo", nombre);
         
         List<ArticuloEntity> mismoNombre = query.getResultList();
         
         ArticuloEntity resultado;
         
         if(mismoNombre == null){
             resultado = null;
         } else if (mismoNombre.isEmpty()){
             resultado = null;
         } else{
             resultado = mismoNombre.get(0);
         }
         
         return resultado;
     }
}
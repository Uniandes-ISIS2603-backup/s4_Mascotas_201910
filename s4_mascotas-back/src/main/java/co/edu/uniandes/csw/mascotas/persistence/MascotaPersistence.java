/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.persistence;

import co.edu.uniandes.csw.mascotas.entities.MascotaEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Natalia Sanabria Forero (n.sanabria)
 */
@Stateless
public class MascotaPersistence 
{
    @PersistenceContext(unitName = "mascotasPU")
    protected EntityManager em;
    
    /**
     * Agrega una mascota a la base de datos
     * @param mascotaEntity
     * @return 
     */
    public MascotaEntity create(MascotaEntity mascotaEntity)
    {
        em.persist(mascotaEntity);
        return mascotaEntity;
    }
    
    /**
     * Encuentra una mascota a partir de su id
     * @param mascotaId
     * @return 
     */
    public MascotaEntity find(Long mascotaId)
    {
        return em.find(MascotaEntity.class, mascotaId);
    }
    
    /**
     * Retorna una lista con todas las mascotas en el sistema
     * @return 
     */
    public List<MascotaEntity> findAll()
    {
        TypedQuery<MascotaEntity> query= em.createQuery("select u from MascotaEntity u", MascotaEntity.class);
        return query.getResultList();
    }
    
    /**
     * Cambia el estado de una mascota en el sistema
     * @param mascota
     * @return 
     */
    public MascotaEntity actualizarEstadoMascota(MascotaEntity mascota)
    {
        return em.merge(mascota);
    }
    
    /**
     * Retorna las mascotas cuyo estado corresponde al estado pasado por parámetro
     * @param pEstado
     * @return 
     */
    public List<MascotaEntity> darMascotasPorEstado(String pEstado)
    {
        MascotaEntity.Estados_mascota estado;
        estado = MascotaEntity.Estados_mascota.valueOf(pEstado);
        TypedQuery<MascotaEntity> query= em.createQuery("select u from MascotaEntity u where u.estado = :estado", MascotaEntity.class);
        query.setParameter("estado", estado);
        return query.getResultList();
    }
    
    /**
     * Retorna las mascotas cuyo nombre coincide o contiene el nombre ingresado por parámetro
     * @param pNombre
     * @return 
     */
    public List<MascotaEntity> darMascotasPorNombre( String pNombre )
    {
        TypedQuery<MascotaEntity> query= em.createQuery("select u from MascotaEntity u where u.nombre = :name ", MascotaEntity.class);
        query.setParameter("name", pNombre);
        return query.getResultList();
    }
    
    /**
     * Retorna las mascotas del tipo indicado por el parámetro
     * @param pTipo
     * @return 
     */
    public List<MascotaEntity> darMascotasPorTipo(String pTipo)
    {
        TypedQuery<MascotaEntity> query= em.createQuery("select u from MascotaEntity u where u.tipo = :tipo ", MascotaEntity.class);
        query.setParameter("tipo", pTipo);
        return query.getResultList();
    }
    
    public List<MascotaEntity> filtarPorParametros(List<String[]> params)
    {
        String sqlQuery = "select u from MascotaEntity u where ";
        for(int i = 0; i < params.size(); i++)
        {
            sqlQuery += params.get(i)[0] + " = " + params.get(i)[1];
            if(i+1<params.size())
                sqlQuery += " AND ";
        }
        TypedQuery<MascotaEntity> query = em.createQuery(sqlQuery, MascotaEntity.class);
        return query.getResultList();
    }
}
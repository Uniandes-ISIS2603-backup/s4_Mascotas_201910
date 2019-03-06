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
    
    public MascotaEntity create(MascotaEntity mascotaEntity)
    {
        em.persist(mascotaEntity);
        return mascotaEntity;
    }
    
    public MascotaEntity find(Long mascotaId)
    {
        return em.find(MascotaEntity.class, mascotaId);
    }
    
    public List<MascotaEntity> findAll()
    {
        TypedQuery<MascotaEntity> query= em.createQuery("select u from MascotaEntity u", MascotaEntity.class);
        return query.getResultList();
    }
    
    public MascotaEntity actualizarEstadoMascota(MascotaEntity mascota)
    {
        return em.merge(mascota);
    }
    
    public List<MascotaEntity> darMascotasPorEstado(String pEstado)
    {
        MascotaEntity.Estados_mascota estado;
        estado = MascotaEntity.Estados_mascota.valueOf(pEstado);
        TypedQuery<MascotaEntity> query= em.createQuery("select u from MascotaEntity u where u.estado = :estado", MascotaEntity.class);
        query.setParameter("estado", estado);
        return query.getResultList();
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.test.persistence;
import co.edu.uniandes.csw.mascotas.entities.MascotaEnAdopcionEntity;

import co.edu.uniandes.csw.mascotas.persistence.MascotaEnAdopcionPersistence;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
/**
 *
 * @author s.canales
 */
@RunWith(Arquillian.class)
public class MascotaEnAdopcionPersistenceTest {
    
    @Inject
    private MascotaEnAdopcionPersistence mp;
    
    @PersistenceContext
    private EntityManager em;
    
    @Deployment
    public static JavaArchive deploymento(){
      return ShrinkWrap.create(JavaArchive.class)
                .addPackage(MascotaEnAdopcionEntity.class.getPackage())
                .addPackage(MascotaEnAdopcionPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    @Test
    public void createMascotaEnAdopcionTest(){
        
        
        PodamFactory factory = new PodamFactoryImpl();
        MascotaEnAdopcionEntity entity = factory.manufacturePojo(MascotaEnAdopcionEntity.class);
        
        MascotaEnAdopcionEntity me = mp.create(entity);
        
        Assert.assertNotNull(me);
        
        MascotaEnAdopcionEntity e = em.find(MascotaEnAdopcionEntity.class , me.getId());
        
        Assert.assertEquals(entity.getPasado() , e.getPasado() );
        
//        mp.delete(e.getId());
//        mp.delete(me.getId());
//        Assert.assertNull(em.find(MascotaEnAdopcionEntity.class , e.getId()));
       // Assert.assertEquals(entity.getMascota() , e.getMascota() );
        
    }
}
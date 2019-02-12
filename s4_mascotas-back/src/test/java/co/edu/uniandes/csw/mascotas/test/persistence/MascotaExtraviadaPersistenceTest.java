/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.test.persistence;

import co.edu.uniandes.csw.mascotas.entities.MascotaExtraviadaEntity;
import co.edu.uniandes.csw.mascotas.persistence.MascotaExtraviadaPersistence;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import junit.framework.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author Sebastián Lemus Cadena (s.lemus)
 */
@RunWith(Arquillian.class)
public class MascotaExtraviadaPersistenceTest {
    
    /**
     * La clase de persistencia sobre la cual se realizan las pruebas
     */
    @Inject
    private MascotaExtraviadaPersistence persistence;
    
    /**
     * Manejador de persistencia
     */
    @PersistenceContext
    private EntityManager em;
    
    @Deployment
    public static JavaArchive deployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(MascotaExtraviadaEntity.class.getPackage())
                .addPackage(MascotaExtraviadaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    @Test
    public void createMascotaExtraviadaTest(){
        
        PodamFactory factory = new PodamFactoryImpl();
        
        MascotaExtraviadaEntity entity = factory.manufacturePojo(MascotaExtraviadaEntity.class); 
        MascotaExtraviadaEntity resultEntity = persistence.create(entity);
        
        Assert.assertNotNull(resultEntity);
        
        MascotaExtraviadaEntity foundEntity = em.find(MascotaExtraviadaEntity.class, resultEntity.getId());
        Assert.assertEquals(entity.getCiudad(), foundEntity.getCiudad());
        Assert.assertEquals(entity.getDireccion(), foundEntity.getDireccion());
        Assert.assertEquals(entity.getEstado(), foundEntity.getEstado());
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.test.persistence;

import co.edu.uniandes.csw.mascotas.entities.RecompensaEntity;
import co.edu.uniandes.csw.mascotas.persistence.RecompensaPersistence;
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
public class RecompensaTest {
    
    /**
     * La clase de persistencia sobre la cual se realizan las pruebas
     */
    @Inject
    private RecompensaPersistence persistence;
    
    /**
     * Manejador de persistencia para este conjunto de pruebas
     */
    @PersistenceContext
    private EntityManager em;
    
    @Deployment
    public static JavaArchive deployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(RecompensaEntity.class.getPackage())
                .addPackage(RecompensaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    /**
     * Método que prueba el funcionamiento de persistir una nueva
     * recompensa en la base de datos
     */
    @Test
    public void createRecompensaTest(){
        
        PodamFactory factory = new PodamFactoryImpl();
        
        RecompensaEntity entity = factory.manufacturePojo(RecompensaEntity.class);
        RecompensaEntity resultEntity = persistence.create(entity);
        
        Assert.assertNotNull(resultEntity);
        
        RecompensaEntity foundEntity = em.find(RecompensaEntity.class, resultEntity.getId());
        Assert.assertEquals(entity.getEstado(), foundEntity.getEstado());
        Assert.assertEquals(entity.getValor(), foundEntity.getValor());
        Assert.assertEquals(entity.getMedioDePago(), foundEntity.getMedioDePago());
    }
}

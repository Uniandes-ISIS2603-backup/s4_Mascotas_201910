/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.test.persistence;

import co.edu.uniandes.csw.mascotas.entities.MascotaEntity;
import co.edu.uniandes.csw.mascotas.persistence.MascotaPersistence;
import java.util.Random;
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
 * @author Natalia Sanabria Forero (n.sanabria)
 */
@RunWith(Arquillian.class)
public class MascotaEntityPersistenceTest 
{
    @Inject
    private MascotaPersistence ep;
    
    @PersistenceContext
    private EntityManager em;
    
    /**
     * @return Devuelve el JAR que Arquillian va a desplegar en Payara embebido.
     */
    @Deployment
    public static JavaArchive deployment( )
    {
        return ShrinkWrap.create(JavaArchive.class).addPackage(MascotaEntity.class.getPackage())
                .addPackage(MascotaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml");   
    }
    
    @Test
    public void createMascotaTest( )
    {
        PodamFactory factory = new PodamFactoryImpl();
        MascotaEntity newEntity = factory.manufacturePojo(MascotaEntity.class);
        MascotaEntity mascota = ep.create(newEntity);
        
        Assert.assertNotNull(mascota);
        
        MascotaEntity entity = em.find(MascotaEntity.class, mascota.getId());
        
        Assert.assertNotNull(entity);
        Assert.assertEquals(entity.getTipo(), newEntity.getTipo());
    }
    
    @Test
    public void actualizarEstadoMascotaTest()
    {
        PodamFactory factory = new PodamFactoryImpl();
        MascotaEntity newEntity = factory.manufacturePojo(MascotaEntity.class);
        MascotaEntity mascota = ep.create(newEntity);
        
        Assert.assertNotNull(mascota);
        
        Random random = new Random();
        Integer randomInt = random.nextInt();
        
        MascotaEntity entidadActualizada = ep.actualizarEstadoMascota(mascota.getId(), randomInt);
        
        mascota = em.find( MascotaEntity.class, mascota.getId());
        Assert.assertEquals(entidadActualizada.getEstado(), mascota.getEstado());
    }
}
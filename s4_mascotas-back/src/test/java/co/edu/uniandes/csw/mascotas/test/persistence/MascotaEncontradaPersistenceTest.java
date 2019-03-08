/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.test.persistence;

import co.edu.uniandes.csw.mascotas.entities.MascotaEncontradaEntity;
import co.edu.uniandes.csw.mascotas.persistence.MascotaEncontradaPersistence;
import javax.persistence.EntityManager;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.runner.RunWith;
import org.jboss.arquillian.container.test.api.Deployment;
import org.junit.Assert;
import org.junit.Test;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author Johan E. Vivas Sepulveda (je.vivas)
 */
@RunWith(Arquillian.class)
public class MascotaEncontradaPersistenceTest {

    private MascotaEncontradaPersistence mascotaPersist;

    protected EntityManager em;

    @Deployment
    public static JavaArchive createDeployment() {

        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(MascotaEncontradaEntity.class.getPackage())
                .addPackage(MascotaEncontradaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    @Test
    public void createMascotaEncontradaTest() {

        PodamFactory factory = new PodamFactoryImpl();
        
        MascotaEncontradaEntity newEntity = factory.manufacturePojo(MascotaEncontradaEntity.class);
        MascotaEncontradaEntity mascotaE = mascotaPersist.create(newEntity);

        Assert.assertNotNull(mascotaE);

        MascotaEncontradaEntity entityComp = em.find(MascotaEncontradaEntity.class, mascotaE.getId());

        Assert.assertEquals(newEntity.getDescripcion(), entityComp.getDescripcion());
        Assert.assertEquals(newEntity.getEstado(), entityComp.getEstado());
        Assert.assertEquals(newEntity.getUbicacion(), entityComp.getUbicacion());

    }

}

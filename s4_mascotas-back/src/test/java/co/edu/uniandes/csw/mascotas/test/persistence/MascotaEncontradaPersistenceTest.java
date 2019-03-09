/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.test.persistence;

import co.edu.uniandes.csw.mascotas.entities.MascotaEncontradaEntity;
import co.edu.uniandes.csw.mascotas.persistence.MascotaEncontradaPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.runner.RunWith;
import org.jboss.arquillian.container.test.api.Deployment;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author Johan E. Vivas Sepulveda (je.vivas)
 */
@RunWith(Arquillian.class)
public class MascotaEncontradaPersistenceTest {

    @Inject
    private MascotaEncontradaPersistence persistence;

    @PersistenceContext
    protected EntityManager em;
    
    private List<MascotaEncontradaEntity> listaPrueba = new ArrayList<>();
    
    @Inject
    private UserTransaction utx;

    @Deployment
    public static JavaArchive createDeployment() {

        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(MascotaEncontradaEntity.class.getPackage())
                .addPackage(MascotaEncontradaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    private void inicializacionListaPrueba(){
        PodamFactory factory = new PodamFactoryImpl();
        for(int i = 0; i < 10; i++){
            MascotaEncontradaEntity e = factory.manufacturePojo(MascotaEncontradaEntity.class);
            em.persist(e);
            listaPrueba.add(e);
        }
    }
    
    private void clearData() {
        em.createQuery("delete from MascotaEncontradaEntity").executeUpdate();
    }
    
    @Before
    public void configTest() {
        try {
            utx.begin();
            em.joinTransaction();
            clearData();
            inicializacionListaPrueba();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    @Test
    public void createMascotaEncontradaTest() {

        PodamFactory factory = new PodamFactoryImpl();
        
        MascotaEncontradaEntity newEntity = factory.manufacturePojo(MascotaEncontradaEntity.class);
        MascotaEncontradaEntity mascotaE = persistence.create(newEntity);

        Assert.assertNotNull(mascotaE);

        MascotaEncontradaEntity entityComp = em.find(MascotaEncontradaEntity.class, mascotaE.getId());

        Assert.assertEquals(newEntity.getDescripcion(), entityComp.getDescripcion());
        Assert.assertEquals(newEntity.getEstado(), entityComp.getEstado());
        Assert.assertEquals(newEntity.getUbicacion(), entityComp.getUbicacion());

    }
    
    @Test
    public void deleteMascotaEncontradaTest(){
        MascotaEncontradaEntity entityP = listaPrueba.get(7);
        persistence.delete(entityP.getId());
        MascotaEncontradaEntity deleted = em.find(MascotaEncontradaEntity.class, entityP.getId());
        Assert.assertNull(deleted);
    }

}

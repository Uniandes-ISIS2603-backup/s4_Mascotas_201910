/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.test.persistence;
import co.edu.uniandes.csw.mascotas.entities.MascotaEnAdopcionEntity;
import co.edu.uniandes.csw.mascotas.entities.MascotaExtraviadaEntity;

import co.edu.uniandes.csw.mascotas.persistence.MascotaEnAdopcionPersistence;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;
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
    
    /**
     * la clase de persistencia sobre la cual se realizan las pruebas
     */
    @Inject
    private MascotaEnAdopcionPersistence mp;
    
    /**
     * manejador de persistencia
     */
    @PersistenceContext
    private EntityManager em;
    /**
     * manejador de transacciones
     */
    @Inject
    private UserTransaction utx;
    /**
     * lista de entidades de mascota en adopcion sobre las cuales se van a hacer pruebas
     */
    private List<MascotaEnAdopcionEntity> listaPrueba = new ArrayList<>();
    
    @Deployment
    public static JavaArchive deploymento(){
      return ShrinkWrap.create(JavaArchive.class)
                .addPackage(MascotaEnAdopcionEntity.class.getPackage())
                .addPackage(MascotaEnAdopcionPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    /**
     * se inicializa la lista de prueba
     */
    private void inicializacionListaPrueba(){
        PodamFactory factory = new PodamFactoryImpl();
        for(int i = 0; i < 10; i++){
            MascotaEnAdopcionEntity e = factory.manufacturePojo(MascotaEnAdopcionEntity.class);
            em.persist(e);
            listaPrueba.add(e);
        }
    }
    /**
     * Limpia las tablas que están implicadas en la prueba.
     */
    private void clearData() {
        em.createQuery("delete from MascotaExtraviadaEntity").executeUpdate();
    }
    /**
     * configuración inicial para la prueba
     */
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
    /**
     * prueba que el método create esté funcionando correctamente
     */
    @Test
    public void createMascotaEnAdopcionTest(){
        
        
        PodamFactory factory = new PodamFactoryImpl();
        MascotaEnAdopcionEntity entity = factory.manufacturePojo(MascotaEnAdopcionEntity.class);
        
        MascotaEnAdopcionEntity me = mp.create(entity);
        
        Assert.assertNotNull(me);
        
        MascotaEnAdopcionEntity found = em.find(MascotaEnAdopcionEntity.class , me.getId());
        
        Assert.assertEquals(entity.getPasado() , found.getPasado() );
        Assert.assertEquals(entity.isAdoptada(), found.isAdoptada());
        Assert.assertEquals(entity.getRazonAdopcion() , found.getRazonAdopcion());
              
    }
    /**
     * prueba que el método update esté funcionando correctamente
     */
    @Test
    public void updateMascotaEnAdopcionTest(){
        
        PodamFactory factory = new PodamFactoryImpl();
        MascotaEnAdopcionEntity aProbar = listaPrueba.get(8);
        MascotaEnAdopcionEntity entity = factory.manufacturePojo(MascotaEnAdopcionEntity.class);
        entity.setId(aProbar.getId());
        mp.update(entity);
        MascotaEnAdopcionEntity resultado = em.find(MascotaEnAdopcionEntity.class, aProbar.getId());
        Assert.assertEquals(resultado.getPasado(),entity.getPasado());
        Assert.assertEquals(resultado.getRazonAdopcion(), entity.getRazonAdopcion() );
        Assert.assertEquals(resultado.isAdoptada() , entity.isAdoptada());
        
    }
    /**
     * prueba que el método delete esté funcioando correctamene
     */
    @Test 
    public void deleteMascotaEnAdopcionTest(){
        MascotaEnAdopcionEntity entity = listaPrueba.get(5);
        mp.delete(entity.getId());
        
        Assert.assertNull(em.find(MascotaEnAdopcionEntity.class,entity.getId()));
    }
}
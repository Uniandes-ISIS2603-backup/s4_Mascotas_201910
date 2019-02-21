/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.test.persistence;

import co.edu.uniandes.csw.mascotas.entities.MascotaExtraviadaEntity;
import co.edu.uniandes.csw.mascotas.persistence.MascotaExtraviadaPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import junit.framework.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
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
     * Manejador de persistencia para este conjunto de pruebas
     */
    @PersistenceContext
    private EntityManager em;
    
    /**
     * Lista de procesos de mascota extraviada sobre la cual se realizan algunas pruebas
     */
    private List<MascotaExtraviadaEntity> listaPrueba = new ArrayList<>();
    
    /**
     * Manejador de transacciones
     */
    @Inject
    private UserTransaction utx;
    
    @Deployment
    public static JavaArchive deployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(MascotaExtraviadaEntity.class.getPackage())
                .addPackage(MascotaExtraviadaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    /**
     * Inicializa la lista de prueba
     */
    private void inicializacionListaPrueba(){
        PodamFactory factory = new PodamFactoryImpl();
        for(int i = 0; i < 10; i++){
            MascotaExtraviadaEntity e = factory.manufacturePojo(MascotaExtraviadaEntity.class);
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
     * Configuración inicial de la prueba.
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
     * Método que prueba el funcionamiento de persistir un nuevo proceso
     * de mascota extraviada en la base de datos
     */
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
    
    /**
     * Método que prueba la funcionalidad de consultar una tupla
     * de la tabla MascotaExtraviadaEntity 
     */
    @Test
    public void getRecompensaTest(){
        MascotaExtraviadaEntity entity = listaPrueba.get(5);
        MascotaExtraviadaEntity foundEntity = persistence.find(entity.getId());
        
        Assert.assertNotNull(foundEntity);
        Assert.assertEquals(entity.getCiudad(), foundEntity.getCiudad());
        Assert.assertEquals(entity.getDireccion(), foundEntity.getDireccion());
        Assert.assertEquals(entity.getEstado(), foundEntity.getEstado());
    }
    
    /**
     * Método que prueba la funcionalidad de consultar todas las tuplas
     * de la tabla MascotaExtraviadaEntity 
     */
    @Test
    public void getRecompensasTest(){
        List<MascotaExtraviadaEntity> xs = persistence.findAll();
        Assert.assertEquals(listaPrueba.size(), xs.size());
        for(MascotaExtraviadaEntity m : listaPrueba){
            boolean found = false;
            for(MascotaExtraviadaEntity x : xs){
                if(m.getId().equals(x.getId()));
                found = true;
            }
            Assert.assertTrue(found);
        }
    }
    
    /**
     * Método que prueba la funcionalidad de actualizar los
     * valores de un proceso de mascota extraviada
     */
    @Test
    public void updateMascotaExtraviadaTest(){
        PodamFactory factory = new PodamFactoryImpl();
        MascotaExtraviadaEntity entity = listaPrueba.get(8);
        MascotaExtraviadaEntity newEntity = factory.manufacturePojo(MascotaExtraviadaEntity.class);
        newEntity.setId(entity.getId());
        persistence.update(newEntity);
        
        MascotaExtraviadaEntity foundEntity = em.find(MascotaExtraviadaEntity.class, entity.getId());
        Assert.assertEquals(newEntity.getCiudad(), foundEntity.getCiudad());
        Assert.assertEquals(newEntity.getDireccion(), foundEntity.getDireccion());
        Assert.assertEquals(newEntity.getEstado(), foundEntity.getEstado());
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.test.persistence;

import co.edu.uniandes.csw.mascotas.entities.RecompensaEntity;
import co.edu.uniandes.csw.mascotas.persistence.RecompensaPersistence;
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
public class RecompensaPersistenceTest {
    
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
    
    /**
     * Lista de recompensas sobre la cual se realizan algunas pruebas
     */
    private List<RecompensaEntity> listaPrueba = new ArrayList<>();
    
    /**
     * Manejador de transacciones
     */
    @Inject
    private UserTransaction utx;
    
    @Deployment
    public static JavaArchive deployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(RecompensaEntity.class.getPackage())
                .addPackage(RecompensaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    /**
     * Inicializa la lista de prueba
     */
    private void inicializacionListaPrueba(){
        PodamFactory factory = new PodamFactoryImpl();
        for(int i = 0; i < 10; i++){
            RecompensaEntity e = factory.manufacturePojo(RecompensaEntity.class);
            em.persist(e);
            listaPrueba.add(e);
        }
    }
    
    /**
     * Limpia las tablas que están implicadas en la prueba.
     */
    private void clearData() {
        em.createQuery("delete from RecompensaEntity").executeUpdate();
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
    
    /**
     * Método que prueba la funcionalidad de consultar una tupla
     * de la tabla RecompensaEntity 
     */
    @Test
    public void getRecompensaTest(){
        RecompensaEntity entity = listaPrueba.get(5);
        RecompensaEntity foundEntity = persistence.find(entity.getId());
        
        Assert.assertNotNull(foundEntity);
        Assert.assertEquals(entity.getEstado(), foundEntity.getEstado());
        Assert.assertEquals(entity.getValor(), foundEntity.getValor());
        Assert.assertEquals(entity.getMedioDePago(), foundEntity.getMedioDePago());
    }
    
    /**
     * Método que prueba la funcionalidad de consultar todas las tuplas
     * de la tabla RecompensaEntity 
     */
    @Test
    public void getRecompensasTest(){
        List<RecompensaEntity> xs = persistence.findAll();
        Assert.assertEquals(listaPrueba.size(), xs.size());
        for(RecompensaEntity r : listaPrueba){
            boolean found = false;
            for(RecompensaEntity x : xs){
                if(r.getId().equals(x.getId()));
                found = true;
            }
            Assert.assertTrue(found);
        }
    }
    
    /**
     * Método que prueba la funcionalidad de actualizar los
     * valores de una recompensa
     */
    @Test
    public void updateRecompensaTest(){
        PodamFactory factory = new PodamFactoryImpl();
        RecompensaEntity entity = listaPrueba.get(8);
        RecompensaEntity newEntity = factory.manufacturePojo(RecompensaEntity.class);
        newEntity.setId(entity.getId());
        persistence.update(newEntity);
        
        RecompensaEntity foundEntity = em.find(RecompensaEntity.class, entity.getId());
        Assert.assertEquals(newEntity.getEstado(), foundEntity.getEstado());
        Assert.assertEquals(newEntity.getValor(), foundEntity.getValor());
        Assert.assertEquals(newEntity.getMedioDePago(), foundEntity.getMedioDePago());
        
    }
    
    /**
     * Método que prueba la eliminación de una recompensa
     */
    @Test
    public void deleteRecompensaTest(){
        RecompensaEntity entity = listaPrueba.get(5);
        persistence.delete(entity.getId());
        RecompensaEntity deleted = em.find(RecompensaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
}

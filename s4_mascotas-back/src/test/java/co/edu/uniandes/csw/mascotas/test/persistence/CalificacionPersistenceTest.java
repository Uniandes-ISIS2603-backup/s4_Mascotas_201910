/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.test.persistence;

import co.edu.uniandes.csw.mascotas.entities.CalificacionEntity;
import co.edu.uniandes.csw.mascotas.entities.MascotaExtraviadaEntity;

import co.edu.uniandes.csw.mascotas.persistence.CalificacionPersistence;
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
public class CalificacionPersistenceTest {
    
    /**
     * La clase de persistencia sobre la cual se realizan las pruebas
     */
    @Inject
    private CalificacionPersistence cp;
    /**
     * Manejador de persistencia para este conjunto de pruebas
     */
    @PersistenceContext
    private EntityManager em;
    
     /**
     * Manejador de transacciones
     */
    @Inject
    private UserTransaction utx;
    /**
     * Lista de procesos de mascota extraviada sobre la cual se realizan algunas pruebas
     */
    private List<CalificacionEntity> listaPrueba = new ArrayList<>();
    
    @Deployment
    public static JavaArchive deploymento(){
      return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CalificacionEntity.class.getPackage())
                .addPackage(CalificacionPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
     /**
     * Inicializa la lista de prueba
     */
    private void inicializacionListaPrueba(){
        PodamFactory factory = new PodamFactoryImpl();
        for(int i = 0; i < 10; i++){
            CalificacionEntity e = factory.manufacturePojo(CalificacionEntity.class);
            em.persist(e);
            listaPrueba.add(e);
        }
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
     * Limpia las tablas que están implicadas en la prueba.
     */
    private void clearData() {
        em.createQuery("delete from MascotaExtraviadaEntity").executeUpdate();
    }
   /**
    * cerifica que el método create funcione correctamente
    */
     @Test
    public void createCalificacionTest(){
        
        
        PodamFactory factory = new PodamFactoryImpl();
        CalificacionEntity entity = factory.manufacturePojo(CalificacionEntity.class);
        
        CalificacionEntity ce = cp.create(entity);
        Assert.assertNotNull(ce);
        CalificacionEntity e = em.find(CalificacionEntity.class , ce.getId());
        Assert.assertEquals(entity.getCalificacion() + "" , e.getCalificacion() + "" );
        Assert.assertEquals(entity.getComentario(), e.getComentario() );
    }
    /**
     * verifica que el método update funcione correctamente
     */
    @Test
    public void updateCalificacionTest(){
        
        PodamFactory factory = new PodamFactoryImpl();
        CalificacionEntity entity = listaPrueba.get(8);
        CalificacionEntity newEntity = factory.manufacturePojo(CalificacionEntity.class);
        newEntity.setId(entity.getId());
        cp.update(newEntity);
        
        CalificacionEntity resultado = em.find(CalificacionEntity.class, entity.getId());
        Assert.assertEquals(resultado.getCalificacion(), newEntity.getCalificacion());
        Assert.assertEquals(resultado.getComentario(), newEntity.getComentario());
    }
    /**
     * verifica que el método delete funcione correctamente
     */
    @Test
    public void deleteCalificacionTest(){
        CalificacionEntity cal = listaPrueba.get(5);
        cp.delete(cal.getId());
        Assert.assertNull(em.find(CalificacionEntity.class, cal.getId()));
    }
}
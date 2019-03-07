/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.test.persistence;

import co.edu.uniandes.csw.mascotas.entities.EventoEntity;
import co.edu.uniandes.csw.mascotas.persistence.EventoPersistence;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.Assert;
import org.junit.Before;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author Daniela Gonzalez
 */

@RunWith(Arquillian.class)
public class EventoPersistenteTest {
    
    @Inject
    private EventoPersistence ep;
    
    @PersistenceContext
    private EntityManager em;
    
     /**
     * Lista de eventos sobre la cual se realizan algunas pruebas
     */
    private List<EventoEntity> listaPrueba = new ArrayList<EventoEntity>();
    
    /**
     * Variable para marcar las transacciones del em anterior cuando se
     * crean/borran datos para las pruebas.
     */
    @Inject
    private UserTransaction utx;
    
    
    /**
     *
     * @return Devuelve el jar que Arquillian va a desplegar en el Glassfish
     * embebido. El jar contiene las clases de Evento, el descriptor de la
     * base de datos y el archivo beans.xml para resolver la inyección de
     * dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(EventoEntity.class.getPackage())
                .addPackage(EventoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
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
     *
     *
     */
    private void clearData() {
        em.createQuery("delete from EventoEntity").executeUpdate();
    }
    
    /**
     * Inicializa la lista de prueba
     */
    private void inicializacionListaPrueba(){
        PodamFactory factory = new PodamFactoryImpl();
        for(int i = 0; i < 3; i++){
            EventoEntity e = factory.manufacturePojo(EventoEntity.class);
            em.persist(e);
            listaPrueba.add(e);
        }
    }
    
     /**
     * Prueba para crear un Evento.
     *
     *
     */
    @Test
    public void createEventoTest() {
        
        PodamFactory factory = new PodamFactoryImpl();
        EventoEntity newEntity = factory.manufacturePojo(EventoEntity.class);
        EventoEntity ee = ep.create(newEntity);

        Assert.assertNotNull(ee);

        EventoEntity entity = em.find(EventoEntity.class, ee.getId());

        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
    }
    
    /**
     * Prueba para consultar la lista de eventos.
     */
    //@Test
    //public void getEventosTest() {
      //  List<EventoEntity> list = ep.findAll();
        //Assert.assertEquals(listaPrueba.size(), list.size());
       // for (EventoEntity ent : listaPrueba) {
           //  boolean found = false;
          //  for (EventoEntity entity : list) {
             //   if (ent.getId().equals(entity.getId())) {
             //       found = true;
             //  }
           // }
            //Assert.assertTrue(found);
      //  }
    //}
    
    
     /**
     * Prueba para consultar un Evento.
     */
    @Test
    public void getEventoTest() {
        EventoEntity entity = listaPrueba.get(0);
        EventoEntity newEntity = ep.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getNombre(), newEntity.getNombre());
    }
    
    /**
     * Prueba para actualizar un evento.
     */
    @Test
    public void actualizarEventoTest() {
        EventoEntity entity = listaPrueba.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        EventoEntity newEntity = factory.manufacturePojo(EventoEntity.class);

        newEntity.setId(entity.getId());

        ep.actualizarEvento(newEntity);

        EventoEntity resp = em.find(EventoEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getNombre(), resp.getNombre());
    }
    
    /**
     * Prueba para eliminar un evento.
     */
    @Test
    public void deleteEventoTest() {
        EventoEntity entity = listaPrueba.get(0);
        ep.delete(entity.getId());
        EventoEntity deleted = em.find(EventoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}
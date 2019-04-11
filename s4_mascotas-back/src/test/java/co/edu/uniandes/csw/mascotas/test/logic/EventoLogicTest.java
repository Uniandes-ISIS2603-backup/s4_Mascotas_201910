/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.test.logic;

import co.edu.uniandes.csw.mascotas.ejb.EventoLogic;
import co.edu.uniandes.csw.mascotas.entities.EventoEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.mascotas.persistence.EventoPersistence;
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
 * @author Daniela Gonzalez
 */

@RunWith(Arquillian.class)
public class EventoLogicTest {
    
    
    private PodamFactory factory = new PodamFactoryImpl();
     
    /**
     * Inyección de la dependencia a la clase EventoLogic cuyos métodos se
     * van a probar.
     */
    @Inject
    private EventoLogic eventoLogic;
    
    /**
     * Contexto de Persistencia que se va a utilizar para acceder a la Base de
     * datos por fuera de los métodos que se están probando.
     */
    @PersistenceContext
    private EntityManager em;
    
    
     /**
     * Variable para marcar las transacciones del em anterior cuando se
     * crean/borran datos para las pruebas.
     */
    @Inject
    private UserTransaction utx;
    
    /**
     * Lista que tiene los datos de prueba.
     */
    private List<EventoEntity> data = new ArrayList<EventoEntity>();
    
     /**
     *
     * @return Devuelve el jar que Arquillian va a desplegar en el Glassfish
     * embebido. El jar contiene las clases de Articulo, el descriptor de la
     * base de datos y el archivo beans.xml para resolver la inyección de
     * dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(EventoEntity.class.getPackage())
                .addPackage(EventoLogic.class.getPackage())
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
            clearData();
            insertData();
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
        em.createQuery("delete from EventoEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            EventoEntity entity = factory.manufacturePojo(EventoEntity.class);

            em.persist(entity);
            data.add(entity);

        }
    }

    @Test
    public void crearEventoTest() throws BusinessLogicException {
        EventoEntity newEntity = factory.manufacturePojo(EventoEntity.class);
        EventoEntity result = eventoLogic.crearEvento(newEntity);
        Assert.assertNotNull(result);
        EventoEntity entity = em.find(EventoEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
    }
    
    /**
     * Prueba para crear un evento con el mismo nombre de un evento que ya
     * existe.
     *
     * @throws BusinessLogicException
     */
 // @Test(expected = BusinessLogicException.class)
   // public void createEventoConMismoNombreTest() throws BusinessLogicException {
     //   EventoEntity newEntity = factory.manufacturePojo(EventoEntity.class);
     //   newEntity.setNombre(data.get(0).getNombre());
     //   eventoLogic.crearEvento(newEntity);
    //}
    
    /**
     * Prueba para consultar la lista de eventos.
     */
    @Test
    public void getEventosTest() {
        List<EventoEntity> list = eventoLogic.encontrarTodosEventos();
        Assert.assertEquals(data.size(), list.size());
        for (EventoEntity entity : list) {
            boolean found = false;
            for (EventoEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    /**
     * Prueba para consultar un articulo.
     */
    @Test
    public void getEventoTest() {
        EventoEntity entity = data.get(0);
        EventoEntity resultEntity = eventoLogic.encontrarEventoPorId(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getNombre(), resultEntity.getNombre());
    }
    
    /**
     * Prueba para actualizar un evento.
     * @throws BusinessLogicException 
     */
    @Test
    public void updateEventoTest() throws BusinessLogicException {
        EventoEntity entity = data.get(0);
        EventoEntity pojoEntity = factory.manufacturePojo(EventoEntity.class);
        pojoEntity.setId(entity.getId());
        eventoLogic.actualizarEvento(pojoEntity.getId(), pojoEntity);
        EventoEntity resp = em.find(EventoEntity.class, entity.getId());
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getNombre(), resp.getNombre());
    }
    
     /**
     * Actualizar un articulo con el mismo titulo de un evento que ya
     * existe.
     *
     * @throws BusinessLogicException
     */
 //   @Test(expected = BusinessLogicException.class)
   // public void updateEventoConMismoNombreTest() throws BusinessLogicException {
     //   EventoEntity entity = data.get(0);
       // entity.setNombre(data.get(1).getNombre());
        //eventoLogic.actualizarEvento(entity.getId(), entity);
    //}
    
     /**
     * Prueba para eliminar un evento.
     */
    @Test
    public void deleteEventoTest() {
        EventoEntity entity = data.get(2);
        eventoLogic.eliminarEvento(entity.getId());
        EventoEntity deleted = em.find(EventoEntity.class, entity.getId());
        Assert.assertNull(deleted);
     }
    
}

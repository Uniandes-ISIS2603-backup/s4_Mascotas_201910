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
    private List<EventoEntity> listaPrueba = new ArrayList<>();
    
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
        for(int i = 0; i < 10; i++){
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
    
    @Test
    public void actualizarNombreTest()
    {
        PodamFactory factory = new PodamFactoryImpl();
        EventoEntity newEntity = factory.manufacturePojo(EventoEntity.class);
        EventoEntity ee = ep.create(newEntity);
        
        Assert.assertNotNull(ee);
        
        String nuevoNombre = "abcd";
        
        EventoEntity entidadActualizada = ep.actualizarNombre(ee.getId(), nuevoNombre);
        
        ee = em.find( EventoEntity.class, ee.getId());
        Assert.assertEquals(entidadActualizada.getNombre(), ee.getNombre());
    }
    
     @Test
    public void actualizarDescripcionTest()
    {
        PodamFactory factory = new PodamFactoryImpl();
        EventoEntity newEntity = factory.manufacturePojo(EventoEntity.class);
        EventoEntity ee = ep.create(newEntity);
        
        Assert.assertNotNull(ee);
        
        String nuevaDescripcion = "abcd";
        
        EventoEntity entidadActualizada = ep.actualizarDescripcion(ee.getId(), nuevaDescripcion);
        
        ee = em.find( EventoEntity.class, ee.getId());
        Assert.assertEquals(entidadActualizada.getDescripcion(), ee.getDescripcion());
    }
    
    @Test
    public void actualizarImagenTest()
    {
        PodamFactory factory = new PodamFactoryImpl();
        EventoEntity newEntity = factory.manufacturePojo(EventoEntity.class);
        EventoEntity ee = ep.create(newEntity);
        
        Assert.assertNotNull(ee);
        
        String nuevaImagen = "abcd";
        
        EventoEntity entidadActualizada = ep.actualizarImagen(ee.getId(), nuevaImagen);
        
        ee = em.find( EventoEntity.class, ee.getId());
        Assert.assertEquals(entidadActualizada.getImagen(), ee.getImagen());
    }
    
    @Test
    public void actualizarFechaInicioTest()
    {
        PodamFactory factory = new PodamFactoryImpl();
        EventoEntity newEntity = factory.manufacturePojo(EventoEntity.class);
        EventoEntity ee = ep.create(newEntity);
        
        Assert.assertNotNull(ee);
        
        Date fecha = new Date();
        
        EventoEntity entidadActualizada = ep.actualizarFechaInicio(ee.getId(), fecha);
        
        ee = em.find( EventoEntity.class, ee.getId());
        Assert.assertEquals(entidadActualizada.getFechaInicio(), ee.getFechaInicio());
    }
    
    @Test
    public void actualizarFechaFinTest()
    {
        PodamFactory factory = new PodamFactoryImpl();
        EventoEntity newEntity = factory.manufacturePojo(EventoEntity.class);
        EventoEntity ee = ep.create(newEntity);
        
        Assert.assertNotNull(ee);
        
        Date fecha = new Date();
        
        EventoEntity entidadActualizada = ep.actualizarFechaFin(ee.getId(), fecha);
        
        ee = em.find( EventoEntity.class, ee.getId());
        Assert.assertEquals(entidadActualizada.getFechaFin(), ee.getFechaFin());
    }
}
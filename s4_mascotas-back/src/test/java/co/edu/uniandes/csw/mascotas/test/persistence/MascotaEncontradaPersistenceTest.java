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

    /**
     * Clase de persistencia a probar
     */
    @Inject
    private MascotaEncontradaPersistence persistence;

    /**
     * Manejador de persistencia estándar para hacer las comparaciones
     */
    @PersistenceContext
    protected EntityManager em;
    
    /**
     * Lista con algunos datos que se usarán para las pruebas
     */
    private List<MascotaEncontradaEntity> listaPrueba = new ArrayList<>();
    
    /**
     * Trasacción
     */
    @Inject
    private UserTransaction utx;

    /**
     * Método de despliegue
     * @return 
     */
    @Deployment
    public static JavaArchive createDeployment() {

        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(MascotaEncontradaEntity.class.getPackage())
                .addPackage(MascotaEncontradaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    /**
     * Crea una lista con 10 elementos que se utilizará en las pruebas
     */
    private void inicializacionListaPrueba(){
        PodamFactory factory = new PodamFactoryImpl();
        for(int i = 0; i < 10; i++){
            MascotaEncontradaEntity e = factory.manufacturePojo(MascotaEncontradaEntity.class);
            em.persist(e);
            listaPrueba.add(e);
        }
    }
    
    /**
     * Borra la información de las pruebas de la base de datos
     */
    private void clearData() 
    {
        em.createQuery("delete from MascotaEncontradaEntity").executeUpdate();
    }
    
    /**
     * Configuración de las pruebas
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
     * Prueba para el método de crear un proceso de mascota encontrada
     */
    @Test
    public void createMascotaEncontradaTest() {

        PodamFactory factory = new PodamFactoryImpl();
        
        MascotaEncontradaEntity newEntity = factory.manufacturePojo(MascotaEncontradaEntity.class);
        MascotaEncontradaEntity mascotaE = persistence.create(newEntity);

        Assert.assertNotNull(mascotaE);

        MascotaEncontradaEntity entityComp = em.find(MascotaEncontradaEntity.class, mascotaE.getId());

       // Assert.assertEquals(newEntity.getDescripcion(), entityComp.getDescripcion());
        Assert.assertEquals(newEntity.getEstado(), entityComp.getEstado());
        Assert.assertEquals(newEntity.getUbicacion(), entityComp.getUbicacion());

    }
    
    /**
     * Prueba para el método de eliminar un proceso de mascota encontrada por su id
     */
    @Test
    public void deleteMascotaEncontradaTest()
    {
        MascotaEncontradaEntity entityP = listaPrueba.get(7);
        persistence.delete(entityP.getId());
        MascotaEncontradaEntity deleted = em.find(MascotaEncontradaEntity.class, entityP.getId());
        Assert.assertNull(deleted);
    }
    
    /***
     * Prueba para el método de encontrar un proceso de mascota encontrada por su id
     */
    @Test
    public void findTest()
    {
        PodamFactory factory = new PodamFactoryImpl();
        MascotaEncontradaEntity e = factory.manufacturePojo(MascotaEncontradaEntity.class);
        MascotaEncontradaEntity mascota = persistence.create(e);
        
        Assert.assertNotNull(e);
        
        MascotaEncontradaEntity m = persistence.find(mascota.getId());
        MascotaEncontradaEntity m2 = em.find(MascotaEncontradaEntity.class, mascota);
        
         Assert.assertEquals(m2.getId(), mascota.getId());
         Assert.assertEquals(m2.getEstado(), mascota.getEstado());
         Assert.assertEquals(m2.getUbicacion(), mascota.getUbicacion());
         Assert.assertEquals(m2.getFechaInicializacion(), mascota.getFechaInicializacion());
    }

    /**
     * Prueba para el método de buscar todos los elementos de la lista
     */
    public void findAllTest( )
    {
        for(MascotaEncontradaEntity m : listaPrueba )
        {
            MascotaEncontradaEntity mascota = persistence.find(m.getId());
            Assert.assertEquals(m.getId(), mascota.getId());
            Assert.assertEquals(m.getEstado(), mascota.getEstado());
            Assert.assertEquals(m.getUbicacion(), mascota.getUbicacion());
            Assert.assertEquals(m.getFechaInicializacion(), mascota.getFechaInicializacion());
        }
    }
}

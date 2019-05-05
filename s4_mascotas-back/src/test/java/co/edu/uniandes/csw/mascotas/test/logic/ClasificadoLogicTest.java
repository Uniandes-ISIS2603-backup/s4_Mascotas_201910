/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.test.logic;

import co.edu.uniandes.csw.mascotas.ejb.ClasificadoLogic;
import co.edu.uniandes.csw.mascotas.entities.ClasificadoEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.mascotas.persistence.ClasificadoPersistence;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
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
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author Daniela Gonzalez
 */

@RunWith(Arquillian.class)
public class ClasificadoLogicTest {
    
     private PodamFactory factory = new PodamFactoryImpl();
    
     /**
     * Inyección de la dependencia a la clase ClasificadoLogic cuyos métodos se
     * van a probar.
     */
    @Inject
    private ClasificadoLogic clasificadoLogic;
    
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
    private List<ClasificadoEntity> data = new ArrayList<ClasificadoEntity>();
    
    
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
                .addPackage(ClasificadoEntity.class.getPackage())
                .addPackage(ClasificadoLogic.class.getPackage())
                .addPackage(ClasificadoPersistence.class.getPackage())
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
        em.createQuery("delete from ClasificadoEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            ClasificadoEntity entity = factory.manufacturePojo(ClasificadoEntity.class);
            em.persist(entity);
            data.add(entity);

        }
    }

    @Test
    public void crearClasificadoTest() throws BusinessLogicException {
        ClasificadoEntity newEntity = factory.manufacturePojo(ClasificadoEntity.class);
        ClasificadoEntity result = clasificadoLogic.createClasificado(newEntity);
        Assert.assertNotNull(result);
        ClasificadoEntity entity = em.find(ClasificadoEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
    }
    
     /**
     * Prueba para crear un clasificado sin nombre
     *
     * @throws BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createClasificadoSinNombreTest() throws BusinessLogicException {
         ClasificadoEntity newEntity = factory.manufacturePojo(ClasificadoEntity.class);
         newEntity.setNombre(null);
         clasificadoLogic.createClasificado(newEntity);
    }
    
    /**
     * Prueba para crear un clasificado sin contenido
     *
     * @throws BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createClasificadoSinContenidoTest() throws BusinessLogicException {
         ClasificadoEntity newEntity = factory.manufacturePojo(ClasificadoEntity.class);
         newEntity.setContenido(null);
         clasificadoLogic.createClasificado(newEntity);
    }
    
    
     /**
     * Prueba para consultar la lista de clasificados.
     */
    @Test
    public void getClasificadosTest() {
        List<ClasificadoEntity> list = clasificadoLogic.getClasificados();
        Assert.assertEquals(data.size(), list.size());
        for (ClasificadoEntity entity : list) {
            boolean found = false;
            for (ClasificadoEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    /**
     * Prueba para consultar un clasificado.
     */
    @Test
    public void getClasificadoTest() {
        ClasificadoEntity entity = data.get(0);
        ClasificadoEntity resultEntity = clasificadoLogic.buscarClasificadoPorId(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getNombre(), resultEntity.getNombre());
    }
    
     /**
     * Prueba para actualizar un clasificado.
     * @throws BusinessLogicException 
     */
    @Test
    public void updateClasificadoTest() throws BusinessLogicException {
        ClasificadoEntity entity = data.get(0);
        ClasificadoEntity pojoEntity = factory.manufacturePojo(ClasificadoEntity.class);
        pojoEntity.setId(entity.getId());
        clasificadoLogic.actualizarClasificado(pojoEntity.getId(), pojoEntity);
        ClasificadoEntity resp = em.find(ClasificadoEntity.class, entity.getId());
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getNombre(), resp.getNombre());
    }

    /**
     * Prueba para actualizar un clasificado sin titulo
     *
     * @throws BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void updateClasificadoSinNombreTest() throws BusinessLogicException {
         ClasificadoEntity entity = data.get(0);
         entity.setNombre(null);
         clasificadoLogic.actualizarClasificado(data.get(0).getId(), entity);
    }
    
    /**
     * Prueba para actualizar un clasificado sin contenido
     *
     * @throws BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void updateClasificadoSinContenidoTest() throws BusinessLogicException {
         ClasificadoEntity entity = data.get(0);
         entity.setContenido(null);
         clasificadoLogic.actualizarClasificado(data.get(0).getId(), entity);
    }
    
    /**
     * Prueba para eliminar un clasificado.
     */
    @Test
    public void deleteClasificadoTest() {
        ClasificadoEntity entity = data.get(2);
        clasificadoLogic.eliminarClasificado(entity.getId());
        ClasificadoEntity deleted = em.find(ClasificadoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
    
}

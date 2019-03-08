/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.test.logic;

import co.edu.uniandes.csw.mascotas.ejb.ArticuloLogic;
import co.edu.uniandes.csw.mascotas.entities.ArticuloEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.mascotas.persistence.ArticuloPersistence;
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
public class ArticuloLogicTest {
    
     private PodamFactory factory = new PodamFactoryImpl();
     
    /**
     * Inyección de la dependencia a la clase ArticuloLogic cuyos métodos se
     * van a probar.
     */
    @Inject
    private ArticuloLogic articuloLogic;
    
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
    private List<ArticuloEntity> data = new ArrayList<ArticuloEntity>();
    
    
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
                .addPackage(ArticuloEntity.class.getPackage())
                .addPackage(ArticuloLogic.class.getPackage())
                .addPackage(ArticuloPersistence.class.getPackage())
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
        em.createQuery("delete from ArticuloEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            ArticuloEntity entity = factory.manufacturePojo(ArticuloEntity.class);
            entity.setTema(ArticuloLogic.CUIDADO);
            em.persist(entity);
            data.add(entity);

        }
    }

    @Test
    public void crearArticuloTest() throws BusinessLogicException {
        ArticuloEntity newEntity = factory.manufacturePojo(ArticuloEntity.class);
        newEntity.setTema(ArticuloLogic.CUIDADO);
        ArticuloEntity result = articuloLogic.crearArticulo(newEntity);
        Assert.assertNotNull(result);
        ArticuloEntity entity = em.find(ArticuloEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getTitulo(), entity.getTitulo());
    }
    
     /**
     * Prueba para crear un articulo con el mismo titulo de un articulo que ya
     * existe.
     *
     * @throws BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createArticuloConMismoTituloTest() throws BusinessLogicException {
        ArticuloEntity newEntity = factory.manufacturePojo(ArticuloEntity.class);
        newEntity.setTema(ArticuloLogic.CUIDADO);
        newEntity.setTitulo(data.get(0).getTitulo());
        articuloLogic.crearArticulo(newEntity);
    }
    
     /**
     * Prueba para consultar la lista de articulos.
     */
    @Test
    public void getArticulosTest() {
        List<ArticuloEntity> list = articuloLogic.encontrarTodosArticulos();
        Assert.assertEquals(data.size(), list.size());
        for (ArticuloEntity entity : list) {
            boolean found = false;
            for (ArticuloEntity storedEntity : data) {
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
    public void getArticuloTest() {
        ArticuloEntity entity = data.get(0);
        ArticuloEntity resultEntity = articuloLogic.encontrarArticuloPorId(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getTitulo(), resultEntity.getTitulo());
    }
    
     /**
     * Prueba para actualizar un articulo.
     * @throws BusinessLogicException 
     */
    @Test
    public void updateArticuloTest() throws BusinessLogicException {
        ArticuloEntity entity = data.get(0);
        ArticuloEntity pojoEntity = factory.manufacturePojo(ArticuloEntity.class);
        pojoEntity.setId(entity.getId());
        pojoEntity.setTema(ArticuloLogic.CUIDADO);
        articuloLogic.actualizarArticulo(pojoEntity.getId(), pojoEntity);
        ArticuloEntity resp = em.find(ArticuloEntity.class, entity.getId());
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getTitulo(), resp.getTitulo());
    }
 
    /**
     * Actualizar un articulo con el mismo titulo de un articulo que ya
     * existe.
     *
     * @throws BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void updateArticuloConMismoTituloTest() throws BusinessLogicException {
        ArticuloEntity entity = data.get(0);
        entity.setTitulo(data.get(1).getTitulo());
        articuloLogic.actualizarArticulo(entity.getId(), entity);
    }
    
    /**
     * Prueba para eliminar un articulo.
     */
    @Test
    public void deleteArticuloTest() {
        ArticuloEntity entity = data.get(2);
        articuloLogic.eliminarArticulo(entity.getId());
        ArticuloEntity deleted = em.find(ArticuloEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.test.persistence;

import co.edu.uniandes.csw.mascotas.entities.ArticuloEntity;
import co.edu.uniandes.csw.mascotas.entities.UsuarioEntity;
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
public class ArticuloPersistenceTest {
    
    @Inject
    private ArticuloPersistence ap;
    
    @PersistenceContext
    private EntityManager em;
    
    /**
     * Lista de eventos sobre la cual se realizan algunas pruebas
     */
    private List<ArticuloEntity> listaPrueba = new ArrayList<>();
    
    /**
     * Variable para marcar las transacciones del em anterior cuando se
     * crean/borran datos para las pruebas.
     */
    @Inject
    private UserTransaction utx;
    
    
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
        em.createQuery("delete from ArticuloEntity").executeUpdate();
    }
    
    /**
     * Inicializa la lista de prueba
     */
    private void inicializacionListaPrueba(){
        PodamFactory factory = new PodamFactoryImpl();
        for(int i = 0; i < 3; i++){
            ArticuloEntity e = factory.manufacturePojo(ArticuloEntity.class);
            em.persist(e);
            listaPrueba.add(e);
        }
    }
    
      /**
     * Prueba para crear un Articulo.
     *
     *
     */
    @Test
    public void createArticuloTest() {
        
        PodamFactory factory = new PodamFactoryImpl();
        ArticuloEntity newEntity = factory.manufacturePojo(ArticuloEntity.class);
        ArticuloEntity ea = ap.create(newEntity);

        Assert.assertNotNull(ea);

        ArticuloEntity entity = em.find(ArticuloEntity.class, ea.getId());

        Assert.assertEquals(newEntity.getTitulo(), entity.getTitulo());
    }
    
    /**
     * Prueba para consultar la lista de articulos.
     */
    @Test
    public void getArticulosTest() {
        List<ArticuloEntity> list = ap.findAll();
        Assert.assertEquals(listaPrueba.size(), list.size());
        for (ArticuloEntity ent : listaPrueba) {
           boolean found = false;
             for (ArticuloEntity entity : list) {
                 if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
             }
            Assert.assertTrue(found);
         }
    }
    
    
     /**
     * Prueba para consultar un Articulo.
     */
    @Test
    public void getArticuloTest() {
        ArticuloEntity entity = listaPrueba.get(0);
        ArticuloEntity newEntity = ap.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getTitulo(), newEntity.getTitulo());
    }
    
    /**
     * Prueba para actualizar un Articulo.
     */
    @Test
    public void actualizarArticuloTest() {
        ArticuloEntity entity = listaPrueba.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        ArticuloEntity newEntity = factory.manufacturePojo(ArticuloEntity.class);

        newEntity.setId(entity.getId());

        ap.actualizarArticulo(newEntity);

        ArticuloEntity resp = em.find(ArticuloEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getTitulo(), resp.getTitulo());
    }
    
    /**
     * Prueba para eliminar un articulo.
     */
    @Test
    public void deleteArticuloTest() {
        ArticuloEntity entity = listaPrueba.get(0);
        ap.delete(entity.getId());
        ArticuloEntity deleted = em.find(ArticuloEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
   
}
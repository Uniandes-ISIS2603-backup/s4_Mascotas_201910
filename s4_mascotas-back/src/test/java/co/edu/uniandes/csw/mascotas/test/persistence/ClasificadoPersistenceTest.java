/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.test.persistence;

import co.edu.uniandes.csw.mascotas.entities.ClasificadoEntity;
import co.edu.uniandes.csw.mascotas.persistence.ClasificadoPersistence;
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
 * @author Johan E. Vivas Sepulveda (je.vivas)
 */

@RunWith(Arquillian.class)
public class ClasificadoPersistenceTest {
    
    @Inject
    private ClasificadoPersistence cp;
    
    @PersistenceContext
    protected EntityManager em;
    
      /**
     * Lista de clasificadps sobre la cual se realizan algunas pruebas
     */
    private List<ClasificadoEntity> listaPrueba = new ArrayList<>();
    
    /**
     * Variable para marcar las transacciones del em anterior cuando se
     * crean/borran datos para las pruebas.
     */
    @Inject
    private UserTransaction utx;
    
    @Deployment
    public static JavaArchive createDeployment(){
        
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ClasificadoEntity.class.getPackage())
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
        em.createQuery("delete from ClasificadoEntity").executeUpdate();
    }
    
    /**
     * Inicializa la lista de prueba
     */
    private void inicializacionListaPrueba(){
        PodamFactory factory = new PodamFactoryImpl();
        for(int i = 0; i < 3; i++){
            ClasificadoEntity e = factory.manufacturePojo(ClasificadoEntity.class);
            em.persist(e);
            listaPrueba.add(e);
        }
    }
    
    @Test
    public void createClasificadoTest(){
        
        PodamFactory factory = new PodamFactoryImpl();
        ClasificadoEntity newEntity = factory.manufacturePojo(ClasificadoEntity.class);
        ClasificadoEntity ce = cp.create(newEntity);
        
        Assert.assertNotNull(ce);
        
        ClasificadoEntity entityC = em.find(ClasificadoEntity.class, ce.getId());
        
        Assert.assertEquals(newEntity.getNombre(), entityC.getNombre());
    }
    
     /**
     * Prueba para consultar la lista de clasificados.
     */
    @Test
    public void getClasificadosTest() {
        List<ClasificadoEntity> list = cp.findAll();
        Assert.assertEquals(listaPrueba.size(), list.size());
        for (ClasificadoEntity ent : listaPrueba) {
           boolean found = false;
             for (ClasificadoEntity entity : list) {
                 if (ent.getId().equals(entity.getId())) {
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
        ClasificadoEntity entity = listaPrueba.get(0);
        ClasificadoEntity newEntity = cp.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getNombre(), newEntity.getNombre());
    }
    
    /**
     * Prueba para actualizar un Articulo.
     */
    @Test
    public void actualizarClasificadoTest() {
        ClasificadoEntity entity = listaPrueba.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        ClasificadoEntity newEntity = factory.manufacturePojo(ClasificadoEntity.class);

        newEntity.setId(entity.getId());

        cp.actualizarClasificado(newEntity);

        ClasificadoEntity resp = em.find(ClasificadoEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getNombre(), resp.getNombre());
    }
    
    /**
     * Prueba para eliminar un clasificado.
     */
    @Test
    public void deleteClasificadoTest() {
        ClasificadoEntity entity = listaPrueba.get(0);
        cp.delete(entity.getId());
        ClasificadoEntity deleted = em.find(ClasificadoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}

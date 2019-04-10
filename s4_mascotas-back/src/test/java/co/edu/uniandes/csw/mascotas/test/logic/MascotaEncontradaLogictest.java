/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.test.logic;

import co.edu.uniandes.csw.mascotas.ejb.MascotaEncontradaLogic;
import co.edu.uniandes.csw.mascotas.entities.MascotaEncontradaEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.mascotas.persistence.MascotaEncontradaPersistence;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
import java.util.ArrayList;
import java.util.List;
import javax.transaction.UserTransaction;

/**
 *
 * @author Johan E. Vivas Sepulveda (je.vivas)
 */

@RunWith(Arquillian.class)
public class MascotaEncontradaLogictest {
    
    @Inject
    private MascotaEncontradaLogic logica;
    
    @PersistenceContext
    private EntityManager em;
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    private List<MascotaEncontradaEntity> listaPruebaMascotaEncontrada = new ArrayList<>();
    
    @Inject
    private UserTransaction utx;
    
    @Deployment
    public static JavaArchive deployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(MascotaEncontradaEntity.class.getPackage())
                .addPackage(MascotaEncontradaLogic.class.getPackage())
                .addPackage(MascotaEncontradaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    private void inicializacionListaPrueba(){
        for (int i = 0; i < 10; i++) {
            MascotaEncontradaEntity entity = factory.manufacturePojo(MascotaEncontradaEntity.class);
            em.persist(entity);
            listaPruebaMascotaEncontrada.add(entity);
        }
    }
    
    private void clearData(){
        em.createQuery("delete from MascotaEncontradaEntity").executeUpdate();
    }
    
    @Before
    public void configTest(){
        try{
            utx.begin();
            em.joinTransaction();
            clearData();
            inicializacionListaPrueba();
            utx.commit();
        }
        catch (Exception e){
            e.printStackTrace();
            try{
                utx.rollback();
            }
            catch(Exception e1){
                e1.printStackTrace();
            }
        }
        
    }
    
    @Test
    public void createMascotaEncontradaTest()throws Exception{
        MascotaEncontradaEntity mascotaEntity = factory.manufacturePojo(MascotaEncontradaEntity.class);
        mascotaEntity.setEstado(MascotaEncontradaEntity.PENDIENTE);
        
        MascotaEncontradaEntity result = logica.createMascotaEncontrada(mascotaEntity);
        Assert.assertNotNull(result);
        
        MascotaEncontradaEntity foundEntity = em.find(MascotaEncontradaEntity.class, mascotaEntity.getId());
        //Assert.assertEquals(result.getDescripcion(), foundEntity.getDescripcion());
        Assert.assertEquals(result.getEstado(), foundEntity.getEstado());
        Assert.assertEquals(result.getUbicacion(), foundEntity.getUbicacion());
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createMascotaEncontradaConEstadoInvalidoTest() throws Exception{
        MascotaEncontradaEntity entity = factory.manufacturePojo(MascotaEncontradaEntity.class);
        
        entity.setEstado(MascotaEncontradaEntity.ENTREGADA);
        logica.createMascotaEncontrada(entity);
    }
    
    @Test
    public void deleteProcesoMascotaEncontrada() throws Exception{
        MascotaEncontradaEntity entity = factory.manufacturePojo(MascotaEncontradaEntity.class);
        
        entity.setEstado(MascotaEncontradaEntity.PENDIENTE);
        logica.deleteProcesoMascotaEncontrada(entity.getId());
    }
    
}

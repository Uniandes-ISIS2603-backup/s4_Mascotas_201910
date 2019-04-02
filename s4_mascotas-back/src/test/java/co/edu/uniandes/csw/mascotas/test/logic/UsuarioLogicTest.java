/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.test.logic;

import co.edu.uniandes.csw.mascotas.ejb.UsuarioLogic;
import co.edu.uniandes.csw.mascotas.entities.UsuarioEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;

import co.edu.uniandes.csw.mascotas.persistence.UsuarioPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.junit.runner.RunWith;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import org.jboss.arquillian.container.test.api.Deployment;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Maria Ana Ortiz(ma.ortiz1)
 */
@RunWith(Arquillian.class)
public class UsuarioLogicTest {

    @Inject
    private UsuarioLogic logic;

    private PodamFactory factory = new PodamFactoryImpl();

    @PersistenceContext
    private EntityManager em;

    private List<UsuarioEntity> listaPruebaUsuario = new ArrayList<>();

    @Inject
    private UserTransaction utx;

    @Deployment
    public static JavaArchive deployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(UsuarioEntity.class.getPackage())
                .addPackage(UsuarioLogic.class.getPackage())
                .addPackage(UsuarioPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    private void inicializacionListaPrueba() {
        for (int i = 0; i < 1; i++) {
            UsuarioEntity us = factory.manufacturePojo(UsuarioEntity.class);
            em.persist(us);
            listaPruebaUsuario.add(us);
        }

    }

    private void clearData() {
        em.createQuery("delete from UsuarioEntity").executeUpdate();
        

    }

    @Before
    public void configTest() {

        try {
            utx.begin();
            em.joinTransaction();
            inicializacionListaPrueba();
            clearData();
            
        } catch (Exception e) {
            e.printStackTrace();

            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    @Test
    public void createUsuario() throws Exception {
        UsuarioEntity entity = factory.manufacturePojo(UsuarioEntity.class);
        UsuarioEntity resu = logic.crearUsuario(entity);
        Assert.assertNotNull(resu);
        UsuarioEntity fu = em.find(UsuarioEntity.class, entity.getId());
        Assert.assertEquals(resu.getUsuario(), fu.getUsuario());
        Assert.assertEquals(resu.getCorreo(), fu.getCorreo());

    }
    
    @Test(expected=BusinessLogicException.class)
    public void createUsuarioRepetida()throws BusinessLogicException
    {

     UsuarioEntity nuevaEn= factory.manufacturePojo(UsuarioEntity.class);
     UsuarioEntity nueva2= factory.manufacturePojo(UsuarioEntity.class);
     em.persist(nueva2);
     nuevaEn.setUsuario(nueva2.getUsuario());
     UsuarioEntity us = logic.crearUsuario(nuevaEn);
        
    }
    
    @Test(expected= BusinessLogicException.class)
    public void crearUsuarioCorreoRepetido()throws BusinessLogicException{
           UsuarioEntity nuevaEn= factory.manufacturePojo(UsuarioEntity.class);
     UsuarioEntity nueva2= factory.manufacturePojo(UsuarioEntity.class);
     em.persist(nueva2);
     nuevaEn.setCorreo(nueva2.getCorreo());
     UsuarioEntity us = logic.crearUsuario(nuevaEn);
        
    }
 
    @Test
    public void getUsuario() 
    {
        UsuarioEntity nuevaEn= factory.manufacturePojo(UsuarioEntity.class);
        em.persist(nuevaEn);
        UsuarioEntity is = logic.getUsuario(nuevaEn.getId());
        Assert.assertEquals(nuevaEn.getId(),is.getId());
        
    }
    
    @Test
    public void updateUsuario()throws BusinessLogicException
    {      
        UsuarioEntity entity = logic.crearUsuario(factory.manufacturePojo(UsuarioEntity.class));
        UsuarioEntity podamEntity = factory.manufacturePojo(UsuarioEntity.class);
        podamEntity.setId(entity.getId());
        podamEntity.setUsuario(entity.getUsuario());
        podamEntity.setCorreo(entity.getCorreo());
        logic.actualizarInformacion(entity.getId(), podamEntity);
        UsuarioEntity resp = em.find(UsuarioEntity.class, entity.getId());
        Assert.assertEquals(podamEntity.getId(),resp.getId());
        Assert.assertEquals(podamEntity.getContrasenha(),resp.getContrasenha());
        Assert.assertEquals(podamEntity.getUsuario(), resp.getUsuario());
            
        
    }
         
    @Test(expected =BusinessLogicException.class)
    public void updateUsuarioCambiarNombreUsuario()throws BusinessLogicException
    {
    UsuarioEntity entity = logic.crearUsuario(factory.manufacturePojo(UsuarioEntity.class));
        UsuarioEntity podamEntity = factory.manufacturePojo(UsuarioEntity.class);
        podamEntity.setId(entity.getId());
        
        podamEntity.setCorreo(entity.getCorreo());
        logic.actualizarInformacion(entity.getId(), podamEntity);
        UsuarioEntity resp = em.find(UsuarioEntity.class, entity.getId());
        Assert.assertEquals(podamEntity.getId(),resp.getId());
        Assert.assertEquals(podamEntity.getContrasenha(),resp.getContrasenha());
        Assert.assertEquals(podamEntity.getUsuario(), resp.getUsuario());
    }
    
     @Test(expected =BusinessLogicException.class)
    public void updateUsuarioCambiarCorreoUsuario()throws BusinessLogicException
    {      
        UsuarioEntity entity = logic.crearUsuario(factory.manufacturePojo(UsuarioEntity.class));
        UsuarioEntity podamEntity = factory.manufacturePojo(UsuarioEntity.class);
        podamEntity.setId(entity.getId());
        podamEntity.setUsuario(entity.getUsuario());

        logic.actualizarInformacion(entity.getId(), podamEntity);
        UsuarioEntity resp = em.find(UsuarioEntity.class, entity.getId());
        Assert.assertEquals(podamEntity.getId(),resp.getId());
        Assert.assertEquals(podamEntity.getContrasenha(),resp.getContrasenha());
        Assert.assertEquals(podamEntity.getUsuario(), resp.getUsuario());
            
        
    }
    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.test.persistence;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import co.edu.uniandes.csw.mascotas.entities.UsuarioEntity;

import co.edu.uniandes.csw.mascotas.persistence.UsuarioPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import junit.framework.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
/**
 *
 * @author Maria Ana Ortiz (ma.ortiz1)
 */
@RunWith(Arquillian.class)
public class UsuarioPersistenceTest {
    
    @Inject
    private UsuarioPersistence persistence;
    
    @PersistenceContext
    private EntityManager em;
    
    
    private List <UsuarioEntity> listaUsuarioPrueba = new ArrayList<>();
    
    
    @Inject 
    private UserTransaction ustx;
    
    @Deployment
    public static JavaArchive deployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(UsuarioEntity.class.getPackage())
                .addPackage(UsuarioPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    private void inicializacionLista(){
        PodamFactory factory = new PodamFactoryImpl();
        for(int i = 0; i<10;i++)
        {
            UsuarioEntity nuevoU = factory.manufacturePojo(UsuarioEntity.class);
            em.persist(nuevoU);
            listaUsuarioPrueba.add(nuevoU);
            
        }
    }
    
    private void clearData() {
        em.createQuery("delete from MascotaExtraviadaEntity").executeUpdate();
    }
    
    @Before
    public void configTest(){
        try{
            ustx.begin();
            em.joinTransaction();
            clearData();
            inicializacionLista();
        } catch(Exception e)
        {
            e.printStackTrace();
            try{
                ustx.rollback();
            }
            catch(Exception e1){
                e1.printStackTrace();
            }
        }
    }
    
    
    
    
    
    @Test
    public void createUsuarioTest(){
        PodamFactory factory = new PodamFactoryImpl();
        UsuarioEntity entityU = factory.manufacturePojo(UsuarioEntity.class);
        UsuarioEntity resulEntityU = persistence.create(entityU);
        Assert.assertNotNull(resulEntityU);
        Assert.assertNotNull(entityU);
        System.out.println(resulEntityU.getId());
        UsuarioEntity encontradaEntity = em.find(UsuarioEntity.class, resulEntityU.getId());
        Assert.assertNotNull(encontradaEntity);
        Assert.assertEquals(entityU.getTelefono(),encontradaEntity.getTelefono());
        Assert.assertEquals(entityU.getNombre(),encontradaEntity.getNombre());
        Assert.assertEquals(entityU.getCorreo(),encontradaEntity.getCorreo());
        
    }

    
    @Test
    public void buscarUsuario(){
        PodamFactory factory = new PodamFactoryImpl();
        UsuarioEntity entity = factory.manufacturePojo(UsuarioEntity.class);
        UsuarioEntity resul = persistence.create(entity);
        UsuarioEntity resulP=persistence.findByUser(entity.getUsuario());
        Assert.assertEquals(resulP.getNombre(),entity.getNombre());
    }
      @Test
    public void buscarUsuarioCorreo(){
        PodamFactory factory = new PodamFactoryImpl();
        UsuarioEntity entity = factory.manufacturePojo(UsuarioEntity.class);
        UsuarioEntity resul = persistence.create(entity);
        UsuarioEntity resulP=persistence.findByCorreo(entity.getCorreo());
        Assert.assertEquals(resulP.getNombre(),entity.getNombre());
    }
}

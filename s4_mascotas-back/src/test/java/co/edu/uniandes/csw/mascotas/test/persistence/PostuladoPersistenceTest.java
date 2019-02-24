/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.test.persistence;


import co.edu.uniandes.csw.mascotas.entities.PostuladoEntity;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.runner.RunWith;
import co.edu.uniandes.csw.mascotas.persistence.PostuladoPersistence;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.jboss.arquillian.container.test.api.Deployment;
import org.junit.Assert;
import org.junit.Test;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author s.canales
 */
@RunWith(Arquillian.class)
public class PostuladoPersistenceTest {
    
    @Inject
    private PostuladoPersistence pp;
    
    @PersistenceContext
    private EntityManager em;
    
    @Deployment
    public static JavaArchive deployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PostuladoEntity.class.getPackage())
                .addPackage(PostuladoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    @Test
    public void createPostuladoTest(){
        
        
        PodamFactory factory = new PodamFactoryImpl();
        PostuladoEntity entity = factory.manufacturePojo(PostuladoEntity.class);
        
        PostuladoEntity pe = pp.create(entity);
                
        Assert.assertNotNull(pe);
        
        PostuladoEntity e = em.find(PostuladoEntity.class , pe.getId());
        
        Assert.assertEquals(entity.getNombreUsuario() , e.getNombreUsuario());
    }
        
    
}
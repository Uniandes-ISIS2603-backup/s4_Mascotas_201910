/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.test.persistence;

import co.edu.uniandes.csw.mascotas.entities.ClasificadoEntity;
import co.edu.uniandes.csw.mascotas.persistence.ClasificadoPersistence;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
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
    private ClasificadoPersistence clasifp;
    
    @PersistenceContext
    protected EntityManager em;
    
    @Deployment
    public static JavaArchive createDeployment(){
        
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ClasificadoEntity.class.getPackage())
                .addPackage(ClasificadoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    @Test
    public void createClasificadoTest(){
        
        PodamFactory factory = new PodamFactoryImpl();
        ClasificadoEntity newEntity = factory.manufacturePojo(ClasificadoEntity.class);
        
        ClasificadoEntity clasifE = clasifp.create(newEntity);
        
        Assert.assertNotNull(clasifE);
        
        ClasificadoEntity entityC = em.find(ClasificadoEntity.class, clasifE.getId());
        
        Assert.assertEquals(newEntity.getNombre(), entityC.getNombre());
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.test.logic;

import javax.inject.Inject;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.runner.RunWith;
import co.edu.uniandes.csw.mascotas.ejb.CalificacionLogic;
import co.edu.uniandes.csw.mascotas.entities.CalificacionEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.mascotas.persistence.CalificacionPersistence;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author s.canales
 */
@RunWith(Arquillian.class)
public class CalificacionLogicTest {
    
    @Inject
    private CalificacionLogic logic;
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private CalificacionPersistence cp;
    
    @PersistenceContext
    private EntityManager em;
    
    @Deployment
    public static JavaArchive createDevelopment(){
        return ShrinkWrap.create(JavaArchive.class)
        .addPackage(CalificacionEntity.class.getPackage())
        .addPackage(CalificacionLogic.class.getPackage())
        .addPackage(CalificacionPersistence.class.getPackage())
        .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
        .addAsManifestResource("META-INF/beans.xml", "beans.xml");       
        
    }
    
//    @Test
//    public void createCalificacionTest() throws BusinessLogicException{
//        
//        
//        PodamFactory factory = new PodamFactoryImpl();
//        CalificacionEntity entity = factory.manufacturePojo(CalificacionEntity.class);
//        CalificacionEntity ce = logic.createCalificacion(entity);
//        Assert.assertNotNull(ce);
//        CalificacionEntity e = em.find(CalificacionEntity.class , ce.getId());
//        Assert.assertEquals(entity.getCalificacion() + "" , e.getCalificacion() + "" );
//    }
    @Test(expected = BusinessLogicException.class)
    public void createCalificacionConNumeroErroneo() throws BusinessLogicException{
        
        PodamFactory factory = new PodamFactoryImpl();
        CalificacionEntity nuevaEntity = factory.manufacturePojo(CalificacionEntity.class);
        nuevaEntity.setCalificacion(6);
        logic.createCalificacion(nuevaEntity);
        nuevaEntity.setCalificacion(-3);
        logic.createCalificacion(nuevaEntity);
    }
    
}

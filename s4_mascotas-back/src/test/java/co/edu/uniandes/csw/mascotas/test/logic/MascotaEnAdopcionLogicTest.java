/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.test.logic;

import co.edu.uniandes.csw.mascotas.ejb.CalificacionLogic;
import co.edu.uniandes.csw.mascotas.ejb.MascotaEnAdopcionLogic;
import co.edu.uniandes.csw.mascotas.entities.CalificacionEntity;
import co.edu.uniandes.csw.mascotas.entities.MascotaEnAdopcionEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.mascotas.persistence.CalificacionPersistence;
import co.edu.uniandes.csw.mascotas.persistence.MascotaEnAdopcionPersistence;
import java.util.Date;
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
 * @author s.canales
 */
@RunWith(Arquillian.class)
public class MascotaEnAdopcionLogicTest {
    
     @Inject
    private MascotaEnAdopcionLogic logic;
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private MascotaEnAdopcionPersistence cp;
    
    @PersistenceContext
    private EntityManager em;
    
    @Deployment
    public static JavaArchive deploymento(){
      return ShrinkWrap.create(JavaArchive.class)
                .addPackage(MascotaEnAdopcionEntity.class.getPackage())
                .addPackage(MascotaEnAdopcionLogic.class.getPackage())
                .addPackage(MascotaEnAdopcionPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createCalificacionConNumeroErroneo() throws BusinessLogicException{
        
        PodamFactory factory = new PodamFactoryImpl();
        MascotaEnAdopcionEntity nuevaEntity = factory.manufacturePojo(MascotaEnAdopcionEntity.class);
        nuevaEntity.setAdoptada(true);
        logic.createMascotaEnAdopcion(nuevaEntity);
        nuevaEntity.setFechaFinal(new Date(0));
        logic.createMascotaEnAdopcion(nuevaEntity);
        nuevaEntity.setFechaInicio(null);
        logic.createMascotaEnAdopcion(nuevaEntity);
        nuevaEntity.setPasado(null);
        logic.createMascotaEnAdopcion(nuevaEntity);
        nuevaEntity.setRazonAdopcion(null);
        logic.createMascotaEnAdopcion(nuevaEntity);
    }
    
    
}

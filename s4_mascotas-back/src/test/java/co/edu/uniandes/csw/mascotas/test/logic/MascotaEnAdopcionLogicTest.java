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
 * @author s.canales
 */
@RunWith(Arquillian.class)
public class MascotaEnAdopcionLogicTest {
    
    /**
     * La lógica sobre la cual se ejecutan las pruebas
     */
     @Inject
    private MascotaEnAdopcionLogic logic;
    
     /**
      * El objeto con el cuál se crean entidades con valores de atributo aleatorios
      */
    private PodamFactory factory = new PodamFactoryImpl();
    
    
    
    /**
     * Manejador de persistencia para este conjunto de pruebas
     */
    @PersistenceContext
    private EntityManager em;
    
    /**
     * maneja las transacciones
     */
    @Inject
    private UserTransaction utx;
    
    @Deployment
    public static JavaArchive deploymento(){
      return ShrinkWrap.create(JavaArchive.class)
                .addPackage(MascotaEnAdopcionEntity.class.getPackage())
                .addPackage(MascotaEnAdopcionLogic.class.getPackage())
                .addPackage(MascotaEnAdopcionPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
     /**
     * Limpia las tablas que están implicadas en la prueba.
     */
    private void clearData() {
        em.createQuery("delete from MascotaExtraviadaEntity").executeUpdate();
        em.createQuery("delete from RecompensaEntity").executeUpdate();
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
     * valida que esté funcionando la lógica cuando los datos sí cumplen las reglas de negocio
     */
    @Test
    public void createMascotaEnAdopcion() throws Exception{
         PodamFactory factory = new PodamFactoryImpl();
        MascotaEnAdopcionEntity nuevaEntity = factory.manufacturePojo(MascotaEnAdopcionEntity.class);
        nuevaEntity.setPasado("La recogimos de la calle hace 2 y la transformamos");
        nuevaEntity.setRazonAdopcion("nos vamos del país");
        nuevaEntity.setAdoptada(false);
        MascotaEnAdopcionEntity resultado =logic.createMascotaEnAdopcion(nuevaEntity);
        Assert.assertEquals(nuevaEntity.getPasado(),resultado.getPasado());
        Assert.assertEquals(nuevaEntity.getRazonAdopcion(), resultado.getRazonAdopcion());
        Assert.assertEquals(nuevaEntity.isAdoptada(),resultado.isAdoptada());
        
    }
    /**
     * valida que se estén lanzando excepciones cuando se incumplen las reglas de negocio para cerar un proceso
     * @throws BusinessLogicException 
     */
    @Test(expected = BusinessLogicException.class)
    public void createMascotaEnAdopcionErronea() throws BusinessLogicException{
        
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

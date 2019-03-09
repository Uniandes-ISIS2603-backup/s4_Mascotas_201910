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
import co.edu.uniandes.csw.mascotas.entities.MascotaEnAdopcionEntity;
import co.edu.uniandes.csw.mascotas.entities.MascotaExtraviadaEntity;
import co.edu.uniandes.csw.mascotas.entities.RecompensaEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.mascotas.persistence.CalificacionPersistence;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author s.canales
 */
@RunWith(Arquillian.class)
public class CalificacionLogicTest {
    /**
     * la lógica sobre la cual se ejecutan las pruebas
     */
    @Inject
    private CalificacionLogic logic;
    /**
     * objeto que genera valores aleatorios para los atributos de las entidades
     */
    private PodamFactory factory = new PodamFactoryImpl();
    
    
    /**
     * maneja las persistencia para estas pruebas
     */
    @PersistenceContext
    private EntityManager em;
        /**
     * lista de procesos para realizar pruebas
     */
    private List<MascotaEnAdopcionEntity> pruebaProcesosMascotaEnAdopcion;
    /**
     * lista de calificaciones
     */
    private List<CalificacionEntity> pruebaCalificaciones;
    /**
     * Manejador de transacciones
     */
    @Inject
    private UserTransaction utx;
    
    @Deployment
    public static JavaArchive createDevelopment(){
        return ShrinkWrap.create(JavaArchive.class)
        .addPackage(CalificacionEntity.class.getPackage())
        .addPackage(CalificacionLogic.class.getPackage())
        .addPackage(CalificacionPersistence.class.getPackage())
        .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
        .addAsManifestResource("META-INF/beans.xml", "beans.xml");       
        
    }
    /**
     * inicializa la lista de prueba
     */
    private void inicializacionListaPrueba(){
        for (int i = 0; i < 10; i++) {
            MascotaEnAdopcionEntity p = factory.manufacturePojo(MascotaEnAdopcionEntity.class);
            em.persist(p);
            pruebaProcesosMascotaEnAdopcion.add(p);
        }
        
        for(int i = 0; i < 10; i++){
            CalificacionEntity entity = factory.manufacturePojo(CalificacionEntity.class);
            
            em.persist(entity);
            pruebaCalificaciones.add(entity);
        }
        
    }
    /**
     * Limpia las tablas que están implicadas en la prueba.
     */
    private void clearData() {
        em.createQuery("delete from RecompensaEntity").executeUpdate();
        em.createQuery("delete from MascotaExtraviadaEntity").executeUpdate();
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
     * se valida que esté funcionando la lógica cuando se cumplen los datos
     * @throws BusinessLogicException 
     */
    @Test
    public void createCalificacionTest() throws BusinessLogicException{
        
        CalificacionEntity calificacion = factory.manufacturePojo(CalificacionEntity.class);
        calificacion.setCalificacion(1);
        calificacion.setComentario("mal proceso");
        
        CalificacionEntity result = logic.createCalificacion(calificacion);
        Assert.assertNotNull(result);

        Assert.assertEquals(result.getCalificacion() + "" , calificacion.getCalificacion() + "" );
    }
    /**
     * se valida que se manden excepciones cuando las reglas de negocio se incumplen
     */
    @Test(expected = BusinessLogicException.class)
    public void createCalificacionConNumeroErroneo() throws BusinessLogicException{
        
        PodamFactory factory = new PodamFactoryImpl();
        CalificacionEntity nuevaEntity = new CalificacionEntity();
        
        
        nuevaEntity.setComentario(null);
        nuevaEntity.setCalificacion(6);
        logic.createCalificacion(nuevaEntity);
        nuevaEntity.setCalificacion(-3);
        logic.createCalificacion(nuevaEntity);
    }
   
    
}

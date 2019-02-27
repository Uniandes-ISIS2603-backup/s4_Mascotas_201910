/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.test.logic;

import co.edu.uniandes.csw.mascotas.entities.MascotaExtraviadaEntity;
import co.edu.uniandes.csw.mascotas.persistence.MascotaExtraviadaPersistence;
import co.edu.uniandes.csw.mascotas.ejb.MascotaExtraviadaLogic;
import co.edu.uniandes.csw.mascotas.entities.RecompensaEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
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
 * @author Sebastián Lemus Cadena (s.lemus)
 */
@RunWith(Arquillian.class)
public class MascotaExtraviadaLogicTest {
    
    /**
     * La lógica sobre la cual se ejecutan las pruebas
     */
    @Inject
    private MascotaExtraviadaLogic logic;
    
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
     * Lista de procesos de mascota extraviada sobre la cual se realizan algunas pruebas
     */
    private List<MascotaExtraviadaEntity> listaPruebaProcesosMascotaExtraviada = new ArrayList<>();
    
        /**
     * Lista de recompensas sobre la cual se realizan algunas pruebas
     */
    private List<RecompensaEntity> listaPruebaRecompensas = new ArrayList<>();
    
    /**
     * Manejador de transacciones
     */
    @Inject
    private UserTransaction utx;
    
    @Deployment
    public static JavaArchive deployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(MascotaExtraviadaEntity.class.getPackage())
                .addPackage(MascotaExtraviadaLogic.class.getPackage())
                .addPackage(MascotaExtraviadaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
        /**
     * Inicializa la lista de prueba
     */
    private void inicializacionListaPrueba(){
        for (int i = 0; i < 10; i++) {
            MascotaExtraviadaEntity p = factory.manufacturePojo(MascotaExtraviadaEntity.class);
            em.persist(p);
            listaPruebaProcesosMascotaExtraviada.add(p);
        }
        
        for(int i = 0; i < 10; i++){
            RecompensaEntity r = factory.manufacturePojo(RecompensaEntity.class);
            r.setProcesoMascotaExtraviada(listaPruebaProcesosMascotaExtraviada.get(i));
            em.persist(r);
            listaPruebaRecompensas.add(r);
        }
        listaPruebaProcesosMascotaExtraviada.get(0).setRecompensa(listaPruebaRecompensas.get(0));
    }
    
    /**
     * Limpia las tablas que están implicadas en la prueba.
     */
    private void clearData() {
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
     * Prueba la creación de un proceso de mascota extraviada desde la lógica
     * @throws Exception 
     */
    @Test
    public void createMascotaExtraviadaTest()throws Exception{
        MascotaExtraviadaEntity entity = factory.manufacturePojo(MascotaExtraviadaEntity.class);
        entity.setEstado(MascotaExtraviadaEntity.PENDIENTE);
        MascotaExtraviadaEntity result = logic.createMascotaExtraviada(entity);
        Assert.assertNotNull(result);
        MascotaExtraviadaEntity foundEntity = em.find(MascotaExtraviadaEntity.class, entity.getId());
        Assert.assertEquals(result.getCiudad(), foundEntity.getCiudad());
        Assert.assertEquals(result.getDireccion(), foundEntity.getDireccion());
        Assert.assertEquals(result.getEstado(), foundEntity.getEstado());
        
    }
    
    /**
     * Prueba la creación de un proceso de mascota extraviada
     * con un estado diferente a 'PENDIENTE'. Se espera que genere una Excepción
     * @throws Exception 
     */
    @Test(expected = BusinessLogicException.class)
    public void createMascotaExtraviadaConEstadoInvalidoTest() throws Exception{
        MascotaExtraviadaEntity entity = factory.manufacturePojo(MascotaExtraviadaEntity.class);
        entity.setEstado(MascotaExtraviadaEntity.ENCONTRADO);
        logic.createMascotaExtraviada(entity);
    }
    
    /**
     * Prueba la eliminación de un proceso de mascota extraviada 
     * si tiene una recompensa asociada. Se espera que genere una Excepción
     */
    @Test(expected = BusinessLogicException.class)
    public void deleteMascotaExtraviadaConRecompensa() throws Exception{
        MascotaExtraviadaEntity entiy = listaPruebaProcesosMascotaExtraviada.get(0);
        logic.deleteProcesoMascotaExtraviada(entiy.getId());
    }
}

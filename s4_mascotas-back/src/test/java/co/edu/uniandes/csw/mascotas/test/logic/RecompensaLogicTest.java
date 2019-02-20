/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.test.logic;

import co.edu.uniandes.csw.mascotas.entities.RecompensaEntity;
import co.edu.uniandes.csw.mascotas.persistence.RecompensaPersistence;
import co.edu.uniandes.csw.mascotas.ejb.RecompensaLogic;
import co.edu.uniandes.csw.mascotas.entities.MascotaExtraviadaEntity;
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
public class RecompensaLogicTest {
    
    /**
     * La lógica sobre la cual se ejecutan las pruebas
     */
    @Inject
    private RecompensaLogic logic;
    
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
     * Lista de recompensas sobre la cual se realizan algunas pruebas
     */
    private List<RecompensaEntity> listaPruebaRecompensas = new ArrayList<>();
    
    /**
     * Lista de procesos de mascota extraviada a las cuales se les van a asociar
     * algunas recompensas
     */
    private List<MascotaExtraviadaEntity> listaPruebaMascotaExtraviada = new ArrayList<>();
    
    /**
     * Manejador de transacciones
     */
    @Inject
    private UserTransaction utx;
    
    @Deployment
    public static JavaArchive deployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(RecompensaEntity.class.getPackage())
                .addPackage(RecompensaLogic.class.getPackage())
                .addPackage(RecompensaPersistence.class.getPackage())
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
            listaPruebaMascotaExtraviada.add(p);
        }
        
        for(int i = 0; i < 10; i++){
            RecompensaEntity r = factory.manufacturePojo(RecompensaEntity.class);
            r.setProcesoMascotaExtraviada(listaPruebaMascotaExtraviada.get(i));
            em.persist(r);
            listaPruebaRecompensas.add(r);
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
     * Prueba la creación de una recompensa desde la lógica
     * @throws Exception 
     */
    @Test
    public void createRecompensaTest() throws Exception{
        RecompensaEntity entity = factory.manufacturePojo(RecompensaEntity.class);
        entity.setEstado(RecompensaEntity.PENDIENTE);
        entity.setValor(Math.abs(entity.getValor()));
        entity.setProcesoMascotaExtraviada(listaPruebaMascotaExtraviada.get(0));
        RecompensaEntity result = logic.createRecompensa(entity);
        Assert.assertNotNull(result);
        RecompensaEntity foundEntity = em.find(RecompensaEntity.class, entity.getId());
        Assert.assertEquals(result.getEstado(), foundEntity.getEstado());
        Assert.assertEquals(result.getValor(), foundEntity.getValor());
        Assert.assertEquals(result.getMedioDePago(), foundEntity.getMedioDePago());
    }
    
    /**
     * Prueba la creación de una recompensa con un valor inválido (negativo).
     * Se espera que genere una Excepción.
     * @throws Exception 
     */
    @Test(expected = BusinessLogicException.class)
    public void createRecompensaConValorInvalidoTest() throws Exception{
        RecompensaEntity entity = factory.manufacturePojo(RecompensaEntity.class);
        entity.setEstado(RecompensaEntity.PENDIENTE);
        entity.setValor(-100.0);
        entity.setProcesoMascotaExtraviada(listaPruebaMascotaExtraviada.get(0));
        logic.createRecompensa(entity);
    }
    
    /**
     * Prueba la creación de una recompensa
     * con un estado diferente a 'PENDIENTE'. Se espera que genere una Excepción.
     * @throws Exception 
     */
    @Test(expected = BusinessLogicException.class)
    public void createRecompensaConEstadoInvalidoTest() throws Exception{
        RecompensaEntity entity = factory.manufacturePojo(RecompensaEntity.class);
        entity.setEstado(RecompensaEntity.PAGADO);
        entity.setValor(Math.abs(entity.getValor()));
        entity.setProcesoMascotaExtraviada(listaPruebaMascotaExtraviada.get(0));
        logic.createRecompensa(entity);
    }
    
    /**
     * Prueba la creación de una recompensa sin un proceso asociado.
     * Se espera que genere una Excepción.
     * @throws Exception 
     */
    @Test(expected = BusinessLogicException.class)
    public void createRecompensaSinProcesoMascotaExtraviada() throws Exception{
        RecompensaEntity entity = factory.manufacturePojo(RecompensaEntity.class);
        entity.setEstado(RecompensaEntity.PENDIENTE);
        entity.setValor(Math.abs(entity.getValor()));
        entity.setProcesoMascotaExtraviada(null);
        logic.createRecompensa(entity);
    }
}

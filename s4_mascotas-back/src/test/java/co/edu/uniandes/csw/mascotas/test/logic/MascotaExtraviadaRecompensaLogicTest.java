/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.test.logic;

import co.edu.uniandes.csw.mascotas.entities.MascotaExtraviadaEntity;
import co.edu.uniandes.csw.mascotas.entities.RecompensaEntity;
import co.edu.uniandes.csw.mascotas.persistence.RecompensaPersistence;
import co.edu.uniandes.csw.mascotas.ejb.MascotaExtraviadaRecompensaLogic;
import co.edu.uniandes.csw.mascotas.ejb.MascotaExtraviadaLogic;
import co.edu.uniandes.csw.mascotas.ejb.RecompensaLogic;
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
public class MascotaExtraviadaRecompensaLogicTest {
    
    /**
     * La lógica sobre la cual se ejecutan las pruebas
     */
    @Inject
    private MascotaExtraviadaRecompensaLogic logic;
    
    /**
     * La lógica de los procesos de mascota extraviada
     */
    @Inject
    private MascotaExtraviadaLogic mascotaExtraviadaLogic;
    
    /**
     * La lógica de las recompensas de los procesos de mascota extraviada
     */
    @Inject
    private RecompensaLogic recompensaLogic;
    
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
                .addPackage(RecompensaEntity.class.getPackage())
                .addPackage(MascotaExtraviadaRecompensaLogic.class.getPackage())
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
            listaPruebaProcesosMascotaExtraviada.add(p);
        }
        
        for(int i = 0; i < 10; i++){
            RecompensaEntity r = factory.manufacturePojo(RecompensaEntity.class);
            listaPruebaRecompensas.add(r);
        }
        for(int i = 0; i < 10; i++){
            MascotaExtraviadaEntity p = listaPruebaProcesosMascotaExtraviada.get(i);
            RecompensaEntity r = listaPruebaRecompensas.get(i);
            p.setRecompensa(r);
            r.setProcesoMascotaExtraviada(p);
            em.persist(p);
            em.persist(r);
        }
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
     * Prueba la funcionalidad de remover una recompensa de
     * un proceso de mascotas 
     */
    @Test
    public void removerRecompensaTest()throws Exception{
        MascotaExtraviadaEntity p = listaPruebaProcesosMascotaExtraviada.get(5);
        RecompensaEntity r = p.getRecompensa();
        logic.removeRecompensaConProceso(p.getId());
        MascotaExtraviadaEntity foundP = mascotaExtraviadaLogic.getProcesoMascotaExtraviada(p.getId());
        Assert.assertNull(foundP.getRecompensa());
        try{
            RecompensaEntity foundR = recompensaLogic.getRecompensa(r.getId());
        }catch(Exception e){
            Assert.assertTrue(true);
        }
    }
}

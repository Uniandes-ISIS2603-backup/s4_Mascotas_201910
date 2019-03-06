/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.test.logic;

import co.edu.uniandes.csw.mascotas.ejb.MascotaLogic;
import co.edu.uniandes.csw.mascotas.entities.MascotaEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.mascotas.persistence.MascotaPersistence;
import com.sun.istack.logging.Logger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
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
 * @author Natalia Sanabria Forero (n.sanabria)
 */
@RunWith(Arquillian.class)
public class MascotaLogicTest {
    
    private static final Logger LOGGER = Logger.getLogger(MascotaLogic.class.getName(), MascotaLogic.class);
    
    private PodamFactory factory = new PodamFactoryImpl();
    /**
     * Inyección de la dependencia de la clase de lógica, cuyos métodos se van a probar
     */
    @Inject
    private MascotaLogic logica;
    
    /**
     * Contexto de persistencia que se utilizará para acceder a la base de datos por fuera de los métodos que se están probando.
     */
    @PersistenceContext
    private EntityManager em;
    
    /**
     * Variable para marcar las transacciones del em cuando se crean/borran datos para las pruebas
     */
    @Inject
    UserTransaction utx;
    
    /**
     * Lista con los datos de prueba
     */
    private List<MascotaEntity> data = new ArrayList<MascotaEntity>();
    
    /**
     * @return Devuelve el JAR que Arquillian va a desplegar en Payara embebido.
     */
    @Deployment
    public static JavaArchive deployment( )
    {
        return ShrinkWrap.create(JavaArchive.class).addPackage(MascotaEntity.class.getPackage())
                .addPackage(MascotaLogic.class.getPackage())
                .addPackage(MascotaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml");   
    }
    
    @Before
    public void configTest()
    {
        try{
            utx.begin();
            em.joinTransaction();
            clearData();
            insertData();
            utx.commit();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            try 
            {
                utx.rollback();
            } catch (Exception e1) 
            {
                e1.printStackTrace();
            }
        }
    }
    
    private void clearData()
    {
        em.createQuery("delete from MascotaEntity").executeUpdate();
    }
    
    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las pruebas
     */
    private void insertData()
    {
        for(int i = 0; i< 3; i++)
        {
            MascotaEntity entidad = factory.manufacturePojo(MascotaEntity.class);
            if(i % 2 == 0)
            {
                entidad.setTipo(MascotaLogic.PERRO);
            }
            else
            {
                entidad.setTipo(MascotaLogic.GATO);
            }
            em.persist(entidad);
            data.add(entidad);
        }
    }
    
    @Test
    public void createMascotaTest() throws BusinessLogicException
    {
        MascotaEntity nuevaEntidad = factory.manufacturePojo(MascotaEntity.class);
        nuevaEntidad.setTipo(MascotaLogic.PERRO);
        MascotaEntity resultado = logica.crearMascota(nuevaEntidad);
        
        Assert.assertNotNull(resultado);
        MascotaEntity entidad = em.find(MascotaEntity.class, resultado.getId());
        
        Assert.assertEquals(entidad.getId(), resultado.getId());
        Assert.assertEquals(entidad.getDescripcion(), resultado.getDescripcion());
        Assert.assertEquals(entidad.getEstado(), resultado.getEstado());
        Assert.assertEquals(entidad.getRaza(), resultado.getRaza());
        Assert.assertEquals(entidad.getTipo(), resultado.getTipo());
        
        ArrayList<String> fotosEntidad = (ArrayList)entidad.getFotos();
        ArrayList<String> fotosResultado = (ArrayList) resultado.getFotos();
        Assert.assertEquals(fotosEntidad.size(), fotosResultado.size());
        
        for(int i = 0; i< fotosEntidad.size(); i++)
        {
            Assert.assertEquals(fotosEntidad.get(i), fotosResultado.get(i));
        }
    }
    
    /**
     * Test que valida que un tipo que no sea PERRO o GATO no pueda ser ingresado al sistema
     * @throws BusinessLogicException 
     */
    @Test(expected = BusinessLogicException.class)
    public void crearMascotaTestConTipoDiferenteTest() throws BusinessLogicException
    {
        MascotaEntity nuevaEntidad = factory.manufacturePojo(MascotaEntity.class);
        nuevaEntidad.setTipo("PAJARO");
        logica.crearMascota(nuevaEntidad);
    }
  
    /**
     * Test que valida que no se puede crear una mascota con descripción nula
     * @throws co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException
     */
    @Test (expected = BusinessLogicException.class) 
    public void crearMascotaDescripcionNulaTest()throws BusinessLogicException
    {
        MascotaEntity nuevaEntidad = factory.manufacturePojo(MascotaEntity.class);
        nuevaEntidad.setDescripcion(null);
        logica.crearMascota(nuevaEntidad);
    }
    
    /**
     * Test que valida que no se puede crear una mascota con descripción vacía
     * @throws co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException
     */
    @Test (expected = BusinessLogicException.class) 
    public void crearMascotaDescripcionVaciaTest()throws BusinessLogicException
    {
        MascotaEntity nuevaEntidad = factory.manufacturePojo(MascotaEntity.class);
        nuevaEntidad.setDescripcion("");
        logica.crearMascota(nuevaEntidad);
    }
    
    @Test
    public void darMascotasPorEstadoTest() throws BusinessLogicException
    {
        String estado = data.get(0).getEstado().name();
        List<MascotaEntity> mascotas = logica.darMascotasPorEstado(estado);
        if(mascotas.size()<1)
            Assert.fail();
        for(MascotaEntity mascota : mascotas )
        {
            if(!mascota.getEstado().name().equals(estado))
                Assert.fail();
        }
    }
    
    @Test (expected = BusinessLogicException.class)
    public void darMascotasPorEstadoNoValidoTest() throws BusinessLogicException
    {
        int length = 8;
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
             + "abcdefghijklmnopqrstuvwxyz"
             + "0123456789";
        String str = new Random().ints(length, 0, chars.length())
                         .mapToObj(i -> "" + chars.charAt(i))
                         .collect(Collectors.joining());
        logica.darMascotasPorEstado(str);
    }
}

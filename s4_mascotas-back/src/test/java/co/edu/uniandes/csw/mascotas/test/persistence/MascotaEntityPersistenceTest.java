/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.test.persistence;

import co.edu.uniandes.csw.mascotas.entities.MascotaEntity;
import co.edu.uniandes.csw.mascotas.persistence.MascotaPersistence;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
 * Clase que prueba los métodos de la persistencia para el Recurso Mascota
 * @author Natalia Sanabria Forero (n.sanabria)
 */
@RunWith(Arquillian.class)
public class MascotaEntityPersistenceTest 
{
    /**
     * Clase de persistencia que se va a probar
     */
    @Inject
    private MascotaPersistence ep;
    
    /**
     * Manejdar de entidades que permite comparar los resultados
     */
    @PersistenceContext
    private EntityManager em;
    
    /**
     * @return Devuelve el JAR que Arquillian va a desplegar en Payara embebido.
     */
    @Deployment
    public static JavaArchive deployment( )
    {
        return ShrinkWrap.create(JavaArchive.class).addPackage(MascotaEntity.class.getPackage())
                .addPackage(MascotaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml");   
    }
    
    /**
     * Prueba que verifica que una nueva mascota quede persistida en la base de datos.
     */
    @Test
    public void createMascotaTest( )
    {
        PodamFactory factory = new PodamFactoryImpl();
        MascotaEntity newEntity = factory.manufacturePojo(MascotaEntity.class);
        MascotaEntity mascota = ep.create(newEntity);
        
        Assert.assertNotNull(mascota);
        
        MascotaEntity entity = em.find(MascotaEntity.class, mascota.getId());
        
        Assert.assertNotNull(entity);
        Assert.assertEquals(entity.getTipo(), newEntity.getTipo());
    }
    
    /**
     * Prueba que verifica que el estado de una mascota cambie en la base de datos después de su actualización <br>
     */
    @Test
    public void actualizarEstadoMascotaTest()
    {
        PodamFactory factory = new PodamFactoryImpl();
        MascotaEntity newEntity = factory.manufacturePojo(MascotaEntity.class);
        MascotaEntity mascota = ep.create(newEntity);
        
        Assert.assertNotNull(mascota);
        
        MascotaEntity entidadActualizada = ep.actualizarEstadoMascota(mascota);
        
        mascota = em.find( MascotaEntity.class, mascota.getId());
        Assert.assertEquals(entidadActualizada.getEstado(), mascota.getEstado());
    }
    
    /**
     * Prueba que verifica que la mascota es encontrada satisfactoriamente
     */
    @Test
    public void findTest()
    {
        PodamFactory factory = new PodamFactoryImpl();
        MascotaEntity newEntity = factory.manufacturePojo(MascotaEntity.class);
        MascotaEntity mascota = ep.create(newEntity);
        
        Assert.assertNotNull(mascota);
        
        mascota = em.find( MascotaEntity.class, mascota.getId());
        MascotaEntity mascota2 = ep.find(mascota.getId());
        
        Assert.assertEquals(mascota2.getId(), mascota.getId());
        Assert.assertEquals(mascota2.getDescripcion(),(mascota.getDescripcion()));
        Assert.assertEquals(mascota.getEstado(), mascota2.getEstado());
        Assert.assertEquals(mascota.getTipo(), mascota2.getTipo());
        Assert.assertEquals(mascota.getRaza(), mascota2.getRaza());
        
    }
    
    @Test
    public void buscarMascotaPorNombreTest()
    {
        PodamFactory factory = new PodamFactoryImpl();
        MascotaEntity newEntity = factory.manufacturePojo(MascotaEntity.class);
        MascotaEntity mascota = ep.create(newEntity);
        
        Assert.assertNotNull(mascota);
        
        List<MascotaEntity> mascotas = ep.darMascotasPorNombre(mascota.getNombre());
        
        Assert.assertNotNull(mascotas);
        Assert.assertTrue(mascotas.size()>0);
        
        for( MascotaEntity m : mascotas )
        {
            Assert.assertEquals(m.getNombre(), mascota.getNombre());
        }
    }
    
    public void filtrarTest( ) throws IllegalAccessException
    {
        PodamFactory factory = new PodamFactoryImpl();
        MascotaEntity newEntity = factory.manufacturePojo(MascotaEntity.class);
        MascotaEntity mascota = ep.create(newEntity);
        
        Assert.assertNotNull(mascota);
        
        String[] filtros = new String[2];
        filtros[0] = "nombre";
        filtros[1] = "raza";
        
        ArrayList<String[]> params = new ArrayList<>();
        for(int i = 0; i < filtros.length; i++)
        {
            String[] nuevo = new String[2];
            nuevo[0] = filtros[i];
            String metodo = "get"+filtros[i].substring(0, 1).toUpperCase()+filtros[i].substring(1);
            try 
            {
                Method method = mascota.getClass().getMethod(metodo);
                nuevo[1] = (String)method.invoke(mascota);
            } 
            catch (NoSuchMethodException ex) 
            {
                Assert.fail("No debería generar excepción");
                Logger.getLogger(MascotaEntityPersistenceTest.class.getName()).log(Level.SEVERE, null, ex);
            } 
            catch (SecurityException ex) 
            {
                Assert.fail("No debería generar excepción");
                Logger.getLogger(MascotaEntityPersistenceTest.class.getName()).log(Level.SEVERE, null, ex);
            } 
            catch (IllegalArgumentException ex) 
            {
                Assert.fail("No debería generar excepción");
                Logger.getLogger(MascotaEntityPersistenceTest.class.getName()).log(Level.SEVERE, null, ex);
            } 
            catch (InvocationTargetException ex) 
            {
                Assert.fail("No debería generar excepción");
                Logger.getLogger(MascotaEntityPersistenceTest.class.getName()).log(Level.SEVERE, null, ex);
            }
            params.add(nuevo);
        }
        
        List<MascotaEntity> mascotas = ep.filtarPorParametros(params);
        
        Assert.assertNotNull(mascotas);
        Assert.assertTrue(mascotas.size()>0);
        
//        for (MascotaEntity m : mascotas) {
//            String metodo = "get" + filtros[i].substring(0, 1).toUpperCase() + filtros[i].substring(1);
//            try {
//                Method method = mascota.getClass().getMethod(metodo);
//                nuevo[1] = (String) method.invoke(mascota);
//            } catch (NoSuchMethodException ex) {
//                Assert.fail("No debería generar excepción");
//                Logger.getLogger(MascotaEntityPersistenceTest.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (SecurityException ex) {
//                Assert.fail("No debería generar excepción");
//                Logger.getLogger(MascotaEntityPersistenceTest.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (IllegalArgumentException ex) {
//                Assert.fail("No debería generar excepción");
//                Logger.getLogger(MascotaEntityPersistenceTest.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (InvocationTargetException ex) {
//                Assert.fail("No debería generar excepción");
//               Logger.getLogger(MascotaEntityPersistenceTest.class.getName()).log(Level.SEVERE, null, ex);
//            }
//       }
    }
}
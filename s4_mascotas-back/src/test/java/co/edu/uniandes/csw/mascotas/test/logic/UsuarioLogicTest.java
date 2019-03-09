/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.test.logic;

import co.edu.uniandes.csw.mascotas.ejb.UsuarioLogic;
import co.edu.uniandes.csw.mascotas.entities.UsuarioEntity;

import co.edu.uniandes.csw.mascotas.persistence.UsuarioPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.junit.runner.RunWith;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import org.jboss.arquillian.container.test.api.Deployment;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author estudiante
 */
@RunWith(Arquillian.class)
public class UsuarioLogicTest {

    @Inject
    private UsuarioLogic logic;

    private PodamFactory factory = new PodamFactoryImpl();

    @PersistenceContext
    private EntityManager em;
    
    
    
    
    private List<UsuarioEntity> listaPruebaUsuario = new ArrayList<>();
    
    @Inject
    private UserTransaction utx;
    
    @Deployment
     public static JavaArchive deployment(){
         return ShrinkWrap.create(JavaArchive.class)
                .addPackage(UsuarioEntity.class.getPackage())
                .addPackage(UsuarioLogic.class.getPackage())
                .addPackage(UsuarioPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
     }           
    
     
     private void inicializacionListaPrueba(){
        for(int  i = 0; i< 1; i++){
            UsuarioEntity us = factory.manufacturePojo(UsuarioEntity.class);
            em.persist(us);
            listaPruebaUsuario.add(us);
                    }
        
     }
     
     private void clearData(){
         em.createQuery("delete from UsuarioEntity").executeUpdate();
         em.createQuery("delete from Recompensa Entity").executeUpdate();
         
     }
     
     @Test
     public void createUsuario() throws Exception{
         UsuarioEntity entity = factory.manufacturePojo(UsuarioEntity.class);
         UsuarioEntity resu=logic.crearUsuario(entity);
         Assert.assertNotNull(resu);
         UsuarioEntity  fu= em.find(UsuarioEntity.class,entity.getId());
         Assert.assertEquals(resu.getUsuario(),fu.getUsuario());
         Assert.assertEquals(resu.getCorreo(),fu.getCorreo());
         
                 
     }
     
     @Before
     public void configTest(){
      
         try{
             utx.begin();
             em.joinTransaction();
             clearData();
         }
         catch(Exception e){
             e.printStackTrace(); 
             
             try {
                 utx.rollback();
             }
             catch(Exception e1){
                 e1.printStackTrace();
             }
         }
     }
     
     
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.test.logic;

import co.edu.uniandes.csw.mascotas.ejb.ArticuloLogic;
import co.edu.uniandes.csw.mascotas.ejb.UsuarioArticulosLogic;
import co.edu.uniandes.csw.mascotas.ejb.UsuarioLogic;
import co.edu.uniandes.csw.mascotas.entities.ArticuloEntity;
import co.edu.uniandes.csw.mascotas.entities.UsuarioEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.mascotas.persistence.ArticuloPersistence;
import co.edu.uniandes.csw.mascotas.persistence.UsuarioPersistence;
import java.util.ArrayList;
import java.util.List;
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
 * @author Daniela Gonzalez
 */
@RunWith(Arquillian.class)
public class UsuarioArticulosLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private UsuarioArticulosLogic usuarioArticulosLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<UsuarioEntity> data = new ArrayList<>();

    private List<ArticuloEntity> articulosData = new ArrayList();
    
    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ArticuloEntity.class.getPackage())
                .addPackage(ArticuloLogic.class.getPackage())
                .addPackage(ArticuloPersistence.class.getPackage())
                .addPackage(UsuarioEntity.class.getPackage())
                .addPackage(UsuarioLogic.class.getPackage())
                .addPackage(UsuarioPersistence.class.getPackage())
                .addPackage(UsuarioArticulosLogic.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    /**
     * Configuración inicial de la prueba.
     */
    @Before
    public void configTest() {
        try {
            utx.begin();
            clearData();
            insertData();
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
     * Limpia las tablas que están implicadas en la prueba.
     */
    private void clearData() {
        em.createQuery("delete from ArticuloEntity").executeUpdate();
        em.createQuery("delete from UsuarioEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            ArticuloEntity articulos = factory.manufacturePojo(ArticuloEntity.class);
           
            articulosData.add(articulos);
        }
        for (int i = 0; i < 3; i++) {
            UsuarioEntity entity = factory.manufacturePojo(UsuarioEntity.class);
      
            data.add(entity);
            
        }
        for (int i = 0; i < 3; i++) {
            ArticuloEntity articulo = articulosData.get(i);
            UsuarioEntity usuario = data.get(i);
            
            articulo.setAutor(usuario);
            
            em.persist(articulo);
            em.persist(usuario);
        }
        
    }

     /**
     * Prueba para asociar un Articulo existente a un Usuario.
     */
    @Test
    public void agregarArticuloTest() {
        
        
        
        ArticuloEntity articulos = articulosData.get(1);
        UsuarioEntity usuario = data.get(0);
        
        
        ArticuloEntity response = usuarioArticulosLogic.agregarArticulo(articulos.getId(), usuario.getId());

        Assert.assertNotNull(response);
        Assert.assertEquals(articulos.getId(), response.getId());
    }
    
    
    /**
     * Prueba para obtener una colección de instancias de Articulos asociadas a una
     * instancia Usuario.
     */
    @Test
    public void darArticulosTest() {
        List<ArticuloEntity> list = usuarioArticulosLogic.darArticulos(data.get(0).getId());

        Assert.assertEquals(1, list.size());
    }

    /**
     * Prueba para obtener una instancia de Articulos asociada a una instancia
     * Usuario.
     *
     * @throws BusinessLogicException
     */
    @Test
    public void darArticuloTest() throws BusinessLogicException {
        UsuarioEntity entity = data.get(0);
        ArticuloEntity articuloEntity =articulosData.get(0);
        ArticuloEntity response = usuarioArticulosLogic.darArticuloPorId(entity.getId(), articuloEntity.getId());

        Assert.assertEquals(articuloEntity.getId(), response.getId());
        Assert.assertEquals(articuloEntity.getTitulo(), response.getTitulo());
        Assert.assertEquals(articuloEntity.getTema(), response.getTema());
        Assert.assertEquals(articuloEntity.getContenido(), response.getContenido());
    }
    
    /**
     * Prueba para obtener una instancia de Articulo asociada a una instancia
     * Usuario que no le pertenece.
     *
     * @throws co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void getBookNoAsociadoTest() throws BusinessLogicException {
        UsuarioEntity entity = data.get(0);
        ArticuloEntity articuloEntity = articulosData.get(1);
        usuarioArticulosLogic.darArticuloPorId(entity.getId(), articuloEntity.getId());
    }
    
}

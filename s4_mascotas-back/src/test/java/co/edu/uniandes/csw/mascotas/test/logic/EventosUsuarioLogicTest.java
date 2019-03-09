/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.test.logic;

import co.edu.uniandes.csw.mascotas.ejb.EventoLogic;
import co.edu.uniandes.csw.mascotas.ejb.EventosUsuarioLogic;
import co.edu.uniandes.csw.mascotas.entities.EventoEntity;
import co.edu.uniandes.csw.mascotas.entities.UsuarioEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.mascotas.persistence.EventoPersistence;
import co.edu.uniandes.csw.mascotas.persistence.UsuarioPersistence;
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
 * Clase de pruebas que verifica el correcto cumplimiento de las reglas de negocio de la clase EventosUsuarioLogic
 * @author Natalia Sanabria Forero
 */
@RunWith(Arquillian.class)
public class EventosUsuarioLogicTest 
{
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private UsuarioLogic ul;
    
    @Inject
    private EventosUsuarioLogic eul;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    private UserTransaction utx;
    
    private List<UsuarioEntity> dataUsuarios;
    
    private List<EventoEntity> dataEventos;
    
    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(UsuarioEntity.class.getPackage())
                .addPackage(UsuarioLogic.class.getPackage())
                .addPackage(UsuarioPersistence.class.getPackage())
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
        em.createQuery("delete from BookEntity").executeUpdate();
        em.createQuery("delete from EditorialEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            EventoEntity evento = factory.manufacturePojo(EventoEntity.class);
            em.persist(evento);
            dataEventos.add(evento);
        }
        for (int i = 0; i < 3; i++) {
            UsuarioEntity entity = factory.manufacturePojo(UsuarioEntity.class);
            em.persist(entity);
            dataUsuarios.add(entity);
            if (i == 0) {
                dataEventos.get(i).setOrganizador(entity);
            }
        }
    }
    
    /**
     * Prueba que verifica que se asocia un evento a un usuario correctamente
     * @throws BusinessLogicException 
     */
    @Test
    public void agregarEventoAUsuarioTest() throws BusinessLogicException
    {
        UsuarioEntity usuario = dataUsuarios.get(0);
        EventoEntity evento = dataEventos.get(1);
        EventoEntity resultado = eul.agregarEventoAUsuario(evento.getId(), usuario.getId());
        
        Assert.assertNotNull(resultado);
        Assert.assertEquals(evento.getId(), resultado.getId());
        Assert.assertEquals(evento.getDescripcion(), resultado.getDescripcion());
        Assert.assertEquals(evento.getImagen(), resultado.getImagen());
        Assert.assertEquals(evento.getNombre(), resultado.getNombre());
        Assert.assertEquals(evento.getOrganizador(), usuario);
    }

    
}

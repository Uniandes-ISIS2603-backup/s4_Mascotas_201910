/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.ejb;

import co.edu.uniandes.csw.mascotas.entities.ArticuloEntity;
import co.edu.uniandes.csw.mascotas.entities.EventoEntity;
import co.edu.uniandes.csw.mascotas.entities.MascotaEnAdopcionEntity;
import co.edu.uniandes.csw.mascotas.entities.MascotaExtraviadaEntity;
import co.edu.uniandes.csw.mascotas.entities.UsuarioEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.mascotas.persistence.UsuarioPersistence;
import java.time.Clock;

import java.util.List;
import javax.ejb.Stateless;
import java.util.logging.Logger;
import javax.inject.Inject;

/**
 *
 * @author Maria Ana Ortiz (ma.ortiz1)
 */
@Stateless
public class UsuarioLogic {

    private static final Logger LOGGER = Logger.getLogger(UsuarioLogic.class.getName());

    @Inject
    private UsuarioPersistence persistencia;

    /**
     * Crea un nuevo usuario
     *
     * @param usuario - Entidad del usuario
     * @return Entidad del usuario
     * @throws BusinessLogicException
     */
    public UsuarioEntity crearUsuario(UsuarioEntity usuario) throws BusinessLogicException {

        if (persistencia.findByUser(usuario.getUsuario()) != null) {
            throw new BusinessLogicException("El usuario ya esta registrado");
        }

        if (persistencia.findByCorreo(usuario.getCorreo()) != null) {
            throw new BusinessLogicException("El correo ya tiene un usuario registrado");
        }
        persistencia.create(usuario);
        return usuario;

    }

    /**
     * Retorna el usuario del sistema
     *
     * @param id - id del usuario a buscar
     * @return El usuario del sistema asociado al id
     * @throws BusinessLogicException
     */
    public UsuarioEntity getUsuario(Long id) {
        UsuarioEntity usuario = persistencia.find(id);

        return usuario;
    }

    /**
     * Retorna la lista de todos los usuarios
     *
     * @return La lista de todos los usuarios
     */
    public List<UsuarioEntity> getUsuarios() {
        return persistencia.findAll();
    }

    /**
     * Eliman un usuario
     *
     * @param id - id del usuario a eliminar
     * @throws BusinessLogicException
     */
    public void deleteUsuario(Long id) throws BusinessLogicException {
        //Revisar no puede tener nada pendiente
        UsuarioEntity entity = getUsuario(id);

        List<EventoEntity> eventos = entity.getEventos();
        List<ArticuloEntity> articulos = entity.getArticulos();
        List<MascotaExtraviadaEntity> procesosMascotaExtraviada = entity.getProcesosMascotaExtraviada();
//        List<MascotaEncontradaEntity> procesoMascotaEncontrada = entity.getProcesosMascotaEncontrada();
//        List<ClasificadoEntity> clasificados = entity.getClasificados();
        List<MascotaEnAdopcionEntity> procesosMascotasAdopcion = entity.getProcesosMascotaAdopcion();

        if (eventos != null && !eventos.isEmpty()) {
            throw new BusinessLogicException("El usuario no puede ser organizaddor de ningun evento");
        }
        if (articulos != null && !articulos.isEmpty()) {
            throw new BusinessLogicException("El usuario a eliminar no puede ser autor de articulos");
        }
        if (procesosMascotaExtraviada != null && !procesosMascotaExtraviada.isEmpty()) {
            throw new BusinessLogicException("El usuario no puede tener ningun proceso de mascota extraviada");
        }
//        if(procesoMascotaEncontrada != null && procesoMascotaEncontrada.size()>0){
//            throw new BusinessLogicException("El usuario no puede tener ningun proceso de mascota encontrada para ser eliminado");
//        }
//        if(clasificados != null&& clasificados.size()>0){
//            throw new BusinessLogicException("El usuario no puede ser autor de ningun clasificado");
//        }
        if (procesosMascotasAdopcion != null
                && !procesosMascotasAdopcion.isEmpty()) {
            throw new BusinessLogicException("El usuario no puede tener procesos");
        }
        persistencia.delete(id);
    }

    public UsuarioEntity actualizarInformacion(Long id, UsuarioEntity usuario) throws BusinessLogicException {
        UsuarioEntity entity = getUsuario(id);


        if (!entity.getUsuario().equalsIgnoreCase(usuario.getUsuario())) {

            throw new BusinessLogicException("El usuario no se puede modificar");
        }
        if (!entity.getCorreo().equalsIgnoreCase(usuario.getCorreo())) {
            throw new BusinessLogicException("El correo del usuario no se puede modificar");
        }
//        if(usuario.getId()!= entity.getId()){
//            throw new BusinessLogicException("El identiicador del usuario no se puede modificar");
//        }

        return persistencia.update(usuario);
    }

    /**
     * Retorna el usuario que corresponde al usuario pasaod por parametro si no
     * existe lanza excepcion
     *
     * @param usuario - el usuario que se desea buscar
     * @return retorna un objeto de tipo Usuario entity
     * @throws BusinessLogicException - si el usuario no existe
     */
    public UsuarioEntity buscarUsuarioXUsuario(String usuario) throws BusinessLogicException {
        UsuarioEntity encontrado = persistencia.findByUser(usuario);
        if (encontrado != null) {
            return encontrado;
        } else {
            throw new BusinessLogicException("EL usuario no Existe");
        }

    }

    /**
     * Retorna si la contraseña corresponde
     * @param usuario - nombre de usuario
     * @param contrasenha - contraseña a verificar del usuario
     * @return boolean - verdadero si corresponde
     * @throws BusinessLogicException - si el usuario no existe
     */
    public Boolean verificarContrasenha(String usuario, String contrasenha) throws BusinessLogicException {
        UsuarioEntity encontrado = buscarUsuarioXUsuario(usuario);
     
        if (encontrado == null) {
            throw new BusinessLogicException("El usuario verificado no existe");
        } else {
            Boolean respuesta = contrasenha.equals(encontrado.getContrasenha())?true:false;
            return respuesta;
        }

    }
}

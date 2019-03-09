/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.ejb;

import co.edu.uniandes.csw.mascotas.entities.EventoEntity;
import co.edu.uniandes.csw.mascotas.entities.UsuarioEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.mascotas.persistence.EventoPersistence;
import co.edu.uniandes.csw.mascotas.persistence.UsuarioPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Lógica que maneja las reglas de negocio correspondientes a los eventos organziados por un usuario
 * @author Natalia Sanabria Forero (n.sanabria)
 */
@Stateless
public class EventosUsuarioLogic 
{
    /**
     * Variable que permite acceder a la persistencia de los eventos
     */
    @Inject
    private EventoPersistence ep;
    
    /**
     * Variable que permite acceder a la persistencia de los usuarios
     */
    @Inject
    private UsuarioPersistence up;
    
    
    /**
     * Le agrega un evento a un usuario
     * @param eventoId identificador único del evento
     * @param usuarioId identificador único del usuario
     * @return el evento agregado
     * @throws co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException Si el usuario no existe en el sistema
     */
    public EventoEntity agregarEventoAUsuario(Long eventoId, Long usuarioId) throws BusinessLogicException
    {
        EventoEntity evento = ep.find(eventoId);
        UsuarioEntity usuario = up.find(usuarioId);
        if(usuario == null)
            throw new BusinessLogicException("El recurso /usuarios/"+usuarioId+" no existe");
        List<EventoEntity> eventosUsuario = usuario.getEventos();
        eventosUsuario.add(evento);
        usuario.setEventos(eventosUsuario);
        return evento;
    }
    
    /**
     * Retorna todos los eventos que han sido organizados por un usuario
     * @param usuarioId id del usuario
     * @return eventos del usuario
     */
    public List<EventoEntity> darEventosUsuario(Long usuarioId)
    {
        UsuarioEntity usuario = up.find(usuarioId);
        return usuario.getEventos();
    }
    
    /**
     * Busca un evento asociado a un usuario
     * @param eventoId id del evento
     * @param usuarioId id del usuario
     * @return el evento encontrado en la lista de eventos del usuario
     * @throws BusinessLogicException Si el evento no se encuentra en la lista de eventos del usuario
     */
    public EventoEntity buscarEventoEnUsuario(Long eventoId, Long usuarioId) throws BusinessLogicException
    {
        List<EventoEntity> eventosUsuario = up.find(usuarioId).getEventos();
        EventoEntity evento = ep.find(eventoId);
        int indice = eventosUsuario.indexOf(evento);
        if(indice >= 0)
            return eventosUsuario.get(indice);
        
        throw new BusinessLogicException("El evento buscado no está asociado al usuario ingresado");
    }
    
}

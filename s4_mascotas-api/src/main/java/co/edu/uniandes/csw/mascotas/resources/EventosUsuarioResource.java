/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.resources;

import co.edu.uniandes.csw.mascotas.ejb.EventoLogic;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Clase que implementa el recurso usuario/{id}/eventos
 * @authorNatalia Sanabria Forero
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EventosUsuarioResource 
{
    /**
     * Variable para acceder a la lógica del usuario
     */
 //   @Inject
 //   private UsuarioLogic usuarioLogic;
    
    /**
     * Variable para acceder a la lógica de los eventos de la aplicación
     */
    @Inject
    private EventoLogic eventoLogic;
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.resources;

import co.edu.uniandes.csw.mascotas.dtos.MascotaExtraviadaDTO;
import co.edu.uniandes.csw.mascotas.entities.MascotaExtraviadaEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author Sebastián Lemus Cadena (s.lemus)
 */
@Path("procesosMascotaExtraviada")
@Produces("application/json")
@Consumes("application/json")
public class MascotaExtraviadaResource {
    
    private static final Logger LOGGER = Logger.getLogger(MascotaExtraviadaResource.class.getName());
    
    
}

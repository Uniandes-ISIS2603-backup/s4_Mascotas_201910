/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.ejb;

import co.edu.uniandes.csw.mascotas.persistence.MascotaExtraviadaPersistence;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Sebastián Lemus Cadena (s.lemus)
 */
@Stateless
public class MascotaExtraviadaLogic {
    
    private static final Logger LOGGER = Logger.getLogger(MascotaExtraviadaLogic.class.getName());
    
    /**
     * Instancia de la clase que maneja la persistencia de los procesos
     * de mascota extraviada
     */
    @Inject
    private MascotaExtraviadaPersistence persistence;
}

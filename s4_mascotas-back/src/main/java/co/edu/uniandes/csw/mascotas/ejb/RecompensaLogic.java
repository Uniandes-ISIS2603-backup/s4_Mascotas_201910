/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.ejb;

import co.edu.uniandes.csw.mascotas.ejb.MascotaExtraviadaLogic;
import co.edu.uniandes.csw.mascotas.entities.RecompensaEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.mascotas.persistence.RecompensaPersistence;
import java.util.logging.Logger;
import javax.inject.Inject;

/**
 *
 * @author Sebastián Lemus Cadena (s.lemus)
 */
public class RecompensaLogic {
    
    private static final Logger LOGGER = Logger.getLogger(RecompensaLogic.class.getName());
    
    /**
     * Instancia de la clase que maneja la persistencia las recompensas
     */
    @Inject
    private RecompensaPersistence persistence;
    
    public RecompensaEntity createRecompensa(RecompensaEntity r) throws Exception{
        if (r.getValor() < 0) {
            throw new BusinessLogicException("El valor de la recompensa no puede ser negativo");
        }
        if (r.getEstado() != RecompensaEntity.PENDIENTE) {
            throw new BusinessLogicException("Una nueva recompensa debería estar en estado 'PENDIENTE'");
        }
        persistence.create(r);
        return r;
    }
}

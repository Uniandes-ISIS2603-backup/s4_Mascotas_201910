/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.ejb;

import co.edu.uniandes.csw.mascotas.entities.MascotaEntity;
import co.edu.uniandes.csw.mascotas.persistence.MascotaPersistence;
import javax.ejb.Stateless;
import javax.inject.Inject;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Natalia Sanabria Forero (n.sanabria)
 */
@Stateless
public class MascotaLogic {
    
    @Inject
    private MascotaPersistence persistencia;
    
    /**
     * Crea una nueva mascota por medio de la entidad ingresada por parámetro
     * @param mascota
     * @return
     * @throws BusinessLogicException 
     */
    public MascotaEntity crearMascota( MascotaEntity mascota ) throws BusinessLogicException
    {
        //Validación de reglas de negocio
        if( !mascota.getTipo().equals("PERRO") && !mascota.getTipo().equals("GATO"))
        {
            throw new BusinessLogicException("Una mascota sólo puede ser perro o gato");
        }
        mascota = persistencia.create(mascota);
        return mascota;
    }
    
    /**
     * Busca una mascota por su identificador
     * @param id Identificador único de la mascota
     * @return buscada
     */
    public MascotaEntity buscarMascotaPorId( Long id )
    {
        MascotaEntity buscada = persistencia.find(id);
        return buscada;
    }
    
    /**
     * Retorna una lista con todas las mascotas que hay en la base de datos
     * @return 
     */
    public List<MascotaEntity> darTodasLasMascotas( )
    {
        List<MascotaEntity> respuesta = persistencia.findAll();
        return respuesta;
        
    }
}

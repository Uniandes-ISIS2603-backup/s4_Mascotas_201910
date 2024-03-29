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
import java.util.List;

/**
 * Clase que se encarga de validar las reglas de negocio relacionadas con el Recurso Mascota
 * @author Natalia Sanabria Forero (n.sanabria)
 */
@Stateless
public class MascotaLogic 
{
    // ---------------------------------------
    // Constantes
    // ---------------------------------------
      
    /**
     * Constante que define el tipo de mascota perro 
     */
    public static final String PERRO = "PERRO";
    
    /**
     * Constante que define el tipo de mascota gato
     */
    public static final String GATO = "GATO";
        
    /**
     * Instancia inyectada de la persistencia que permite el acceso a la base de datos
     */
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
        if( (!mascota.getTipo().equals(PERRO) && !mascota.getTipo().equals(GATO)) || mascota.getTipo() == null)
        {
            throw new BusinessLogicException("Una mascota sólo puede ser perro o gato");
        }
        if(mascota.getFotos()== null)
        {
            throw new BusinessLogicException("Por favor incluya al menos una foto o video");
        }
        if(mascota.getDescripcion()==null || mascota.getDescripcion().equals(""))
        {
            throw new BusinessLogicException("Es necesario diligenciar el campo de descripción");
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
        return persistencia.find(id);
    }
    
    /**
     * Retorna una lista con todas las mascotas que hay en la base de datos
     * @return 
     */
    public List<MascotaEntity> darTodasLasMascotas( )
    {
        return persistencia.findAll();
    }
    
    /**
     * Cambia el estado de una mascota con el estado que venga en la entidad que llega por parámetro <br>
     * @param mascota Mascota con susdatos e identificador y su nuevo estado
     * @return mascota
     * @throws BusinessLogicException Si el estado ingresado no corresponde a uno de los estados del sistema
     */
    public MascotaEntity cambiarEstadoMascota(MascotaEntity mascota) throws BusinessLogicException
    {
        String estadoMascota = mascota.getEstado().name();
        MascotaEntity.Estados_mascota[] estados = MascotaEntity.Estados_mascota.values();
        boolean found = false;
        for (MascotaEntity.Estados_mascota estado : estados) {
            if (estadoMascota.equals(estado.name())) {
                found = true;
                persistencia.actualizarEstadoMascota(mascota);
            }
        }
        if (!found ) 
        {
            throw new BusinessLogicException("El estado de la mascota no corresponde a un estado válido.");
        }
        return mascota;

        
    }
    
    /**
     * Retorna una lista de las mascotas cuyo estado es el ingresado por parámetro <br>
     * @param pEstado
     * @return
     * @throws BusinessLogicException Si el String ingresado como estado no tiene una representación en los estados posibles del atributo.
     */
    public List<MascotaEntity> darMascotasPorEstado(String pEstado) throws BusinessLogicException
    {
        // Validar reglas de negocio (estado pertenece a los posibles estados)
         MascotaEntity.Estados_mascota[] estados = MascotaEntity.Estados_mascota.values();
        boolean found = false;
        for (MascotaEntity.Estados_mascota estado : estados) {
            if (pEstado.equals(estado.name())) {
                found = true;
            }
        }
        if(!found)
            throw new BusinessLogicException( " El estado ingresado no es válido. ");
        
        return persistencia.darMascotasPorEstado(pEstado);
    }
    
    /**
     * Retorna una lista con las mascotas cuyo nombre coincida o contenga el nombre pasado por parámetro
     * @param pNombre
     * @return 
     */
    public List<MascotaEntity> darMascotasPorNombre(String pNombre )
    {
        return persistencia.darMascotasPorNombre(pNombre);
    }
    
    /**
     * Retorna una lista con las mascotas cuyo tipo coincida con el tipo ingresado por parámetro
     * @param pTipo
     * @return
     * @throws BusinessLogicException 
     */
    public List<MascotaEntity> darMascotasPorTipo(String pTipo) throws BusinessLogicException
    {
       if(!pTipo.equals(PERRO) || !pTipo.equals(GATO))
        {
            throw new BusinessLogicException("El tipo ingresado debe ser PERRO o GATO");
        }
        return persistencia.darMascotasPorTipo(pTipo);
    }
    
}

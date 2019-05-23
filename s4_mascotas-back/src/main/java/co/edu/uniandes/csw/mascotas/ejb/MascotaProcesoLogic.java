/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.ejb;

import co.edu.uniandes.csw.mascotas.entities.MascotaEntity;
import co.edu.uniandes.csw.mascotas.entities.MascotaExtraviadaEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.mascotas.persistence.MascotaEncontradaPersistence;
import co.edu.uniandes.csw.mascotas.persistence.MascotaExtraviadaPersistence;
import co.edu.uniandes.csw.mascotas.persistence.MascotaPersistence;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Clase encargada de la lógica relacionada con los criterios de búsqueda
 * posibles que involucran la mascota y su proceso
 * 
 * @author Sebastián Lemus Cadena (s.lemus)
 */
@Stateless
public class MascotaProcesoLogic {
    
    private static final Logger LOGGER = Logger.getLogger(MascotaExtraviadaLogic.class.getName());
    
    /**
     * Constante que representa un proceso de mascota extraviada
     */
    public static final String EXTRAVIADA = "EXTRAVIADA";
    
    /**
     * Constante que representa un proceso de mascota encontrada
     */
    public static final String ENCONTRADA = "ENCONTRADA";
    
    /**
     * Instancia de la clase que maneja la persistencia de las mascotas
     */
    @Inject
    private MascotaPersistence mascotaPersistence;
    
    /**
     * Instancia de la clase que maneja la persistencia de los procesos
     * de mascota extraviada
     */
    @Inject
    private MascotaExtraviadaPersistence mascotaExtraviadaPersistence;
    
        /**
     * Instancia de la clase que maneja la persistencia de los procesos
     * de mascota encontrada
     */
    @Inject
    private MascotaEncontradaPersistence mascotaEncontradaPersistence;
    
    /**
     * Retorna una lista filtrada con los procesos de mascota extraviada cuya 
     * recompensa es menor o igual a un precio dado
     * @param precio
     * @return Lista de procesos de mascota extraviada
     * @throws Exception 
     */
    public List<MascotaExtraviadaEntity> darProcesosConRecompensaMenorA(Double precio) throws Exception{
        
        if(precio < 0){
            throw new BusinessLogicException("El precio de una recompensa no puede ser negativo");
        }
        
        List<MascotaExtraviadaEntity> procesos = mascotaExtraviadaPersistence.findAll();
        List<MascotaExtraviadaEntity> procesosFiltrados = new LinkedList<>();
        
        for( MascotaExtraviadaEntity p : procesos){
            if(p.getRecompensa().getValor() <= precio){
                procesosFiltrados.add(p);
            }
        }
        
        return procesosFiltrados;
    }
    
    /**
     * Retorna una lista de los procesos de mascota extraviada
     * con las mascotas del nombre especificado
     * @param nombreMascota
     * @return Lista de procesos
     */
    public List<MascotaExtraviadaEntity> darProcesosExtraviadaConNombreDeMascotaIgualA(String nombreMascota){
        List<MascotaExtraviadaEntity> procesos = mascotaExtraviadaPersistence.findAll();
        List<MascotaExtraviadaEntity> procesosFiltrados = new LinkedList<>();
        
        for( MascotaExtraviadaEntity p : procesos){
            if(p.getMascota().getNombre().equals(nombreMascota)){
                procesosFiltrados.add(p);
            }
        }
        
        return procesosFiltrados;
    }
    
    /**
     * Retorna una lista de los procesos de mascota extraviada
     * con las mascotas de la raza especificada
     * @param raza
     * @return Lista de procesos
     */
    public List<MascotaExtraviadaEntity> darProcesosExtraviadaConRazaIgualA(String raza){
        List<MascotaExtraviadaEntity> procesos = mascotaExtraviadaPersistence.findAll();
        List<MascotaExtraviadaEntity> procesosFiltrados = new LinkedList<>();
        
        for( MascotaExtraviadaEntity p : procesos){
            if(p.getMascota().getRaza().equals(raza)){
                procesosFiltrados.add(p);
            }
        }
        return procesosFiltrados;
    }
    
    /**
     * Retorna una lista de los procesos de mascota extraviada
     * con las mascotas del tipo especificado
     * @param raza
     * @return Lista de procesos
     */
    public List<MascotaExtraviadaEntity> darProcesosExtraviadaConTipoIgualA(String tipo)throws Exception{
        
        if(!tipo.equals(MascotaEntity.PERRO) && !tipo.equals(MascotaEntity.GATO)){
            throw new BusinessLogicException("Las mascotas solo son de tipo gato o perro");
        }
        
        List<MascotaExtraviadaEntity> procesos = mascotaExtraviadaPersistence.findAll();
        List<MascotaExtraviadaEntity> procesosFiltrados = new LinkedList<>();
        
        for( MascotaExtraviadaEntity p : procesos){
            if(p.getMascota().getTipo().equals(tipo)){
                procesosFiltrados.add(p);
            }
        }
        
        return procesosFiltrados;
    }
    
    /**
     * Una lista de mascotas de acuerdo al proceso determinado
     * @param estado
     * @return Lista de mascotas
     * @throws Exception 
     */
    public List<MascotaEntity> darMascotasPorEstado (String estado) throws Exception{
        if(!estado.equals(MascotaEntity.Estados_mascota.ADOPTADO) && !estado.equals(MascotaEntity.Estados_mascota.EXTRAVIADO)
                && !estado.equals(MascotaEntity.Estados_mascota.ENCONTRADO) && !estado.equals(MascotaEntity.Estados_mascota.EN_ADOPCION)){
            throw new BusinessLogicException("El estado de la mascota no es correcto");
        }
        
        List<MascotaEntity> mascotas = mascotaPersistence.findAll();
        List<MascotaEntity> mascotasFiltrados = new LinkedList<>();
        
        for(MascotaEntity m : mascotas){
            if(m.getEstado().equals(estado)){
                mascotasFiltrados.add(m);
            }
        }
        return mascotasFiltrados;
    }
    
}

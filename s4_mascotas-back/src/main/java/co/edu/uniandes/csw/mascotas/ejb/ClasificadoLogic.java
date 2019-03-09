/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.ejb;

import co.edu.uniandes.csw.mascotas.entities.ClasificadoEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.mascotas.persistence.ClasificadoPersistence;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

/**
 *
 * @author Johan E. Vivas Sepulveda (je.vivas)
 */

@Stateless
public class ClasificadoLogic {
    
    @Inject
    private ClasificadoPersistence persistence;
    
    public ClasificadoEntity createClasificado(ClasificadoEntity clasif) throws BusinessLogicException{
        
        if( clasif.getNombre() ==  null){
            throw new BusinessLogicException("El clasificado debe tener un nombre");
        }
        
        if( clasif.getContenido() == null){
            throw new BusinessLogicException("El contenido del clasificado no puede estar vacío");
        }
        
        return persistence.create(clasif);
    }
    
    public List<ClasificadoEntity> getClasificados(){
        return persistence.findAll();
    }
    
    public ClasificadoEntity buscarClasificado( String nombreC){
        ClasificadoEntity sookClasif = persistence.find(nombreC);
        
        return sookClasif;
    }
    
}

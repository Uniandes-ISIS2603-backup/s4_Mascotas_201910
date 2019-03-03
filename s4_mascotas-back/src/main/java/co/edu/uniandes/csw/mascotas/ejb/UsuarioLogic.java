/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.ejb;
import co.edu.uniandes.csw.mascotas.entities.UsuarioEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.mascotas.persistence.UsuarioPersistence;
import javax.ejb.Stateless;
import java.util.logging.Logger;
import javax.inject.Inject;

/**
 *
 * @author Maria Ana Ortiz (ma.ortiz1)
 */
@Stateless
public class UsuarioLogic {
    

private static final Logger LOGGER =Logger.getLogger(UsuarioLogic.class.getName());
    
 @Inject   
 private UsuarioPersistence persistencia;   
    
 
 /**
  * 
  * @param usuario
  * @return
  * @throws BusinessLogicException 
  */
 public UsuarioEntity crearUsuario(UsuarioEntity usuario) throws BusinessLogicException{
     if( )
     
 }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.dtos;
import java.io.Serializable;

/**
 *
 * @author Maria Ana Ortiz Botero 
  */
public class UsuarioDTO implements Serializable{
    
    /**
     * Usuario con el que queda registrado
     */
    private String usuario;
    
    /**
     * Contraseña correspondiente al usuario
     */
    private String contrasenha;
    
    /**
     * Nombre del usuario 
     */
    private String nombre;
    
    /**
     * Dirreccion de correo a la que la cuenta del usuario esta registrado
     */
    private String correo;
    
    /**
     * Telefono de contacto del usuario
     */
    private int telefono;
    
    
    /**
     * Registra si el usuario desea recibir notificaciones
     */
    private boolean recibeNotificaciones;

    /**
     * Constructor
     */
    public UsuarioDTO(){
        
    }
    
    /**
     * 
     * @return El usuario con el que esta registrado
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * Cambia el usuario con el que esta registrado
     * @param usuario 
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
    /**
     * Cambia la contraseña que el usuario tiene registrada
     * @param contrasenha 
     */
    public void setContrasenha(String contrasenha) {
        this.contrasenha = contrasenha;
    }
    
    /**
     * Cambia el nombre que el usuario tiene registrado
     * @param nombre 
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    /**
     * Cambia el correo que el usuario tiene registrado
     * @param correo 
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    /**
     * Cambia el telefono de contacto que el usuario tiene registrado
     * @param telefono 
     */
    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }
    
    /**
     * Cambia si el usario desea recibir notificaciones
     * @param recibeNotificaciones 
     */
    public void setRecibeNotificaciones(boolean recibeNotificaciones) {
        this.recibeNotificaciones = recibeNotificaciones;
    }

    /**
     * 
     * @return la contraseña que el usuario tiene registrada 
     */
    public String getContrasenha() {
        return contrasenha;
    }

    /**
     * 
     * @return el nombre que el usuario tiene registrado
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * 
     * @return retorna el correo que el usuario tiene registrado
     */
    public String getCorreo() {
        return correo;
    }
    /**
     * 
     * @return retorna el telefono que el usario tiene registrado
     */
    public int getTelefono() {
        return telefono;
    }
    
    
    /**
     * 
     * @return retorna si el usuario deseas recibir notificaciones
     */
    public boolean isRecibeNotificaciones() {
        return recibeNotificaciones;
    }
    
    
    
    public String toString(){
        return "UsuarioDTO{"+"usuario:"+usuario+", contrasenha:"+contrasenha+", correo:"+correo+", nombre:"+nombre+", telefono:"+telefono+", recibeNotificaciones:"+recibeNotificaciones+"}";
    }
            
    
    
}

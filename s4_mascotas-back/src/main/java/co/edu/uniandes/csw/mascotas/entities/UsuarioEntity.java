/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.entities;

import java.io.Serializable;
import javax.persistence.Entity;


/**
 *
 * @author Maria Ana Ortiz ma.ortiz1
 */
@Entity
public class UsuarioEntity extends BaseEntity implements Serializable{
    
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
    
    
    public UsuarioEntity(){
        
    }
    
    /**
     * Registra si el usuario desea recibir notificaciones
     */
    private boolean recibeNotificaciones;

    public String getUsuario() {
        return usuario;
    }

    public String getContrasenha() {
        return contrasenha;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public int getTelefono() {
        return telefono;
    }

    public boolean isRecibeNotificaciones() {
        return recibeNotificaciones;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setContrasenha(String contrasenha) {
        this.contrasenha = contrasenha;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public void setRecibeNotificaciones(boolean recibeNotificaciones) {
        this.recibeNotificaciones = recibeNotificaciones;
    }
    
    
    
    
    
    
}

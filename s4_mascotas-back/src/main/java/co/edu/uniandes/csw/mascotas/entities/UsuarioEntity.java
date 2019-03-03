/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Entity;
import uk.co.jemos.podam.common.PodamExclude;
import javax.persistence.OneToMany;
import javax.persistence.FetchType;



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
    private Integer telefono;
    
    
    /**
     * Registra si el usuario desea recibir notificaciones
     */ 
    private Boolean recibeNotificaciones;

    /**
     * La coleccion de procesos de mascota extraviada que tiene el usuario 
     */
    @PodamExclude
    @OneToMany(mappedBy = "usuario",fetch = FetchType.LAZY)
    Collection<MascotaExtraviadaEntity> procesosMascotaExtraviada;
    
//    @PodamExclude
//    @OneToMany(mappedBy = "usuario",fetch = FetchType.LAZY)
//    Collection<MascotaEncontradaEntity> procesosMascotaEncontrada;
//    
//     @PodamExclude
//    @OneToMany(mappedBy = "autor",fetch = javax.persistence.FetchType.LAZY)
//    Collection<ClasificadoEntity> clasificados;    
    
    /**
     * La colecccion de articulos relacioandos al autor 
     */
    @PodamExclude
    @OneToMany(mappedBy = "autor",fetch = FetchType.LAZY)
    Collection<ArticuloEntity> articulos;
     
    /**
     * La coleccion de procesos de mascota en adopcion que tiene el usuario
     */
    @PodamExclude 
    @OneToMany(mappedBy = "usuario",fetch = FetchType.LAZY)
    Collection<MascotaEnAdopcionEntity> procesosMascotasAdopcion;
  
    
    
    
    
    
    /**
     * Constructor por defecto
     */
    public UsuarioEntity(){
        
    }
    
   /**
     * 
     * @return El usuario con el que esta registrado
     */
    public String getUsuario() {
        return usuario;
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
    public Integer getTelefono() {
        return telefono;
    }
    
    
    /**
     * 
     * @return retorna si el usuario deseas recibir notificaciones
     */
    public Boolean isRecibeNotificaciones() {
        return recibeNotificaciones;
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
    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    /**
     * Cambia si el usario desea recibir notificaciones
     * @param recibeNotificaciones 
     */
    public void setRecibeNotificaciones(Boolean recibeNotificaciones) {
        this.recibeNotificaciones = recibeNotificaciones;
    }

    /**
     * 
     * @return coleccion de procesos de mascota extraviada que tiene el usuario
     */
    public Collection<MascotaExtraviadaEntity> getProcesosMascotaExtraviada() {
        return procesosMascotaExtraviada;
    }

    /**
     * 
     * @return coleccion de articulos que tiene el usuario
     */
    public Collection<ArticuloEntity> getArticulos() {
        return articulos;
    }

    /**
     * 
     * @return coleccion de procesos de adopcion que tiene el usuario 
     */
    public Collection<MascotaEnAdopcionEntity> getProcesosMascotasAdopcion() {
        return procesosMascotasAdopcion;
    }

    /**
     * Cambia los procesos de mascota extraviada que tiene el usuario
     * @param procesosMascotaExtraviada 
     */
    public void setProcesosMascotaExtraviada(Collection<MascotaExtraviadaEntity> procesosMascotaExtraviada) {
        this.procesosMascotaExtraviada = procesosMascotaExtraviada;
    }

    /**
     * Cambia los articulos que tiene el usuario
     * @param articulos 
     */
    public void setArticulos(Collection<ArticuloEntity> articulos) {
        this.articulos = articulos;
    }

    /**
     * Cambia los procesos mascota en adopcion que tiene el usuario
     * @param procesosMascotasAdopcion 
     */
    public void setProcesosMascotasAdopcion(Collection<MascotaEnAdopcionEntity> procesosMascotasAdopcion) {
        this.procesosMascotasAdopcion = procesosMascotasAdopcion;
    }
    
    
    
    
    
    
}

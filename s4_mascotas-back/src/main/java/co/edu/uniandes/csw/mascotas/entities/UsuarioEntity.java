/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;


/**
 *
 * @author Maria Ana Ortiz ma.ortiz1 y Sebastiàn Lemus Cadena
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
    
    /**
     * Lista de articulos
     */
    @PodamExclude
    @OneToMany( mappedBy = "autor" )
    private List<ArticuloEntity> articulos;
    
     /**
     * Lista de eventos
     */
     @PodamExclude
     @OneToMany(mappedBy = "organizador")
    private List<EventoEntity> eventos;
//    
    /**
     * El conjunto de procesos de mascota extraviada que puede tener un usuario
     */
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MascotaExtraviadaEntity> procesosMascotaExtraviada;
    
    /**
     * El conjunto de procesos de mascota en adopciòn de los cuales puede ser dueño el usuario
     */
    @OneToMany(mappedBy = "duenio", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MascotaEnAdopcionEntity> procesosMascotaAdopcion;
    
    /**
     * El conjunto de procesos de mascota en adopciòn a los cuales se puede postular el usuario
     */
    @ManyToMany(mappedBy = "postulados", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MascotaEnAdopcionEntity> postulacionesMascotaAdopcion;
    
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

    public List<ArticuloEntity> getArticulos(){
        return articulos;
    }
    
    public List<EventoEntity> getEventos(){
        return eventos;
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

    public void setRecibeNotificaciones(Boolean recibeNotificaciones) {
        this.recibeNotificaciones = recibeNotificaciones;
    }
    
    public void setArticulos(List<ArticuloEntity> articulos){
        this.articulos = articulos;
    }
    
    public void setEventos(List<EventoEntity> eventos){
        this.eventos = eventos;
    }

    public List<MascotaExtraviadaEntity> getProcesosMascotaExtraviada() {
        return procesosMascotaExtraviada;
    }

    public void setProcesosMascotaExtraviada(List<MascotaExtraviadaEntity> procesosMascotaExtraviada) {
        this.procesosMascotaExtraviada = procesosMascotaExtraviada;
    }

    public List<MascotaEnAdopcionEntity> getProcesosMascotaAdopcion() {
        return procesosMascotaAdopcion;
    }

    public void setProcesosMascotaAdopcion(List<MascotaEnAdopcionEntity> procesosMascotaAdopcion) {
        this.procesosMascotaAdopcion = procesosMascotaAdopcion;
    }

    public List<MascotaEnAdopcionEntity> getPostulacionesMascotaAdopcion() {
        return postulacionesMascotaAdopcion;
    }

    public void setPostulacionesMascotaAdopcion(List<MascotaEnAdopcionEntity> postulacionesMascotaAdopcion) {
        this.postulacionesMascotaAdopcion = postulacionesMascotaAdopcion;
    }
    
    
    
}

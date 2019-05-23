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
     * constante que representa un usuario de tipo administrador
     */
    public static final String  ADMIN = "ADMIN";
    
    
    /**
     * Constante que representa un usuario normal
     */
    public static final String  NORMAL = "NORMAL";
    
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
    private String telefono;
    
     /**
<<<<<<< HEAD
     * dia de nacimiento
=======
     * ..
>>>>>>> ac93a846573e30c6712b5a9232ea0ebd96f3fdde
     */
    private String dayBirth;
    
    /**
<<<<<<< HEAD
     * Mes de nacimiento
=======
     * ..
>>>>>>> ac93a846573e30c6712b5a9232ea0ebd96f3fdde
     */
    private String monthBirth;
    
    /**
<<<<<<< HEAD
     * Año de nacimiento
=======
     * ..
>>>>>>> ac93a846573e30c6712b5a9232ea0ebd96f3fdde
     */
    private String yearBirth;
    
    /**
     * Rol que tiene el usuario 
     */
    private String rol;
    
    /**
     * Ruta imagen perfil
     */
    private String rutaImagen;
    

    
    /**
     * Lista de articulos
     */
    @PodamExclude
    @OneToMany( mappedBy = "autor",cascade = CascadeType.ALL )
    private List<ArticuloEntity> articulos;
    
     /**
     * Lista de eventos
     */
     @PodamExclude
     @OneToMany(mappedBy = "organizador",cascade = CascadeType.ALL)
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
    
<<<<<<< HEAD
    /**
     * 
     */
    public UsuarioEntity(){
=======
    public UsuarioEntity()
    {
>>>>>>> ac93a846573e30c6712b5a9232ea0ebd96f3fdde
        
    }
    
    /**
     * Registra si el usuario desea recibir notificaciones
     */
    private Boolean recibeNotificaciones;

    /**
     * 
     * @return 
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * 
     * @return 
     */
    public String getContrasenha() {
        return contrasenha;
    }

    /**
     * 
     * @return 
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * 
     * @return 
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * 
     * @return 
     */
    public String getTelefono() {
        return this.telefono;
    }

    /**
     * 
     * @return 
     */
    public List<ArticuloEntity> getArticulos(){
        return articulos;
    }
    /**
     * 
     * @return 
     */
    public List<EventoEntity> getEventos(){
        return eventos;
    }
    /**
     * 
     * @return 
     */
    public Boolean getRecibeNotificaciones(){
        return recibeNotificaciones;
    }
    /**
     * 
     * @param usuario 
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * 
     * @param contrasenha 
     */
    public void setContrasenha(String contrasenha) {
        this.contrasenha = contrasenha;
    }

    /**
     * 
     * @param nombre 
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * 
     * @param correo 
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

<<<<<<< HEAD
   

    /**
     * 
     * @param recibeNotificaciones 
     */
=======
>>>>>>> ac93a846573e30c6712b5a9232ea0ebd96f3fdde
    public void setRecibeNotificaciones(Boolean recibeNotificaciones) {
        this.recibeNotificaciones = recibeNotificaciones;
    }
    
    /**
     * 
     * @param articulos 
     */
    public void setArticulos(List<ArticuloEntity> articulos){
        this.articulos = articulos;
    }
    
    /**
     * 
     * @param eventos 
     */
    public void setEventos(List<EventoEntity> eventos){
        this.eventos = eventos;
    }

    /**
     * 
     * @return 
     */
    public List<MascotaExtraviadaEntity> getProcesosMascotaExtraviada() {
        return procesosMascotaExtraviada;
    }

    /**
     * 
     * @param procesosMascotaExtraviada 
     */
    public void setProcesosMascotaExtraviada(List<MascotaExtraviadaEntity> procesosMascotaExtraviada) {
        this.procesosMascotaExtraviada = procesosMascotaExtraviada;
    }

    /**
     * 
     * @return 
     */
    public List<MascotaEnAdopcionEntity> getProcesosMascotaAdopcion() {
        return procesosMascotaAdopcion;
    }

    /**
     * 
     * @param procesosMascotaAdopcion 
     */
    public void setProcesosMascotaAdopcion(List<MascotaEnAdopcionEntity> procesosMascotaAdopcion) {
        this.procesosMascotaAdopcion = procesosMascotaAdopcion;
    }

    /**
     * 
     * @return 
     */
    public List<MascotaEnAdopcionEntity> getPostulacionesMascotaAdopcion() {
        return postulacionesMascotaAdopcion;
    }

    /**
     * 
     * @param postulacionesMascotaAdopcion 
     */
    public void setPostulacionesMascotaAdopcion(List<MascotaEnAdopcionEntity> postulacionesMascotaAdopcion) {
        this.postulacionesMascotaAdopcion = postulacionesMascotaAdopcion;
    }

    /**
     * 
     * @return 
     */
    public String getDayBirth() {
        return dayBirth;
    }

    /**
     * 
     * @return 
     */
    public String getMonthBirth() {
        return monthBirth;
    }

    /**
     * 
     * @return 
     */
    public String getYearBirth() {
        return yearBirth;
    }

    /**
     * 
     * @param dayBirth 
     */
    public void setDayBirth(String dayBirth) {
        this.dayBirth = dayBirth;
    }

    /**
     * 
     * @param monthBirth 
     */
    public void setMonthBirth(String monthBirth) {
        this.monthBirth = monthBirth;
    }

    /**
     * 
     * @param yearBirth 
     */
    public void setYearBirth(String yearBirth) {
        this.yearBirth = yearBirth;
    }
    /**
     * 
     * @param telefono 
     */
     public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

     /**
      * 
      * @return 
      */
    public String getRol() {
        return rol;
    }

    /**
     * 
     * @param rol 
     */
    public void setRol(String rol) {
        this.rol = rol;
    }

    /**
     * 
     * @return 
     */
    public String getRutaImagen() {
        return rutaImagen;
    }
    
    /**
     * 
     * @param rutaImagen 
     */
    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }

    
    
    
}

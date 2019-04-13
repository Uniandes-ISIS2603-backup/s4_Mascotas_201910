/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.dtos;

import co.edu.uniandes.csw.mascotas.entities.ArticuloEntity;
import co.edu.uniandes.csw.mascotas.entities.EventoEntity;
import co.edu.uniandes.csw.mascotas.entities.UsuarioEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author Maria Ana Ortiz Botero 
  */
public class UsuarioDTO implements Serializable{
    
    /**
     * 
     */
    private Long id;
    
    /**
     * 
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
     * Registra si el usuario desea recibir notificaciones
     */
    private Boolean recibeNotificaciones;
    
    /**
     * Dia de nacimiento
     */
    private String dayBirth;
    
    /**
     * Mes de nacimiento
     */
    private String monthBirth;
    
    /**
     * Año de nacimiento
     */
    private String yearBirth;
    
    
     /**
     * Lista de articulos
     */
   // private List<ArticuloDTO> articulos;
    
     /**
     * Lista de eventos
     */
   // private List<EventoDTO> eventos;

    /**
     * Constructor
     */
    public UsuarioDTO(){
        
    }
    
    public UsuarioDTO(UsuarioEntity e)
    {
        if (e!=null){
            
            this.usuario= e.getUsuario();
            this.contrasenha = e.getContrasenha();
            this.correo = e.getCorreo();
            this.recibeNotificaciones = e.isRecibeNotificaciones();
            this.telefono = e.getTelefono();
            this.nombre = e.getNombre();
            this.id=e.getId();
            this.dayBirth=e.getDayBirth();
            this.monthBirth=e.getMonthBirth();
            this.yearBirth= e.getYearBirth();
            
        }
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
    public void setTelefono(String telefono) {
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
    public String getTelefono() {
        return telefono;
    }
    
    
    /**
     * 
     * @return retorna si el usuario deseas recibir notificaciones
     */
    public Boolean isRecibeNotificaciones() {
        return recibeNotificaciones;
    }
    
   // public List<ArticuloDTO> getArticulos(){
     //   return articulos;
    //}
    
    //public void setArticulos(List<ArticuloDTO> articulos){
      //  this.articulos = articulos;
    //}

    //public List<EventoDTO> getEventos(){
      //  return eventos;
    //}
    
    //public void setEventos(List<EventoDTO> eventos){
      //  this.eventos = eventos;
    //}
    
    public UsuarioEntity toEntity(){
        UsuarioEntity entity = new UsuarioEntity();
        entity.setUsuario(this.usuario);
        entity.setNombre(this.nombre);
        entity.setCorreo(this.correo);
        entity.setTelefono(this.telefono);
        entity.setRecibeNotificaciones(this.recibeNotificaciones);
        entity.setContrasenha(this.contrasenha);
        entity.setId(this.id);
        entity.setMonthBirth(this.monthBirth);
        entity.setDayBirth(this.dayBirth);
        entity.setYearBirth(this.yearBirth);
        //entity.setArticulos(convertArticulosToEntity(this.articulos));
        //entity.setEventos(convertEventosToEntity(this.eventos));
 
     return entity;         
    }
    
   // public List<ArticuloEntity> convertArticulosToEntity(List<ArticuloDTO> as){
     //   List<ArticuloEntity> ls = new ArrayList<>();
       // for(ArticuloDTO a : as){
         //   ls.add(a.toEntity());
        //}
        //return ls;
    //}
    
    //public List<EventoEntity> convertEventosToEntity(List<EventoDTO> as){
      //  List<EventoEntity> ls = new ArrayList<>();
        //for(EventoDTO a : as){
          //  ls.add(a.toEntity());
        //}
        //return ls;
    //}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    
   
    public String getDayBirth() {
        return dayBirth;
    }

    public String getMonthBirth() {
        return monthBirth;
    }

    public String getYearBirth() {
        return yearBirth;
    }

    public void setDayBirth(String dayBirth) {
        this.dayBirth = dayBirth;
    }

    public void setMonthBirth(String monthBirth) {
        this.monthBirth = monthBirth;
    }

    public void setYearBirth(String yearBirth) {
        this.yearBirth = yearBirth;
    }
    
     @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
            
    
}

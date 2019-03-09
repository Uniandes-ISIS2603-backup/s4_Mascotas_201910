/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.dtos;

import co.edu.uniandes.csw.mascotas.entities.EventoEntity;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Daniela Gonzalez
 */
public class EventoDTO implements Serializable{
    
    private Long id;
    private String nombre;
    private String descripcion;
    private String imagen;
    private Date fechaInicio;
    private Date fechaFin;
    
    /*
    * Relación a un usuario dado que esta tiene cardinalidad 1.
     */
    private UsuarioDTO organizador;
    
    /**
     * Constructor por defecto
     */
    public EventoDTO () {
        
    }
    
    /**
     * Constructor a partir de una entidad
     *
     * @param entity La entidad de la cual se construye el DTO
     */
     public EventoDTO (EventoEntity entity) {
        
         this.id = entity.getId();
         this.nombre = entity.getNombre();
         this.descripcion = entity.getDescripcion();
         this.imagen = entity.getImagen();
         this.fechaInicio = entity.getFechaInicio();
         this.fechaFin = entity.getFechaFin();
         
         if(entity.getOrganizador() != null){
             this.organizador = new UsuarioDTO(entity.getOrganizador());
         }
         else{
             this.organizador = null;
         }
    }
    
    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the imagen
     */
    public String getImagen() {
        return imagen;
    }

    /**
     * @param imagen the imagen to set
     */
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    /**
     * @return the fechaInicio
     */
    public Date getFechaInicio() {
        return fechaInicio;
    }

    /**
     * @param fechaInicio the fechaInicio to set
     */
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /**
     * @return the fechaFin
     */
    public Date getFechaFin() {
        return fechaFin;
    }

    /**
     * @param fechaFin the fechaFin to set
     */
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }
    
    /**
     * @return the organizador
     */
    public UsuarioDTO getOrganizador() {
        return organizador;
    }

    /**
     * @param elOrganizador the organizador to set
     */
    public void setOrganizador(UsuarioDTO elOrganizador) {
      this.organizador = elOrganizador;
    }
 
          /**
     * Convertir de DTO a Entity
     * @return Un EventoEntity con los valores del DTO
     */
    public EventoEntity toEntity(){
        
        EventoEntity entity = new EventoEntity();
        entity.setNombre(this.nombre);
        entity.setDescripcion(this.descripcion);
        entity.setImagen(this.imagen);
        entity.setFechaInicio(this.fechaInicio);
        entity.setFechaFin(this.fechaFin);
        
        if(this.organizador != null){
            entity.setOrganizador(this.organizador.toEntity());
        }
        
        return entity;
    }
    
    
}
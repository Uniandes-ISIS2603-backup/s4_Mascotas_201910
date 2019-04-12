/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.dtos;

import co.edu.uniandes.csw.mascotas.entities.CalificacionEntity;
import java.io.Serializable;

/**
 *
 * @author s.canales
 */
public class CalificacionDTO implements Serializable{
    
    private Long id;
    /**
     * calificación numérica asociada al proceso de adopción
     */
    private Integer calificacion;
    
    /**
     * comentario del proceso de adopción
     */
    private String comentario;
    /**
     * constructor
     */
    public CalificacionDTO() {
    
}
    public CalificacionDTO(CalificacionEntity e){
        
        this.id = e.getId();
        this.calificacion = e.getCalificacion();
        this.comentario = e.getComentario();
       
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
     * @return the calificacion
     */
    public Integer getCalificacion() {
        return calificacion;
    }

    /**
     * @param calificacion the calificacion to set
     */
    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }

    /**
     * @return the comentario
     */
    public String getComentario() {
        return comentario;
    }

    /**
     * @param comentario the comentario to set
     */
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
    
    public CalificacionEntity toEntity(){
        
        CalificacionEntity entidad = new CalificacionEntity();
        entidad.setComentario(comentario);
        entidad.setCalificacion(calificacion);
        return entidad;
    }
}

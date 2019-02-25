/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.dtos;

import co.edu.uniandes.csw.mascotas.entities.MascotaEnAdopcionEntity;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author s.canales
 */
public class MascotaEnAdopcionDTO implements Serializable{
    
    private Long id;
    
    /**
     * razon por la cual va a ser postulada la mascota para adopción
     */
    private String razonAdopcion;
    
    /**
     * pasado de la mascota
     */
    private String pasado;
    
    /**
     * fecha de inicio y fin del proceso de adopción
     */
    private Date fechaInicio;
    private Date fechaFinal;
    
    /**
     * estado de adopción de la mascota
     */
    
    private boolean adoptada;
    
    public MascotaEnAdopcionDTO(){
    
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
     * @return the razonAdopcion
     */
    public String getRazonAdopcion() {
        return razonAdopcion;
    }

    /**
     * @param razonAdopcion the razonAdopcion to set
     */
    public void setRazonAdopcion(String razonAdopcion) {
        this.razonAdopcion = razonAdopcion;
    }

    /**
     * @return the pasado
     */
    public String getPasado() {
        return pasado;
    }

    /**
     * @param pasado the pasado to set
     */
    public void setPasado(String pasado) {
        this.pasado = pasado;
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
     * @return the fechaFinal
     */
    public Date getFechaFinal() {
        return fechaFinal;
    }

    /**
     * @param fechaFinal the fechaFinal to set
     */
    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    /**
     * @return the adoptada
     */
    public boolean isAdoptada() {
        return adoptada;
    }

    /**
     * @param adoptada the adoptada to set
     */
    public void setAdoptada(boolean adoptada) {
        this.adoptada = adoptada;
    }
    
    public MascotaEnAdopcionEntity toEntity(){
        
        MascotaEnAdopcionEntity entity = new MascotaEnAdopcionEntity();
        entity.setAdoptada(adoptada);
        entity.setFechaFinal(fechaFinal);
        entity.setFechaInicio(fechaInicio);
        entity.setPasado(pasado);
        entity.setRazonAdopcion(razonAdopcion);
        
        return entity;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.dtos;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Johan E. Vivas Sepulveda (je.vivas)
 */
public class MascotaEncontradaDTO implements Serializable{
    /**
     * Nimero de Id que tiene el proceso.
     */
    private Long id;
    
    /**
     * Fecha en la que fue encontrada la mascota.
     */
    private Date fechaInicio;
    
    /**
     * Fecha en que la mascota fue recogida.
     */
    private Date fechaFin;
    
    /**
     * Estado de finalización del proceso mascota encontrada.
     * PENDIENTE - RECOGIDA.
     */
    private String estado;
    
    /**
     * Ubicación donde fue encontrada la mascota.
     */
    private String ubicacion;
    
    /**
     * Descripción de la mascota encontrada.
     */
    private String descripcion;
    
    /**
     * 
     */
    public MascotaEncontradaDTO(){
        
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
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * @return the ubicacion
     */
    public String getUbicacion() {
        return ubicacion;
    }

    /**
     * @param ubicacion the ubicacion to set
     */
    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
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
}

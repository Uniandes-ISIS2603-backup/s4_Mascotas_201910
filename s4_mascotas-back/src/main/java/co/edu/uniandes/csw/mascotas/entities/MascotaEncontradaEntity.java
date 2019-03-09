/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author Johan E. Vivas Sepulveda (je.vivas)
 */

@Entity
public class MascotaEncontradaEntity extends BaseEntity implements Serializable{
    
    @Temporal(TemporalType.DATE)
    private Date fechaInicializacion;
    
    @Temporal(TemporalType.DATE)
    private Date fechaFinalizacion;
    
    private String estado;
    
    private String ubicacion;
    
    private String descripcion;
    
    public static final String PENDIENTE = "PENDIENTE";
    
    public static final String ENTREGADA = "ENTREGADA";
    
    @PodamExclude
    @ManyToOne
    private MascotaEntity mascota;
    
    public MascotaEncontradaEntity () {
        
    }

    /**
     * @return the fechaInicio
     */
    public Date getFechaInicializacion() {
        return fechaInicializacion;
    }

    /**
     * @param fechaInicio the fechaInicio to set
     */
    public void setFechaInicializacion(Date fechaInicio) {
        this.fechaInicializacion = fechaInicio;
    }

    /**
     * @return the fechaFin
     */
    public Date getFechaFinalizacion() {
        return fechaFinalizacion;
    }

    /**
     * @param fechaFin the fechaFin to set
     */
    public void setFechaFinalizacion(Date fechaFin) {
        this.fechaFinalizacion = fechaFin;
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

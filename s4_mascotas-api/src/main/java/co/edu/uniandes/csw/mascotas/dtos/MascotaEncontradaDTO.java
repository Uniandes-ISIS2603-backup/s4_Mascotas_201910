/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.dtos;

import java.io.Serializable;
import java.util.Date;
import co.edu.uniandes.csw.mascotas.entities.MascotaEncontradaEntity;

/**
 *
 * @author Johan E. Vivas Sepulveda (je.vivas) / Natalia Sanabra Forero (n.sanabria)
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
     * Constructor vacío por defecto
     */
    public MascotaEncontradaDTO()
    {
        // Constructor por defecto
    }
    
    /**
     * Constructor a partir 
     * @param e 
     */
    public MascotaEncontradaDTO(MascotaEncontradaEntity e)
    {
        if(e!=null)
        {
            this.id = e.getId();
            this.estado = e.getEstado();
            this.fechaFin = e.getFechaFinalizacion();
            this.fechaInicio = e.getFechaInicializacion();
            this.ubicacion = e.getUbicacion();
        }
    }

    /**
     * Construye una entidad a partir del DTO
     * @return MascotaEncontradaEntity
     */
    public MascotaEncontradaEntity toEntity()
    {
        MascotaEncontradaEntity e = new MascotaEncontradaEntity( );
        e.setEstado(estado);
        e.setFechaFinalizacion(fechaFin);
        e.setFechaInicializacion(fechaInicio);
        e.setId(id);
        e.setUbicacion(ubicacion);
        return e;
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
    public void setUbicacion(String ubicacion) 
    {
        this.ubicacion = ubicacion;
    }
}

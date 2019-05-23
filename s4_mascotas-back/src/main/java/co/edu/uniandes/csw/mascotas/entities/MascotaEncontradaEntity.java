/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
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
    
    public static final String PENDIENTE = "PENDIENTE";
    
    public static final String ENTREGADA = "ENTREGADA";
    
     /**
     * La informaciòn de la mascota relacionada a la mascota
     */
    @PodamExclude
    @OneToOne(mappedBy = "procesoMascotaEncontrada", cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    private MascotaEntity mascota;
        
    /*
    * Información del usuario dueño del proceso
    */
    @PodamExclude
    @OneToOne
    private UsuarioEntity usuario;
    
    /**
     * Constructor vacío por defecto
     */
    public MascotaEncontradaEntity () 
    {
        // Constructor vacío por defecto
    }

    /**
     * @return the fechaInicio
     */
    public Date getFechaInicializacion() 
    {
        return fechaInicializacion;
    }

    /**
     * @param fechaInicio the fechaInicio to set
     */
    public void setFechaInicializacion(Date fechaInicio) 
    {
        this.fechaInicializacion = fechaInicio;
    }

    /**
     * @return the fechaFin
     */
    public Date getFechaFinalizacion() 
    {
        return fechaFinalizacion;
    }

    /**
     * @param fechaFin the fechaFin to set
     */
    public void setFechaFinalizacion(Date fechaFin) 
    {
        this.fechaFinalizacion = fechaFin;
    }

    /**
     * @return the estado
     */
    public String getEstado() 
    {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) 
    {
        this.estado = estado;
    }

    /**
     * @return the ubicacion
     */
    public String getUbicacion() 
    {
        return ubicacion;
    }

    /**
     * @param ubicacion the ubicacion to set
     */
    public void setUbicacion(String ubicacion) 
    {
        this.ubicacion = ubicacion;
    }
    
    public UsuarioEntity getUsuario( )
    {
        return usuario;
    }
    
    public void setUsuario( UsuarioEntity usuario )
    {
        this.usuario = usuario;
    }
    
    public MascotaEntity getMascota( )
    {
        return mascota;
    }
    
    public void setMascota( MascotaEntity mascota )
    {
        this.mascota = mascota;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author Natalia Sanabria Forero (n.sanabria)
 */
@Entity
public class MascotaEntity extends BaseEntity implements Serializable 
{   
    private List<String> fotos;
    
    private String raza;
    
    private String descripcion;
    
    private String tipo;
    
    private Integer estado;
    
    /**
     * El proceso de mascota extraviada que contiene la 
     * informaciòn de la mascota (solo si existe dicho proceso)
     */
    @PodamExclude
    @OneToOne(mappedBy = "mascota", fetch=FetchType.LAZY)
    private MascotaExtraviadaEntity procesoMascotaExtraviada;

    // Constructor vacío por defecto
    public MascotaEntity( )
    {
        
    }

    /**
     * @return the fotos
     */
    public List<String> getFotos() {
        return fotos;
    }

    /**
     * @param fotos the fotos to set
     */
    public void setFotos(List<String> fotos) {
        this.fotos = fotos;
    }

    /**
     * @return the raza
     */
    public String getRaza() {
        return raza;
    }

    /**
     * @param raza the raza to set
     */
    public void setRaza(String raza) {
        this.raza = raza;
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
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the estado
     */
    public int getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    /**
     * 
     * @return El proceso de mascota extraviada asociado (si existe)
     */
    public MascotaExtraviadaEntity getProcesoMascotaExtraviada() {
        return procesoMascotaExtraviada;
    }
    
    /**
     * Modifica el proceso de mascota extraviada asociado 
     * @param procesoMascotaExtraviada 
     */
    public void setProcesoMascotaExtraviada(MascotaExtraviadaEntity procesoMascotaExtraviada) {
        this.procesoMascotaExtraviada = procesoMascotaExtraviada;
    }
    
    
}
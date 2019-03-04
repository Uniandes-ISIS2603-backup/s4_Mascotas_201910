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
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author Natalia Sanabria Forero (n.sanabria)
 */
@Entity
public class MascotaEntity extends BaseEntity implements Serializable 
{   
    
     /**
     * Constante que define el tipo de mascota perro 
     */
    public static final String PERRO = "PERRO";
    
    /**
     * Constante que define el tipo de mascota gato
     */
    public static final String GATO = "GATO";
    
    public enum Estados_mascota
    {
        EN_ADOPCION, EXTRAVIADO, ENCONTRADO, ADOPTADO;  
        
        /**
         * Verifica si la enumeración contiene un valor
         * @param s Valor a verificar
         * @return True si el valor existe dentro de la enumeración, false de lo contrario
         */
        public boolean contains(String s){
            for(Estados_mascota estado:values())
                if (estado.name().equals(s)) 
                    return true;
             return false;
        }
        
    }
    
    private List<String> fotos;
    
    private String raza;
    
    private String descripcion;
    
    private String tipo;
    
    private Estados_mascota estado;
    
    
    
    /**
     * El proceso de mascota extraviada que contiene la 
     * información de la mascota (solo si existe dicho proceso)
     */
    @PodamExclude
    @OneToOne(mappedBy = "mascota", cascade = CascadeType.ALL,fetch=FetchType.LAZY)
    private MascotaExtraviadaEntity procesoMascotaExtraviada;

    /** Constructor vacío por defecto */
    public MascotaEntity( )
    {
        // Implementación por default de la entidad
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
    public Estados_mascota getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(Estados_mascota estado) {
        this.estado = estado;
    }

    /**
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
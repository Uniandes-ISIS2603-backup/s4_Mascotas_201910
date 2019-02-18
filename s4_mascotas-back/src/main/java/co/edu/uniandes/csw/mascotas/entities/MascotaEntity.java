/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Natalia Sanabria Forero
 */
@Entity
public class MascotaEntity extends BaseEntity implements Serializable 
{   
    private ArrayList<String> fotos;
    
    private String raza;
    
    private String descripcion;
    
    private String tipo;
    
    private Integer estado;

    // Constructor vacío por defecto
    public MascotaEntity( )
    {
        
    }

    /**
     * @return the fotos
     */
    public ArrayList<String> getFotos() {
        return fotos;
    }

    /**
     * @param fotos the fotos to set
     */
    public void setFotos(ArrayList<String> fotos) {
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
    
}
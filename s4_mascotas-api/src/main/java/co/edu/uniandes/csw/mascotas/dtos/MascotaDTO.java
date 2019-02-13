/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.dtos;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author estudiante
 */
public class MascotaDTO implements Serializable
{
    // ATRIBUTOS
    
    private String id;
    
    private enum tipo{
        PERRO, GATO
    };
    
    private enum estados{
        EN_ADOPCION, EXTRAVIADO, ENCONTRADO, ADOPTADO
    }
    
    private String estado_mascota;
    
    private ArrayList<String> fotos;
    
    private String raza;
    
    private String descripcion;

    // COSNTRUCTOR
    
    public MascotaDTO( )
    {
        
    }
    
    // METODOS
    
    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
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
     * @return the estado_mascota
     */
    public String getEstado_mascota() {
        return estado_mascota;
    }

    /**
     * @param estado_mascota the estado_mascota to set
     */
    public void setEstado_mascota(String estado_mascota) {
        this.estado_mascota = estado_mascota;
    }
    
    
}

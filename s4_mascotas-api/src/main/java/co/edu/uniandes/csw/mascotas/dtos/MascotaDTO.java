/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.dtos;

import co.edu.uniandes.csw.mascotas.entities.MascotaEntity;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Natalia Sanabria Forero (n.sanabria)
 */
public class MascotaDTO implements Serializable
{
    // ATRIBUTOS
    
    /**
     * Identificador único de la mascota
     */
    private Long id;
    
    /**
     * Nombre de la mascota
     */
    private String nombre;
    
    /**
     * Tipo de la mascota. Solo admite elementos de {PERRO, GATO}
     */
    private String tipo;
    
    /**
     * Describe el estado de la mascota
     */
    private MascotaEntity.Estados_mascota estado;
    
    /**
     * Considera una lista con las fotos o videos de la mascota
     */
    private List<String> fotos;
    
    /**
     * Raza de la mascota
     */
    private String raza;
    
    /**
     * Descripción de la mascota
     */
    private String descripcion;

    // COSNTRUCTOR
    
    /**
     * Constructor vacío
     */
    public MascotaDTO( )
    {
        
    }
    
    /**
     * Constructor que crea un DTO a partir de una entidad <br>
     * @param entity Entidad con la información para crear una nueva instancia de DTO <br>
     */
    public MascotaDTO(MascotaEntity entity)
    {
        if(entity!= null)
        {
            this.id = entity.getId();
            this.raza = entity.getRaza();
            this.tipo = entity.getTipo();
            this.estado = entity.getEstado();
            this.descripcion = entity.getDescripcion();
            this.fotos = entity.getFotos();
            this.nombre = entity.getNombre();
        }
    }
    
    // METODOS
    
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
    public MascotaEntity.Estados_mascota getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(MascotaEntity.Estados_mascota estado) {
        this.estado = estado;
    }
    
    /**
     * Convierte un DTO en una entidad
     * @return entity Entidad con la información del DTO de origen
     */
    public MascotaEntity toEntity( )
    {
        MascotaEntity entity = new MascotaEntity( );
        entity.setId(this.id);
        entity.setEstado(this.estado);
        entity.setRaza(this.raza);
        entity.setDescripcion(this.descripcion);
        entity.setTipo(this.tipo);
        entity.setFotos(this.fotos);
        entity.setNombre(this.nombre);
        return entity;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
}
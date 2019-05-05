/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.dtos;

import co.edu.uniandes.csw.mascotas.entities.ClasificadoEntity;
import java.io.Serializable;

/**
 *
 * @author Johan E. Vivas Sepulveda (je.vivas)
 */
public class ClasificadoDTO implements Serializable{
    
    private Long id;
    private String nombre;
    private String contenido;
    private String enlace;
    
    /*
    * Relación a un usuario dado que esta tiene cardinalidad 1.
     */
    private UsuarioDTO autor;
    
    /**
     * Constructor por defecto
     */
    public ClasificadoDTO() {

    }

    /**
     * Constructor a partir de una entidad
     *
     * @param entity La entidad de la cual se construye el DTO
     */
    public ClasificadoDTO(ClasificadoEntity entity) {

        if (entity != null) {

            this.id = entity.getId();
            this.nombre = entity.getNombre();
            this.contenido = entity.getContenido();
            this.enlace = entity.getEnlace();

            if (entity.getAutor() != null) {
                this.autor = new UsuarioDTO(entity.getAutor());
            } else {
                this.autor = null;
            }
        }

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

    /**
     * @return the contenido
     */
    public String getContenido() {
        return contenido;
    }

    /**
     * @param contenido the contenido to set
     */
    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    /**
     * @return the enlace
     */
    public String getEnlace() {
        return enlace;
    }

    /**
     * @param enlace the enlace to set
     */
    public void setEnlace(String enlace) {
        this.enlace = enlace;
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
     * @return the autor
     */
    public UsuarioDTO getAutor() {
        return autor;
    }

    /**
     * @param autor the autor to set
     */
    public void setAutor(UsuarioDTO autor) {
        this.autor = autor;
    }
    
     /**
     * Convertir de DTO a Entity
     *
     * @return Un ArticuloEntity con los valores del DTO
     */
    public ClasificadoEntity toEntity() {

        ClasificadoEntity entity = new ClasificadoEntity();
        entity.setNombre(this.nombre);
        entity.setContenido(this.contenido);
        entity.setEnlace(this.enlace);
       
         if(this.autor != null){
            entity.setAutor(this.autor.toEntity());
        }

        return entity;
    }
}

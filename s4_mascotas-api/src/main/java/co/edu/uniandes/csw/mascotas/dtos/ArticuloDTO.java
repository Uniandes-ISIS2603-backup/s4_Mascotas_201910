/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.dtos;

import co.edu.uniandes.csw.mascotas.entities.ArticuloEntity;
import java.io.Serializable;

/**
 *
 * @author Daniela González
 */
public class ArticuloDTO implements Serializable {

    // ---------------------------------------
    // Constantes
    // ---------------------------------------
    
    /**
     * Constante que define el tema de cuidado del animal  
     */
    public final static String CUIDADO = "CUIDADO";
    
     /**
     * Constante que define el tema de salud del animal  
     */
    public final static String SALUD = "SALUD";
    
     /**
     * Constante que define el tema de entrenamiento del animal  
     */
    public final static String ENTRENAMIENTO = "ENTRENAMIENTO";
    
     /**
     * Constante que define el tema de tenencia responsable del animal  
     */
    public final static String TENENCIA_RESPONSABLE = "TENENCIA RESPONSABLE";
    
    private Long id;
    private String titulo;
    private String tema;
    private String contenido;
    private String resumen;
    
    /*
    * Relación a un usuario dado que esta tiene cardinalidad 1.
     */
    private UsuarioDTO autor;

    /**
     * Constructor por defecto
     */
    public ArticuloDTO() {

    }

    /**
     * Constructor a partir de una entidad
     *
     * @param entity La entidad de la cual se construye el DTO
     */
    public ArticuloDTO(ArticuloEntity entity) {

        if (entity != null) {

            this.id = entity.getId();
            this.titulo = entity.getTitulo();
            this.tema = entity.getTema();
            this.contenido = entity.getContenido();
            this.resumen = entity.getResumen();

            if (entity.getAutor() != null) {
                this.autor = new UsuarioDTO(entity.getAutor());
            } else {
                this.autor = null;
            }
        }

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
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @return the tema
     */
    public String getTema() {
        return tema;
    }

    /**
     * @param tema the tema to set
     */
    public void setTema(String tema) {
        this.tema = tema;
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
     * @return the resumen
     */
    public String getResumen() {
        return resumen;
    }

    /**
     * @param resumen the resumen to set
     */
    public void setResumen(String resumen) {
        this.resumen = resumen;
    }
    
    /**
     * @return the autor
     */
    public UsuarioDTO getAutor() {
        return autor;
    }

    /**
     * @param elAutor the autor to set
     */
    public void setAutor(UsuarioDTO elAutor) {
        this.autor = elAutor;
    }

    /**
     * Convertir de DTO a Entity
     *
     * @return Un ArticuloEntity con los valores del DTO
     */
    public ArticuloEntity toEntity() {

        ArticuloEntity entity = new ArticuloEntity();
        entity.setTitulo(this.titulo);
        entity.setTema(this.tema);
        entity.setContenido(this.contenido);
       
         if(this.autor != null){
            entity.setAutor(this.autor.toEntity());
        }

        return entity;
    }

}

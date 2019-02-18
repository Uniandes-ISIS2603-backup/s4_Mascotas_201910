/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.dtos;

import java.io.Serializable;

/**
 *
 * @author estudiante
 */
public class ArticuloDTO implements Serializable{
    
    private Long id;
    private String titulo;
    private String tema;
    private String contenido;
    private UsuarioDTO autor;

    public ArticuloDTO () { 
    
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
    
}

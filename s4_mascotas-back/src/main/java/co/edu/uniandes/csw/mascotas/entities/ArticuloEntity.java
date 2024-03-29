/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author Daniela Gonzalez
 */

@Entity
public class ArticuloEntity extends BaseEntity implements Serializable  {
    
    private String titulo;
    private String tema;
    private String contenido;
    private String resumen;
   
    @PodamExclude
    @ManyToOne
    private UsuarioEntity autor;
   
    
    /**Constructor por defecto
     */
    public ArticuloEntity(){
        
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
    public UsuarioEntity getAutor() {
          return autor;
    }
    
    /**
     * @param autor the autor to set
     */
    public void setAutor(UsuarioEntity autor) {
         this.autor = autor;
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
    
    
    
}

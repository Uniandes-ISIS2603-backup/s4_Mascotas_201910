/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.dtos;

import co.edu.uniandes.csw.mascotas.entities.UsuarioEntity;
import java.util.List;

/**
 *
 * @author Sebastián Lemus Cadena (s.lemus) y Maria Ana Ortiz Botero 
 */
public class UsuarioDetailDTO {
    
    /**
     * Conjunto de artìculos que puede tener y crear un usuario
     */
    public List<ArticuloDTO> articulos;
    
    /**
     * Conjunto de eventos que puede organizar un usuario
     */
    public List<EventoDTO> eventos;
    
    /**
     * Falta clase de clasificados DTO - JOHAN VIVAS
     */
    
    /**
     * Conjunto de procesos de adopciòn de mascota que puede tener un usuario
     */
    public List<MascotaEnAdopcionDTO> procesosMascotaAdopcion;
    
    /**
     * Falta clase de MascotaEncontrada - JOHAN VIVAS
     */
    public List<MascotaExtraviadaDTO> procesosMascotaExtraviada;
    
    /**
     * Constructor por defecto
     */
    public UsuarioDetailDTO(){
        super();
    }
    
    /**
     * Crea un usario con sus asociaciones de acuerdo a la informaciòn 
     * del entity dado
     * @param entity 
     */
    public UsuarioDetailDTO(UsuarioEntity entity){
        
    }
    
    /**
     * 
     * @return La representaciòn del usuarioDTO en entidad
     */
    public UsuarioEntity toEntity(){
        return null;
    }
    
    /**
     * 
     * @return El conjunto de artìculos
     */
    public List<ArticuloDTO> getArticulos() {
        return articulos;
    }
    
    /**
     * Modicfica el conjunto de artìculos
     * @param articulos 
     */
    public void setArticulos(List<ArticuloDTO> articulos) {
        this.articulos = articulos;
    }

    /**
     * 
     * @return El conjunto de eventos
     */
    public List<EventoDTO> getEventos() {
        return eventos;
    }

    /**
     * Modifica el conjunto de eventos
     * @param eventos 
     */
    public void setEventos(List<EventoDTO> eventos) {
        this.eventos = eventos;
    }

    /**
     * 
     * @return El conjunto de procesos de adopciòn
     */
    public List<MascotaEnAdopcionDTO> getProcesosMascotaAdopcion() {
        return procesosMascotaAdopcion;
    }

    /**
     * Modifica el conjunto de procesos de adopciòn
     * @param procesosMascotaAdopcion 
     */
    public void setProcesosMascotaAdopcion(List<MascotaEnAdopcionDTO> procesosMascotaAdopcion) {
        this.procesosMascotaAdopcion = procesosMascotaAdopcion;
    }

    /**
     * 
     * @return El conjunto de procesos de mascota extraviada
     */
    public List<MascotaExtraviadaDTO> getProcesosMascotaExtraviada() {
        return procesosMascotaExtraviada;
    }

    /**
     * Modifica el conjunto de procesos de mascota extraviada
     * @param procesosMascotaExtraviada 
     */
    public void setProcesosMascotaExtraviada(List<MascotaExtraviadaDTO> procesosMascotaExtraviada) {
        this.procesosMascotaExtraviada = procesosMascotaExtraviada;
    }
    
    
    
}

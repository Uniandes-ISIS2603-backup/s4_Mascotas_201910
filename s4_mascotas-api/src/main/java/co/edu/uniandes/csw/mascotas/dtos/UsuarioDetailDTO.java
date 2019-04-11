/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.dtos;

import co.edu.uniandes.csw.mascotas.entities.ArticuloEntity;
import co.edu.uniandes.csw.mascotas.entities.EventoEntity;
import co.edu.uniandes.csw.mascotas.entities.MascotaEnAdopcionEntity;
import co.edu.uniandes.csw.mascotas.entities.MascotaExtraviadaEntity;
import co.edu.uniandes.csw.mascotas.entities.UsuarioEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sebastián Lemus Cadena (s.lemus) y Maria Ana Ortiz Botero 
 */
public class UsuarioDetailDTO extends UsuarioDTO implements Serializable{
    
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
        super(entity);
        if(entity.getArticulos()!=null){
            articulos=new ArrayList<>();
            for(ArticuloEntity articulo: entity.getArticulos()){
                articulos.add(new ArticuloDTO(articulo));
                
            }
        }
        if(entity.getEventos()!=null){
            eventos = new ArrayList<>();
            for(EventoEntity evento: entity.getEventos())
            {
             eventos.add(new EventoDTO(evento));   
            }    
        }
        if(entity.getProcesosMascotaExtraviada()!=null){
            procesosMascotaExtraviada = new ArrayList<>();
            for(MascotaExtraviadaEntity procesoMascota: entity.getProcesosMascotaExtraviada()){
                procesosMascotaExtraviada.add(new MascotaExtraviadaDTO(procesoMascota));
                
            }
        }
        if(entity.getProcesosMascotaAdopcion()!=null){
            procesosMascotaAdopcion=new ArrayList<>();
            for(MascotaEnAdopcionEntity procesoMascota: entity.getProcesosMascotaAdopcion()){
                procesosMascotaAdopcion.add(new MascotaEnAdopcionDTO(procesoMascota));
            }
                    
        }
         
        
        
    }
    
    /**
     * 
     * @return La representaciòn del usuarioDTO en entidad
     */
    public UsuarioEntity toEntity(){
        UsuarioEntity useEntity = super.toEntity();
        if(this.eventos!=null){   
             ArrayList<EventoEntity> eventosE= new ArrayList<>();
            for(EventoDTO evento: eventos)
            {
             eventosE.add(evento.toEntity());   
            }    
            useEntity.setEventos(eventosE);
        }
        if(this.articulos!=null){
            ArrayList<ArticuloEntity> articulosE = new ArrayList<>();
            for(ArticuloDTO articulo: this.articulos){
                articulosE.add(articulo.toEntity());
            }
           useEntity.setArticulos(articulosE);
        }
        if(this.procesosMascotaAdopcion!=null){
            ArrayList<MascotaEnAdopcionEntity> adopcionEn = new ArrayList<>();
            for(MascotaEnAdopcionDTO mascotaA: this.procesosMascotaAdopcion){
                adopcionEn.add(mascotaA.toEntity());
            }
        }
        if(this.procesosMascotaExtraviada!=null){
            ArrayList<MascotaExtraviadaEntity> extraviadaEn = new ArrayList<>();
            for(MascotaExtraviadaDTO mascotaEx:this.procesosMascotaExtraviada){
                extraviadaEn.add(mascotaEx.toEntity());
            }
        }
              
        
        return useEntity;
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

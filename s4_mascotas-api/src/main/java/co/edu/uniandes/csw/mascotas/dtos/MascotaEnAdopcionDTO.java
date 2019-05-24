/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.dtos;

import co.edu.uniandes.csw.mascotas.entities.MascotaEnAdopcionEntity;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author s.canales
 */
public class MascotaEnAdopcionDTO implements Serializable{
    
    private Long id;
    
    /**
     * razon por la cual va a ser postulada la mascota para adopción
     */
    private String razonAdopcion;
    
    /**
     * pasado de la mascota
     */
    private String pasado;
    
    /**
     * fecha de inicio y fin del proceso de adopción
     */
    private Date fechaInicio;
    private Date fechaFinal;
    
    /**
     * dueño del proceso
     */
    private UsuarioDTO duenio;
     /**
     * relación con calificacion
     */
    private CalificacionDTO calificacion;
    
    /**
     * relacion con mascota asociada al proceso
     */
    private MascotaDTO mascota;
    
    /**
     * estado de adopción de la mascota
     */
    
    private Boolean adoptada;
    
    /**
     * usuarios postulados
     */
    
    //private List<UsuarioDTO> postulados;
    /**
     * constructor con un entity como parámetro
     * @param entity 
     */
    public MascotaEnAdopcionDTO(MascotaEnAdopcionEntity entity, boolean shallow){
        
        if(entity != null){
            
            id = entity.getId();
            razonAdopcion = entity.getRazonAdopcion();
            pasado = entity.getPasado();
            fechaInicio = entity.getFechaInicio();
            fechaFinal = entity.getFechaFinal();
            adoptada = entity.isAdoptada();
            
            if(!shallow && entity.getDuenio() != null){
                this.duenio = new UsuarioDTO(entity.getDuenio());
            }else{
                this.duenio = null;
            }
            
            if(!shallow && entity.getCalificacion() != null){
                this.calificacion = new CalificacionDTO(entity.getCalificacion());
            }else{
                this.calificacion = null;
            }
            
            if(!shallow && entity.getMascota() != null){
                this.mascota = new MascotaDTO(entity.getMascota());
            }
            else{
                this.mascota = null;
            }
           // this.postulados = null;
            
        }
        
    
    }
    
    public MascotaEnAdopcionDTO(MascotaEnAdopcionEntity entity){
        this(entity, false);
    }
    
    public MascotaEnAdopcionDTO(){
        //constructor vacío por defecto
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
     * @return the razonAdopcion
     */
    public String getRazonAdopcion() {
        return razonAdopcion;
    }

    /**
     * @param razonAdopcion the razonAdopcion to set
     */
    public void setRazonAdopcion(String razonAdopcion) {
        this.razonAdopcion = razonAdopcion;
    }

    /**
     * @return the pasado
     */
    public String getPasado() {
        return pasado;
    }

    /**
     * @param pasado the pasado to set
     */
    public void setPasado(String pasado) {
        this.pasado = pasado;
    }

    /**
     * @return the fechaInicio
     */
    public Date getFechaInicio() {
        return fechaInicio;
    }

    /**
     * @param fechaInicio the fechaInicio to set
     */
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /**
     * @return the fechaFinal
     */
    public Date getFechaFinal() {
        return fechaFinal;
    }

    /**
     * @param fechaFinal the fechaFinal to set
     */
    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    /**
     * @return the adoptada
     */
    public Boolean isAdoptada() {
        return getAdoptada();
    }

    /**
     * @param adoptada the adoptada to set
     */
    public void setAdoptada(Boolean adoptada) {
        this.adoptada = adoptada;
    }
    
    public MascotaEnAdopcionEntity toEntity(){
        
        MascotaEnAdopcionEntity entity = new MascotaEnAdopcionEntity();
        entity.setAdoptada(getAdoptada());
        entity.setFechaFinal(fechaFinal);
        entity.setFechaInicio(fechaInicio);
        entity.setPasado(pasado);
        entity.setRazonAdopcion(razonAdopcion);
       
         if(this.duenio != null){
             entity.setDuenio(this.duenio.toEntity());
         }
         
         if(this.calificacion != null){
             entity.setCalificacion(this.calificacion.toEntity());
         }
        
         if(this.mascota != null){
             entity.setMascota(this.mascota.toEntity());
         }
         entity.setPostulados(null);
        return entity;
    }

    /**
     * @return the duenio
     */
    public UsuarioDTO getDuenio() {
        return duenio;
    }

    /**
     * @param duenio the duenio to set
     */
    public void setDuenio(UsuarioDTO duenio) {
        this.duenio = duenio;
    }

    /**
     * @return the mascota
     */
    public MascotaDTO getMascota() {
        return mascota;
    }

    /**
     * @param mascota the mascota to set
     */
    public void setMascota(MascotaDTO mascota) {
        this.mascota = mascota;
    }

    /**
     * @return the adoptada
     */
    public Boolean getAdoptada() {
        return adoptada;
    }

    /**
     * @return the calificacion
     */
    public CalificacionDTO getCalificacion() {
        return calificacion;
    }

    /**
     * @param calificacion the calificacion to set
     */
    public void setCalificacion(CalificacionDTO calificacion) {
        this.calificacion = calificacion;
    }

    /**
     * @return the postulados
     */
    public List<UsuarioDTO> getPostulados() {
        return null;
    }

    /**
     * @param postulados the postulados to set
     */
    public void setPostulados(List<UsuarioDTO> postulados) {
       // this.postulados = postulados;
    }
    
}

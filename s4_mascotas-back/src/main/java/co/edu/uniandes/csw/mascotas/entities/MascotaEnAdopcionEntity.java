/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.entities;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author s.canales
 */
@Entity
public class MascotaEnAdopcionEntity extends BaseEntity implements Serializable{
    
    /**
     * el estado de la mascota
     */
    private boolean adoptada;
    /**
     * el pasado de la mascota
     */
    private String pasado;
    /**
     * fecha en la que comienza el proceso y en la que termina
     */
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Temporal(TemporalType.DATE)
    private Date fechaFinal;
    /**
     * calificación asignada al proceso de adopción
     */
    //private CalificacionEntity calificacion;
    
    /**
     * nombre de ususarios postulados para adoptar la mascota
     */
    //private PostuladoEntity postulados;
    
    /**
     * constructor
     */
    public MascotaEnAdopcionEntity(){
        
    }

    /**
     * @return the adoptada
     */
    public boolean isAdoptada() {
        return adoptada;
    }

    /**
     * @param adoptada the adoptada to set
     */
    public void setAdoptada(boolean adoptada) {
        this.adoptada = adoptada;
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
     * @return the calificacion
     */
//    public CalificacionEntity getCalificacion() {
//        return calificacion;
//    }
//
//    /**
//     * @param calificacion the calificacion to set
//     */
//    public void setCalificacion(CalificacionEntity calificacion) {
//        this.calificacion = calificacion;
//    }
//
//    /**
//     * @return the postulados
//     */
//    public PostuladoEntity getPostulados() {
//        return postulados;
//    }
//
//    /**
//     * @param postulados the postulados to set
//     */
//    public void setPostulados(PostuladoEntity postulados) {
//        this.postulados = postulados;
//    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.entities;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.transaction.UserTransaction;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author s.canales
 */
@Entity
public class CalificacionEntity extends BaseEntity implements Serializable {
    /**
     * comentario asociado al proceso de adopción
     */
    private String comentario;
    /**
     * calificación numérica asociada al proceso de adopción
     */
    private Integer calificacion;
   
    @PodamExclude
    @OneToOne
    private MascotaEnAdopcionEntity procesoMascotaEnAdopcion;
    
    /**
     * constructor
     */
    public CalificacionEntity(){
        //constructor vacío por defecto
    }
   

    /**
     * @return the comentario
     */
    public String getComentario() {
        return comentario;
    }

    /**
     * @param comentario the comentario to set
     */
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    /**
     * @return the calificacion
     */
    public Integer getCalificacion() {
        return calificacion;
    }

    /**
     * @param calificacion the calificacion to set
     */
    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }
      /**
     * @return el proceso
     */
    public MascotaEnAdopcionEntity getMascotaEnAdopcion() {
        return procesoMascotaEnAdopcion;
    }

    /**
     * @param proceso the calificacion to set
     */
    public void setMascotaEnAdopcion(MascotaEnAdopcionEntity proceso) {
        this.procesoMascotaEnAdopcion = proceso;
    }

   
    
}

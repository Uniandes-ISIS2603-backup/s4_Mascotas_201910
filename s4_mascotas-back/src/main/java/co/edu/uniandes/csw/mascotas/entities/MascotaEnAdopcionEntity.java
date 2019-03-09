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
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
     * la razón por la cual se quiere poner en adopción la mascota
     */
    private String razonAdopcion;
    
    /**
     * El usuario que es dueño del proceso de mascota en adopciòn
     */
    @PodamExclude
    @ManyToOne
    private UsuarioEntity duenio;
    
    /**
     * los usuarios postulados 
     */
    @PodamExclude
    @ManyToMany
    private List<UsuarioEntity> postulados;
    
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
    @PodamExclude
    @OneToOne(
            mappedBy = "procesoMascotaEnAdopcion" ,
            cascade = CascadeType.ALL ,
            fetch = FetchType.LAZY
    )
    private CalificacionEntity calificacion;

    @PodamExclude
    @OneToOne
    private MascotaEntity mascota;
    /**
     * constructor
     */
    public MascotaEnAdopcionEntity(){
        //constructor vacío por defecto
    }
    /**
     * 
     * @return la mascota
     */
    public MascotaEntity getMascotaEntity(){
        return mascota;
    }
    /**
     * modifica la mascota
     * @param nueva 
     */
    public void setMascotaEntity(MascotaEntity nuevaMascota){
        mascota = nuevaMascota;
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

    public UsuarioEntity getDuenio() {
        return duenio;
    }

    public void setDuenio(UsuarioEntity duenio) {
        this.duenio = duenio;
    }

    public List<UsuarioEntity> getPostulados() {
        return postulados;
    }

    public void setPostulados(List<UsuarioEntity> postulados) {
        this.postulados = postulados;
    }

    /**
     * @return the calificacion
     */
    public CalificacionEntity getCalificacion() {
        return calificacion;
    }

    /**
     * @param calificacion the calificacion to set
     */
    public void setCalificacion(CalificacionEntity calificacion) {
        this.calificacion = calificacion;
    }

    /**
     * @return the mascota
     */
    public MascotaEntity getMascota() {
        return mascota;
    }

    /**
     * @param mascota the mascota to set
     */
    public void setMascota(MascotaEntity mascota) {
        this.mascota = mascota;
    }


}

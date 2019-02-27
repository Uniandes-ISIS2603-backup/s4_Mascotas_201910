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

/**
 *
 * @author Sebastián Lemus Cadena (s.lemus)
 */
@Entity
public class MascotaExtraviadaEntity extends BaseEntity implements Serializable{

    /**
     * La dirección específica donde la mascota fue extraviada
     */
    private String direccion;
    
    /**
     * La ciudad donde la mascota se extravió
     */
    private String ciudad;
    
    /**
     * Representa el estado del proceso de mascota extraviada;
     * este puede ser 'PENDIENTE' o 'ENCONTRADO'
     */
    private String estado;
    
    /**
     * Relación a una Recompensa de cardinalidad 1
     */
    @PodamExclude
    @OneToOne
    private RecompensaEntity recompensa;
    
    /**
     * La informaciòn de la mascota relacionada al proceso
     */
    @PodamExclude
    @OneToOne
    private MascotaEntity mascota;
    
    /**
     * Las siguientes dos constantes contienen los dos valores
     * que puede tener el atributo 'estado'
     */
    
    public static final String PENDIENTE = "PENDIENTE";
    public static final String ENCONTRADO = "ENCONTRADO";

    /**
     * Constructor por defecto
     */
    public MascotaExtraviadaEntity() {
        
    }
    
    /**
     * 
     * @return La dirección del proceso de mascota extraviada
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Modifica la dirección del proceso de mascota extraviada
     * @param direccion 
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * 
     * @return La ciudad del proceso de mascota extraviada
     */
    public String getCiudad() {
        return ciudad;
    }

    /**
     * Modifica la ciudad del proceso de mascota extraviada
     * @param ciudad 
     */
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    /**
     * 
     * @return El estado del proceso de mascota extraviada
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Modifica el estado del proceso de mascota extraviada
     * @param estado 
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * 
     * @return La recompensa del proceso
     */
    public RecompensaEntity getRecompensa() {
        return recompensa;
    }

    /**
     * Modifica la recompensa del proceso
     * @param recompensa 
     */
    public void setRecompensa(RecompensaEntity recompensa) {
        this.recompensa = recompensa;
    }

    /**
     * 
     * @return La mascota asociada al proceso
     */
    public MascotaEntity getMascota() {
        return mascota;
    }
    
    /**
     * Modifica la mascota asociada al proceso
     * @param mascota 
     */
    public void setMascota(MascotaEntity mascota) {
        this.mascota = mascota;
    }
    
    

}

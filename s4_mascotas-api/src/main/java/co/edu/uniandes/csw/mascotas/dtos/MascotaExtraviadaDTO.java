/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.dtos;

import co.edu.uniandes.csw.mascotas.entities.MascotaExtraviadaEntity;
import co.edu.uniandes.csw.mascotas.entities.RecompensaEntity;
import java.io.Serializable;


/**
 *
 * @author Sebastián Lemus Cadena (s.lemus)
 */
public class MascotaExtraviadaDTO implements Serializable{
    
    /**
     * El identificador del DTO instanciado
     */
    private Long id;
    
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
    private RecompensaDTO recompensa;
    
    /**
     * La mascota relacionada con el proceso
     */
    private MascotaDTO mascota;
    
    /**
     * Las siguientes dos constantes contienen los dos valores
     * que puede tener el atributo 'estado'
     */
    
    public static final String PENDIENTE = "PENDIENTE";
    public static final String ENCONTRADO = "ENCONTRADO";
    
    /**
     * Constructor por defecto
     */
    public MascotaExtraviadaDTO(){
        
    }
    
    /**
     * Crea un nuevo DTO con los valores que recibe de la entidad respectiva
     * @param e - la entidad
     */
    public MascotaExtraviadaDTO(MascotaExtraviadaEntity e){
        if(e != null){
            this.id = e.getId();
            this.direccion = e.getDireccion();
            this.ciudad = e.getCiudad();
            this.estado = e.getEstado();
            
            if(e.getRecompensa() != null){
                this.recompensa = new RecompensaDTO(e.getRecompensa());
            }else{
                this.recompensa = null;
            }
            
            if(e.getMascota() != null){
                this.mascota = new MascotaDTO(e.getMascota());
            }else{
                this.mascota = null;
            }
        }
    }
   
    /**
     * 
     * @return El id del proceso de mascota extraviada
     */
    public Long getId() {
        return id;
    }

    /**
     * Modifica el valor del id del proceso de mascota extraviada
     * 
     * @param id - el id a modificar
     */
    public void setId(Long id) {
        this.id = id;
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
     * @return La recompensa ofrecida por el proceso
     */ 
    public RecompensaDTO getRecompensa() {
        return recompensa;
    }

    /**
     * Modifica la recompensa ofrecida por el proceso
     * @param recompensa 
     */
    public void setRecompensa(RecompensaDTO recompensa) {
        this.recompensa = recompensa;
    }

    /**
     * 
     * @return La mascota relacionada con el proceso
     */
    public MascotaDTO getMascota() {
        return mascota;
    }
    
    /**
     * Modifica la mascota relacionada con el proceso
     * @param mascota 
     */
    public void setMascota(MascotaDTO mascota) {
        this.mascota = mascota;
    }
    
    /**
     * Convertir de DTO a Entity
     * @return Un MascotaExtraviadaEntity con los valores del DTO
     */
    public MascotaExtraviadaEntity toEntity(){
        MascotaExtraviadaEntity entity = new MascotaExtraviadaEntity();
        entity.setCiudad(this.ciudad);
        entity.setDireccion(this.direccion);
        entity.setEstado(this.estado);
        entity.setRecompensa(this.recompensa.toEntity());
        entity.setMascota(this.mascota.toEntity());

        return entity;
    }
    
    /**
     * 
     * @return Representación de la clase en String
     */
    @Override
    public String toString() {
        return "MascotaExtraviadaDTO{" + "id=" + id + ", direccion=" + direccion + ", ciudad=" + ciudad + ", estado=" + estado + '}';
    } 
    
}

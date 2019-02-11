/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.dtos;

import co.edu.uniandes.csw.mascotas.entities.RecompensaEntity;
import java.io.Serializable;

/**
 *
 * @author Sebastián Lemus Cadena (s.lemus)
 */
public class RecompensaDTO implements Serializable{
    
    /**
     * El identificador del DTO instanciado
     */
    private Long id;
    
    /**
     * Representa el medio de pago por el cuál se pagará la
     * recompensa a una mascota extraviada
     */
    private String medioDePago;
    
    /**
     * Representa el pago de la recompensa que ha ofrecido
     * aquel usuario que haya iniciado un proceso de MascotaExtraviada
     */
    private Double valor;
    
    /**
     * Representa el estado de la recompensa, este puede asumir dos 
     * valores únicamente: 'PENDIENTE' o 'PAGADO'
     */
    private String estado;
    
    /**
     * Las siguientes dos constantes contienen los dos valores
     * que puede tener el atributo 'estado'
     */
    
    private static final String PENDIENTE = "PENDIENTE";
    private static final String PAGADO = "PAGADO";
    
    /**
     * Constructor por defecto
     */
    public RecompensaDTO(){
        
    }

    /**
     * Crea un nuevo DTO con los valores que recibe de la entidad respectiva
     * @param e - la entidad
     */
    public RecompensaDTO(RecompensaEntity e){
        if(e != null){
            this.medioDePago = e.getMedioDePago();
            this.valor = e.getValor();
            this.estado = e.getEstado();
        }
    }
    
    /**
     * 
     * @return El identificador de la recompensa
     */
    public Long getId() {
        return id;
    }

    /**
     * Modifica el identificador de la recompensa
     * @param id 
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 
     * @return El medio de pago para la recompensa
     */
    public String getMedioDePago() {
        return medioDePago;
    }

    /**
     * Modifica el medio de pago para la recompensa
     * @param medioDePago 
     */
    public void setMedioDePago(String medioDePago) {
        this.medioDePago = medioDePago;
    }

    /**
     * 
     * @return El valor de la recompensa
     */
    public Double getValor() {
        return valor;
    }

    /**
     * Modifica el valor de la recompensa
     * @param valor 
     */
    public void setValor(Double valor) {
        this.valor = valor;
    }

    /**
     * 
     * @return El estado de pago de la recompensa
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Modifica el estado de pago de la recompensa
     * @param estado 
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Convertir de DTO a Entity
     * @return Un RecompensaEntity con los valores del DTO
     */
    public RecompensaEntity toEntity(){
        RecompensaEntity e = new RecompensaEntity();
        e.setMedioDePago(medioDePago);
        e.setValor(valor);
        e.setMedioDePago(medioDePago);
        
        return e;
    }
    
    /**
     * 
     * @return Representación de la clase en String
     */
    @Override
    public String toString() {
        return "RecompensaDTO{" + "id=" + id + ", medioDePago=" + medioDePago + ", valor=" + valor + ", estado=" + estado + '}';
    }
    
    
    
    
    
}

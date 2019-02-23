/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author Sebastián Lemus Cadena (s.lemus)
 */
@Entity
public class RecompensaEntity extends BaseEntity implements Serializable{
    
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
     * El proceso de mascota extraviada asociado
     */
    @PodamExclude
    @OneToOne(mappedBy = "recompensa", fetch=FetchType.LAZY)
    private MascotaExtraviadaEntity procesoMascotaExtraviada;
    
    /**
     * Las siguientes dos constantes contienen los dos valores
     * que puede tener el atributo 'estado'
     */
    
    public static final String PENDIENTE = "PENDIENTE";
    public static final String PAGADO = "PAGADO";

    /**
     * Constructor por defecto
     */
    public RecompensaEntity() {
        
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
     * 
     * @return El proceso de mascota extraviada asociado a la recompensa
     */
    public MascotaExtraviadaEntity getProcesoMascotaExtraviada() {
        return procesoMascotaExtraviada;
    }

    /**
     * Modifica el proceso de mascota extraviada asociado a la recompensa
     * @param procesoMascotaExtraviada 
     */
    public void setProcesoMascotaExtraviada(MascotaExtraviadaEntity procesoMascotaExtraviada) {
        this.procesoMascotaExtraviada = procesoMascotaExtraviada;
    }
    
    
    
}

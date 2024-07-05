/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uis.software1.sisbo_backend.modelo;

import javax.persistence.*;

@Entity
@Table(name = "Boleta")
public class Boleta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_boleta")
    private Long id_boleta;

    @Column(name = "precio")
    private Integer precio;
    
    @Column(name = "mercado_secundario")
    private boolean mercado_secundario;

    @ManyToOne
    @JoinColumn(name = "id_localidad")
    private Localidad localidad;

    @ManyToOne
    @JoinColumn(name = "id_seguidor")
    private Seguidor seguidor;

    public Boleta() {
    }

    public Boleta(Long id_boleta, Integer precio, boolean mercado_secundario, EventoDeportivo eventoDeportivo, Localidad localidad, Seguidor seguidor) {
        this.id_boleta = id_boleta;
        this.precio = precio;
        this.mercado_secundario = mercado_secundario;
        this.localidad = localidad;
        this.seguidor = seguidor;
    }


    // Getters y Setters
    public Long getIdBoleta() {
        return id_boleta;
    }

    public void setIdBoleta(Long idBoleta) {
        this.id_boleta = idBoleta;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    public Localidad getLocalidad() {
        return localidad;
    }

    public void setLocalidad(Localidad localidad) {
        this.localidad = localidad;
    }

    public boolean getMercadoSecundario() {
        return mercado_secundario;
    }

    public void setMercadoSecundario(boolean mercado_secundario) {
        this.mercado_secundario = mercado_secundario;
    }

    public Seguidor getSeguidor() {
        return seguidor;
    }

    public void setSeguidor(Seguidor seguidor) {
        this.seguidor = seguidor;
    }
}

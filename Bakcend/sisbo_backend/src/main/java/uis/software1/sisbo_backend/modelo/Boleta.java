/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uis.software1.sisbo_backend.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Carlos
 */
@Entity
@Table(name = "Boleta")
public class Boleta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_boleta")
    private Long id_boleta;

    @Column(name = "precio")
    private Integer precio;

    @ManyToOne
    @JoinColumn(name = "id_evento")
    private EventoDeportivo eventoDeportivo;

    @ManyToOne
    @JoinColumn(name = "id_localidad")
    private Localidad localidad;

    @ManyToOne
    @JoinColumn(name = "id_mercado_secundario")
    private MercadoSecundario mercadoSecundario;

    @ManyToOne
    @JoinColumn(name = "id_seguidor")
    private Seguidor seguidor;

    public Boleta() {
    }

    public Boleta(Integer precio, EventoDeportivo eventoDeportivo, Localidad localidad, MercadoSecundario mercadoSecundario, Seguidor seguidor) {
        this.precio = precio;
        this.eventoDeportivo = eventoDeportivo;
        this.localidad = localidad;
        this.mercadoSecundario = mercadoSecundario;
        this.seguidor = seguidor;
    }

    // Getters y Setters
    public Long getIdBoleta() {
        return id_boleta;
    }

    public void setIdBoleta(Long id_boleta) {
        this.id_boleta = id_boleta;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    public EventoDeportivo getEventoDeportivo() {
        return eventoDeportivo;
    }

    public void setEventoDeportivo(EventoDeportivo eventoDeportivo) {
        this.eventoDeportivo = eventoDeportivo;
    }

    public Localidad getLocalidad() {
        return localidad;
    }

    public void setLocalidad(Localidad localidad) {
        this.localidad = localidad;
    }

    public MercadoSecundario getMercadoSecundario() {
        return mercadoSecundario;
    }

    public void setMercadoSecundario(MercadoSecundario mercadoSecundario) {
        this.mercadoSecundario = mercadoSecundario;
    }

    public Seguidor getSeguidor() {
        return seguidor;
    }

    public void setSeguidor(Seguidor seguidor) {
        this.seguidor = seguidor;
    }
}
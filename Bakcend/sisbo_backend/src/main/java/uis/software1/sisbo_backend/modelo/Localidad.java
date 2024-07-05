/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uis.software1.sisbo_backend.modelo;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Entity;
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
@Table(name = "Localidad")
public class Localidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_localidad")
    private Long id_localidad;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "precio")
    private Integer precio;

    @Column(name = "cantidad_puestos_total")
    private Integer cantidad_puestos_total;

    @Column(name = "cantidad_puestos_vendidos")
    private Integer cantidad_puestos_vendidos;

    @ManyToOne
    @JoinColumn(name = "id_evento")
    private EventoDeportivo eventoDeportivo;

    public Localidad() {
    }

    public Localidad(Long id_localidad, String nombre, Integer precio, Integer cantidad_puestos_total, Integer cantidad_puestos_vendidos, EventoDeportivo eventoDeportivo) {
        this.id_localidad = id_localidad;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad_puestos_total = cantidad_puestos_total;
        this.cantidad_puestos_vendidos = cantidad_puestos_vendidos;
        this.eventoDeportivo = eventoDeportivo;
    }

    public Long getId_localidad() {
        return id_localidad;
    }

    public void setId_localidad(Long id_localidad) {
        this.id_localidad = id_localidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    public Integer getCantidad_puestos_total() {
        return cantidad_puestos_total;
    }

    public void setCantidad_puestos_total(Integer cantidad_puestos_total) {
        this.cantidad_puestos_total = cantidad_puestos_total;
    }

    public Integer getCantidad_puestos_vendidos() {
        return cantidad_puestos_vendidos;
    }

    public void setCantidad_puestos_vendidos(Integer cantidad_puestos_vendidos) {
        this.cantidad_puestos_vendidos = cantidad_puestos_vendidos;
    }

    public EventoDeportivo getEventoDeportivo() {
        return eventoDeportivo;
    }

    public void setEventoDeportivo(EventoDeportivo eventoDeportivo) {
        this.eventoDeportivo = eventoDeportivo;
    }
    
}
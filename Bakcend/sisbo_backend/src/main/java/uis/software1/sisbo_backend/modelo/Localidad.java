/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uis.software1.sisbo_backend.modelo;

import javax.persistence.*;

@Entity
@Table(name = "Localidad")
public class Localidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_localidad")
    private Long idLocalidad;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "precio")
    private Integer precio;

    @Column(name = "cantidad_puestos_total")
    private Integer cantidadPuestosTotal;

    @Column(name = "cantidad_puestos_vendidos")
    private Integer cantidadPuestosVendidos;

    @ManyToOne
    @JoinColumn(name = "id_evento")
    private EventoDeportivo eventoDeportivo;

    public Localidad() {
    }

    public Localidad(String nombre, Integer precio, Integer cantidadPuestosTotal, Integer cantidadPuestosVendidos, EventoDeportivo eventoDeportivo) {
        this.nombre = nombre;
        this.precio = precio;
        this.cantidadPuestosTotal = cantidadPuestosTotal;
        this.cantidadPuestosVendidos = cantidadPuestosVendidos;
        this.eventoDeportivo = eventoDeportivo;
    }

    // Getters y Setters
    public Long getIdLocalidad() {
        return idLocalidad;
    }

    public void setIdLocalidad(Long idLocalidad) {
        this.idLocalidad = idLocalidad;
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

    public Integer getCantidadPuestosTotal() {
        return cantidadPuestosTotal;
    }

    public void setCantidadPuestosTotal(Integer cantidadPuestosTotal) {
        this.cantidadPuestosTotal = cantidadPuestosTotal;
    }

    public Integer getCantidadPuestosVendidos() {
        return cantidadPuestosVendidos;
    }

    public void setCantidadPuestosVendidos(Integer cantidadPuestosVendidos) {
        this.cantidadPuestosVendidos = cantidadPuestosVendidos;
    }

    public EventoDeportivo getEventoDeportivo() {
        return eventoDeportivo;
    }

    public void setEventoDeportivo(EventoDeportivo eventoDeportivo) {
        this.eventoDeportivo = eventoDeportivo;
    }
}
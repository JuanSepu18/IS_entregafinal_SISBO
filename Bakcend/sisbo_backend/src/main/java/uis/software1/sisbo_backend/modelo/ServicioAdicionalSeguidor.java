/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uis.software1.sisbo_backend.modelo;

import javax.persistence.*;

@Entity
@Table(name = "ServicioAdicionalSeguidor")
public class ServicioAdicionalSeguidor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_servicio_seguidor")
    private Long id_servicio_seguidor;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "id_seguidor")
    private Seguidor seguidor;

    public ServicioAdicionalSeguidor() {
    }

    public ServicioAdicionalSeguidor(String nombre, String descripcion, Seguidor seguidor) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.seguidor = seguidor;
    }

    // Getters y Setters
    public Long getIdServicioSeguidor() {
        return id_servicio_seguidor;
    }

    public void setIdServicioSeguidor(Long id_servicio_seguidor) {
        this.id_servicio_seguidor = id_servicio_seguidor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Seguidor getSeguidor() {
        return seguidor;
    }

    public void setSeguidor(Seguidor seguidor) {
        this.seguidor = seguidor;
    }
}

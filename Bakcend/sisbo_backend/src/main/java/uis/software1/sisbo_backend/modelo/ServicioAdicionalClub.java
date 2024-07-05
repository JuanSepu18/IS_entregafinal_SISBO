/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uis.software1.sisbo_backend.modelo;

import javax.persistence.*;

@Entity
@Table(name = "ServicioAdicionalClub")
public class ServicioAdicionalClub {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_servicio_club")
    private Long id_servicio_club;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "precio")
    private Integer precio;

    @Column(name = "unidades_totales")
    private Integer unidades_totales;

    @Column(name = "unidades_vendidas")
    private Integer unidades_vendidas;

    @ManyToOne
    @JoinColumn(name = "id_club")
    private Club club;

    public ServicioAdicionalClub() {
    }

    public ServicioAdicionalClub(Long id_servicio_club, String nombre, String descripcion, Integer precio, Integer unidades_totales, Integer unidades_vendidas, Club club) {
        this.id_servicio_club = id_servicio_club;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.unidades_totales = unidades_totales;
        this.unidades_vendidas = unidades_vendidas;
        this.club = club;
    }

    public Long getId_servicio_club() {
        return id_servicio_club;
    }

    public void setId_servicio_club(Long id_servicio_club) {
        this.id_servicio_club = id_servicio_club;
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

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    public Integer getUnidades_totales() {
        return unidades_totales;
    }

    public void setUnidades_totales(Integer unidades_totales) {
        this.unidades_totales = unidades_totales;
    }

    public Integer getUnidades_vendidas() {
        return unidades_vendidas;
    }

    public void setUnidades_vendidas(Integer unidades_vendidas) {
        this.unidades_vendidas = unidades_vendidas;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    
}

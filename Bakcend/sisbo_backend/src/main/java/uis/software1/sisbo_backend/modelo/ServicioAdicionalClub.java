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
    private Long idServicioClub;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "precio")
    private Integer precio;

    @Column(name = "unidades_maximas")
    private Integer unidadesMaximas;

    @Column(name = "unidades_vendidas")
    private Integer unidadesVendidas;

    @ManyToOne
    @JoinColumn(name = "id_club")
    private Club club;

    public ServicioAdicionalClub() {
    }

    public ServicioAdicionalClub(String nombre, String descripcion, Integer precio, Integer unidadesMaximas, Integer unidadesVendidas, Club club) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.unidadesMaximas = unidadesMaximas;
        this.unidadesVendidas = unidadesVendidas;
        this.club = club;
    }

    // Getters y Setters
    public Long getIdServicioClub() {
        return idServicioClub;
    }

    public void setIdServicioClub(Long idServicioClub) {
        this.idServicioClub = idServicioClub;
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

    public Integer getUnidadesMaximas() {
        return unidadesMaximas;
    }

    public void setUnidadesMaximas(Integer unidadesMaximas) {
        this.unidadesMaximas = unidadesMaximas;
    }

    public Integer getUnidadesVendidas() {
        return unidadesVendidas;
    }

    public void setUnidadesVendidas(Integer unidadesVendidas) {
        this.unidadesVendidas = unidadesVendidas;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }
}


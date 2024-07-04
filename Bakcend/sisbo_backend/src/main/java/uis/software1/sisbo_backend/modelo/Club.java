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
import javax.persistence.Table;


/**
 *
 * @author Carlos
 */
@Entity
@Table(name = "Club")
public class Club {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_club")
    private Long idClub;

    @Column(name = "nombre", nullable = false, length = 300)
    private String nombre;

    @Column(name = "correo_electronico", nullable = false, length = 200)
    private String correoElectronico;

    @Column(name = "estadio_propio", nullable = false, length = 300)
    private String estadioPropio;

    // Getters and Setters
    public Long getIdClub() {
        return idClub;
    }

    public void setIdClub(Long idClub) {
        this.idClub = idClub;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getEstadioPropio() {
        return estadioPropio;
    }

    public void setEstadioPropio(String estadioPropio) {
        this.estadioPropio = estadioPropio;
    }
}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uis.software1.sisbo_backend.modelo;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "correo_electronico")
    private String correo_electronico;

    @Column(name = "estadio_propio")
    private String estadioPropio;
    
    @Column(name = "contrasena")
    private String contrasena;
    

    public Club() {
    }

    public Club(Long idClub, String nombre, String correo_electronico, String estadioPropio, String contrasena) {
        this.idClub = idClub;
        this.nombre = nombre;
        this.correo_electronico = correo_electronico;
        this.estadioPropio = estadioPropio;
        this.contrasena = contrasena;
    }

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
        return correo_electronico;
    }

    public void setCorreoElectronico(String correo_electronico) {
        this.correo_electronico = correo_electronico;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getEstadioPropio() {
        return estadioPropio;
    }

    public void setEstadioPropio(String estadioPropio) {
        this.estadioPropio = estadioPropio;
    }
}
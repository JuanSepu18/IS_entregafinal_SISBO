/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uis.software1.sisbo_backend.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Carlos
 */
@Entity
@Table(name = "Seguidor")
public class Seguidor {
    
    @Id
    @Column(name = "documento_de_identidad")
    private String documento_de_identidad;
    
    @Column(name = "nombre")
    private String nombre;
    
    @Column(name = "correo")
    private String correo_electronico;
    
    @Column(name = "contrasena")
    private String contrasena;

    
    public Seguidor() {
    }
    
    public Seguidor(String documento_de_identidad, String nombre, String correo_electronico, String contrasena) {
        this.documento_de_identidad = documento_de_identidad;
        this.nombre = nombre;
        this.correo_electronico = correo_electronico;
        this.contrasena = contrasena;
    }

    public String getDocumento_de_identidad() {
        return documento_de_identidad;
    }

    public void setDocumento_de_identidad(String documento_de_identidad) {
        this.documento_de_identidad = documento_de_identidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo_electronico() {
        return correo_electronico;
    }

    public void setCorreo_electronico(String correo_electronico) {
        this.correo_electronico = correo_electronico;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    
    
}

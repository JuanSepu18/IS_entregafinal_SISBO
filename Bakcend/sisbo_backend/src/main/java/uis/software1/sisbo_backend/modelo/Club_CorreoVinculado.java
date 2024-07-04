/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uis.software1.sisbo_backend.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Carlos
 */
@Entity
@Table(name = "Club_CorreoVinculado")
public class Club_CorreoVinculado {

    @Id
    @ManyToOne
    @JoinColumn(name = "id_club")
    private Club club;

    @Column(name = "correo")
    private String correo;

    public Club_CorreoVinculado() {
    }

    public Club_CorreoVinculado(Club club, String correo) {
        this.club = club;
        this.correo = correo;
    }

    // Getters y Setters
    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}

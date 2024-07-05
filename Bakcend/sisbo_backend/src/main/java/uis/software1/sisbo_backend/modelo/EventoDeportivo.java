/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uis.software1.sisbo_backend.modelo;
import java.util.Date;
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
@Table(name = "EventoDeportivo")
public class EventoDeportivo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_evento")
    private Long id_evento;
    
    @Column(name = "oponente")
    private String oponente;

    @Column(name = "estadio")
    private String estadio;

    @Column(name = "fecha")
    private Date fecha;

    @Column(name = "hora_ingreso")
    private String hora_ingreso;

    @Column(name = "hora_cierre")
    private String hora_cierre;

    @ManyToOne
    @JoinColumn(name = "id_club")
    private Club id_club;

    public EventoDeportivo() {
    }

    public EventoDeportivo(Long id_evento, String oponente, String estadio, Date fecha, String hora_ingreso, String hora_cierre, Club id_club) {
        this.id_evento = id_evento;
        this.oponente = oponente;
        this.estadio = estadio;
        this.fecha = fecha;
        this.hora_ingreso = hora_ingreso;
        this.hora_cierre = hora_cierre;
        this.id_club = id_club;
    }

    public Long getId_evento() {
        return id_evento;
    }

    public void setId_evento(Long id_evento) {
        this.id_evento = id_evento;
    }

    public String getOponente() {
        return oponente;
    }

    public void setOponente(String oponente) {
        this.oponente = oponente;
    }

    public String getEstadio() {
        return estadio;
    }

    public void setEstadio(String estadio) {
        this.estadio = estadio;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getHora_ingreso() {
        return hora_ingreso;
    }

    public void setHora_ingreso(String hora_ingreso) {
        this.hora_ingreso = hora_ingreso;
    }

    public String getHora_cierre() {
        return hora_cierre;
    }

    public void setHora_cierre(String hora_cierre) {
        this.hora_cierre = hora_cierre;
    }

    public Club getId_club() {
        return id_club;
    }

    public void setId_club(Club id_club) {
        this.id_club = id_club;
    }
}

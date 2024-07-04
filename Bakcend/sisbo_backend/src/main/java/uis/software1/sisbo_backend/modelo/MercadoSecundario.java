/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uis.software1.sisbo_backend.modelo;

import javax.persistence.*;

@Entity
@Table(name = "MercadoSecundario")
public class MercadoSecundario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mercado_secundario")
    private Long idMercadoSecundario;

    public MercadoSecundario() {
    }

    // Getters y Setters
    public Long getIdMercadoSecundario() {
        return idMercadoSecundario;
    }

    public void setIdMercadoSecundario(Long idMercadoSecundario) {
        this.idMercadoSecundario = idMercadoSecundario;
    }
}
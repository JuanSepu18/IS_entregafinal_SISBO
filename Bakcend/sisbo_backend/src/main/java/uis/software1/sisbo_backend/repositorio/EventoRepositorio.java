/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package uis.software1.sisbo_backend.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import uis.software1.sisbo_backend.modelo.EventoDeportivo;

/**
 *
 * @author Carlos
 */
public interface EventoRepositorio extends JpaRepository<EventoDeportivo, Long>{
    
}

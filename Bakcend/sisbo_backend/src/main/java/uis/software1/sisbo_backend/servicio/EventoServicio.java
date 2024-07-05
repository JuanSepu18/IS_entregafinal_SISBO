/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uis.software1.sisbo_backend.servicio;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uis.software1.sisbo_backend.modelo.EventoDeportivo;
import uis.software1.sisbo_backend.repositorio.EventoRepositorio;

/**
 *
 * @author Carlos
 */
@Service
public class EventoServicio {
    
    @Autowired
    EventoRepositorio eventoRepo;
    
    // Read
    public List<EventoDeportivo> getEvento() {
        return eventoRepo.findAll();
    }

    // Read by ID
    public EventoDeportivo buscarEvento(Long id) {
        EventoDeportivo eventoDeportivo = null;
        eventoDeportivo = eventoRepo.findById(id).orElse(null);
        if(eventoDeportivo == null){
            return null;
        }
        return eventoDeportivo;
    }

    // Create y Update
    public EventoDeportivo nuevoEvento(EventoDeportivo eventoDeportivo){
        return eventoRepo.save(eventoDeportivo);
    }

    // Delete
    public int borrarClub(Long id) {
        eventoRepo.deleteById(id);
        return 1;
    }
    
}

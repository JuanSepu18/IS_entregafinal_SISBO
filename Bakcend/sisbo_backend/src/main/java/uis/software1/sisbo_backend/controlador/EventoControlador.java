/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uis.software1.sisbo_backend.controlador;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uis.software1.sisbo_backend.modelo.EventoDeportivo;
import uis.software1.sisbo_backend.servicio.EventoServicio;

/**
 *
 * @author Carlos
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/api/Evento")
public class EventoControlador {
    
    @Autowired
    EventoServicio eventoServicio;
    
    //Listar
    @GetMapping("/list")
    public List<EventoDeportivo> listarEventos(){
        return eventoServicio.getEvento();
    }
    
    //Buscar por ID
    @GetMapping("/list/{id}")
    public EventoDeportivo buscarPorId(@PathVariable Long id){
        return eventoServicio.buscarEvento(id);
    }
    
    //Crear
    @PostMapping("/")
    public ResponseEntity<EventoDeportivo> agregar(@RequestBody EventoDeportivo eventoDeportivo){
        EventoDeportivo obj = eventoServicio.nuevoEvento(eventoDeportivo);
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }
    
    //Actualizar
    @PutMapping("/")
    public ResponseEntity<EventoDeportivo> actualizar(@RequestBody EventoDeportivo evento){
        EventoDeportivo obj = eventoServicio.buscarEvento(evento.getId_evento());
        if(obj != null){
            obj.setEstadio(evento.getEstadio());
            obj.setFecha(evento.getFecha());
            obj.setHora_cierre(evento.getHora_cierre());
            obj.setHora_ingreso(evento.getHora_ingreso());
            obj.setOponente(evento.getOponente());
            
            eventoServicio.nuevoEvento(obj);
        } else{
            return new ResponseEntity<>(obj, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }
    
    
    
    //Borrar
    @DeleteMapping("/{id}")
    public ResponseEntity<EventoDeportivo> borrar(@PathVariable Long id){
        EventoDeportivo obj = eventoServicio.buscarEvento(id);
        if(obj != null){
            eventoServicio.borrarClub(id);
        } else{
            return new ResponseEntity<>(obj, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }
}

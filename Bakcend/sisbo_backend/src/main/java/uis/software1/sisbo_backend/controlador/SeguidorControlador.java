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
import uis.software1.sisbo_backend.modelo.Seguidor;
import uis.software1.sisbo_backend.servicio.SeguidorServicio;

/**
 *
 * @author Carlos
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/api/Seguidor")
public class SeguidorControlador {
    
    @Autowired
    SeguidorServicio seguidorServicio;
    
    //Listar
    @GetMapping("/list")
    public List<Seguidor> listarSeguidores(){
        return seguidorServicio.getSeguidor();
    }
    
    //Buscar por ID
    @GetMapping("/list/{id}")
    public Seguidor buscarPorId(@PathVariable String id){
        return seguidorServicio.buscarSeguidor(id);
    }
    
    //Crear
    @PostMapping("/")
    public ResponseEntity<Seguidor> agregar(@RequestBody Seguidor seguidor){
        Seguidor obj = seguidorServicio.nuevoSeguidor(seguidor);
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }
    
    //Actualizar
    @PutMapping("/")
    public ResponseEntity<Seguidor> actualizar(@RequestBody Seguidor seguidor){
        Seguidor obj = seguidorServicio.buscarSeguidor(seguidor.getDocumento_de_identidad());
        if(obj != null){
            obj.setCorreo_electronico(seguidor.getCorreo_electronico());
            obj.setContrasena(seguidor.getContrasena());
            obj.setNombre(seguidor.getNombre());
            
            seguidorServicio.nuevoSeguidor(obj);
        } else{
            return new ResponseEntity<>(obj, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }
    
    //Borrar
    @DeleteMapping("/{id}")
    public ResponseEntity<Seguidor> borrar(@PathVariable String id){
        Seguidor obj = seguidorServicio.buscarSeguidor(id);
        if(obj != null){
            seguidorServicio.borrarSeguidor(id);
        } else{
            return new ResponseEntity<>(obj, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }
}
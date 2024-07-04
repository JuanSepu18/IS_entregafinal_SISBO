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
import uis.software1.sisbo_backend.modelo.Club;
import uis.software1.sisbo_backend.servicio.ClubServicio;

/**
 *
 * @author Carlos
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/api/Club")
public class ClubControlador {
    
    @Autowired
    ClubServicio clubServicio;
    
    //Listar
    @GetMapping("/list")
    public List<Club> listarUsers(){
        return clubServicio.getClub();
    }
    
    //Buscar por ID
    @GetMapping("/list/{id}")
    public Club buscarPorId(@PathVariable Long id){
        return clubServicio.buscarClub(id);
    }
    
    //Crear
    @PostMapping("/")
    public ResponseEntity<Club> agregar(@RequestBody Club club){
        Club obj = clubServicio.nuevoClub(club);
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }
    
    
    
    //Actualizar
    @PutMapping("/")
    public ResponseEntity<Club> actualizar(@RequestBody Club club){
        Club obj = clubServicio.buscarClub(club.getIdClub());
        if(obj != null){
            obj.setCorreoElectronico(club.getCorreoElectronico());
            obj.setEstadioPropio(club.getEstadioPropio());
            obj.setNombre(club.getNombre());
            
            clubServicio.nuevoClub(obj);
        } else{
            return new ResponseEntity<>(obj, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }
    
    
    
    //Borrar
    @DeleteMapping("/{id}")
    public ResponseEntity<Club> borrar(@PathVariable Long id){
        Club obj = clubServicio.buscarClub(id);
        if(obj != null){
            clubServicio.borrarClub(id);
        } else{
            return new ResponseEntity<>(obj, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }
}

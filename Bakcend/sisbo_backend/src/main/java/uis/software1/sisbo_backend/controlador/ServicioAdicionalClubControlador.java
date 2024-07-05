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
import uis.software1.sisbo_backend.modelo.ServicioAdicionalClub;
import uis.software1.sisbo_backend.servicio.ServicioAdicionalClubServicio;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/ServicioClub")
public class ServicioAdicionalClubControlador {

    @Autowired
    ServicioAdicionalClubServicio servicioAdicionalClubServicio;

    // Listar
    @GetMapping("/list")
    public List<ServicioAdicionalClub> listarServiciosAdicionalesClub() {
        return servicioAdicionalClubServicio.getServicioAdicionalClub();
    }

    // Buscar por ID
    @GetMapping("/list/{id}")
    public ServicioAdicionalClub buscarPorId(@PathVariable Long id) {
        return servicioAdicionalClubServicio.buscarServicioAdicionalClub(id);
    }

    // Crear
    @PostMapping("/")
    public ResponseEntity<ServicioAdicionalClub> agregar(@RequestBody ServicioAdicionalClub servicioAdicionalClub) {
        ServicioAdicionalClub nuevoServicio = servicioAdicionalClubServicio.nuevoServicioAdicionalClub(servicioAdicionalClub);
        return new ResponseEntity<>(nuevoServicio, HttpStatus.CREATED);
    }

    // Actualizar
    @PutMapping("/")
    public ResponseEntity<ServicioAdicionalClub> actualizar(@RequestBody ServicioAdicionalClub servicioAdicionalClub) {
        ServicioAdicionalClub obj = servicioAdicionalClubServicio.buscarServicioAdicionalClub(servicioAdicionalClub.getId_servicio_club());
        if (obj != null) {
            obj.setDescripcion(servicioAdicionalClub.getDescripcion());
            obj.setNombre(servicioAdicionalClub.getNombre());
            obj.setPrecio(servicioAdicionalClub.getPrecio());
            obj.setUnidades_totales(servicioAdicionalClub.getUnidades_totales());
            obj.setUnidades_vendidas(servicioAdicionalClub.getUnidades_vendidas());

            servicioAdicionalClubServicio.nuevoServicioAdicionalClub(obj);
        } else {
            return new ResponseEntity<>(obj, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    // Borrar
    @DeleteMapping("/{id}")
    public ResponseEntity<ServicioAdicionalClub> borrar(@PathVariable Long id) {
        ServicioAdicionalClub obj = servicioAdicionalClubServicio.buscarServicioAdicionalClub(id);
        if (obj != null) {
            servicioAdicionalClubServicio.borrarServicioAdicionalClub(id);
        } else {
            return new ResponseEntity<>(obj, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }
}


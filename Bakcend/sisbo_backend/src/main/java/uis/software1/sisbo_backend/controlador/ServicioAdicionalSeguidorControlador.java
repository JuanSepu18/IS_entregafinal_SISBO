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
import uis.software1.sisbo_backend.modelo.ServicioAdicionalSeguidor;
import uis.software1.sisbo_backend.servicio.ServicioAdicionalSeguidorServicio;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/ServicioSeguidor")
public class ServicioAdicionalSeguidorControlador {

    @Autowired
    ServicioAdicionalSeguidorServicio servicioAdicionalSeguidorServicio;

    // Listar
    @GetMapping("/list")
    public List<ServicioAdicionalSeguidor> listarServiciosAdicionalesSeguidor() {
        return servicioAdicionalSeguidorServicio.getServicioAdicionalSeguidor();
    }

    // Buscar por ID
    @GetMapping("/list/{id}")
    public ServicioAdicionalSeguidor buscarPorId(@PathVariable Long id) {
        return servicioAdicionalSeguidorServicio.buscarServicioAdicionalSeguidor(id);
    }

    // Crear
    @PostMapping("/")
    public ResponseEntity<ServicioAdicionalSeguidor> agregar(@RequestBody ServicioAdicionalSeguidor servicioAdicionalSeguidor) {
        ServicioAdicionalSeguidor obj = servicioAdicionalSeguidorServicio.nuevoServicioAdicionalSeguidor(servicioAdicionalSeguidor);
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    // Actualizar
    @PutMapping("/")
    public ResponseEntity<ServicioAdicionalSeguidor> actualizar(@RequestBody ServicioAdicionalSeguidor servicioAdicionalSeguidor) {
        ServicioAdicionalSeguidor obj = servicioAdicionalSeguidorServicio.buscarServicioAdicionalSeguidor(servicioAdicionalSeguidor.getIdServicioSeguidor());
        if (obj != null) {
            obj.setNombre(servicioAdicionalSeguidor.getNombre());
            obj.setDescripcion(servicioAdicionalSeguidor.getDescripcion());

            servicioAdicionalSeguidorServicio.nuevoServicioAdicionalSeguidor(obj);
        } else {
            return new ResponseEntity<>(obj, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    // Borrar
    @DeleteMapping("/{id}")
    public ResponseEntity<ServicioAdicionalSeguidor> borrar(@PathVariable Long id) {
        ServicioAdicionalSeguidor obj = servicioAdicionalSeguidorServicio.buscarServicioAdicionalSeguidor(id);
        if (obj != null) {
            servicioAdicionalSeguidorServicio.borrarServicioAdicionalSeguidor(id);
        } else {
            return new ResponseEntity<>(obj, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }
}
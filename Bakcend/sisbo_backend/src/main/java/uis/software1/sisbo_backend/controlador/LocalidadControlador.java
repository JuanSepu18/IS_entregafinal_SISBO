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
import uis.software1.sisbo_backend.modelo.Localidad;
import uis.software1.sisbo_backend.servicio.LocalidadServicio;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/Localidad")
public class LocalidadControlador {

    @Autowired
    LocalidadServicio localidadServicio;

    // Listar
    @GetMapping("/list")
    public List<Localidad> listarLocalidades() {
        return localidadServicio.getLocalidad();
    }

    // Buscar por ID
    @GetMapping("/list/{id}")
    public Localidad buscarPorId(@PathVariable Long id) {
        return localidadServicio.buscarLocalidad(id);
    }
    
    // Crear
    @PostMapping("/")
    public ResponseEntity<Localidad> agregar(@RequestBody Localidad localidad) {
        Localidad obj = localidadServicio.nuevaLocalidad(localidad);
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    // Actualizar
    @PutMapping("/")
    public ResponseEntity<Localidad> actualizar(@RequestBody Localidad localidad) {
        Localidad obj = localidadServicio.buscarLocalidad(localidad.getId_localidad());
        if (obj != null) {
            obj.setCantidad_puestos_total(localidad.getCantidad_puestos_total());
            obj.setCantidad_puestos_vendidos(localidad.getCantidad_puestos_vendidos());
            obj.setNombre(localidad.getNombre());
            obj.setPrecio(localidad.getPrecio());

            localidadServicio.nuevaLocalidad(obj);
        } else {
            return new ResponseEntity<>(obj, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    // Borrar
    @DeleteMapping("/{id}")
    public ResponseEntity<Localidad> borrar(@PathVariable Long id) {
        Localidad obj = localidadServicio.buscarLocalidad(id);
        if (obj != null) {
            localidadServicio.borrarLocalidad(id);
        } else {
            return new ResponseEntity<>(obj, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }
    
}
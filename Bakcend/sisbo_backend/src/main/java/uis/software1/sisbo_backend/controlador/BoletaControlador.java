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
import uis.software1.sisbo_backend.modelo.Boleta;
import uis.software1.sisbo_backend.servicio.BoletaServicio;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/Boleta")
public class BoletaControlador {

    @Autowired
    BoletaServicio boletaServicio;

    // Listar
    @GetMapping("/list")
    public List<Boleta> listarBoletas() {
        return boletaServicio.getBoleta();
    }

    // Buscar por ID
    @GetMapping("/list/{id}")
    public Boleta buscarPorId(@PathVariable Long id) {
        return boletaServicio.buscarBoleta(id);
    }

    // Crear
    @PostMapping("/")
    public ResponseEntity<Boleta> agregar(@RequestBody Boleta boleta) {
        Boleta obj = boletaServicio.nuevaBoleta(boleta);
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    // Actualizar
    @PutMapping("/")
    public ResponseEntity<Boleta> actualizar(@RequestBody Boleta boleta) {
        Boleta obj = boletaServicio.buscarBoleta(boleta.getIdBoleta());
        if (obj != null) {
            obj.setMercadoSecundario(boleta.getMercadoSecundario());
            obj.setPrecio(boleta.getPrecio());
            obj.setSeguidor(boleta.getSeguidor());
            
            boletaServicio.nuevaBoleta(obj);
        } else {
            return new ResponseEntity<>(obj, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    // Borrar
    @DeleteMapping("/{id}")
    public ResponseEntity<Boleta> borrar(@PathVariable Long id) {
        Boleta obj = boletaServicio.buscarBoleta(id);
        if (obj != null) {
            boletaServicio.borrarBoleta(id);
        } else {
            return new ResponseEntity<>(obj, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }
}
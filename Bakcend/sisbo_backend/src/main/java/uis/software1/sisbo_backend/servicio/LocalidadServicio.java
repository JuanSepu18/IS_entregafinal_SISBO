/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uis.software1.sisbo_backend.servicio;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uis.software1.sisbo_backend.modelo.Localidad;
import uis.software1.sisbo_backend.repositorio.LocalidadRepositorio;

@Service
public class LocalidadServicio {

    @Autowired
    private LocalidadRepositorio localidadRepo;

    // Listar 
    public List<Localidad> getLocalidad() {
        return localidadRepo.findAll();
    }

    // Crear y Actualizar
    public Localidad nuevaLocalidad(Localidad localidad) {
        return localidadRepo.save(localidad);
    }

    // Buscar
    public Localidad buscarLocalidad(Long id) {
        return localidadRepo.findById(id).orElse(null);
    }

    // Borrar
    public int borrarLocalidad(Long id) {
        localidadRepo.deleteById(id);
        return 1;
    }
}

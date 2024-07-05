/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uis.software1.sisbo_backend.servicio;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uis.software1.sisbo_backend.modelo.ServicioAdicionalSeguidor;
import uis.software1.sisbo_backend.repositorio.ServicioAdicionalSeguidorRepositorio;

@Service
public class ServicioAdicionalSeguidorServicio {

    @Autowired
    private ServicioAdicionalSeguidorRepositorio servicioAdicionalSeguidorRepo;

    // Listar 
    public List<ServicioAdicionalSeguidor> getServicioAdicionalSeguidor() {
        return servicioAdicionalSeguidorRepo.findAll();
    }

    // Crear y Actualizar
    public ServicioAdicionalSeguidor nuevoServicioAdicionalSeguidor(ServicioAdicionalSeguidor servicioAdicionalSeguidor) {
        return servicioAdicionalSeguidorRepo.save(servicioAdicionalSeguidor);
    }

    // Buscar
    public ServicioAdicionalSeguidor buscarServicioAdicionalSeguidor(Long id) {
        return servicioAdicionalSeguidorRepo.findById(id).orElse(null);
    }

    // Borrar
    public int borrarServicioAdicionalSeguidor(Long id) {
        servicioAdicionalSeguidorRepo.deleteById(id);
        return 1;
    }
}

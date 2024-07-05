/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uis.software1.sisbo_backend.servicio;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uis.software1.sisbo_backend.modelo.ServicioAdicionalClub;
import uis.software1.sisbo_backend.repositorio.ServicioAdicionalClubRepositorio;

@Service
public class ServicioAdicionalClubServicio {

    @Autowired
    private ServicioAdicionalClubRepositorio servicioAdicionalClubRepo;

    // Listar 
    public List<ServicioAdicionalClub> getServicioAdicionalClub() {
        return servicioAdicionalClubRepo.findAll();
    }

    // Crear y Actualizar
    public ServicioAdicionalClub nuevoServicioAdicionalClub(ServicioAdicionalClub servicioAdicionalClub) {
        return servicioAdicionalClubRepo.save(servicioAdicionalClub);
    }

    // Buscar
    public ServicioAdicionalClub buscarServicioAdicionalClub(Long id) {
        return servicioAdicionalClubRepo.findById(id).orElse(null);
    }

    // Borrar
    public int borrarServicioAdicionalClub(Long id) {
        servicioAdicionalClubRepo.deleteById(id);
        return 1;
    }
}

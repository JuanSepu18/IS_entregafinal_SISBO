/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uis.software1.sisbo_backend.servicio;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uis.software1.sisbo_backend.modelo.Boleta;
import uis.software1.sisbo_backend.repositorio.BoletaRepositorio;

@Service
public class BoletaServicio {

    @Autowired
    private BoletaRepositorio boletaRepo;

    // Listar 
    public List<Boleta> getBoleta() {
        return boletaRepo.findAll();
    }

    // Crear y Actualizar
    public Boleta nuevaBoleta(Boleta boleta) {
        return boletaRepo.save(boleta);
    }

    // Buscar
    public Boleta buscarBoleta(Long id) {
        return boletaRepo.findById(id).orElse(null);
    }

    // Borrar
    public int borrarBoleta(Long id) {
        boletaRepo.deleteById(id);
        return 1;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uis.software1.sisbo_backend.servicio;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uis.software1.sisbo_backend.modelo.Seguidor;
import uis.software1.sisbo_backend.repositorio.SeguidorRepositorio;

/**
 *
 * @author Carlos
 */
@Service
public class SeguidorServicio {
    
    @Autowired
    SeguidorRepositorio seguidorRepo;
    
    
    // Listar 
    public List<Seguidor> getSeguidor(){
        return seguidorRepo.findAll();
    }
    
    // Crear
    public Seguidor nuevoSeguidor(Seguidor seguidor){
        return seguidorRepo.save(seguidor);
    }
    
    // Buscar
    public Seguidor buscarSeguidor(String id){
        Seguidor seguidor = null;
        seguidor = seguidorRepo.findById(id).orElse(null);
        if(seguidor == null){
            return null;
        }
        return seguidor;
    }
    
    // Borrar
    public int borrarSeguidor(String id){
        seguidorRepo.deleteById(id);
        return 1;
    }
    
}

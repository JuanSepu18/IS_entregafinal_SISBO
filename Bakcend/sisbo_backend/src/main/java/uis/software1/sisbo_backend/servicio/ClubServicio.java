/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uis.software1.sisbo_backend.servicio;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uis.software1.sisbo_backend.modelo.Club;
import uis.software1.sisbo_backend.repositorio.ClubRepositorio;

/**
 *
 * @author Carlos
 */
@Service
public class ClubServicio {

    @Autowired
    private ClubRepositorio clubRepo;

    // Read
    public List<Club> getClub() {
        return clubRepo.findAll();
    }

    // Read by ID
    public Club buscarClub(Long id) {
        Club club = null;
        club = clubRepo.findById(id).orElse(null);
        if(club == null){
            return null;
        }
        return club;
    }

    // Create y Update
    public Club nuevoClub(Club club){
        return clubRepo.save(club);
    }

    // Delete
    public int borrarClub(Long id) {
        clubRepo.deleteById(id);
        return 1;
    }
}
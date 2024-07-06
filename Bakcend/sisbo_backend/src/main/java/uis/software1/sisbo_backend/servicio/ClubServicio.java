/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uis.software1.sisbo_backend.servicio;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uis.software1.sisbo_backend.modelo.Club;
import uis.software1.sisbo_backend.modelo.LoginClub;
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
    
    //Login
    public int login(LoginClub loginClub){
        int CountClub = clubRepo.findByEmailAndPasswordInt(loginClub.getCorreo_electronico(), loginClub.getContrasena());
        return CountClub;
    };
    
    public ResponseEntity<?> ingresar(LoginClub loginClub){
        Map<String, Object> response = new HashMap<>();
        Club club= null;
        
        try{
            club = clubRepo.findByEmailAndPassword(loginClub.getCorreo_electronico(), loginClub.getContrasena());
            
            if(club == null){
                response.put("Club", null);
                response.put("Mensaje", "Alerta: email o password incorrectos");
                response.put("statusCode", HttpStatus.NOT_FOUND.value());
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }else{
                response.put("Club", club);
                response.put("Mensaje", "Datos correctos");
                response.put("statusCode", HttpStatus.OK.value());
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            
        }catch(Exception e){
            response.put("Club", null);
            response.put("Mensaje", "Ha ocurrido un error");
            response.put("statusCode", HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
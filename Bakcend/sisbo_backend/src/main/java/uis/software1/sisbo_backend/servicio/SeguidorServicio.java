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
import uis.software1.sisbo_backend.modelo.LoginSeguidor;
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
    
    //Login
    public int login(LoginSeguidor loginSeguidor){
        int CountClub = seguidorRepo.findByEmailAndPasswordInt(loginSeguidor.getCorreo_electronico(), loginSeguidor.getContrasena());
        return CountClub;
    };
    
    public ResponseEntity<?> ingresar(LoginSeguidor loginSeguidor){
        Map<String, Object> response = new HashMap<>();
        Seguidor seguidor= null;
        
        try{
            seguidor = seguidorRepo.findByEmailAndPassword(loginSeguidor.getCorreo_electronico(), loginSeguidor.getContrasena());
            
            if(seguidor == null){
                response.put("Seguidor", null);
                response.put("Mensaje", "Alerta: email o password incorrectos");
                response.put("statusCode", HttpStatus.NOT_FOUND.value());
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }else{
                response.put("Seguidor", seguidor);
                response.put("Mensaje", "Datos correctos");
                response.put("statusCode", HttpStatus.OK.value());
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            
        }catch(Exception e){
            response.put("Seguidor", null);
            response.put("Mensaje", "Ha ocurrido un error");
            response.put("statusCode", HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}

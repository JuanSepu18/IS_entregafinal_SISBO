/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package uis.software1.sisbo_backend.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uis.software1.sisbo_backend.modelo.Club;

/**
 *
 * @author Carlos
 */
public interface ClubRepositorio extends JpaRepository<Club, Long>{
    
    @Query("select count(*) from Club as p where p.correo_electronico = :correo_electronico and p.contrasena= :contrasena")
    Integer findByEmailAndPasswordInt(@Param("correo_electronico") String correo_electronico, @Param("contrasena") String contrasena);
    
    @Query("select p from Club as p where p.correo_electronico = :correo_electronico and p.contrasena= :contrasena")
    Club findByEmailAndPassword(@Param("correo_electronico") String correo_electronico, @Param("contrasena") String contrasena);
    
}

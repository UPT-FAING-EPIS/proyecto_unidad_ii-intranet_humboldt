/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.users.api.Servicio;

import com.users.api.Entidad.Usuarios;
import com.users.api.Repositorio.UsuarioRepositorio;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioServicio {

    private final UsuarioRepositorio userrepositorio;
        
        public void save (Usuarios userrol){
            userrepositorio.save(userrol);
        }
        
        public List<Usuarios> findAll(){
            return userrepositorio.findAll();
        }
        
        public Optional<Usuarios> findById(String dni){
            return userrepositorio.findById(dni);
        }
        
}

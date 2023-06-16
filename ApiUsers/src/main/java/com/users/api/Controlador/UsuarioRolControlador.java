/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.users.api.Controlador;

import com.users.api.Entidad.UsuarioRol;
import com.users.api.Publicador.Publisher;
import com.users.api.Servicio.UsuarioRolServicio;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuariorol")
@RequiredArgsConstructor
public class UsuarioRolControlador {
    private final UsuarioRolServicio userrolservicio;
    private final Publisher rabbitMQPublisher;
    
    @GetMapping
    public List<UsuarioRol> findAll(){
        return userrolservicio.findAll();
    }
    
    @GetMapping("/{idur}")
    public UsuarioRol findById(@PathVariable Long idur){
         Optional<UsuarioRol> usuariorol = userrolservicio.findById(idur);
        // Envío del mensaje a RabbitMQ
        String mensaje = "Se ha realizado una busqueda del usuario : " + idur;
        rabbitMQPublisher.send(mensaje);
        return usuariorol.orElse(null);
    }
    
    @PostMapping
    public void save(@RequestBody UsuarioRol ur){
        userrolservicio.save(ur);
        //Envio de mensaje a RabbitMQ
        String mensaje = "Se ha asignado el rol al DNI: " + ur.getFk_dni();
        rabbitMQPublisher.send(mensaje);
    }
    
    @PutMapping("/{idur}")
    public void update(@RequestBody UsuarioRol ur){
        userrolservicio.save(ur);
        //Envio de mensaje a RabbitMQ
        String mensaje = "Se ha actualizado el Rol del usuario: " +ur.getFk_dni();
        rabbitMQPublisher.send(mensaje);
    }    
       
}

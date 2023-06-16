/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.users.api.Controlador;

import com.users.api.Entidad.Usuarios;
import com.users.api.Publicador.Publisher;
import com.users.api.Servicio.UsuarioServicio;
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
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioControlador {
    private final UsuarioServicio usuarioservicio;
    private final Publisher rabbitMQPublisher;
    
    @GetMapping
    public List<Usuarios> findAll(){
        return usuarioservicio.findAll();
    }
    
    @GetMapping("/{id}")
    public Usuarios findById(@PathVariable String id){
        Optional<Usuarios> usuario = usuarioservicio.findById(id);
        // Envío del mensaje a RabbitMQ
        String mensaje = "Se ha realizado una busqueda del usuario con DNI : " + id;
        rabbitMQPublisher.send(mensaje);
        return usuario.orElse(null);
        
    }
    
    @PostMapping
    public void save(@RequestBody Usuarios u){
        usuarioservicio.save(u);
        //Envio de mensaje a RabbitMQ
        String mensaje = "Se ha guardado un nuevo usuario con el DNI: " + u.getDni();
        rabbitMQPublisher.send(mensaje);
    }
    
    @PutMapping("/{id}")
    public void update(@RequestBody Usuarios u){
        usuarioservicio.save(u);
        //Envio de mensaje a RabbitMQ
        String mensaje = "Se ha actualizado el Usuario: " + u.getDni();
        rabbitMQPublisher.send(mensaje);
    }       
}

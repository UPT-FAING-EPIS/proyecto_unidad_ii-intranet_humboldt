package com.users.api.Controlador;

import com.users.api.Entidad.Rol;
import com.users.api.Publicador.Publisher;
import com.users.api.Servicio.RolServicio;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/rol")
@RequiredArgsConstructor
public class RolControlador {
    private final RolServicio rolservicio;
    private final Publisher rabbitMQPublisher;
    
    
    @PostMapping("/roles")
    public void save(@RequestBody Rol r){
        rolservicio.save(r);
        //Envio de mensaje a RabbitMQ
        String mensaje = "Se ha guardado un nuevo rol: " + r.getNomrol();
        rabbitMQPublisher.send(mensaje);
        
    }
    
    @GetMapping
    public List<Rol> findAll(){
        return rolservicio.findAll();        
    }
    
    @GetMapping("/roles/{idrol}")
    public Rol findById(@PathVariable String idrol){
        Optional<Rol> rol = rolservicio.findById(idrol);
        // Envío del mensaje a RabbitMQ
        String mensaje = "Se ha realizado una busqueda del Rol " + idrol;
        rabbitMQPublisher.send(mensaje);
        return rol.orElse(null);
    }
    
    @DeleteMapping("/roles/{idrol}")
    public void deleteById(@PathVariable String idrol){
        rolservicio.deleteById(idrol);
        //Envio de mensaje a RabbitMQ
        String mensaje = "Se ha eliminado el rol con ID " + idrol;
        rabbitMQPublisher.send(mensaje);
    }
    
    @PutMapping("/{idrol}")
    public void update(@RequestBody Rol r){
        rolservicio.save(r);        
        //Envio de mensaje a RabbitMQ
        String mensaje = "Se ha actualizado el Rol " + r.getNomrol();
        rabbitMQPublisher.send(mensaje);
    }
}

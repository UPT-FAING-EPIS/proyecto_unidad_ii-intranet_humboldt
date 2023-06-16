package com.users.api.Consumidor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Consumer {    
    
    @RabbitListener(queues = {"${apiusuarios.queue.name}"})
    public void receive(@Payload String message) {
        log.info("Notificacion: {}", message);
        makeSlow();         
    }
    
    private void makeSlow(){
        try{
            Thread.sleep(5000);
        }catch(InterruptedException e){
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

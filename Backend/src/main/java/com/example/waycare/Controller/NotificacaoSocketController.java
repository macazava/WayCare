package com.example.waycare.Controller;

import com.example.waycare.models.Notificacao;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificacaoSocketController {

    @MessageMapping("/nova-notificacao")
    @SendTo("/notificacoes/fluxo")
    public Notificacao enviar(Notificacao notificacao) {
        return notificacao;
    }
     }

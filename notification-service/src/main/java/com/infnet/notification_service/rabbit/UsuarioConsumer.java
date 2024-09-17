package com.infnet.notification_service.rabbit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.infnet.notification_service.model.Usuario;
import com.infnet.notification_service.service.EmailService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Slf4j
@Component
public class UsuarioConsumer {
    private final ObjectMapper objectMapper;
    private final EmailService emailService;

    @RabbitListener(queues = {"chefe-criacao-queue"})
    public Usuario receive(@Payload String json) {
        try {
            Usuario usuario = objectMapper.readValue(json, Usuario.class);
            log.info("Notificação do", usuario);
            emailService.sendRegistrationEmail(usuario);

            return usuario;
        } catch (JsonProcessingException | MessagingException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}

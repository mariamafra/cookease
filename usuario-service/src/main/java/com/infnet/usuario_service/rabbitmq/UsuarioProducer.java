package com.infnet.usuario_service.rabbitmq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.infnet.usuario_service.model.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioProducer {
    private final AmqpTemplate amqpTemplate;
    private final ObjectMapper objectMapper;

    public void send(Usuario usuario) throws JsonProcessingException {
        amqpTemplate.convertAndSend("chefe-criacao-exc", "chefe-criacao-rk", objectMapper.writeValueAsString(usuario));
    }
}

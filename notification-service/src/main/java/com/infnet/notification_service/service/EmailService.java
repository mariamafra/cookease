package com.infnet.notification_service.service;

import com.infnet.notification_service.model.Email;
import com.infnet.notification_service.model.Usuario;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class EmailService {

    // Configurações do servidor SMTP
//    private final String username = "seu-email@gmail.com";
//    private final String password = "sua-senha";
    private final String host = "smtp.gmail.com";
    private final String port = "587";

    public void sendEmail(Email email, Usuario usuario) throws MessagingException {
        // Configurações do servidor SMTP
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);

        // Sessão com autenticação
        Session session = Session.getInstance(props);

        // Criação da mensagem de e-mail
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(usuario.getEmail()));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email.getTo()));
        message.setSubject(email.getSubject());
        message.setText(email.getBody());

        // Envio do e-mail
        Transport.send(message);
    }
}

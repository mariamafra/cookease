package com.infnet.notification_service.service;

import com.infnet.notification_service.model.Usuario;
import jakarta.mail.*;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender mailSender;

    public void sendRegistrationEmail(Usuario usuario) throws MessagingException {
        this.createEmail(usuario.getEmail(), usuario.toString(), "Notificação RabbitMQ! Olá " + usuario.getNome());
    }

    private void createEmail(String toEmail, String body, String subject) throws MessagingException {

        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

        mimeMessageHelper.setFrom("newsapi.noreply@gmail.com"); //e-mail que vai enviar.
        mimeMessageHelper.setTo(toEmail); //e-mail que vai receber.
        mimeMessageHelper.setText(
                "<h1>Bem-vindo!!!!</h1> <h3>Você acabou de chegar na melhor rede de receitas do mundo!!! O <strong style=\"color: red;\">COOKEASE</strong></h3>", true);
        mimeMessageHelper.setSubject(subject);

        mailSender.send(mimeMessage);
    }
}

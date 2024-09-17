package com.infnet.notification_service.service;

import com.infnet.notification_service.model.Email;
import com.infnet.notification_service.model.Usuario;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
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

    // Configurações do servidor SMTP
//    private final String username = "seu-email@gmail.com";
//    private final String password = "sua-senha";
/*    private final String host = "smtp.gmail.com";
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
    }*/

    public void sendRegistrationEmail() {
        try {
            this.createEmail("rafaela.breves@al.infnet.edu.br", "Bem-vindo ao Cookease!!!", "Rafaela");
        } catch (MessagingException e) {
            throw new RuntimeException("Erro ao mandar e-mail.");
        }
    }

    private void createEmail(String toEmail, String body, String subject) throws MessagingException {

        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

        mimeMessageHelper.setFrom("newsapi.noreply@gmail.com"); //e-mail que vai enviar.
        mimeMessageHelper.setTo(toEmail); //e-mail que vai receber.
        mimeMessageHelper.setText(body, true);
        mimeMessageHelper.setSubject(subject);

        mailSender.send(mimeMessage);
    }
}

package com.infnet.notification_service.controller;


import com.infnet.notification_service.model.Email;
import com.infnet.notification_service.model.Usuario;
import com.infnet.notification_service.service.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/send")
    public String sendEmail(@RequestBody Email email, @RequestBody Usuario usuario) {
        try {
            emailService.sendEmail(email, usuario);
            return "E-mail enviado com sucesso!";
        } catch (MessagingException e) {
            e.printStackTrace();
            return "Falha ao enviar o e-mail.";
        }
    }
}

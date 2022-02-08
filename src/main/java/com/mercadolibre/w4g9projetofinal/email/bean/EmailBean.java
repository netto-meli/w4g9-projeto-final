package com.mercadolibre.w4g9projetofinal.email.bean;


import com.mercadolibre.w4g9projetofinal.email.service.EmailService;
import com.mercadolibre.w4g9projetofinal.email.service.SmtpEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.MailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailBean {

    @Autowired
    MailSender mailSender;

    @Bean
    public EmailService emailService() {
        return new SmtpEmailService(mailSender);
    }
}

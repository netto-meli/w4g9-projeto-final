package com.mercadolibre.w4g9projetofinal.email.service;

import com.mercadolibre.w4g9projetofinal.entity.User;
import org.springframework.mail.SimpleMailMessage;

public interface EmailService {

    void sendEmail(SimpleMailMessage msg);

    void sendNewPasswordEmail(User user, String newPass);
}

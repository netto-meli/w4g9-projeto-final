package com.mercadolibre.w4g9projetofinal.email.service;

import com.mercadolibre.w4g9projetofinal.entity.User;

import javax.mail.internet.MimeMessage;

/***
 * Interface de serviço para Email
 *
 * @author Marcos Sá
 */
public interface EmailService {

    void sendHtmlEmail(MimeMessage msg);

    void sendNewPasswordHtml(User user, String newPass);
}

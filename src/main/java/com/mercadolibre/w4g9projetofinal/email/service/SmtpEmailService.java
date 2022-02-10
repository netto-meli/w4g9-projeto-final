package com.mercadolibre.w4g9projetofinal.email.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.internet.MimeMessage;

/***
 * Classe de configuração para envio de email
 *
 * @author Marcos Sá
 */

public class SmtpEmailService extends AbstractEmailService{

    private JavaMailSender javaMailSender;

    public SmtpEmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    private static final Logger LOG = LoggerFactory.getLogger(SmtpEmailService.class);

    @Override
    public void sendHtmlEmail(MimeMessage msg) {
        LOG.info("Enviando email...");
        javaMailSender.send(msg);
        LOG.info("Envio realizado com sucesso...");
    }
}

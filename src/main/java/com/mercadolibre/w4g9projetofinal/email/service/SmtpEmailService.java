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

    /*** Instancia de JavaMailSender: <b>JavaMailSender</b>.
     */
    private JavaMailSender javaMailSender;

    /*** Construtor para instância de JavaMailSender.
     * @param javaMailSender sender
     */
    public SmtpEmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    /*** Método para envio de email HTML.
     * @param msg msg
     */
    @Override
    public void sendHtmlEmail(MimeMessage msg) {
        javaMailSender.send(msg);
    }
}

package com.mercadolibre.w4g9projetofinal.email.service;

import com.mercadolibre.w4g9projetofinal.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

/***
 * Classe de configuração para envio de email no formato HTML
 *
 * @author Marcos Sá
 */
public abstract class AbstractEmailService implements EmailService{

    /*** sender definido em Application.yml: <b>{default.sender}</b>.
     */
    @Value("${default.sender}")
    private String sender;

    /*** Instancia de TemplateEngine: <b>TemplateEngine</b>.
     */
    @Autowired
    private TemplateEngine templateEngine;

    /*** Instancia de JavaMailSender: <b>JavaMailSender</b>.
     */
    @Autowired
    private JavaMailSender javaMailSender;

    /*** Método que faz intância de User e newPass para template de Email HTML
     * @param user objeto user a ser inserido no template HTML
     * @param newPass objeto newwPass a ser inserido no template HTML
     * @return string
     */
    protected String htmlFromTemplateNewPassword(User user, String newPass) {
        Context context = new Context();
        context.setVariable("user", user);
        context.setVariable("newPass", newPass);
        return templateEngine.process("email/newPassword", context);
    }

    /*** Método que faz envio de Email HTML
     * @param user objeto user vindo do template HTML
     * @param newPass objeto newPass vindo do template HTML
     */
    @Override
    public void sendNewPasswordHtml(User user, String newPass) {
        try{
            MimeMessage mm = prepareMimeMessagePassword(user, newPass);
            sendHtmlEmail(mm);
        }
        catch (MessagingException e) {
            e.getMessage();
        }
    }

    /*** Método que faz ajustes para envio de Email HTML
     * @param user objeto user vindo do template HTML
     * @param newPass objeto newPass vindo do template HTML
     * @return msg
     * @throws MessagingException ex
     */
    protected MimeMessage prepareMimeMessagePassword(User user, String newPass) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mmh = new MimeMessageHelper(mimeMessage, true);
        mmh.setTo(user.getEmail());
        mmh.setFrom(this.sender);
        mmh.setSubject("Solicitação de nova Senha");
        mmh.setSentDate(new Date(System.currentTimeMillis()));
        mmh.setText(htmlFromTemplateNewPassword(user, newPass), true);
        return mimeMessage;
    }
}

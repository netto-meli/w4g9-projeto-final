package com.mercadolibre.w4g9projetofinal;

import com.mercadolibre.w4g9projetofinal.email.service.EmailService;
import com.mercadolibre.w4g9projetofinal.email.service.SmtpEmailService;
import com.mercadolibre.w4g9projetofinal.util.ScopeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.MailSender;

/** 
 * Main class for the App.
 */
@SpringBootApplication
public class Application {
  /** 
   * @param args command line arguments for the application.
   */
  public static void main(String[] args) {
    ScopeUtils.calculateScopeSuffix();
    new SpringApplicationBuilder(Application.class).registerShutdownHook(true).run(args);
  }

  @Bean
  public EmailService emailService() {
    return new SmtpEmailService();
  }

}

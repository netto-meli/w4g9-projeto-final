package com.mercadolibre.w4g9projetofinal.email.service;

import com.mercadolibre.w4g9projetofinal.entity.User;
import com.mercadolibre.w4g9projetofinal.exceptions.ObjectNotFoundException;
import com.mercadolibre.w4g9projetofinal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AuthService {

    private UserRepository userRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private EmailService emailService;

    private Random rand;

    public AuthService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.emailService = new SmtpEmailService();
        this.rand = new Random();
    }

    public void sendNewPassword(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow( () -> new ObjectNotFoundException("Email não encontrado"));

        String newPass = this.newPassword();
        user.setPassword(bCryptPasswordEncoder.encode(newPass));

        userRepository.save(user);
        emailService.sendNewPasswordEmail(user, newPass);
    }

    private String newPassword() {
        char[] vet = new char[12];
        for (int i=0; i<10; i++) {
            vet[i] = randomChar();
        }
        return new String(vet);
    }

    private char randomChar() {
        int opt = rand.nextInt(4);
        //gera um dígito
        if (opt == 0) {
            return (char) (rand.nextInt(10) + 48);
        }
        //Gera letra Maiúscula
        else if (opt == 1) {
            return (char) (rand.nextInt(26) + 65);
        }
        //Gera Caracteres especiais
        else if (opt == 2) {
            return (char) (rand.nextInt(6) + 33);
        }
        //Gera letra Minúscula
        else {
            return (char) (rand.nextInt(26) + 97);
        }
    }

}

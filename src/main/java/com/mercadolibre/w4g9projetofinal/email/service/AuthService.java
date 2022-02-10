package com.mercadolibre.w4g9projetofinal.email.service;

import com.mercadolibre.w4g9projetofinal.entity.User;
import com.mercadolibre.w4g9projetofinal.exceptions.ObjectNotFoundException;
import com.mercadolibre.w4g9projetofinal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

/***
 * Classe de serviço para recuperação de senha
 *
 * @author Marcos Sá
 */
@Service
public class AuthService {

    /*** Instancia de EmailService: <b>EmailService</b>.
     */
    @Autowired
    private EmailService emailService;

    /*** Instancia de UserRepository: <b>UserRepository</b>.
     */
    private final UserRepository userRepository;

    /*** Instancia de BCryptPasswordEncoder: <b>BCryptPasswordEncoder</b>.
     */
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    /*** Instancia de Random: <b>Random</b>.
     */
    private final Random rand;

    /*** Construtor para instância de userRepository e bCryptPasswordEncoder.
     * @param userRepository user
     * @param bCryptPasswordEncoder crypt
     */
    public AuthService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.rand = new Random();
    }

    /*** Método para envio de nova senha
     * @param email String referente ao email do usuário que está solicitando a redefinição de senha
     */
    public void sendNewPassword(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow( () -> new ObjectNotFoundException("Email não encontrado"));

        String newPass = this.newPassword();
        user.setPassword(bCryptPasswordEncoder.encode(newPass));

        userRepository.save(user);
        emailService.sendNewPasswordHtml(user, newPass);
    }

    /*** Método que gera uma senha de 12 caracteres
     * @return string
     */
    private String newPassword() {
        char[] vet = new char[12];
        for (int i=0; i<10; i++) {
            vet[i] = randomChar();
        }
        return new String(vet);
    }

    /*** Método que gera uma caracteres aleatórios para a criação de nova senha
     * @return char
     */
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

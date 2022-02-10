package com.mercadolibre.w4g9projetofinal.email.controller;

import com.mercadolibre.w4g9projetofinal.email.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/***
 * Classe de controller para recuperação de senha
 *
 * @author Marcos Sá
 */

@RestController
@RequestMapping(value = "/api/v1/auth")
public class AuthResource {

    @Autowired
    private AuthService authService;

    @PostMapping(value = "/forgot/")
    public ResponseEntity<Void> forgot(@RequestParam String email) {
        authService.sendNewPassword(email);
        return ResponseEntity.noContent().build();
    }
}

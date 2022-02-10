package com.mercadolibre.w4g9projetofinal.controller;

import com.mercadolibre.w4g9projetofinal.dtos.converter.UserConverter;
import com.mercadolibre.w4g9projetofinal.dtos.response.UserResponseDTO;
import com.mercadolibre.w4g9projetofinal.entity.User;
import com.mercadolibre.w4g9projetofinal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/***
 * Classe de controle para User
 *
 * @author Marcos Sá
 * @author Fernando
 */
@RestController
@RequestMapping("/api/v1/fresh-products/users")
@PreAuthorize("hasRole('ADMIN') OR hasRole('REPRESENTATIVE')")
public class UserController {

    @Autowired
    private UserService service;

    /*** Método para buscar todos os Users do banco de dados<br>
     * GET - /users
     * @return Payload com Lista de Users e ResponseEntity com status <b>OK</b>
     */
    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> findAll() {
        List<User> list = service.findAll();
        List<UserResponseDTO> listDTO = UserConverter.convertEntityListToDtoList(list);
        return ResponseEntity.ok(listDTO);
    }
}

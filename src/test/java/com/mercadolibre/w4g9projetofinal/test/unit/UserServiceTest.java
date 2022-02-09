package com.mercadolibre.w4g9projetofinal.test.unit;

import com.mercadolibre.w4g9projetofinal.entity.User;
import com.mercadolibre.w4g9projetofinal.repository.UserRepository;
import com.mercadolibre.w4g9projetofinal.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class UserServiceTest {

    @Test
    public void verificaBuscaTodosUsuarios() {
        // <-- ARRANGE -->
        List<User> usuarios = new ArrayList<>();
        // Mocks - Class
        UserRepository userRepository = Mockito.mock(UserRepository.class);
        // Mock - Actions
        Mockito.when(userRepository.findAll()).thenReturn(usuarios);
        // Service
        UserService us = new UserService(userRepository);

        // <-- ACT -->
        List<User> usuariosRetornados = us.findAll();

        // <-- ASSERTION -->
        Assertions.assertEquals(usuarios, usuariosRetornados);
    }
}

package com.mercadolibre.w4g9projetofinal.service;

import com.mercadolibre.w4g9projetofinal.entity.User;
import com.mercadolibre.w4g9projetofinal.repository.UserRepository;
import com.mercadolibre.w4g9projetofinal.security.entity.UserSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> findAll() {
        return repository.findAll();
    }

    public static UserSS authenticated() {
        try{
            return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }
        catch (Exception e) {
            throw new AccessDeniedException("Acesso negado");
        }
    }

}

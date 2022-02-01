package com.mercadolibre.w4g9projetofinal.dtos.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CredentiaisDTO implements Serializable {
    private static final long serialVersionUID = 889984971963283064L;

    private String username;
    private String pass;
}

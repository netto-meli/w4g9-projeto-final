package com.mercadolibre.w4g9projetofinal.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
/***
 * DTO para serialização
 * Classe de Request
 * contem: name(String),
 * endereco(String),
 * email(String)
 * @autor Leonardo
 */
public class BuyerRequestDTO {
    @NotEmpty(message = "Campo Obrigatório")
    private String username;
    @NotEmpty
    @Size(min=2 ,max=20,message="Nome deve ter no máximo 20 caracteres e no minimo 2 caracteres. ")
    private String name;
    @Email
    private String email;
    @NotEmpty(message = "Campo Obrigatório")
    private String pass;
    @NotEmpty
    private String address;
}
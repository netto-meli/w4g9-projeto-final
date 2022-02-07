package com.mercadolibre.w4g9projetofinal.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

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
    @NotNull(message = "Campo Obrigatório")
    @NotEmpty(message = "Campo Obrigatório")
    @Size(min=4, max=16,message="Usuário deve ter 4 e 16 caracteres.")
    @Pattern(regexp = "^[A-Za-z][A-Za-z0-9_]{3,16}$",
            message = "Usuário pode conter letras e números, mas deve começar com uma letra")
    private String username;
    @NotNull(message = "Campo Obrigatório")
    @NotEmpty(message = "Campo Obrigatório")
    @Size(min=2 ,max=60,message="Nome deve ter no máximo 60 caracteres e no minimo 2 caracteres.")
    @Pattern(regexp = "^[-'a-zA-ZÀ-ÖØ-öø-ÿ ]+$", message = "Apenas caracteres do alfabeto, incluindo acentos")
    private String name;
    @Email(message = "Email inválido")
    @NotNull(message = "Campo Obrigatório")
    @NotEmpty(message = "Campo Obrigatório")
    @Pattern(regexp = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$",
            message = "Email inválido")
    private String email;
    @NotNull(message = "Campo Obrigatório")
    @NotEmpty(message = "Campo Obrigatório")
    @Size(min=8 , message="Senha deve ter pelo menos 8 caracteres.")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$",
            message = "Senha deve conter caracter maiusculo e minusculo, numero e caractere especial, sem espaço.")
    private String pass;
    @NotNull(message = "Campo Obrigatório")
    @NotEmpty(message = "Campo Obrigatório")
    @Size(min=2 ,max=80,message="Endereço deve ter no máximo 80 caracteres e no minimo 2 caracteres.")
    private String address;
}
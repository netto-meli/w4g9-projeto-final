package com.mercadolibre.w4g9projetofinal.email.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/***
 * Classe para realizar conversao de dados para tratativa de dados
 * @author Marcos Sá - Classe
 * @author Fernando Netto - validações
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailDTO {
    @Email(message = "Email inválido")
    @NotNull(message = "Campo Obrigatório")
    @NotEmpty(message = "Campo Obrigatório")
    @Pattern(regexp = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$",
            message = "Email inválido")
    private String email;
}

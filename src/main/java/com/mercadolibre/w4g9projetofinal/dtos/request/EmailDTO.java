package com.mercadolibre.w4g9projetofinal.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmailDTO implements Serializable {
    private static final long serialVersionUID = -6135537228291738302L;

    @NotEmpty(message = "Preenchimento Obrigatório")
    @Email(message = "Email inválido")
    private String email;

}

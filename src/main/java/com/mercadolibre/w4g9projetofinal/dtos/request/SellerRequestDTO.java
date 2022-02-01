package com.mercadolibre.w4g9projetofinal.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SellerRequestDTO {
    private String name;
    private String email;
    private String username;

    @NotEmpty(message = "Campo obrigatório")
    private String password;
}

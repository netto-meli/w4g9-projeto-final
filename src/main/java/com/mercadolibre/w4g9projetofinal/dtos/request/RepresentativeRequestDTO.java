package com.mercadolibre.w4g9projetofinal.dtos.request;

import com.mercadolibre.w4g9projetofinal.entity.enums.RepresentativeJob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RepresentativeRequestDTO {
    private String name;
    private RepresentativeJob job;
    private String email;
    private String username;

    @NotEmpty(message = "Campo obrigatório")
    private String password;
}

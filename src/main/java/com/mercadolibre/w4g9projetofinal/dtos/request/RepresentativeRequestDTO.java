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
    @NotEmpty(message = "Campo Obrigatório")
    private String username;
    private String name;
    private String email;
    @NotEmpty(message = "Campo Obrigatório")
    private String pass;
    private RepresentativeJob job;
    private Long warehouseId;
}

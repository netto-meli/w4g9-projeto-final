package com.mercadolibre.w4g9projetofinal.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WarehouseRequestDTO {
    @NotNull(message = "Campo Obrigatório")
    @NotEmpty(message = "Campo Obrigatório")
    @Size(min=4, max=16,message="Nome deve ter 4 e 16 caracteres.")
    private String name;
    @NotNull(message = "Campo Obrigatório")
    @NotEmpty(message = "Campo Obrigatório")
    @Size(min=2 ,max=80,message="Endereço deve ter no máximo 80 caracteres e no minimo 2 caracteres.")
    private String location;
}
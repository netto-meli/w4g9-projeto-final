package com.mercadolibre.w4g9projetofinal.dtos.request;

import com.mercadolibre.w4g9projetofinal.entity.enums.CargoRepresentante;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RepresentativeRequestDTO {
    private String name;
    private CargoRepresentante job;
}

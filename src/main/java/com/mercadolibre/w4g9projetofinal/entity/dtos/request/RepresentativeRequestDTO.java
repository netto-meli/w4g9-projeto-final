package com.mercadolibre.w4g9projetofinal.entity.dtos.request;

import com.mercadolibre.w4g9projetofinal.entity.enums.RepresentativeJob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RepresentativeRequestDTO {
    private String name;
    private RepresentativeJob job;
    private String email;
}

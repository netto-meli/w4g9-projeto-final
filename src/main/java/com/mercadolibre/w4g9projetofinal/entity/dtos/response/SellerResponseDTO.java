package com.mercadolibre.w4g9projetofinal.entity.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SellerResponseDTO {

    private Long id;
    private String name;
    private String email;
}

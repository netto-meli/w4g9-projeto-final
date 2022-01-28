package com.mercadolibre.w4g9projetofinal.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WarehouseResponseDTO {
    private long id;
    private String name;
    private String location;
}

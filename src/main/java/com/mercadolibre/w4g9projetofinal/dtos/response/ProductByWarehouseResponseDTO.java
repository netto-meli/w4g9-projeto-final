package com.mercadolibre.w4g9projetofinal.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductByWarehouseResponseDTO {
    private Long productId;
    private List<WarehouseResponseDTOByProduct> warehouses;
}
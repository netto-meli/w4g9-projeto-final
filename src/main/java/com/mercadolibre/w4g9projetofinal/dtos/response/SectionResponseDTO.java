package com.mercadolibre.w4g9projetofinal.dtos.response;

import com.mercadolibre.w4g9projetofinal.entity.enums.RefrigerationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author fbontempo
 * @version 0.2
 * */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SectionResponseDTO {
    private Long id;
    private String name;
    private RefrigerationType refrigerationType;
    private Long warehouseCode;
    private int stockLimit;
    private int currentStock;
    private float minTeperature;
    private float maxTeperature;
}

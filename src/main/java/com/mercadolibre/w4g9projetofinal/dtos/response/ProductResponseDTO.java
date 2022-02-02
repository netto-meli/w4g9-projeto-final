package com.mercadolibre.w4g9projetofinal.dtos.response;

import com.mercadolibre.w4g9projetofinal.entity.enums.RefrigerationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
/***
 * DTO para Deserialização do Advertise
 * contem id (Long),
 * nome (String),
 * descricao (String),
 * categoryRefrigeration (RefrigerationType)
 */
public class ProductResponseDTO {
    private Long id;
    private String name;
    private String description;
    private RefrigerationType categoryRefrigeration;
}

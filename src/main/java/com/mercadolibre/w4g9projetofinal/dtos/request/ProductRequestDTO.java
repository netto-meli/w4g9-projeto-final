package com.mercadolibre.w4g9projetofinal.dtos.request;

import com.mercadolibre.w4g9projetofinal.entity.enums.RefrigerationType;
import lombok.AllArgsConstructor;
import lombok.Data;

/*** DTO para serialização de Produto
 *
 * @author Felipe
 * @author Fernando Netto
 */
@Data
@AllArgsConstructor
public class ProductRequestDTO {
    private String name;
    private String description;
    private float minTemperature;
    private float maxTemperature;
    private RefrigerationType categoryRefrigeration;
}

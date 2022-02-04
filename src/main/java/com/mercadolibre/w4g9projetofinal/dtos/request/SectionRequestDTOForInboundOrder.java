package com.mercadolibre.w4g9projetofinal.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SectionRequestDTOForInboundOrder {
    @NotEmpty
    //todo fazer mais validacoes
    private Long sectionCode;
    private Long warehouseCode;
}

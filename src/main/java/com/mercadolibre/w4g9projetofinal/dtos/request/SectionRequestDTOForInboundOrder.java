package com.mercadolibre.w4g9projetofinal.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SectionRequestDTOForInboundOrder {
    @NotNull(message = "Campo Obrigatório")
    @Positive(message = "Id deve ser um valor positivo")
    private Long sectionCode;
    @NotNull(message = "Campo Obrigatório")
    @Positive(message = "Id deve ser um valor positivo")
    private Long warehouseCode;
}

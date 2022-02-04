package com.mercadolibre.w4g9projetofinal.dtos.request;

import com.mercadolibre.w4g9projetofinal.entity.Section;
import com.mercadolibre.w4g9projetofinal.entity.enums.RefrigerationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author fbontempo
 * @version 0.3
 * @see {@link Section}
 * @see {@link com.mercadolibre.w4g9projetofinal.dtos.response.SectionResponseDTO}
 * */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SectionRequestDTO {
    @NotNull
    @NotEmpty
    @Size(min = 5, max = 15, message = "Tamanho min de 5 e max 15")
    private String name;
    private RefrigerationType type;
    private Long idWarehouse;

    @Min(value = 1, message = "Tamanho minimo 1")
    @Max(value = 100, message = "Tamanho máximo 100")
    private int stockLimit;

    @Min(value = 1, message = "Tamanho minimo 1")
    @Max(value = 100, message = "Tamanho máximo 100")
    private int currentStock;

    @Min(value = -20, message = "Tamanho minimo -20 graus")
    @Max(value = 15, message = "Tamanho máximo 15 graus")
    private float minTeperature;

    @Min(value = -20, message = "Tamanho minimo -20 graus")
    @Max(value = 15, message = "Tamanho máximo 15 graus")
    private float maxTeperature;
}

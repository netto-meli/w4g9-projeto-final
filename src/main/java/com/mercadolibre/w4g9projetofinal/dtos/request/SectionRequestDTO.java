package com.mercadolibre.w4g9projetofinal.dtos.request;

import com.mercadolibre.w4g9projetofinal.entity.enums.RefrigerationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

/**
 * @author fbontempo
 * @version 0.3
 * */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SectionRequestDTO {
    @NotNull(message = "Campo Obrigatório")
    @NotEmpty(message = "Campo Obrigatório")
    @Size(min=4, max=16,message="Nome deve ter 4 e 16 caracteres.")
    private String name;
    @NotNull(message = "Campo Obrigatório")
    private RefrigerationType type;
    @NotNull(message = "Campo Obrigatório")
    @Positive(message = "Id deve ser um valor positivo")
    private Long idWarehouse;
    @NotNull(message = "Campo Obrigatório")
    @Positive(message = "Estoque total deve ser maior que 0")
    private int stockLimit;
    @NotNull(message = "Campo Obrigatório")
    @PositiveOrZero(message = "Estoque atual não pode ser negativo.")
    private int currentStock;
    @NotNull(message = "Campo Obrigatório")
    @Digits(integer = 2, fraction = 2, message = "Temperatura mínima inválida. Aceito apenas 2 dígitos decimais")
    @Max(value = 100, message = "Temperatura mínima tem que ser menor que 100")
    @Min(value = -100, message = "Temperatura mínima tem que ser maior que -100")
    private float minTeperature;
    @NotNull(message = "Campo Obrigatório")
    @Digits(integer = 2, fraction = 2, message = "Temperatura máxima inválida. Aceito apenas 2 dígitos decimais")
    @Max(value = 100 , message = "Temperatura máxima tem que ser menor que 100")
    @Min(value = -100 , message = "Temperatura máxima tem que ser maior que -100")
    private float maxTeperature;
}

package com.mercadolibre.w4g9projetofinal.dtos.request;

import com.mercadolibre.w4g9projetofinal.entity.enums.RefrigerationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

/*** DTO para serialização de Produto
 *
 * @author Felipe
 * @author Fernando Netto
 * @author Leonardo
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestDTO {
    @NotEmpty(message = "Campo Obrigatório")
    @NotNull(message = "Campo Obrigatório")
    @Size(min=4,max=20,message="Nome deve ter no máximo 20 caracteres e no mínimo 4 caracteres. ")
    private String name;
    @NotEmpty(message = "Campo Obrigatório")
    @NotNull(message = "Campo Obrigatório")
    @Size(min=4,max=100,message="Descrição deve ter no máximo 100 caracteres e no mínimo 4 caracteres. ")
    private String description;
    @NotNull(message = "Campo Obrigatório")
    @Digits(integer = 2, fraction = 2, message = "Temperatura mínima inválida. Aceito apenas 2 dígitos decimais")
    @Max(value = 100 , message = "Temperatura mínima tem que ser menor que 100")
    @Min(value = -100 , message = "Temperatura mínima tem que ser maior que -100")
    private float minTemperature;
    @NotNull(message = "Campo Obrigatório")
    @Digits(integer = 2, fraction = 2, message = "Temperatura máxima inválida. Aceito apenas 2 dígitos decimais")
    @Max(value = 100 , message = "Temperatura máxima tem que ser menor que 100")
    @Min(value = -100 , message = "Temperatura máxima tem que ser maior que -100")
    private float maxTemperature;
    @NotNull(message = "Campo Obrigatório")
    private RefrigerationType categoryRefrigeration;
}

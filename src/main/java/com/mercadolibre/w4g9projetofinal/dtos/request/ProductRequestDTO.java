package com.mercadolibre.w4g9projetofinal.dtos.request;

import com.mercadolibre.w4g9projetofinal.entity.enums.RefrigerationType;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/*** DTO para serialização de Produto
 *
 * @author Felipe
 * @author Fernando Netto
 * @author Leonardo
 */
@Data
@AllArgsConstructor
public class ProductRequestDTO {

    @NotEmpty(message = "Campo Obrigatório")
    @NotBlank
    @Size(min=4,max=20,message="Nome deve ter no máximo 20 caracteres e no minimo 4 caracteres. ")
    private String name;
    @Size(min=4,max=20,message="Descrição deve ter no máximo 20 caracteres e no minimo 4 caracteres. ")
    private String description;
    @NotNull(message = "Campo Obrigatório")
    private float minTemperature;
    @NotNull(message = "Campo Obrigatório")
    private float maxTemperature;
    @NotNull(message = "Campo Obrigatório")
    private RefrigerationType categoryRefrigeration;
}

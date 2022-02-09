package com.mercadolibre.w4g9projetofinal.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransporterRequestDTO {
    @NotNull(message = "Campo Obrigatório")
    @NotEmpty(message = "Campo Obrigatório")
    @Size(min=2 ,max=60,message="Nome deve ter no máximo 60 caracteres e no minimo 2 caracteres.")
    @Pattern(regexp = "^[-'a-zA-ZÀ-ÖØ-öø-ÿ ]+$", message = "Apenas caracteres do alfabeto, incluindo acentos")
    private String name;
    @NotNull(message = "Campo Obrigatório")
    @NotEmpty(message = "Campo Obrigatório")
    @Size(min=2 ,max=20,message="Nome do carro deve ter no máximo 20 caracteres e no minimo 2 caracteres.")
    @Pattern(regexp = "^[A-Za-z0-9-_ ]{2,20}$",
            message = "Nome do carro pode conter letras e números")
    private String carModel;
    @NotNull(message = "Campo Obrigatório")
    @NotEmpty(message = "Campo Obrigatório")
    @Pattern(regexp = "[A-Z]{3}[0-9][0-9A-Z][0-9]{2}",
            message = "Placa do carro deve ser padrão antigo ou mercosul, por exemplo AAA0000 ou AAA0A00")
    private String carPlate;
    @NotNull(message = "Campo Obrigatório")
    @PositiveOrZero(message = "Espaço máximo para itens frescos deve ser um valor positivo ou Zero")
    private Integer freshMaxQuantity;
    @NotNull(message = "Campo Obrigatório")
    @PositiveOrZero(message = "Espaço máximo para itens congelados deve ser um valor positivo ou Zero")
    private Integer frozenMaxQuantity;
    @NotNull(message = "Campo Obrigatório")
    @PositiveOrZero(message = "Espaço máximo para itens refrigerados deve ser um valor positivo ou Zero")
    private Integer coldMaxQuantity;
}

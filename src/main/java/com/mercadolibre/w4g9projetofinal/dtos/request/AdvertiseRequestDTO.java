package com.mercadolibre.w4g9projetofinal.dtos.request;

import com.mercadolibre.w4g9projetofinal.entity.enums.AdvertiseStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
/***
 * DTO para serialização
 * Classe de request
 * contem:
 * descricao(String),
 * produto(Product),
 * vendedor(Seller),
 * preco(BigDecimal),
 * status de anuncio(AdvertiseStatus)
 * frete gratuito(boolean)
 * @autor Leonardo
 ***/
public class AdvertiseRequestDTO {
    @NotEmpty(message = "Campo Obrigatório")
    @NotEmpty(message = "Campo Obrigatório")
    @Size(min=4,max=20,message="Descrição deve ter no máximo 20 caracteres e no minimo 4 caracteres. ")
    private String description;
    @NotNull(message = "Campo Obrigatório")
    @Positive(message = "Id deve ser um valor positivo")
    private Long productId;
    @NotNull(message = "Campo Obrigatório")
    @Positive(message = "Id deve ser um valor positivo")
    private Long sellerId;
    @NotNull(message = "Campo Obrigatório")
    @Digits(integer = 10, fraction = 2, message = "Valor inválido. Aceito entre 0,01 e 9.999.999.999,99 , com 2 dígitos decimais")
    @DecimalMin(value = "0.01" , message = "Valor tem que ser maior que 0,01")
    private BigDecimal price;
    @NotNull(message = "Campo Obrigatório")
    private AdvertiseStatus status;
    @NotNull(message = "Campo Obrigatório")
    private Boolean freeShipping;
}

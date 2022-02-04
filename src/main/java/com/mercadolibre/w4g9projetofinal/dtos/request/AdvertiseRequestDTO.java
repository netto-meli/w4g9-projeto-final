package com.mercadolibre.w4g9projetofinal.dtos.request;

import com.mercadolibre.w4g9projetofinal.entity.enums.AdvertiseStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @Size(min=4,max=20,message="Descrição deve ter no máximo 20 caracteres e no minimo 4 caracteres. ")
    private String description;
    private Long productId;
    private Long sellerId;
    @NotNull
    @Positive
    private BigDecimal price;
    @NotEmpty
    @NotNull
    private AdvertiseStatus status;
    private boolean freeShipping;
}

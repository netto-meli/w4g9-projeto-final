package com.mercadolibre.w4g9projetofinal.dtos.request;

import com.mercadolibre.w4g9projetofinal.entity.Product;
import com.mercadolibre.w4g9projetofinal.entity.Seller;
import com.mercadolibre.w4g9projetofinal.entity.enums.AdvertiseStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
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
    @NotEmpty
    //todo fazer mais validacoes
    private String description;
    //todo este produto aqui tem q ser DTO
    private ProductRequestDTO product;
    //todo este seler temque ser DTO
    private SellerRequestDTO seller;
    private BigDecimal price;
    private AdvertiseStatus status;
    private boolean freeShipping;
}

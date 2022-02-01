package com.mercadolibre.w4g9projetofinal.dtos.request;

import com.mercadolibre.w4g9projetofinal.entity.Product;
import com.mercadolibre.w4g9projetofinal.entity.Seller;
import com.mercadolibre.w4g9projetofinal.entity.enums.AdvertiseStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
/***
 * DTO para serialização
 * Classe que request
 * contem:
 * descricao(String), produto(Product), vendedor(Seller), preco(BigDecimal), status de anuncio(AdvertiseStatus) e frete gratuito(boolean)
 * @autor Leonardo
 ***/
public class AdvertiseRequestDTO {
    private String description;
    private Product product;
    private Seller seller;
    private BigDecimal price;
    private AdvertiseStatus status;
    private boolean freeShipping;
}

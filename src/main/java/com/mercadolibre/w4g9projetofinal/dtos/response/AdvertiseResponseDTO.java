package com.mercadolibre.w4g9projetofinal.dtos.response;

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
 * DTO para Deserialização do Advertise
 * contem id(Long),
 * description(String),
 * product(Product),
 * Seller(seller),
 * price(BigDecimal),
 * status(AdvertiseStatus),
 * freeShipping (boolean)
 * @autor Leonardo
 */
public class AdvertiseResponseDTO {
    private Long id;
    private String description;
    private ProductResponseDTO product;
    private SellerResponseDTO seller;
    private BigDecimal price;
    private AdvertiseStatus status;
    private boolean freeShipping;
}

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
public class AdvertiseResponseDTO {
    private Long id;
    private String description;
    private Product product;
    private Seller seller;
    private BigDecimal price;
    private AdvertiseStatus status;
    private boolean freeShipping;
}

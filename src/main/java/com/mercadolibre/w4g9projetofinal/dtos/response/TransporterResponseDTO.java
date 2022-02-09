package com.mercadolibre.w4g9projetofinal.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransporterResponseDTO {
    private Long id;
    private String name;
    private String carModel;
    private String carPlate;
    private List<SellOrderResponseDTO> deliveryOrderList;
    private boolean inRoute;
    private BigDecimal paymentForDelivery;
    private BigDecimal salary;
}

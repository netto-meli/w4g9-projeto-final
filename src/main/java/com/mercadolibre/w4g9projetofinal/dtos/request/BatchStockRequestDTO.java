package com.mercadolibre.w4g9projetofinal.dtos.request;

import lombok.Data;

@Data
public class BatchStockRequestDTO {
    public String batchNumber;
    public String productId;
    public String currentTemperature;
    public String minimumTemperature;
    public String initialQuantity;
    public String currentQuantity;
    public String manufacturingDate;
    public String manufacturingTime;
    public String dueDate;
}
package com.mercadolibre.w4g9projetofinal.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

/***
 * Classe Dto para Deserialização do ProductByBatchResponseDTO
 * contem:
 * list de Bath
 * @author Leonardo
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductByBatch {
    private Long id;
    private int currentQuantity;
}


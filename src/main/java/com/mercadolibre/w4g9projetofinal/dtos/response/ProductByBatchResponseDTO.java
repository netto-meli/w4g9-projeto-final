package com.mercadolibre.w4g9projetofinal.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class ProductByBatchResponseDTO {
    private List<BatchResponseDTO> batchList;
}


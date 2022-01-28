package com.mercadolibre.w4g9projetofinal.entity.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InboundOrderRequestDTO {
        private int orderNumber;
        private LocalDate orderDate;
        private SectionRequestDTO section;
        private List<BatchRequestDTO> batchStock;
}

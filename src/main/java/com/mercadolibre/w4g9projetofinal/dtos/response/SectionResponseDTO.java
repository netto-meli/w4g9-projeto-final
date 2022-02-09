package com.mercadolibre.w4g9projetofinal.dtos.response;

import com.mercadolibre.w4g9projetofinal.entity.Section;
import com.mercadolibre.w4g9projetofinal.entity.Warehouse;
import com.mercadolibre.w4g9projetofinal.entity.enums.RefrigerationType;
import com.mercadolibre.w4g9projetofinal.repository.WarehouseRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;
/**
 * @author fbontempo
 * @version 0.2
 * @see {@link Section}
 * @see {@link com.mercadolibre.w4g9projetofinal.dtos.request.SectionRequestDTO}
 * */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SectionResponseDTO {
    private Long id;
    private String name;
    private RefrigerationType refrigerationType;
    private Long warehouseCode;
    private int stockLimit;
    private int currentStock;
    private float minTeperature;
    private float maxTeperature;
}

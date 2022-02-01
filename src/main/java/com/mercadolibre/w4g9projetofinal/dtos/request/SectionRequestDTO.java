package com.mercadolibre.w4g9projetofinal.dtos.request;

import com.mercadolibre.w4g9projetofinal.entity.Section;
import com.mercadolibre.w4g9projetofinal.entity.Warehouse;
import com.mercadolibre.w4g9projetofinal.entity.enums.RefrigerationType;
import com.mercadolibre.w4g9projetofinal.repository.SectionRepository;
import com.mercadolibre.w4g9projetofinal.repository.WarehouseRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
/**
 * @author fbontempo
 * @version 0.2
 * @see {@link Section}
 * @see {@link com.mercadolibre.w4g9projetofinal.dtos.response.SectionResponseDTO}
 * */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SectionRequestDTO {
    private Long id;
    private String name;
    private RefrigerationType type;
    private Warehouse warehouse;
    private int stockLimit;
    private int currentStock;
    private float minTeperature;
    private float maxTeperature;

    public Section atualizar(Long id, SectionRepository sectionRepository) {
        Section section = sectionRepository.getById(id);
        section.setName(this.name);
        section.setRefrigerationType(this.type);
        section.setWarehouse(this.warehouse);
        section.setStockLimit(this.stockLimit);
        section.setCurrentStock(this.currentStock);
        section.setMinTeperature(this.minTeperature);
        section.setMaxTeperature(this.maxTeperature);
        return section;
    }
}

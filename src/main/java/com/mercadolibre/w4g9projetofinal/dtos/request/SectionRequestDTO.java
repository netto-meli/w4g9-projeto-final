package com.mercadolibre.w4g9projetofinal.dtos.request;

import com.mercadolibre.w4g9projetofinal.entity.Section;
import com.mercadolibre.w4g9projetofinal.entity.Warehouse;
import com.mercadolibre.w4g9projetofinal.repository.SectionRepository;
import com.mercadolibre.w4g9projetofinal.repository.WarehouseRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SectionRequestDTO {
    private Long id;
    private String name;
    private String type;
    private Warehouse warehouseCode;
    private int stockLimit;
    private int currentStock;
    private float minTeperature;
    private float maxTeperature;

    public SectionRequestDTO(Section section) {
        this.id = section.getId();
        this.name = section.getName();
        this.type = section.getType();
        this.warehouseCode = section.getWarehouse();
        this.stockLimit = section.getStockLimit();
        this.currentStock = section.getCurrentStock();
        this.minTeperature = section.getMinTeperature();
        this.maxTeperature = section.getMaxTeperature();
    }

    public static List<SectionRequestDTO> convert(List<Section> sections){
        return sections
                .stream()
                .map(section -> new SectionRequestDTO(section))
                .collect(Collectors.toList());
    }
    public Section convertToSection(WarehouseRepository warehouseRepository){
        Optional<Warehouse> warehouse = warehouseRepository.findById(warehouseCode.getId());
        if (warehouse.isPresent()) {
            return new Section(id, name, type, warehouse.get(), stockLimit, currentStock, minTeperature, maxTeperature);
        }
        return null;
    }
    public Section atualizar(Long id, SectionRepository sectionRepository) {
        Section section = (Section) sectionRepository.getById(id);
        section.setName(this.name);
        section.setType(this.type);
        section.setWarehouse(this.warehouseCode);
        section.setStockLimit(this.stockLimit);
        section.setCurrentStock(this.currentStock);
        section.setMinTeperature(this.minTeperature);
        section.setMaxTeperature(this.maxTeperature);
        return section;
    }
}

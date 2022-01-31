package com.mercadolibre.w4g9projetofinal.dtos.response;

import com.mercadolibre.w4g9projetofinal.entity.Section;
import com.mercadolibre.w4g9projetofinal.entity.Warehouse;
import com.mercadolibre.w4g9projetofinal.repository.WarehouseRepository;
import lombok.Data;

import java.util.Optional;
/**
 * @author fbontempo
 * @version 0.1
 * @see {@link Section}
 * @see {@link com.mercadolibre.w4g9projetofinal.dtos.request.SectionRequestDTO}
 * */
@Data
public class SectionResponseDTO {
    private Long id;
    private String name;
    private String refrigerationType;
    private Warehouse warehouseCode;
    private int stockLimit;
    private int currentStock;
    private float minTeperature;
    private float maxTeperature;

    public SectionResponseDTO(Section section) {
        this.id = section.getId();
        this.name = section.getName();
        this.refrigerationType = section.getRefrigerationType();
        this.warehouseCode = section.getWarehouse();
        this.stockLimit = section.getStockLimit();
        this.currentStock = section.getCurrentStock();
        this.minTeperature = section.getMinTeperature();
        this.maxTeperature = section.getMaxTeperature();
    }

    public Section convert(WarehouseRepository warehouseRepository){
        Optional<Warehouse> warehouse = warehouseRepository.findById(warehouseCode.getId());
        if (warehouse.isPresent()) {
            return new Section(id, name, refrigerationType, warehouse.get(), stockLimit, currentStock,
                    minTeperature,
                    maxTeperature);
        }
        return null;
    }

}

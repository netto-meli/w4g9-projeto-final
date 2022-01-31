package com.mercadolibre.w4g9projetofinal.controller.service;

import com.mercadolibre.w4g9projetofinal.entity.Batch;
import com.mercadolibre.w4g9projetofinal.entity.OrderItem;
import com.mercadolibre.w4g9projetofinal.entity.Section;
import com.mercadolibre.w4g9projetofinal.exceptions.ObjectNotFoundException;
import com.mercadolibre.w4g9projetofinal.exceptions.SectionManagementException;
import com.mercadolibre.w4g9projetofinal.repository.SectionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class SectionService {

    private SectionRepository sectionRepository;

    public Section findById(Long id) {
        return sectionRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Section not found! Please check the id."));
    }

    public Section validateSectionBatches(Section section, List<Batch> batchStock) {

        Section dbSection = this.findById(section.getId());
        if (!section.getWarehouse().getId()
                .equals(dbSection.getWarehouse().getId())) {
            throw new SectionManagementException("Section does not belong to the Warehouse informed.");
        }
        this.validateBatchSection(batchStock, dbSection);
        return dbSection;
    }

    private void validateBatchSection(List<Batch> batchStock, Section dbSection) {
        StringBuilder msg = new StringBuilder();
        boolean throwExeption = false;
        for (Batch b : batchStock) {
            if (b.getMinTemperature() != dbSection.getMinTeperature()) {
                if (!throwExeption) {
                    throwExeption = true;
                    msg = new StringBuilder("Batch number(s): ");
                }
                msg.append(b.getId()).append(", ");
            }
            else{
                int qtd = this.validateAvailableSpaceInStock(
                        b.getInitialQuantity(), dbSection.getCurrentStock(), dbSection.getName(), b.getId());
                dbSection.setCurrentStock(qtd);
            }
        }
        if (throwExeption) {
            msg.append("does not belong to the Section Informed.");
            throw new SectionManagementException(msg.toString());
        }
    }

    private int validateAvailableSpaceInStock(int initialQuantity, int currentStock, String name, Long id) {
        if ( initialQuantity > currentStock )
            throw new SectionManagementException("Setor "
                    + name
                    + " não comporta todos produtos do lote "
                    + id );
        currentStock -= initialQuantity;
        return currentStock;
    }


    public Section save(Section section){
        return sectionRepository.save(section);
    }
}



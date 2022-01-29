package com.mercadolibre.w4g9projetofinal.service;

import com.mercadolibre.w4g9projetofinal.entity.Batch;
import com.mercadolibre.w4g9projetofinal.entity.Section;
import com.mercadolibre.w4g9projetofinal.exceptions.ObjectNotFoundException;
import com.mercadolibre.w4g9projetofinal.exceptions.SectionManagementException;
import com.mercadolibre.w4g9projetofinal.repository.SectionRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SectionService {

    private SectionRepository sectionRepository;

    public Section findById(Long id) {
        return sectionRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Section not found! Please check the id."));
    }

    public void validateSectionBatches(Section section, List<Batch> batchStock) {
        Section dbSection = this.findById(section.getId());
        if (!section.getWarehouse().getId()
                .equals(dbSection.getWarehouse().getId())) {
            throw new SectionManagementException("Section does not belong to the Warehouse informed.");
        }
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
        }
        if (throwExeption) {
            msg.append("does not belong to the Section Informed.");
            throw new SectionManagementException(msg.toString());
        }
    }


}



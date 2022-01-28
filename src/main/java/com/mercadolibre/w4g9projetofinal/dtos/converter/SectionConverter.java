package com.mercadolibre.w4g9projetofinal.dtos.converter;

import com.mercadolibre.w4g9projetofinal.dtos.request.SectionRequestDTO;
import com.mercadolibre.w4g9projetofinal.entity.Section;
import com.mercadolibre.w4g9projetofinal.entity.Warehouse;

public class SectionConverter {
    // todo se der ruim, dar getsection by id
    public static Section convertDtoToEntity (SectionRequestDTO sectionRequestDTO) {
        Section section = new Section(
                Long.valueOf( sectionRequestDTO.getSectionCode() ),
                new Warehouse( Long.valueOf( sectionRequestDTO.getWarehouseCode() ), null, null),
                null, null, 0, 0, 0L, 0L, null);
        return section;
    }
}
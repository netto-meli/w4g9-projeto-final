package com.mercadolibre.w4g9projetofinal.dtos.converter;

import com.mercadolibre.w4g9projetofinal.dtos.request.SectionRequestDTO;
import com.mercadolibre.w4g9projetofinal.dtos.response.SectionResponseDTO;
import com.mercadolibre.w4g9projetofinal.entity.Section;
import com.mercadolibre.w4g9projetofinal.entity.Warehouse;

public class SectionConverter {
    public static Section convertDtoToEntity (SectionRequestDTO sectionRequestDTO) {
        return new Section(
                Long.valueOf( sectionRequestDTO.getSectionCode() ),
                new Warehouse( Long.valueOf( sectionRequestDTO.getWarehouseCode() ), null, null),
                null, null, 0, 0, 0L, 0L, null);
    }

    public static SectionResponseDTO convertEntityToDto(Section s) {
        return new SectionResponseDTO(s.getId().toString(), s.getWarehouse().getId().toString());
    }
}
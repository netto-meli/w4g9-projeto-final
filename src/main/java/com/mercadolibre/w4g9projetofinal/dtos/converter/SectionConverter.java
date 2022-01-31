package com.mercadolibre.w4g9projetofinal.dtos.converter;

import com.mercadolibre.w4g9projetofinal.dtos.request.SectionRequestDTO;
import com.mercadolibre.w4g9projetofinal.dtos.request.SectionRequestDTOForInboundOrder;
import com.mercadolibre.w4g9projetofinal.dtos.response.InboundOrderResponseDTO;
import com.mercadolibre.w4g9projetofinal.dtos.response.SectionResponseDTO;
import com.mercadolibre.w4g9projetofinal.dtos.response.SectionResponseDTOForInboundOrder;
import com.mercadolibre.w4g9projetofinal.entity.InboundOrder;
import com.mercadolibre.w4g9projetofinal.entity.Section;
import com.mercadolibre.w4g9projetofinal.entity.Warehouse;

import java.util.ArrayList;
import java.util.List;

public class SectionConverter {
    public static Section convertDtoFIOToEntity (SectionRequestDTOForInboundOrder sectionRequestDTO) {
        return new Section(
                Long.valueOf( sectionRequestDTO.getSectionCode() ),
                new Warehouse( Long.valueOf( sectionRequestDTO.getWarehouseCode() ), null, null),
                null, null, 0, 0, 0L, 0L, null);
    }

    public static SectionResponseDTOForInboundOrder convertEntityToDtoFIO(Section s) {
        return new SectionResponseDTOForInboundOrder(s.getId().toString(), s.getWarehouse().getId().toString());
    }

    public static SectionResponseDTO convertEntityToDto (Section section) {
        return new SectionResponseDTO(
                section.getId(),
                section.getName(),
                section.getRefrigerationType(),
                section.getWarehouse(),
                section.getStockLimit(),
                section.getCurrentStock(),
                section.getMinTeperature(),
                section.getMaxTeperature());
    }

    public static Section convertDtoToEntity (SectionRequestDTO section) {
        return new Section(
                section.getId(),
                section.getWarehouse(),
                section.getName(),
                null,
                section.getCurrentStock(),
                section.getStockLimit(),
                section.getMinTeperature(),
                section.getMaxTeperature(),
                null);
    }

    public static List<SectionResponseDTO> convertEntityListToDtoList(List<Section> sections) {
        List<SectionResponseDTO> sectionResponseDTOList = new ArrayList<>();
        for (Section s : sections) {
            sectionResponseDTOList.add( convertEntityToDto(s) );
        }
        return sectionResponseDTOList;
    }
}
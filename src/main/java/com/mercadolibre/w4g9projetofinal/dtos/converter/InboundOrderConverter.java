package com.mercadolibre.w4g9projetofinal.dtos.converter;

import com.mercadolibre.w4g9projetofinal.dtos.request.InboundOrderRequestDTO;
import com.mercadolibre.w4g9projetofinal.dtos.response.BatchResponseDTO;
import com.mercadolibre.w4g9projetofinal.dtos.response.InboundOrderResponseDTO;
import com.mercadolibre.w4g9projetofinal.dtos.response.SectionResponseDTO;
import com.mercadolibre.w4g9projetofinal.dtos.response.SectionResponseDTOForInboundOrder;
import com.mercadolibre.w4g9projetofinal.entity.*;

import java.util.ArrayList;
import java.util.List;

public class InboundOrderConverter {

    public static InboundOrder convertDtoToEntity (InboundOrderRequestDTO iorDTO){
        List<Batch> batchList = BatchConverter.convertDtoListToEntityList(iorDTO.getBatchStock());
        Section section = SectionConverter.convertDtoFIOToEntity(iorDTO.getSection());
        return new InboundOrder(
                (long) iorDTO.getOrderNumber(),
                iorDTO.getOrderDate(),
                null,
                null,
                batchList,
                section);
    }

    public static InboundOrderResponseDTO convertEntityToDto(InboundOrder io) {
        List<BatchResponseDTO> batchList = BatchConverter.convertEntityListToDtoList( io.getBatchList() );
        SectionResponseDTOForInboundOrder section = SectionConverter.convertEntityToDtoFIO(io.getSection());
        return new InboundOrderResponseDTO(
                (int) io.getId().longValue(),
                io.getOrderDate(),
                section,
                batchList);
    }

    public static List<InboundOrderResponseDTO> convertEntityListToDtoList(List<InboundOrder> inboundOrderList) {
        List<InboundOrderResponseDTO> inboundOrderDTOList = new ArrayList<>();
        for (InboundOrder io : inboundOrderList) {
            inboundOrderDTOList.add( convertEntityToDto(io) );
        }
        return inboundOrderDTOList;
    }
}

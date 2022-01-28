package com.mercadolibre.w4g9projetofinal.entity.dtos.converter;

import com.mercadolibre.w4g9projetofinal.entity.dtos.request.InboundOrderRequestDTO;
import com.mercadolibre.w4g9projetofinal.entity.*;

import java.util.List;

public class InboundOrderConverter {

    public static InboundOrder convertDtoToEntity (InboundOrderRequestDTO iorDTO, Representative representative){
        List<Batch> batchList = BatchConverter.convertDtoListToEntityList(iorDTO.getBatchStock());
        InboundOrder io = new InboundOrder(
                (long) iorDTO.getOrderNumber(),
                iorDTO.getOrderDate(),
                null,
                representative,
                batchList);
        return io;
    }

}

package com.mercadolibre.w4g9projetofinal.dtos.converter;

import com.mercadolibre.w4g9projetofinal.dtos.request.InboundOrderRequestDTO;
import com.mercadolibre.w4g9projetofinal.entity.*;

import java.util.List;

public class InboundOrderConverter {

    public static InboundOrder convertDtoToEntity (InboundOrderRequestDTO iorDTO){
        List<Batch> batchList = BatchConverter.convertDtoListToEntityList(iorDTO.getBatchStock());
        return new InboundOrder(
                (long) iorDTO.getOrderNumber(),
                iorDTO.getOrderDate(),
                null,
                null,
                batchList);
    }

}

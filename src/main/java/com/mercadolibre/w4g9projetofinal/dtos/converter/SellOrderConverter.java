package com.mercadolibre.w4g9projetofinal.dtos.converter;

import com.mercadolibre.w4g9projetofinal.dtos.response.OrderItemResponseDTO;
import com.mercadolibre.w4g9projetofinal.dtos.response.SellOrderResponseDTO;
import com.mercadolibre.w4g9projetofinal.entity.SellOrder;

import java.util.List;

public class SellOrderConverter {

    public static SellOrderResponseDTO convertEntityToDto (SellOrder obj){
        List<OrderItemResponseDTO> orderItemResponseDTOList =
                OrderItemConverter.convertEntityListToDtoList( obj.getOrderItemList() );

        return new SellOrderResponseDTO(
                obj.getId(),
                obj.getBuyer().getId(),
                orderItemResponseDTOList,
                obj.getShippingRate(),
                obj.getTotalValue());
    }
}

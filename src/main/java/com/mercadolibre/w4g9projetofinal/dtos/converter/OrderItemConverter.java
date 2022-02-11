package com.mercadolibre.w4g9projetofinal.dtos.converter;

import com.mercadolibre.w4g9projetofinal.dtos.response.OrderItemResponseDTO;
import com.mercadolibre.w4g9projetofinal.entity.OrderItem;

import java.util.ArrayList;
import java.util.List;

public class OrderItemConverter {

    public static List<OrderItemResponseDTO> convertEntityListToDtoList(List<OrderItem> orderItemList) {
        List<OrderItemResponseDTO> orderItemResponseDTOList = new ArrayList<>();
        for (OrderItem oi : orderItemList) {
            orderItemResponseDTOList.add(convertEntityToDto(oi));
        }
        return orderItemResponseDTOList;
    }

    public static OrderItemResponseDTO convertEntityToDto (OrderItem io){
        return new OrderItemResponseDTO( io.getQuantity(), io.getAdvertise().getId() );
    }
}

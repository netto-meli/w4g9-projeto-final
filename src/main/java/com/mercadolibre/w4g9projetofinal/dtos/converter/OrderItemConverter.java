package com.mercadolibre.w4g9projetofinal.dtos.converter;

import com.mercadolibre.w4g9projetofinal.dtos.request.BuyerRequestDTO;
import com.mercadolibre.w4g9projetofinal.dtos.response.BuyerResponseDTO;
import com.mercadolibre.w4g9projetofinal.dtos.response.InboundOrderResponseDTO;
import com.mercadolibre.w4g9projetofinal.dtos.response.OrderItemResponseDTO;
import com.mercadolibre.w4g9projetofinal.dtos.response.WarehouseResponseDTO;
import com.mercadolibre.w4g9projetofinal.entity.Buyer;
import com.mercadolibre.w4g9projetofinal.entity.InboundOrder;
import com.mercadolibre.w4g9projetofinal.entity.OrderItem;
import com.mercadolibre.w4g9projetofinal.entity.Warehouse;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

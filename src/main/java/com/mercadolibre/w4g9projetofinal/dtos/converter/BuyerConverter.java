package com.mercadolibre.w4g9projetofinal.dtos.converter;

import com.mercadolibre.w4g9projetofinal.dtos.request.BuyerRequestDTO;
import com.mercadolibre.w4g9projetofinal.dtos.response.BuyerResponseDTO;
import com.mercadolibre.w4g9projetofinal.entity.Buyer;

import java.util.List;
import java.util.stream.Collectors;

public class BuyerConverter {

    public static Buyer convertDtoToEntity(BuyerRequestDTO buyer) {
        return new Buyer(null, buyer.getName(), buyer.getEmail(), buyer.getUsername(), buyer.getPassword(), buyer.getAddress());
    }

    public static BuyerResponseDTO convertEntityToDto(Buyer newBuyer) {
        return new BuyerResponseDTO(newBuyer.getId(), newBuyer.getName(), newBuyer.getEmail(), newBuyer.getUsername(), newBuyer.getAddress());
    }

    public static List<BuyerResponseDTO> convertEntityListToDtoList(List<Buyer> listBuyer) {
        return listBuyer.stream()
                .map(BuyerConverter::convertEntityToDto)
                .collect(Collectors.toList());
    }
}

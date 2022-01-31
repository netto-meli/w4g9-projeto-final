package com.mercadolibre.w4g9projetofinal.dtos.converter;

import com.mercadolibre.w4g9projetofinal.dtos.request.BuyerRequestDTO;
import com.mercadolibre.w4g9projetofinal.dtos.response.BuyerResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

public class BuyerConverter {

    public static Buyer convertDtoToEntityBuyer(BuyerRequestDTO buyer) {
        return new Buyer(null, buyer.getName(), buyer.getEmail(), buyer.getAddress());
    }

    public static BuyerResponseDTO convertEntityToDtoBuyer(Buyer newBuyer) {
        return new BuyerResponseDTO(newBuyer.getId(), newBuyer.getName(), newBuyer.getEmail(), newBuyer.getAddress());
    }

    public static List<BuyerResponseDTO> fromDTOBuyer(List<Buyer> listBuyer) {
        return listBuyer.stream()
                .map(BuyerConverter::convertEntityToDtoBuyer)
                .collect(Collectors.toList());
    }
}

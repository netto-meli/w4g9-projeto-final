package com.mercadolibre.w4g9projetofinal.entity.dtos.converter;

import com.mercadolibre.w4g9projetofinal.entity.dtos.request.BuyerRequestDTO;
import com.mercadolibre.w4g9projetofinal.entity.dtos.response.BuyerResponseDTO;
import com.mercadolibre.w4g9projetofinal.entity.Buyer;

import java.util.List;
import java.util.stream.Collectors;

public class BuyerConverter {

    public static Buyer convertDtoToEntityBuyer(BuyerRequestDTO buyer) {
        return new Buyer(null, null, null, buyer.getAddress());
    }

    public static BuyerResponseDTO convertEntityToDtoBuyer(Buyer newBuyer) {
        return new BuyerResponseDTO(newBuyer.getId(), newBuyer.getAddress());
    }

    public static List<BuyerResponseDTO> fromDTOBuyer(List<Buyer> listBuyer) {
        return listBuyer.stream().map(buyer -> new BuyerResponseDTO(buyer.getId(), buyer.getAddress())).collect(Collectors.toList());
    }
}

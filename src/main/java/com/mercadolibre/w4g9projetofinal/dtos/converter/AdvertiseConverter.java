package com.mercadolibre.w4g9projetofinal.dtos.converter;

import com.mercadolibre.w4g9projetofinal.dtos.request.AdvertiseRequestDTO;
import com.mercadolibre.w4g9projetofinal.dtos.response.AdvertiseResponseDTO;
import com.mercadolibre.w4g9projetofinal.entity.Advertise;

import java.util.List;
import java.util.stream.Collectors;

public class AdvertiseConverter {

    public static Advertise convertDtoToEntityAdvertise(AdvertiseRequestDTO advertise) {
        return new Advertise(
                null,
                advertise.getDescription(),
                advertise.getProduct(),
                advertise.getSeller(),
                advertise.getPrice(),
                advertise.getStatus());
    }

    public static AdvertiseResponseDTO convertEntityToDtoAdvertise(Advertise newAdvertise) {
        return new AdvertiseResponseDTO(
                newAdvertise.getId(),
                newAdvertise.getStatus(),
                newAdvertise.getDescription(),
                newAdvertise.getProduct());
    }

    public static List<AdvertiseResponseDTO> fromDTOAdvertise(List<Advertise> listAdvertise) {
        return listAdvertise.stream()
                .map(AdvertiseConverter::convertEntityToDtoAdvertise)
                .collect(Collectors.toList());
    }
}

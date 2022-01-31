package com.mercadolibre.w4g9projetofinal.dtos.converter;

import com.mercadolibre.w4g9projetofinal.dtos.request.AdvertiseRequestDTO;
import com.mercadolibre.w4g9projetofinal.dtos.response.AdvertiseResponseDTO;
import com.mercadolibre.w4g9projetofinal.entity.Advertise;

import java.util.List;
import java.util.stream.Collectors;

public class AdvertiseConverter {

    public static Advertise convertDtoToEntity(AdvertiseRequestDTO advertise) {
        return new Advertise(
                null,
                advertise.getDescription(),
                advertise.getProduct(),
                advertise.getSeller(),
                advertise.getPrice(),
                advertise.getStatus(),
                advertise.isFreeShipping());
    }

    public static AdvertiseResponseDTO convertEntityToDto(Advertise newAdvertise) {
        return new AdvertiseResponseDTO(
                newAdvertise.getId(),
                newAdvertise.getDescription(),
                newAdvertise.getProduct(),
                newAdvertise.getSeller(),
                newAdvertise.getPrice(),
                newAdvertise.getStatus(),
                newAdvertise.isFreeShipping());
    }

    public static List<AdvertiseResponseDTO> convertEntityListToDtoList(List<Advertise> listAdvertise) {
        return listAdvertise.stream()
                .map(AdvertiseConverter::convertEntityToDto)
                .collect(Collectors.toList());
    }
}

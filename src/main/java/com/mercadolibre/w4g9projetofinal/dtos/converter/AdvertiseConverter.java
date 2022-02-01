package com.mercadolibre.w4g9projetofinal.dtos.converter;

import com.mercadolibre.w4g9projetofinal.dtos.request.AdvertiseRequestDTO;
import com.mercadolibre.w4g9projetofinal.dtos.response.AdvertiseResponseDTO;
import com.mercadolibre.w4g9projetofinal.entity.Advertise;

import java.util.List;
import java.util.stream.Collectors;

/***
 * Classe para realizar conversao de dados para tratativa e devolutiva de dados
 * @Autor Leonardo
 */
public class AdvertiseConverter {


    /***
     * Metodo que recebe um AdvertiseDto e converte em um Advertise
     * @param advertisedto
     * @return Advertise
     */
    public static Advertise convertDtoToEntity(AdvertiseRequestDTO advertisedto) {
        return new Advertise(
                null,
                advertisedto.getDescription(),
                advertisedto.getProduct(),
                advertisedto.getSeller(),
                advertisedto.getPrice(),
                advertisedto.getStatus(),
                advertisedto.isFreeShipping());
    }

    /***
     * Metodo que recebe um Advertise e converte em um AdvertiseDto
     * @param newAdvertise
     * @return AdvertiseResponseDTO
     */
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

    /***
     * Metodo que recebe uma lista de AdvertiseResponseDTO e converte em uma lista de Advertise
     * @param listAdvertise
     * @return listAdvertise
     */
    public static List<AdvertiseResponseDTO> convertEntityListToDtoList(List<Advertise> listAdvertise) {
        return listAdvertise.stream()
                .map(AdvertiseConverter::convertEntityToDto)
                .collect(Collectors.toList());
    }
}

package com.mercadolibre.w4g9projetofinal.dtos.converter;

import com.mercadolibre.w4g9projetofinal.dtos.request.AdvertiseRequestDTO;
import com.mercadolibre.w4g9projetofinal.dtos.response.AdvertiseResponseDTO;
import com.mercadolibre.w4g9projetofinal.entity.Advertise;
import com.mercadolibre.w4g9projetofinal.entity.Product;
import com.mercadolibre.w4g9projetofinal.entity.Seller;

import java.util.List;
import java.util.stream.Collectors;

/***
 * Classe para realizar conversao de dados para tratativa e devolutiva de dados
 * @author Leonardo
 */
public class AdvertiseConverter {


    /***
     * Metodo que recebe um AdvertiseDto e converte em um Advertise
     * @param advertisedto anuncio
     * @return Advertise
     */
    public static Advertise convertDtoToEntity(AdvertiseRequestDTO advertisedto) {
        return new Advertise(
                null,
                advertisedto.getDescription(),
                new Product(advertisedto.getProductId(),
                        null, null, 0F, 0F, null),
                new Seller(advertisedto.getSellerId(),
                        null, null, null, null, null),
                advertisedto.getPrice(),
                advertisedto.getStatus(),
                advertisedto.getFreeShipping());
    }

    /***
     * Metodo que recebe um Advertise e converte em um AdvertiseDto
     * @param newAdvertise anuncio
     * @return AdvertiseResponseDTO
     */
    public static AdvertiseResponseDTO convertEntityToDto(Advertise newAdvertise) {
        return new AdvertiseResponseDTO(
                newAdvertise.getId(),
                newAdvertise.getDescription(),
                ProductConverter.convertEntityToDto(newAdvertise.getProduct()),
                SellerConverter.convertEntityToDto(newAdvertise.getSeller()),
                newAdvertise.getPrice(),
                newAdvertise.getStatus(),
                newAdvertise.isFreeShipping());
    }

    /***
     * Metodo que recebe uma lista de AdvertiseResponseDTO e converte em uma lista de Advertise
     * @param listAdvertise id
     * @return listAdvertise
     */
    public static List<AdvertiseResponseDTO> convertEntityListToDtoList(List<Advertise> listAdvertise) {
        return listAdvertise.stream()
                .map(AdvertiseConverter::convertEntityToDto)
                .collect(Collectors.toList());
    }
}

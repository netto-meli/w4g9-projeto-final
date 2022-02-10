package com.mercadolibre.w4g9projetofinal.dtos.converter;

import com.mercadolibre.w4g9projetofinal.dtos.request.BuyerRequestDTO;
import com.mercadolibre.w4g9projetofinal.dtos.response.BuyerResponseDTO;
import com.mercadolibre.w4g9projetofinal.entity.Buyer;

import java.util.List;
import java.util.stream.Collectors;

/***
 * Classe para realizar conversao de dados para tratativa e devolutiva de dados
 * @author Leonardo
 */
public class BuyerConverter {

    /***
     * Metodo que recebe um BuyerRequestDTO e converte em um Buyer
     * @param buyer comprador
     * @return Buyer
     */
    public static Buyer convertDtoToEntity(BuyerRequestDTO buyer) {
        return new Buyer(null, buyer.getUsername(), buyer.getName(), buyer.getEmail(), buyer.getPass(), buyer.getAddress());
    }

    /***
     * Metodo que recebe um Buyer e converte em um BuyerResponseDTO
     * @param newBuyer comprador
     * @return BuyerResponseDTO
     */
    public static BuyerResponseDTO convertEntityToDto(Buyer newBuyer) {
        return new BuyerResponseDTO(newBuyer.getId(), newBuyer.getUsername(), newBuyer.getName(), newBuyer.getEmail(), newBuyer.getAddress());
    }

    /***
     * Metodo que recebe um istBuyerResponseDTO e converte em um listBuyer
     * @param listBuyer id
     * @return listBuyer
     */
    public static List<BuyerResponseDTO> convertEntityListToDtoList(List<Buyer> listBuyer) {
        return listBuyer.stream()
                .map(BuyerConverter::convertEntityToDto)
                .collect(Collectors.toList());
    }
}

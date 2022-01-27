package com.mercadolibre.w4g9projetofinal.dtos.converter;

import com.mercadolibre.w4g9projetofinal.dtos.request.SellerRequestDTO;
import com.mercadolibre.w4g9projetofinal.dtos.response.SellerResponseDTO;
import com.mercadolibre.w4g9projetofinal.entity.Seller;

public class SellerConverter {
    public static Seller convertDtoToEntity (SellerRequestDTO wD){
        //todo convert
        return new Seller();
    }
    public static SellerResponseDTO convertEntityToDto (Seller w){
        //todo convert
        return new SellerResponseDTO();
    }
}

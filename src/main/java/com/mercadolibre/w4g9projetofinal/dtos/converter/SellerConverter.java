package com.mercadolibre.w4g9projetofinal.dtos.converter;

import com.mercadolibre.w4g9projetofinal.dtos.request.SellerRequestDTO;
import com.mercadolibre.w4g9projetofinal.dtos.request.WarehouseRequestDTO;
import com.mercadolibre.w4g9projetofinal.dtos.response.SellerResponseDTO;
import com.mercadolibre.w4g9projetofinal.dtos.response.WarehouseResponseDTO;
import com.mercadolibre.w4g9projetofinal.entity.Seller;
import com.mercadolibre.w4g9projetofinal.entity.Warehouse;

public class SellerConverter {

    public static Seller convertDtoToEntity (SellerRequestDTO objDTO){
        return new Seller();
    }
    public static SellerResponseDTO convertEntityToDto (Seller obj){
        return new SellerResponseDTO();
    }
}

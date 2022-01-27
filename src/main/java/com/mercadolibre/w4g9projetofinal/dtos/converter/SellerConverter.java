package com.mercadolibre.w4g9projetofinal.dtos.converter;

import com.mercadolibre.w4g9projetofinal.dtos.request.SellerRequestDTO;
import com.mercadolibre.w4g9projetofinal.dtos.request.WarehouseRequestDTO;
import com.mercadolibre.w4g9projetofinal.dtos.response.SellerResponseDTO;
import com.mercadolibre.w4g9projetofinal.dtos.response.WarehouseResponseDTO;
import com.mercadolibre.w4g9projetofinal.entity.Seller;
import com.mercadolibre.w4g9projetofinal.entity.Warehouse;

import java.util.List;
import java.util.stream.Collectors;

public class SellerConverter {

    public static Seller convertDtoToEntity (SellerRequestDTO objDTO){
        return new Seller(null, objDTO.getName(), objDTO.getEmail());
    }

    public static SellerResponseDTO convertEntityToDto (Seller obj){
        return new SellerResponseDTO(obj.getId(), obj.getName(), obj.getEmail());
    }

    public static List<SellerResponseDTO> fromDTO(List<Seller> list) {
        List<SellerResponseDTO> list2 = list.stream().map(x -> new SellerResponseDTO(x.getId(), x.getName(), x.getEmail())).collect(Collectors.toList());
        return list2;
    }
}

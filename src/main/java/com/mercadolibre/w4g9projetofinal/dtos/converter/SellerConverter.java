package com.mercadolibre.w4g9projetofinal.dtos.converter;

import com.mercadolibre.w4g9projetofinal.dtos.request.SellerRequestDTO;
import com.mercadolibre.w4g9projetofinal.dtos.response.RepresentativeResponseDTO;
import com.mercadolibre.w4g9projetofinal.dtos.response.SellerResponseDTO;
import com.mercadolibre.w4g9projetofinal.entity.Representative;
import com.mercadolibre.w4g9projetofinal.entity.Seller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SellerConverter {

    public static Seller convertDtoToEntity (SellerRequestDTO objDTO){
        return new Seller(null, objDTO.getName(), objDTO.getEmail(), null, null, null, null);
    }

    public static SellerResponseDTO convertEntityToDto (Seller obj){
        return new SellerResponseDTO(obj.getId(), obj.getName(), obj.getEmail());
    }

    public static List<SellerResponseDTO> convertEntityListToDtoList(List<Seller> list) {
        List<SellerResponseDTO> list2 = new ArrayList<>();
        for (Seller s : list) {
            list2.add(convertEntityToDto(s));
        }
        return list2;
    }
}

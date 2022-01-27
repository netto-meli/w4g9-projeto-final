package com.mercadolibre.w4g9projetofinal.dtos.converter;

import com.mercadolibre.w4g9projetofinal.dtos.request.RepresentativeRequestDTO;
import com.mercadolibre.w4g9projetofinal.dtos.request.SellerRequestDTO;
import com.mercadolibre.w4g9projetofinal.dtos.response.RepresentativeResponseDTO;
import com.mercadolibre.w4g9projetofinal.dtos.response.SellerResponseDTO;
import com.mercadolibre.w4g9projetofinal.entity.Representative;
import com.mercadolibre.w4g9projetofinal.entity.Seller;

import java.util.List;
import java.util.stream.Collectors;

public class RepresentativeConverter {

    public static Representative convertDtoToEntity (RepresentativeRequestDTO objDTO){
        return new Representative(null, objDTO.getName(), objDTO.getJob(), objDTO.getEmail());
    }

    public static RepresentativeResponseDTO convertEntityToDto (Representative obj){
        return new RepresentativeResponseDTO(obj.getId(), obj.getName(), obj.getJob(), obj.getEmail());
    }

    public static List<RepresentativeResponseDTO> fromDTO(List<Representative> list) {
        List<RepresentativeResponseDTO> list2 = list.stream().map(x -> new RepresentativeResponseDTO(x.getId(), x.getName(), x.getJob(), x.getEmail())).collect(Collectors.toList());
        return list2;
    }
}

package com.mercadolibre.w4g9projetofinal.dtos.converter;

import com.mercadolibre.w4g9projetofinal.dtos.request.RepresentativeRequestDTO;
import com.mercadolibre.w4g9projetofinal.dtos.response.RepresentativeResponseDTO;
import com.mercadolibre.w4g9projetofinal.entity.Representative;

import java.util.ArrayList;
import java.util.List;

public class RepresentativeConverter {

    public static Representative convertDtoToEntity (RepresentativeRequestDTO objDTO){
        return new Representative(null, objDTO.getUsername(), objDTO.getName(), objDTO.getEmail(), objDTO.getPass(), objDTO.getJob());
    }

    public static RepresentativeResponseDTO convertEntityToDto (Representative obj){
        return new RepresentativeResponseDTO(obj.getId(), obj.getUsername(), obj.getName(), obj.getEmail(), obj.getJob());
    }

    public static List<RepresentativeResponseDTO> convertEntityListToDtoList(List<Representative> list) {
        List<RepresentativeResponseDTO> list2 = new ArrayList<>();
        for (Representative r : list) {
            list2.add(convertEntityToDto(r));
        }
        return list2;
    }
}

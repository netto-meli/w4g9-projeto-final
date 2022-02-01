package com.mercadolibre.w4g9projetofinal.dtos.converter;

import com.mercadolibre.w4g9projetofinal.dtos.request.WarehouseRequestDTO;
import com.mercadolibre.w4g9projetofinal.dtos.response.WarehouseResponseDTO;
import com.mercadolibre.w4g9projetofinal.entity.Warehouse;

import java.util.List;
import java.util.stream.Collectors;

public class WarehouseConverter {

    public static Warehouse convertDtoToEntity (WarehouseRequestDTO whDTO){
        return new Warehouse( null, whDTO.getName(), whDTO.getLocation());
    }
    public static WarehouseResponseDTO convertEntityToDto (Warehouse wh){

        return new WarehouseResponseDTO(wh.getId(), wh.getNome(), wh.getLocation());
    }

    public static List<WarehouseResponseDTO> fromDTO (List<Warehouse> list)
    {
        List<WarehouseResponseDTO> list1 = list.stream().map(x ->
                new WarehouseResponseDTO(x.getId(), x.getNome(), x.getLocation()))
                .collect(Collectors.toList());
        return list1;
    }

}

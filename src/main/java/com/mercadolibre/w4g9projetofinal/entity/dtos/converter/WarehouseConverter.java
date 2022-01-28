package com.mercadolibre.w4g9projetofinal.entity.dtos.converter;

import com.mercadolibre.w4g9projetofinal.entity.dtos.request.WarehouseRequestDTO;
import com.mercadolibre.w4g9projetofinal.entity.dtos.response.WarehouseResponseDTO;
import com.mercadolibre.w4g9projetofinal.entity.Warehouse;

import java.util.List;

public class WarehouseConverter {
    public static Warehouse convertDtoToEntity (WarehouseRequestDTO wD){
        //todo convert
        return new Warehouse();
    }
    public static WarehouseResponseDTO convertEntityToDto (Warehouse w){
        //todo convert
        return new WarehouseResponseDTO();
    }

    public static List<WarehouseResponseDTO> fromDTO(List<Warehouse> all) {
        return null;
    }
}

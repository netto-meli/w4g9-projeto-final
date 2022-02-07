package com.mercadolibre.w4g9projetofinal.dtos.converter;

import com.mercadolibre.w4g9projetofinal.dtos.request.WarehouseRequestDTO;
import com.mercadolibre.w4g9projetofinal.dtos.response.ProductByWarehouseResponseDTO;
import com.mercadolibre.w4g9projetofinal.dtos.response.WarehouseResponseDTO;
import com.mercadolibre.w4g9projetofinal.dtos.response.WarehouseResponseDTOByProduct;
import com.mercadolibre.w4g9projetofinal.entity.Warehouse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class WarehouseConverter {
    public static Warehouse convertDtoToEntity (WarehouseRequestDTO whDTO){
        return new Warehouse( null, whDTO.getName(), whDTO.getLocation());
    }

    public static WarehouseResponseDTO convertEntityToDto (Warehouse wh){
        return new WarehouseResponseDTO(wh.getId(), wh.getName(), wh.getLocation());
    }

    public static List<WarehouseResponseDTO> convertEntityListToDtoList(List<Warehouse> all) {
        return all.stream()
                .map(WarehouseConverter::convertEntityToDto)
                .collect(Collectors.toList());
    }

    public static ProductByWarehouseResponseDTO convertEntityToDtoByProduct(Long id, Map<Long,Integer> warehouses){
        List<WarehouseResponseDTOByProduct> warehouseResponseDTOByProductList =
                WarehouseConverter.convertEntityListToDtoListByProduct(warehouses);
        return new ProductByWarehouseResponseDTO(id, warehouseResponseDTOByProductList);
    }

    private static List<WarehouseResponseDTOByProduct> convertEntityListToDtoListByProduct(Map<Long,Integer> warehouses) {
        List<WarehouseResponseDTOByProduct> list2 = new ArrayList<>();
        for (Map.Entry<Long, Integer> entry : warehouses.entrySet()) {
            WarehouseResponseDTOByProduct wh = new WarehouseResponseDTOByProduct(entry.getKey(), entry.getValue());
            list2.add(wh);
        }
        return list2;
    }
}

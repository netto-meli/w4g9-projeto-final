package com.mercadolibre.w4g9projetofinal.dtos.converter;

import com.mercadolibre.w4g9projetofinal.dtos.request.BatchRequestDTO;
import com.mercadolibre.w4g9projetofinal.dtos.response.BatchByDueDateRequestDTO;
import com.mercadolibre.w4g9projetofinal.dtos.response.BatchResponseDTO;
import com.mercadolibre.w4g9projetofinal.entity.Advertise;
import com.mercadolibre.w4g9projetofinal.entity.Batch;
import com.mercadolibre.w4g9projetofinal.entity.enums.RefrigerationType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BatchConverter {

    public static List<BatchResponseDTO> convertEntityListToDtoList(List<Batch> batchList) {
        List<BatchResponseDTO> batchResponseDTOList = new ArrayList<>();
        for (Batch b : batchList) {
            batchResponseDTOList.add(convertEntityToDto(b));
        }
        return batchResponseDTOList;
    }

    public static List<Batch> convertDtoListToEntityList(List<BatchRequestDTO> batchRequestDTOList) {
        List<Batch> batchList = new ArrayList<>();
        for (BatchRequestDTO b : batchRequestDTOList) {
            batchList.add(convertDtoToEntity(b));
        }
        return batchList;
    }

    public static Batch convertDtoToEntity (BatchRequestDTO b){
        return new Batch(
                (long) b.getBatchNumber(),
                b.getInitialQuantity(),
                b.getCurrentQuantity(),
                b.getCurrentTemperature(),
                b.getMinimumTemperature(),
                b.getDueDate(),
                b.getManufacturingDate(),
                b.getManufacturingTime(),
                new Advertise( b.getAdvertiseId(),
                        null,
                        null,
                        null,
                        null,
                        null,
                        false),
                null
        );
    }
    public static BatchResponseDTO convertEntityToDto (Batch b){
        return new BatchResponseDTO(
                b.getId(),
                b.getAdvertise().getId(),
                b.getCurrentTemperature(),
                b.getMinTemperature(),
                b.getInitialQuantity(),
                b.getCurrentQuantity(),
                b.getManufacturingDate(),
                b.getManufacturingTime(),
                b.getDueDate()
        );
    }

    public static BatchByDueDateRequestDTO convertEntityToDtoByDueDate(Batch batch, RefrigerationType productType) {
        return new BatchByDueDateRequestDTO(
                batch.getId(),
                batch.getAdvertise().getId(),
                productType.getDescricao(),
                batch.getDueDate(),
                batch.getCurrentQuantity());
    }

    public static List<BatchByDueDateRequestDTO> convertEntityMapToDtoByDueDateList(Map<Batch, RefrigerationType> batchMap) {
        List<BatchByDueDateRequestDTO> list2 = new ArrayList<>();
        for (Map.Entry<Batch, RefrigerationType> entry : batchMap.entrySet()) {
            BatchByDueDateRequestDTO wh = BatchConverter.convertEntityToDtoByDueDate(entry.getKey(), entry.getValue());
            list2.add(wh);
        }
        return list2;
    }
}

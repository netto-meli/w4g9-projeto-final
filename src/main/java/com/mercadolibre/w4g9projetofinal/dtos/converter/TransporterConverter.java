package com.mercadolibre.w4g9projetofinal.dtos.converter;

import com.mercadolibre.w4g9projetofinal.dtos.request.TransporterRequestDTO;
import com.mercadolibre.w4g9projetofinal.dtos.response.SellOrderResponseDTO;
import com.mercadolibre.w4g9projetofinal.dtos.response.TransporterResponseDTO;
import com.mercadolibre.w4g9projetofinal.entity.Representative;
import com.mercadolibre.w4g9projetofinal.entity.Transporter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TransporterConverter {
    public static Transporter convertDtoToEntity(TransporterRequestDTO tDTO, Long idRepresentative) {
        return new Transporter(null,
                tDTO.getName(),
                tDTO.getCarModel(),
                tDTO.getCarPlate(),
                new Representative(idRepresentative, null, null, null, null, null, null),
                tDTO.getFreshMaxQuantity(),
                tDTO.getFrozenMaxQuantity(),
                tDTO.getColdMaxQuantity(),
                new ArrayList<>(),
                false,
                BigDecimal.ZERO,
                BigDecimal.ZERO);
    }

    public static TransporterResponseDTO convertEntityToDto(Transporter t) {
        List<SellOrderResponseDTO> sellOrderResponseDTOList =
                SellOrderConverter.convertEntityListToDtoList(t.getDeliveryOrderList());
        return new TransporterResponseDTO(t.getId(),
                t.getName(),
                t.getCarModel(),
                t.getCarPlate() ,
                sellOrderResponseDTOList,
                t.isInRoute(),
                t.getPaymentForDelivery(),
                t.getSalary());
    }

    public static List<TransporterResponseDTO> convertEntityListToDtoList(List<Transporter> transporterList) {
        List<TransporterResponseDTO> transporterResponseDTOList = new ArrayList<>();
        for (Transporter t : transporterList) {
            transporterResponseDTOList.add(convertEntityToDto(t));
        }
        return transporterResponseDTOList;
    }
}

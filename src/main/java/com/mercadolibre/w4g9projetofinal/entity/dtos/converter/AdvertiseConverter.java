package com.mercadolibre.w4g9projetofinal.entity.dtos.converter;

import com.mercadolibre.w4g9projetofinal.entity.dtos.request.AdvertiseRequestDTO;
import com.mercadolibre.w4g9projetofinal.entity.Advertise;

public class AdvertiseConverter {
    // se der rruim, dar getadvertise by id
    public static Advertise convertDtoToEntity (AdvertiseRequestDTO advertiseRequestDTO){
        Advertise advertise = new Advertise(advertiseRequestDTO.getId() , null, null, null, null, null);
        return advertise;
    }
}

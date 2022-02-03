package com.mercadolibre.w4g9projetofinal.dtos.converter;

import com.mercadolibre.w4g9projetofinal.dtos.response.UserResponseDTO;
import com.mercadolibre.w4g9projetofinal.entity.Seller;
import com.mercadolibre.w4g9projetofinal.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserConverter {

    public static List<UserResponseDTO> convertEntityListToDtoList(List<User> list) {
        List<UserResponseDTO> list2 = new ArrayList<>();
        for (User u : list) {
            list2.add(convertEntityToDto(u));
        }
        return list2;
    }

    private static UserResponseDTO convertEntityToDto(User s) {
        return new UserResponseDTO(s.getId(), s.getUsername(), s.getName(), s.getEmail());
    }
}

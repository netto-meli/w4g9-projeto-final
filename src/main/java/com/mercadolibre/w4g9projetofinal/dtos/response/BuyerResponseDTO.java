package com.mercadolibre.w4g9projetofinal.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
/***
 * DTO para Deserialização do Buyer
 * contem id (Long),
 * address (String),
 * name(String),
 * email(String)
 * @autor LEonardo
 */
public class BuyerResponseDTO {
    private Long id;
    private String username;
    private String name;
    private String email;
    private String address;
}

package com.mercadolibre.w4g9projetofinal.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/***
 * DTO para Deserialização do Buyer
 * contem id (Long),
 * address (String),
 * name(String),
 * email(String)
 * @author LEonardo
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BuyerResponseDTO {
    private Long id;
    private String username;
    private String name;
    private String email;
    private String address;
}

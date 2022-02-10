package com.mercadolibre.w4g9projetofinal.dtos.response;

import com.mercadolibre.w4g9projetofinal.entity.enums.RepresentativeJob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/***
 * Classe de Response para Representative
 *
 * @author Marcos Sá
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RepresentativeResponseDTO {
    private Long id;
    private String username;
    private String name;
    private String email;
    private RepresentativeJob job;
    private Long warehouseId;
}

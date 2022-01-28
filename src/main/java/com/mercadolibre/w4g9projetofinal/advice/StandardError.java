package com.mercadolibre.w4g9projetofinal.advice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/***
 * Classe StandardError que gera retorno padrão para respostas de erros
 *
 * @author Marcos Sá
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StandardError implements Serializable {
    private static final long serialVersionUID = 1945951435369178405L;

    private Long timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;

}

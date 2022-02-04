package com.mercadolibre.w4g9projetofinal.advice;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class FieldMessage implements Serializable {
    private static final long serialVersionUID = -6150290692713329079L;

    private String fieldName;
    private String message;
}

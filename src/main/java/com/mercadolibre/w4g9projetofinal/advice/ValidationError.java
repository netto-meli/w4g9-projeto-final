package com.mercadolibre.w4g9projetofinal.advice;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {
    private static final long serialVersionUID = 7039015373786598265L;

    @Getter
    private List<FieldMessage> errors = new ArrayList<>();

    public ValidationError(Long timestamp, Integer status, String error, String path, String message) {
        super(timestamp, status, error, path, message);
    }

    public void addError(String fieldName, String message) {
        errors.add(new FieldMessage(fieldName, message));
    }
}

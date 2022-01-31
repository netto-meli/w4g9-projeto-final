package com.mercadolibre.w4g9projetofinal.exceptions;

public class AuthorizationException extends RuntimeException{
    private static final long serialVersionUID = -8844205136016538258L;

    public AuthorizationException (String msg) {
        super(msg);
    }

    public AuthorizationException (String msg, Throwable cause) {
        super(msg, cause);
    }
}

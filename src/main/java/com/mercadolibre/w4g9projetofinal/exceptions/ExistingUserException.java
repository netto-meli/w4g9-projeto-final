package com.mercadolibre.w4g9projetofinal.exceptions;

/***
 * @author Marcos Sá
 */
public class ExistingUserException extends RuntimeException{

    /***
     * Construtor da classe
     *
     * @param msg Mensagem sobre a exceção recebida
     */
    public ExistingUserException(String msg) {
        super(msg);
    }
}

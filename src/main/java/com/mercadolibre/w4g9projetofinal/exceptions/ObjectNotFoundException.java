package com.mercadolibre.w4g9projetofinal.exceptions;

/***
 * @author Marcos Sá
 */
public class ObjectNotFoundException extends RuntimeException{

    /***
     * Construtor da classe
     *
     * @param msg Mensagem sobre a exceção recebida
     */
    public ObjectNotFoundException(String msg) {
        super(msg);
    }
}

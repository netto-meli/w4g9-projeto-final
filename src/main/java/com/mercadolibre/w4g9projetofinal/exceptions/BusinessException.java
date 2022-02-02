package com.mercadolibre.w4g9projetofinal.exceptions;

/*** Exceção de negócio
 * @author Fernando Netto
 */
public class BusinessException extends RuntimeException {

    /***
     * Construtor da classe
     *
     * @param msg Mensagem sobre a exceção recebida
     */
    public BusinessException(String msg) {
        super(msg);
    }
}

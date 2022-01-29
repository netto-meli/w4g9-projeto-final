package com.mercadolibre.w4g9projetofinal.exceptions;

/*** Exceção no gerenciamento do carrinho
 * @author Fernando Netto
 */
public class SectionManagementException extends RuntimeException {

    /***
     * Construtor da classe
     *
     * @param msg Mensagem sobre a exceção recebida
     */
    public SectionManagementException(String msg) {
        super(msg);
    }
}

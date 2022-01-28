package com.mercadolibre.w4g9projetofinal.entity;

import com.mercadolibre.w4g9projetofinal.exceptions.CartManagementException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/*** Entidade para persistência de ItemCarinho
 *
 * @author Fernando Netto
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {
    /***
     * Quantidade do Produto no Pedido long
     */
    private long quantidade;
    /***
     * Produto no pedido
     */
    private Product produto;

    /***
     * Metodo para calcular o valor total do produto no carrinho,
     * com base na quantidade de itens no pedido
     *
     * @return valor total calculado
     */
    public BigDecimal calculaValorTotalProduto(){
        return null;//produto.getValor().multiply(BigDecimal.valueOf(quantidade));
    }

    /***
     * Método para retirar itens de um produto, do carrinho atual do cliente
     *
     * @param qtdProduto quantidade de itens de um produto para retirar
     * @throws CartManagementException Erro ao tentar retirar mais itens do carrinho do que os existentes.
     */
    public void retiraQuantidadeProduto(long qtdProduto) throws CartManagementException {
        if ( this.quantidade < qtdProduto ) throw new CartManagementException(
                    "Impossível retirar mais itens de um produto do que os que já estão no carrinho");
        quantidade -= qtdProduto;
    }
}

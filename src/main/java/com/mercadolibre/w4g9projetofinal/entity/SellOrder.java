package com.mercadolibre.w4g9projetofinal.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Objects;

/*** Entidade para persistência de Pedido
 *
 * @author Fernando Netto
 * @author Marcos
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class SellOrder {
    /***
     * ID do Pedido no tipo Long
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /***
     * ID do Cliente no tipo Long
     */
    private Long idCliente;
    /***
     * Lista de ItemCarrinho (produto no pedido) no formato BigDecimal
     */
    //private List<ItemCarrinho> listaItensCarrinho;
    /***
     * Valor do frete do Pedido no formato BigDecimal
     */
    private BigDecimal valorFrete;
    /***
     * Valor total do Pedido no formato BigDecimal
     */
    private BigDecimal valorTotal;




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        SellOrder sellOrder = (SellOrder) o;
        return id != null && Objects.equals(id, sellOrder.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }





    /*** Método para buscar dentro do pedido, o produto selecionado
     *
     * @param idProduto ID do Produto para o método buscar os dados de ItemCarinho
     * @return ItemCarrinho contendo o produto buscado e quantidade no carrinho
     */
    public CartItem getItemCarrinho(Long idProduto) {
        return null;/*listaItensCarrinho.stream()
                .filter( ic -> ic.getProduto().getId().equals(idProduto))
                .findAny()
                .orElse(null);*/
    }

    /*** Método para atualizar o carrinho, conforme foram adicionados ou removidos itens de um produto
     *
     * @param cartItem Produto para atualizar no carrinho
     * @param endereco Endereço do cliente, para calcular o frete atual
     */
    public void atualizaCarrinho(CartItem cartItem, String endereco) {
        //listaItensCarrinho.removeIf(itemCarrinho::equals);
        /*
        if (itemCarrinho.getQuantidade() > 0 ) {
            listaItensCarrinho.add(itemCarrinho);
            if (!itemCarrinho.getProduto().isFreteGratis())
                this.calculaValorTotalPedido(endereco);
        }*/
    }

    /*** Método para calcular o valor total de itens de um produto no carrinho
     *
     * @param endereco Endereço do cliente, para calcular o frete atual
     */
    public void calculaValorTotalPedido(String endereco){
        BigDecimal valorPedido = BigDecimal.ZERO;
        boolean isFreteGratis = true;
        //for (ItemCarrinho item : listaItensCarrinho) {
          //  BigDecimal valorTotalUmProduto = item.calculaValorTotalProduto();
            //valorPedido = valorTotalUmProduto.add(valorPedido);
            // Se algum dos produtos no pedido não tiver frete gratis,
            // o pedido precisa calcular o valor do frete
          //  //if (!item.getProduto().isFreteGratis()) isFreteGratis = false;
        //}
        //calculaFrete(isFreteGratis, endereco);
        //this.valorTotal = valorPedido.add(this.valorFrete);
    }

    /*** Método para calcular o frete
     *
     * @param isFreteGratis Informação se o pedido somente contém produtos com frete grátis
     * @param endereco Endereço do cliente, para calcular o frete atual
     */
    private void calculaFrete(boolean isFreteGratis, String endereco) {
        this.valorFrete = BigDecimal.ZERO;
        if (!isFreteGratis) {
            this.valorFrete = mockFreteByEnderecoString(endereco);
        }
    }

    /*** Método MOCK para cálculo do frete baseado nos caracteres do endereço
     *
     * @param endereco Endereço do cliente, para calcular o frete atual
     * @return Valor do Frete calculado
     */
    private BigDecimal mockFreteByEnderecoString(String endereco) {
        long tempValorFrete = 0;
        if (endereco == null) endereco = "a";
        // remove todos os espaços em branco
        String mockFrete = endereco.toLowerCase().replaceAll("\\s", "");
        final String alphabet = "abcdefghijklmnopqrstuvwxyz";
        for(int i=0; i < mockFrete.length(); i++){
            tempValorFrete += (alphabet.indexOf(mockFrete.charAt(i))) + 1;
            // Acrescenta em +1; pois se não houver o caractere na lista,
            // o método indexOf retorna -1.
            // Ex: números, caracteres especiais...
        }
        return BigDecimal.valueOf(tempValorFrete);
    }
}

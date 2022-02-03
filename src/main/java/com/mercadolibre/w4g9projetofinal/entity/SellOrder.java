package com.mercadolibre.w4g9projetofinal.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
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
     * Cliente do tipo Buyer
     */
    @ManyToOne
    private Buyer buyer;
    /***
     * Indicador se a ordem de compra ja foi realizada ou se ainda é um carrinho de compras
     */
    private boolean cart;
    /***
     * Lista de ItemCarrinho (produto no pedido) no formato List de CartItem
     */
    @ToString.Exclude
    @OneToMany(
            mappedBy = "sellOrder",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<OrderItem> orderItemList;
    /***
     * Valor do frete do Pedido no formato BigDecimal
     */
    private BigDecimal shippingRate;
    /***
     * Valor total do Pedido no formato BigDecimal
     */
    private BigDecimal totalValue;

    /*** Método para buscar dentro do pedido, o produto selecionado
     *
     * @param advertise ID do Produto para o método buscar os dados de ItemCarinho
     * @return ItemCarrinho contendo o produto buscado e quantidade no carrinho
     */
    public OrderItem getOrderItem(Advertise advertise) {
        return orderItemList.stream()
                .filter( ic -> ic.getAdvertise().equals(advertise))
                .findAny()
                .orElse(null);
    }

    /*** Método para atualizar o carrinho, conforme foram adicionados ou removidos itens de um produto
     *
     * @param qtdProduct Quantidade de itens do produto
     * @param advertise Anuncio do produto
     * @param orderItem Produto para atualizar no carrinho
     */
    public int updateCart(int qtdProduct, Advertise advertise, OrderItem orderItem) {
        if (orderItem == null) {
            orderItem = new OrderItem(null, qtdProduct, advertise, this);
            this.orderItemList.add(orderItem);
        } else {
            orderItem.setQuantity(orderItem.getQuantity() + qtdProduct);
            int idx = this.orderItemList.indexOf(orderItem);
            this.orderItemList.set(idx, orderItem);
        }
        this.calcTotalValueOrder();
        return orderItem.getQuantity();
    }

    /*** Método para calcular o valor total de itens de um produto no carrinho
     *
     */
    public void calcTotalValueOrder(){
        BigDecimal orderPrice = BigDecimal.ZERO;
        boolean isFreeShipping = true;
        for (OrderItem item : orderItemList) {
            BigDecimal totalValueForProduct = item.calculaValorTotalProduto();
            orderPrice = totalValueForProduct.add(orderPrice);
            // Se algum dos produtos no pedido não tiver frete gratis,
            // o pedido precisa calcular o valor do frete
            if (!item.getAdvertise().isFreeShipping()) isFreeShipping = false;
        }
        calculateShipping(isFreeShipping);
        this.totalValue = orderPrice.add(this.shippingRate);
    }

    /*** Método para calcular o frete
     *
     * @param isFreeShipping Informação se o pedido somente contém produtos com frete grátis
     */
    private void calculateShipping(boolean isFreeShipping) {
        this.shippingRate = BigDecimal.ZERO;
        if (!isFreeShipping) {
            this.shippingRate = mockShippingRateByAddress(this.buyer.getAddress());
        }
    }

    /*** Método MOCK para cálculo do frete baseado nos caracteres do endereço
     *
     * @param address Endereço do cliente, para calcular o frete atual
     * @return Valor do Frete calculado
     */
    private BigDecimal mockShippingRateByAddress(String address) {
        long tempShippingRate = 0;
        if (address == null) address = "a";
        // remove todos os espaços em branco
        String mockFrete = address.toLowerCase().replaceAll("\\s", "");
        final String alphabet = "abcdefghijklmnopqrstuvwxyz";
        for(int i=0; i < mockFrete.length(); i++){
            tempShippingRate += (alphabet.indexOf(mockFrete.charAt(i))) + 1;
            // Acrescenta em +1; pois se não houver o caractere na lista,
            // o método indexOf retorna -1.
            // Ex: números, caracteres especiais...
        }
        return BigDecimal.valueOf(tempShippingRate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o ) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        SellOrder sellOrder = (SellOrder) o;
        return id != null && Objects.equals(id, sellOrder.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

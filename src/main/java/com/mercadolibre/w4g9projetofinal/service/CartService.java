package com.mercadolibre.w4g9projetofinal.service;


import com.mercadolibre.w4g9projetofinal.entity.Advertise;
import com.mercadolibre.w4g9projetofinal.entity.Buyer;
import com.mercadolibre.w4g9projetofinal.entity.OrderItem;
import com.mercadolibre.w4g9projetofinal.entity.SellOrder;
import com.mercadolibre.w4g9projetofinal.entity.enums.SellOrderStatus;
import com.mercadolibre.w4g9projetofinal.exceptions.CartManagementException;
import com.mercadolibre.w4g9projetofinal.repository.OrderItemRepository;
import com.mercadolibre.w4g9projetofinal.repository.SellOrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/*** Service dos métodos do carrinho:<br>
 * <b>Adiciona Produtos no Carrinho</b><br>
 * <b>Retira Produtos do Carrinho</b><br>
 * <b>Limpa Carrinho Aberto</b><br>
 * <b>Exibir Carrinho Aberto</b><br>
 * <b>Fechar Carrinho / Gerar Pedido</b>
 *
 * @author Fernando Netto
 */
@Service
@AllArgsConstructor
public class CartService {

    /*** Instancia de repositório: <b>SellOrderRepository</b>.
     */
    private OrderItemRepository orderItemRepository;
    /*** Instancia de repositório: <b>SellOrderRepository</b>.
     */
    private SellOrderRepository sellOrderRepository;
    /*** Instancia de repositório: <b>BuyerRepository</b>.
     */
    private BuyerService buyerService;
    /*** Instancia de repositório: <b>BatchRepository</b>.
     */
    private BatchService batchServce;
    /*** Instancia de repositório: <b>BatchRepository</b>.
     */
    private AdvertiseService advertiseService;

    /*** Método para exibir o carrinho de compras atual de um cliente
     *
     * @param idBuyer ID do Cliente que deseja ver o estado atual do carrinho de compras
     * @return Retorna um <b>SellOrder</b>, com <i>ID nula</i>, pois é um pedido ainda não finalizado (carrinho aberto).
     */
    public SellOrder getCart(Long idBuyer) {
        SellOrder sellOrder = sellOrderRepository
                .findSellOrderByBuyer_IdAndOrderStatus(idBuyer, SellOrderStatus.CART).orElse(null);
        if (sellOrder == null) {
            Buyer buyer = buyerService.findById(idBuyer);
            sellOrder = new SellOrder(null, buyer, SellOrderStatus.CART,
                    new ArrayList<>(), BigDecimal.ZERO, BigDecimal.ZERO);
            sellOrder = sellOrderRepository.save(sellOrder);
        }
        return sellOrder;
    }

    /*** Método para adicionar novo produto, ou mais itens de um mesmo produto, ao carrinho de compras do cliente.<br>
     * Criando persistência do carrinho no repositório.
     *
     * @param idBuyer ID do Cliente que está fazendo o pedido
     * @param idAdvertise ID do Produto que o cliente deseja acrescentar no carrinho de compras
     * @param qtdProduct Quantos itens do produto selecionado, o Cliente deseja adicionar no carrinho
     * @return Retorna um carrinho de compras (internamente é um <b>SellOrder</b> com <i>ID nula</i>,
     * pois se trata um pedido ainda não finalizado).
     */
    public SellOrder addAdvertiseItemsToCart(Long idBuyer, Long idAdvertise, int qtdProduct) {
        if (qtdProduct <= 0)
            throw new CartManagementException("Impossível incluir ZERO produtos no carrinho");
        SellOrder cart = this.getCart(idBuyer);
        Advertise advertise = advertiseService.findById(idAdvertise);
        OrderItem orderItem = this.getOrderItem(cart, advertise);
        int finalQuantity = this.updateCart(cart, qtdProduct, advertise, orderItem);
        batchServce.verifyStock(idAdvertise, finalQuantity);
        return sellOrderRepository.save(cart);
    }

    /*** Método para retirar uma quantidade de um produto do carrinho de compras do cliente.<br>
     * Criando persistência do carrinho no repositório.
     *
     * @param idBuyer ID do Cliente que está fazendo o pedido
     * @param idAdvertise ID do Produto que o cliente deseja acrescentar no carrinho de compras
     * @param qtdProducts Quantos itens do produto selecionado, o Cliente deseja adicionar no carrinho
     * @return Retorna um <b>SellOrder</b>, com <i>ID nula</i>, pois é um pedido ainda não finalizado (carrinho aberto).
     */
    public SellOrder removeAdvertiseItemsFromCart(Long idBuyer, Long idAdvertise, int qtdProducts) {
        SellOrder cart = this.getCart(idBuyer);
        Advertise advertise = advertiseService.findById(idAdvertise);
        OrderItem item = this.getOrderItem(cart, advertise);
        if (item == null) throw new CartManagementException("Impossivel retirar produto que não foi adicionado");
        if (item.getQuantity() < qtdProducts) throw new CartManagementException(
                "Impossível retirar mais itens de um produto do que os que já estão no carrinho");
        int qtd = item.getQuantity();
        item.setQuantity(qtd - qtdProducts);
        if (item.getQuantity() == 0) {
            cart.getOrderItemList().remove(item);
            orderItemRepository.delete(item);
        }
        this.calcTotalValueOrder(cart);
        return sellOrderRepository.save(cart);
    }

    /*** Método para limpar o carrinho de compras de um cliente
     *
     * @param idBuyer ID do Cliente que deseja zerar o carrinho de compras
     */
    @Transactional
    public void emptyCart(Long idBuyer) {
        sellOrderRepository.deleteByBuyer_IdAndOrderStatus(idBuyer, SellOrderStatus.CART);
    }

    /*** Método para fechar o carrinho de compras de um cliente e criar um pedido
     *
     * @param buyerId ID do Cliente que deseja ver o estado atual do carrinho de compras
     * @return Retorna um <b>SellOrder</b>, com <i>ID nula</i>, pois é um pedido ainda não finalizado (carrinho aberto).
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    public SellOrder createSellOrder(Long buyerId) {
        SellOrder cart = this.getCart(buyerId);
        List<OrderItem> orderItemList = cart.getOrderItemList();
        if (orderItemList.size() == 0)
            throw new CartManagementException("Impossível gerar pedido utilizando um carrinho vazio.");
        this.calcTotalValueOrder(cart);
        cart.setOrderStatus(SellOrderStatus.CREATED);
        batchServce.updateStock(orderItemList);
        return sellOrderRepository.save(cart);
    }



    /*** Método para buscar dentro do pedido, o produto selecionado
     *
     * @param sellOrder carrinho
     * @param advertise ID do Produto para o método buscar os dados de ItemCarinho
     * @return ItemCarrinho contendo o produto buscado e quantidade no carrinho
     */
    private OrderItem getOrderItem(SellOrder sellOrder, Advertise advertise) {
        return sellOrder.getOrderItemList().stream()
                .filter( ic -> ic.getAdvertise().equals(advertise))
                .findAny()
                .orElse(null);
    }

    /*** Método para atualizar o carrinho, conforme foram adicionados ou removidos itens de um produto
     *
     * @param sellOrder carrinho
     * @param qtdProduct Quantidade de itens do produto
     * @param advertise Anuncio do produto
     * @param orderItem Produto para atualizar no carrinho
     * @return quantidade da ordem
     */
    private int updateCart(SellOrder sellOrder, int qtdProduct, Advertise advertise, OrderItem orderItem) {
        if (orderItem == null) {
            orderItem = new OrderItem(null, qtdProduct, advertise, sellOrder);
            sellOrder.getOrderItemList().add(orderItem);
        } else {
            orderItem.setQuantity(orderItem.getQuantity() + qtdProduct);
            int idx = sellOrder.getOrderItemList().indexOf(orderItem);
            sellOrder.getOrderItemList().set(idx, orderItem);
        }
        this.calcTotalValueOrder(sellOrder);
        return orderItem.getQuantity();
    }

    /*** Método para calcular o valor total de itens de um produto no carrinho
     *
     * @param sellOrder carrinho
     */
    private void calcTotalValueOrder(SellOrder sellOrder){
        BigDecimal orderPrice = BigDecimal.ZERO;
        boolean isFreeShipping = true;
        for (OrderItem item : sellOrder.getOrderItemList()) {
            BigDecimal totalValueForProduct = this.calculaValorTotalProduto(item);
            orderPrice = totalValueForProduct.add(orderPrice);
            // Se algum dos produtos no pedido não tiver frete gratis,
            // o pedido precisa calcular o valor do frete
            if (!item.getAdvertise().isFreeShipping()) isFreeShipping = false;
        }
        calculateShipping(sellOrder, isFreeShipping);
        sellOrder.setTotalValue(orderPrice.add(sellOrder.getShippingRate()));
    }

    /***
     * Metodo para calcular o valor total do produto no carrinho,
     * com base na quantidade de itens no pedido
     *
     * @param orderItem item do carrinho
     * @return valor total calculado
     */
    private BigDecimal calculaValorTotalProduto(OrderItem orderItem){
        return orderItem.getAdvertise().getPrice().multiply(BigDecimal.valueOf(orderItem.getQuantity()));
    }

    /*** Método para calcular o frete
     *
     * @param sellOrder carrinho
     * @param isFreeShipping Informação se o pedido somente contém produtos com frete grátis
     */
    private void calculateShipping(SellOrder sellOrder, boolean isFreeShipping) {
        sellOrder.setShippingRate(BigDecimal.ZERO);
        if (!isFreeShipping) {
            sellOrder.setShippingRate( mockShippingRateByAddress(sellOrder.getBuyer().getAddress()) );
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
}

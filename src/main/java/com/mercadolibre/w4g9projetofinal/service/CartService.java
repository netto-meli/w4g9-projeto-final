package com.mercadolibre.w4g9projetofinal.service;


import com.mercadolibre.w4g9projetofinal.entity.*;
import com.mercadolibre.w4g9projetofinal.entity.enums.SellOrderStatus;
import com.mercadolibre.w4g9projetofinal.exceptions.CartManagementException;
import com.mercadolibre.w4g9projetofinal.repository.*;
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
        SellOrder sellOrder = sellOrderRepository.findSellOrderByBuyer_IdAndCartTrue(idBuyer).orElse(null);
        if (sellOrder == null) {
            Buyer buyer = buyerService.findById(idBuyer);
            sellOrder = new SellOrder(null, buyer, SellOrderStatus.CART, new ArrayList<>(), BigDecimal.ZERO, BigDecimal.ZERO);
            sellOrderRepository.save(sellOrder);
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
        OrderItem orderItem = cart.getOrderItem(advertise);
        int finalQuantity = cart.updateCart(qtdProduct, advertise, orderItem);
        batchServce.verifyStock(idAdvertise,finalQuantity);
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
        OrderItem item = cart.getOrderItem(advertise);
        if (item.getQuantity() < qtdProducts) throw new CartManagementException(
                "Impossível retirar mais itens de um produto do que os que já estão no carrinho");
        int qtd = item.getQuantity();
        item.setQuantity(qtd - qtdProducts);
        if (item.getQuantity() == 0){
            cart.getOrderItemList().remove(item);
        }
        return sellOrderRepository.save(cart);
    }

    /*** Método para limpar o carrinho de compras de um cliente
     *
     * @param idBuyer ID do Cliente que deseja zerar o carrinho de compras
     */
    @Transactional
    public void emptyCart(Long idBuyer) {
        sellOrderRepository.deleteByBuyer_IdAndCartTrue(idBuyer);
    }

    /*** Método para fechar o carrinho de compras de um cliente e criar um pedido
     *
     * @param buyerId ID do Cliente que deseja ver o estado atual do carrinho de compras
     * @return Retorna um <b>SellOrder</b>, com <i>ID nula</i>, pois é um pedido ainda não finalizado (carrinho aberto).
     */
    @Transactional(propagation= Propagation.REQUIRED, isolation= Isolation.SERIALIZABLE)
    public SellOrder createSellOrder(Long buyerId) {
        SellOrder cart = this.getCart(buyerId);
        List<OrderItem> orderItemList = cart.getOrderItemList();
        if (orderItemList.size() == 0)
            throw new CartManagementException("Impossível gerar pedido utilizando um carrinho vazio.");
        cart.calcTotalValueOrder();
        cart.setOrderStatus(SellOrderStatus.CREATED);
        batchServce.updateStock(orderItemList);
        return sellOrderRepository.save(cart);
    }
}

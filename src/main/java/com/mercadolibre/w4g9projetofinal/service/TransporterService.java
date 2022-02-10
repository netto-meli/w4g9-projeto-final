package com.mercadolibre.w4g9projetofinal.service;

import com.mercadolibre.w4g9projetofinal.entity.OrderItem;
import com.mercadolibre.w4g9projetofinal.entity.SellOrder;
import com.mercadolibre.w4g9projetofinal.entity.Transporter;
import com.mercadolibre.w4g9projetofinal.entity.enums.RefrigerationType;
import com.mercadolibre.w4g9projetofinal.entity.enums.SellOrderStatus;
import com.mercadolibre.w4g9projetofinal.exceptions.BusinessException;
import com.mercadolibre.w4g9projetofinal.exceptions.ObjectNotFoundException;
import com.mercadolibre.w4g9projetofinal.repository.SellOrderRepository;
import com.mercadolibre.w4g9projetofinal.repository.TransporterRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/***
 * Classe referente ao servico de Entregadores e Delivery
 * @author Fernando
 */
@Service
@AllArgsConstructor
public class TransporterService {
    /*** Instancia de repositório: <b>SellOrderRepository</b>.
     */
    private SellOrderRepository sellOrderRepository;
    /*** Instancia de repositório: <b>TransporterRepository</b>.
     */
    private TransporterRepository transporterRepository;

    public List<Transporter> findAll() {
        return transporterRepository.findAll();
    }

    public Transporter findById(Long idTransporter) {
        return transporterRepository.findById(idTransporter)
                .orElseThrow(() -> new ObjectNotFoundException("Transporter not found"));
    }

    /**
     * Metodo para buscar lista de Entregadores, conforme informação de que se estão em rota ou não.
     * @param inRoute boolean informando se estão fazendo Entregas ou não
     * @return Lista de Entregadores
     */
    public List<Transporter> findByStatus(boolean inRoute) {
        return transporterRepository.findByInRoute(inRoute);
    }

    public Transporter insert(Transporter transporter) {
        return transporterRepository.save(transporter);
    }

    /***
     * Atualiza entregador somente se o mesmo não estiver fazendo entregas
     * @param transporter Entregador
     * @return entrtegadot
     */
    public Transporter update(Transporter transporter) {
        Transporter old = this.findById(transporter.getId());
        if (old.isInRoute()) {
            throw new BusinessException("Cannot update Transporter while in route to Delivery");
        }
        transporter = this.updateTransporter(old, transporter);
        return transporterRepository.save(transporter);
    }

    /***
     * Exclui entregador somente se o mesmo não estiver fazendo entregas
     * @param idTransporter Entregador
     */
    public void delete(Long idTransporter) {
        Transporter transporter = this.findById(idTransporter);
        if (transporter.isInRoute()) {
            throw new BusinessException("Cannot delete Transporter while in route to Delivery");
        }
        transporterRepository.delete(transporter);
    }

    /***
     * Metodo que indica um Entregador para a entrega dos pedidos
     * @param listIdToDeliver lista de IDs de entrtegas (pedidos realizados)
     * @return Entregador
     */
    @Transactional
    public Transporter calldelivery(List<Long> listIdToDeliver) {
        List<SellOrder> listDelivery = this.findAllSellOrdersByID(listIdToDeliver);
        Transporter transporter = this.findAvailableTransporter(listDelivery);
        transporter.setDeliveryOrderList(listDelivery);
        transporter.setInRoute(true);
        return transporterRepository.save(transporter);
    }

    /***
     * Metodo que valida entrega dos pedidos
     * @param idTransporter id do entnrtegador
     * @param listIdsDelivered lista dos pedidos entregues
     * @return entregador disponivel
     */
    @Transactional
    public Transporter confirmDelivery(Long idTransporter, List<Long> listIdsDelivered) {
        Transporter transporter = this.findById(idTransporter);
        List<SellOrder> listDelivery = this.findAllSellOrdersByID(listIdsDelivered);
        for (SellOrder delivery : listDelivery) {
            if (!transporter.getDeliveryOrderList().remove(delivery)) {
                throw new ObjectNotFoundException("Delivery id: " + delivery.getId() + " informed, "
                        + "isn't part of the deliveries of the Transporter "
                        + idTransporter);
            }
            delivery.setOrderStatus(SellOrderStatus.DELIVERED);
            sellOrderRepository.save(delivery);
        }
        if(transporter.getDeliveryOrderList().size() > 0) {
            for (SellOrder so :transporter.getDeliveryOrderList()) {
                so.setOrderStatus(SellOrderStatus.NOT_DELIVERED);
            }
            sellOrderRepository.saveAll(transporter.getDeliveryOrderList());
            transporter.setDeliveryOrderList(new ArrayList<>());
        }
        transporter.setInRoute(false);
        BigDecimal salary = transporter.getPaymentForDelivery();
        transporter.setSalary(salary.add(transporter.getSalary()));
        transporter.setPaymentForDelivery(BigDecimal.ZERO);
        return transporterRepository.save(transporter);
    }

    /***
     * Metodo que verifica quais entregadores estão aptos a fazer a entrega
     * @param listDelivery lista de pedidos para entrega
     * @return Entregador apto
     */
    private Transporter findAvailableTransporter(List<SellOrder> listDelivery) {
        int qtdFrozen = 0;
        int qtdFresh = 0;
        int qtdCold = 0;
        BigDecimal shippingRate = BigDecimal.ZERO;
        for (SellOrder so : listDelivery) {
            so.setOrderStatus(SellOrderStatus.SHIPPED);
            shippingRate = shippingRate.add(so.getShippingRate());
            for (OrderItem oi : so.getOrderItemList()) {
                String ref = oi.getAdvertise().getProduct().getCategoryRefrigeration().getCod();
                if (ref.equals(RefrigerationType.COLD.toString())) qtdCold += oi.getQuantity();
                else if (ref.equals(RefrigerationType.FRESH.toString())) qtdFresh += oi.getQuantity();
                else if (ref.equals(RefrigerationType.FROZEN.toString())) qtdFrozen += oi.getQuantity();
            }
        }
        List<Transporter> transporters = transporterRepository
                .findByInRouteFalseAndColdMaxQuantityAfterAndFreshMaxQuantityAfterAndFrozenMaxQuantityAfter
                        (qtdCold, qtdFresh, qtdFrozen);
        if (transporters.size() == 0) {
            throw new ObjectNotFoundException("There is no Transporter available, than can carry: " +
                    qtdFresh + " Fresh items, " +
                    qtdCold + " Cold items and " +
                    qtdFrozen + " Frozen items. Please wait, or try to divide the deliveries.");
        }
        Transporter transporter = transporters.get(0); // get the first available
        if(BigDecimal.ZERO.equals(shippingRate))
            shippingRate = BigDecimal.TEN; // If free-shipping, consider shipping = 10,00
        transporter.setPaymentForDelivery(
                shippingRate.multiply(BigDecimal.valueOf(0.25)));//Payment if 25% of shipping rate
        return transporter;
    }

    /***
     * Traz a lista de pedidos conforme ids
     * @param listIdToDeliver id dos pedidos
     * @return lista dos pedidos
     */
    private List<SellOrder> findAllSellOrdersByID(List<Long> listIdToDeliver) {
        List<SellOrder> listDelivery = sellOrderRepository.findAllById(listIdToDeliver);
        if (listDelivery.size() != listIdToDeliver.size())
            throw new ObjectNotFoundException("Some Id's doesn't exist.");
        return listDelivery;
    }

    private Transporter updateTransporter(Transporter old, Transporter transporter) {
        old.setName(transporter.getName());
        old.setCarModel(transporter.getCarModel());
        old.setCarPlate(transporter.getCarPlate());
        old.setRepresentative(transporter.getRepresentative());
        old.setFreshMaxQuantity(transporter.getFreshMaxQuantity());
        old.setFrozenMaxQuantity(transporter.getFrozenMaxQuantity());
        old.setColdMaxQuantity(transporter.getColdMaxQuantity());
        return old;
    }
}

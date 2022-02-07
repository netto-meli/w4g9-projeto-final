package com.mercadolibre.w4g9projetofinal.repository;

import com.mercadolibre.w4g9projetofinal.entity.SellOrder;
import com.mercadolibre.w4g9projetofinal.entity.enums.SellOrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface SellOrderRepository extends JpaRepository<SellOrder, Long> {
    Optional<SellOrder> findSellOrderByBuyer_IdAndOrderStatus(Long buyer_id, SellOrderStatus orderStatus);
    @Modifying
    void deleteByBuyer_IdAndOrderStatus(Long buyer_id, SellOrderStatus orderStatus);
}

package com.mercadolibre.w4g9projetofinal.repository;

import com.mercadolibre.w4g9projetofinal.entity.SellOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface SellOrderRepository extends JpaRepository<SellOrder, Long> {
    Optional<SellOrder> findSellOrderByBuyer_IdAndCartTrue(Long buyer_id);
    @Modifying
    void deleteByBuyer_IdAndCartTrue(Long buyer_id);
}

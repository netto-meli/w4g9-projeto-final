package com.mercadolibre.w4g9projetofinal.repository;

import com.mercadolibre.w4g9projetofinal.entity.SellOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface SellOrderRepository extends JpaRepository<SellOrder, Long> {

    @Query("select s from SellOrder s where s.buyer.id = ?1 and s.cart = true")
    Optional<SellOrder> findSellOrderByBuyer_IdAndCartTrue(Long buyer_id);
    @Modifying
    @Query("delete from SellOrder s where s.buyer.id = ?1 and s.cart = true")
    void deleteByBuyer_IdAndCartTrue(Long buyer_id);
}

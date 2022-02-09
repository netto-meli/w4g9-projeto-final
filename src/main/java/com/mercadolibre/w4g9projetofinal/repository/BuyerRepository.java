package com.mercadolibre.w4g9projetofinal.repository;

import com.mercadolibre.w4g9projetofinal.entity.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface BuyerRepository extends JpaRepository <Buyer, Long> {
}

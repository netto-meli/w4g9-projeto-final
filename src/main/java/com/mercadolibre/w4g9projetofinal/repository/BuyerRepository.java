package com.mercadolibre.w4g9projetofinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BuyerRepository extends JpaRepository <Buyer, Long> {
}

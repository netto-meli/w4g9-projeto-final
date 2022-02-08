package com.mercadolibre.w4g9projetofinal.repository;

import com.mercadolibre.w4g9projetofinal.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Long> {

    @Query("select s.id from Seller s, Advertise a where s.id = a.seller.id AND a.id = ?1")
    Optional<Long> findSellerByAdvertiseId(Long id);
}

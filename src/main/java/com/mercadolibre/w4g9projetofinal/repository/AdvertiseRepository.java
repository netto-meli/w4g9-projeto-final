package com.mercadolibre.w4g9projetofinal.repository;

import com.mercadolibre.w4g9projetofinal.entity.Advertise;
import org.springframework.data.jpa.repository.JpaRepository;
import com.mercadolibre.w4g9projetofinal.entity.Seller;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface AdvertiseRepository extends JpaRepository<Advertise, Long> {

    @Query(value = "SELECT s.* " +
            " FROM seller s " +
            " JOIN advertise a " +
            " ON a.seller_id = s.ID " +
            " AND a.id = :id ",
            nativeQuery = true)
    Seller findSellerByAdvertiseId(Long id);
}

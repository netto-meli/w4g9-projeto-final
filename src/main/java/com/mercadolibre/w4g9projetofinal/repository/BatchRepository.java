package com.mercadolibre.w4g9projetofinal.repository;

import com.mercadolibre.w4g9projetofinal.entity.Batch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface BatchRepository extends JpaRepository<Batch, Long> {
    Optional<Batch> findByAdvertise_Id(Long advertise_id);
    @Query("select b from Batch b, Advertise a where b.advertise.id = a.id and a.product.id = ?1")
    List<Batch> findByProduct_Id(Long product_id);
}

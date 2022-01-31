package com.mercadolibre.w4g9projetofinal.repository;

import com.mercadolibre.w4g9projetofinal.entity.Batch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface BatchRepository extends JpaRepository<Batch, Long> {
    Optional<Batch> findByAdvertise_Id(Long advertise_id);
}

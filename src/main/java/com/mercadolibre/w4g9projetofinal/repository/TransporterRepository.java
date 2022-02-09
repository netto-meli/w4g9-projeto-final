package com.mercadolibre.w4g9projetofinal.repository;

import com.mercadolibre.w4g9projetofinal.entity.Transporter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransporterRepository extends JpaRepository<Transporter, Long> {
    List<Transporter> findByInRoute(boolean inRoute );
    List<Transporter> findByInRouteFalseAndColdMaxQuantityAfterAndFreshMaxQuantityAfterAndFrozenMaxQuantityAfter
            (int qtdCold, int qtdFresh, int qtdFrozen);
}
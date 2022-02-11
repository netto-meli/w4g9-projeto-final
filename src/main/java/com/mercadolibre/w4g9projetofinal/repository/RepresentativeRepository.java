package com.mercadolibre.w4g9projetofinal.repository;

import com.mercadolibre.w4g9projetofinal.entity.Representative;
import com.mercadolibre.w4g9projetofinal.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepresentativeRepository extends JpaRepository<Representative, Long> {
}

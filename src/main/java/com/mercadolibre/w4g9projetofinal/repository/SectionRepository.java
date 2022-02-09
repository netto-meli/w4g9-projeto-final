package com.mercadolibre.w4g9projetofinal.repository;

import com.mercadolibre.w4g9projetofinal.entity.InboundOrder;
import com.mercadolibre.w4g9projetofinal.entity.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SectionRepository extends JpaRepository<Section, Long> {
    @Query("select s from Section s, InboundOrder i where s.id = i.section.id and i.id =?1")
    Optional<Section> findByInboundOrder_Id(Long id);
}
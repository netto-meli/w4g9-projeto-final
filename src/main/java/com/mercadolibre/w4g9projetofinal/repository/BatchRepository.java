package com.mercadolibre.w4g9projetofinal.repository;

import com.mercadolibre.w4g9projetofinal.entity.Batch;
import com.mercadolibre.w4g9projetofinal.entity.enums.RefrigerationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface BatchRepository extends JpaRepository<Batch, Long> {
    @Query("SELECT b FROM Batch b, InboundOrder i "
            + "WHERE b.inboundOrder.id = i.id "
            + "AND i.section.id = ?1 AND b.dueDate <= ?2")
    List<Batch> findByDueDateBeforeAndSectionId(Long idSection, LocalDate dueDateMax);
    @Query("SELECT b FROM Batch b, InboundOrder i, Section s "
            + "WHERE b.inboundOrder.id = i.id AND i.section.id = s.id "
            + "AND s.refrigerationType = ?1 AND b.dueDate <= ?2")
    List<Batch> findByDueDateBeforeAndRefrigerationType(RefrigerationType refrigerationType, LocalDate dueDateMax);
    @Query("select b from Batch b, Advertise a "
            + "where b.advertise.id = a.id and a.product.id = ?1")
    List<Batch> findByProduct_Id(Long product_id);
    Optional<Batch> findByAdvertise_Id(Long advertise_id);
}
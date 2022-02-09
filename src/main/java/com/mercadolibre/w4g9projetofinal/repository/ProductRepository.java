package com.mercadolibre.w4g9projetofinal.repository;


import com.mercadolibre.w4g9projetofinal.entity.Product;
import com.mercadolibre.w4g9projetofinal.entity.enums.RefrigerationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategoryRefrigeration(RefrigerationType categoryRefrigeration);
}

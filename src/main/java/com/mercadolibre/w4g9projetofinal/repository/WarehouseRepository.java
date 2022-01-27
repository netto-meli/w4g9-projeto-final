package com.mercadolibre.fernando_netto_projeto_final.repository;

import com.mercadolibre.fernando_netto_projeto_final.entity.Warehouse;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseRepository extends CrudRepository<Warehouse, Long> {

}
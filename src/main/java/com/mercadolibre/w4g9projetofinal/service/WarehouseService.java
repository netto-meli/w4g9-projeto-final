package com.mercadolibre.w4g9projetofinal.service;


import com.mercadolibre.w4g9projetofinal.entity.Warehouse;
import com.mercadolibre.w4g9projetofinal.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class WarehouseService {

    @Autowired
   private WarehouseRepository warehouseRepository;

    public List<Warehouse> findAll()
    {
        return warehouseRepository.findAll();
    }

    public Warehouse findById(Long id)
    {
        Optional<Warehouse> wh = warehouseRepository.findById(id);
        return wh.orElse(null);
    }

    public WarehouseService( WarehouseRepository warehouseRepository ) {
        this.warehouseRepository = warehouseRepository;
    }

    public Warehouse insert (Warehouse wh) {
        return warehouseRepository.save(wh);
    }

    public void delete (Long id)
    {
        Warehouse wh = findById(id);
        warehouseRepository.delete(wh);
    }

    public Warehouse update(Warehouse wh) {
        return null;
    }
}



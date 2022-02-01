package com.mercadolibre.w4g9projetofinal.service;


import com.mercadolibre.w4g9projetofinal.entity.Warehouse;
import com.mercadolibre.w4g9projetofinal.repository.WarehouseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class WarehouseService {

    private WarehouseRepository warehouseRepository;

    public List<Warehouse> findAll()
    {
        return (List<Warehouse>) warehouseRepository.findAll();
    }

    public Warehouse findById(Long id)
    {
        Optional<Warehouse> wh = warehouseRepository.findById(id);
        return wh.orElse(null);
    }

    public Warehouse insert (Warehouse wh) {
        return warehouseRepository.save(wh);
    }

    public void delete (Long id)
    {
        Warehouse wh = findById(id);
        warehouseRepository.delete(wh);
    }
}

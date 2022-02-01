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
        List<Warehouse> list = warehouseRepository.findAll();
        return list;
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

    public Warehouse update(Warehouse nWarehouse)
    {
        Warehouse wh = findById(nWarehouse.getId());
        updateWarehouse(nWarehouse, wh);
        return warehouseRepository.save(wh);
    }

    private static void updateWarehouse(Warehouse wh, Warehouse nWarehouse)
    {
        nWarehouse.setId(wh.getId());
        nWarehouse.setNome(wh.getNome());
        nWarehouse.setLocation(wh.getLocation());
    }

    public void delete (Long id)
    {
        Warehouse wh = findById(id);
        warehouseRepository.delete(wh);
    }



}



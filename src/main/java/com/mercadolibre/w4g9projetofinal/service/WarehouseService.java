package com.mercadolibre.w4g9projetofinal.service;


import com.mercadolibre.w4g9projetofinal.entity.Batch;
import com.mercadolibre.w4g9projetofinal.entity.InboundOrder;
import com.mercadolibre.w4g9projetofinal.entity.Section;
import com.mercadolibre.w4g9projetofinal.entity.Warehouse;
import com.mercadolibre.w4g9projetofinal.exceptions.ObjectNotFoundException;
import com.mercadolibre.w4g9projetofinal.repository.BatchRepository;
import com.mercadolibre.w4g9projetofinal.repository.WarehouseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
@AllArgsConstructor
public class WarehouseService {

    private WarehouseRepository warehouseRepository;
    private BatchService batchService;
    private SectionService sectionService;

    public List<Warehouse> findAll()
    {
        return warehouseRepository.findAll();
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

    public Map<Long, Integer> findWarehousesByProductId(Long idProduct) {
        List<Batch> batchList = batchService.findByProductId(idProduct);
        Map<Long,Integer> warehouses = new HashMap<>();
        if (batchList.size() == 0 ) throw new ObjectNotFoundException("There is no Batch for this Product.");
        for (Batch b: batchList) {
            Section s = sectionService.findByInboundOrderId(b.getInboundOrder().getId());
            Long id = s.getWarehouse().getId();
            Integer qtd = warehouses.get(id);
            if (qtd == null) qtd = 0;
            warehouses.put(id, ( qtd + b.getCurrentQuantity() ));
        }
        return warehouses;
    }
}



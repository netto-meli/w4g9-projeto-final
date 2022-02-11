package com.mercadolibre.w4g9projetofinal.service;


import com.mercadolibre.w4g9projetofinal.entity.Batch;
import com.mercadolibre.w4g9projetofinal.entity.Section;
import com.mercadolibre.w4g9projetofinal.entity.Warehouse;
import com.mercadolibre.w4g9projetofinal.exceptions.ObjectNotFoundException;
import com.mercadolibre.w4g9projetofinal.repository.WarehouseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.*;

/***
 * Service de Armazem
 * @author Fernando
 * @author Rafael
 */
@Service
@AllArgsConstructor
public class WarehouseService {

    private WarehouseRepository warehouseRepository;
    private BatchService batchService;
    private SectionService sectionService;

    public List<Warehouse> findAll() {
        return warehouseRepository.findAll();
    }

    public Warehouse findById(Long id) {
        Optional<Warehouse> wh = warehouseRepository.findById(id);
        return wh.orElse(null);
    }

    public Warehouse insert(Warehouse wh) {
        return warehouseRepository.save(wh);
    }

    public Warehouse update(Warehouse nWarehouse) {
        Warehouse wh = findById(nWarehouse.getId());
        updateWarehouse(nWarehouse, wh);
        return warehouseRepository.save(wh);
    }

    public void delete(Long id) {
        Warehouse wh = findById(id);
        warehouseRepository.delete(wh);
    }

    private static void updateWarehouse(Warehouse wh, Warehouse nWarehouse) {
        nWarehouse.setName(wh.getName());
        nWarehouse.setLocation(wh.getLocation());
    }

    /***
     * Metodo para buscar todos os armazens, filtrados por um produto
     * @param idProduct id do produto
     * @return lista de armazens
     */
    public Map<Long, Integer> findWarehousesByProductId(Long idProduct) {
        List<Batch> batchList = batchService.findByProductId(idProduct);
        Map<Long, Integer> warehouses = new HashMap<>();
        if (batchList.size() == 0) throw new ObjectNotFoundException("There is no Batch for this Product.");
        for (Batch b : batchList) {
            if (b.getDueDate().isAfter(LocalDate.now().plusDays(21))) {
                Section s = sectionService.findByInboundOrderId(b.getInboundOrder().getId());
                Long id = s.getWarehouse().getId();
                Integer qtd = warehouses.get(id);
                if (qtd == null) qtd = 0;
                warehouses.put(id, (qtd + b.getCurrentQuantity()));
            }
        }
        return warehouses;
    }
}



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

/*** Classe de serviço do Armazém
 *@author Rafael Menezes
 *@version 1.0
 *@since Release 01 da aplicação
 */
@Service
@AllArgsConstructor
public class WarehouseService {

    /***Instancia de repositório: <i>WarehouseRepository</i>
     */
    private WarehouseRepository warehouseRepository;

    /***Instancia de service: <i>BatchService</i>
     */
    private BatchService batchService;

    /***Instancia de service: <i>SectionService</i>
     */
    private SectionService sectionService;

    /*** Método que retorna lista de Warehouse */
    public List<Warehouse> findAll() {
        return warehouseRepository.findAll();
    }
    /*** Método que busca Warehouse por Id
     * @param id ID do Warehouse a ser retornado
     */

    public Warehouse findById(Long id) {
        Optional<Warehouse> wh = warehouseRepository.findById(id);
        return wh.orElseThrow(()-> new ObjectNotFoundException("Armazem não encontrado! Verifique se os dados digitados estão corretos."));
    }
    /*** Método que insere um Warehouse
     * @param wh objeto Warehouse a ser inserido
     */
    public Warehouse insert(Warehouse wh) {
        return warehouseRepository.save(wh);
    }
    /*** Método que atualiza um Warehouse existente
     *
     * @param nWarehouse Objeto com informações para atualização de um warehouse existente
     */
    public Warehouse update(Warehouse nWarehouse) {
        Warehouse wh = findById(nWarehouse.getId());
        updateWarehouse(nWarehouse, wh);
        return warehouseRepository.save(wh);
    }

    /***
     * Método para update de Warehouse
     * @param wh
     * @param nWarehouse
     */
    private static void updateWarehouse(Warehouse wh, Warehouse nWarehouse) {
        nWarehouse.setName(wh.getName());
        nWarehouse.setLocation(wh.getLocation());
    }

    /*** Método deleta um Warehouse do Bando de dados
     * @param id ID do Seller a ser deletado
     */
    public void delete(Long id) {
        Warehouse wh = findById(id);
        warehouseRepository.delete(wh);
    }

    /*** Verifica o produto na lista de lote
     * @param idProduct lista de lote
     * @return warehouse
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



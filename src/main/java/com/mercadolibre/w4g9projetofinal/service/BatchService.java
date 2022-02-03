package com.mercadolibre.w4g9projetofinal.service;

import com.mercadolibre.w4g9projetofinal.entity.Batch;
import com.mercadolibre.w4g9projetofinal.entity.OrderItem;
import com.mercadolibre.w4g9projetofinal.entity.Section;
import com.mercadolibre.w4g9projetofinal.entity.enums.RefrigerationType;
import com.mercadolibre.w4g9projetofinal.exceptions.BusinessException;
import com.mercadolibre.w4g9projetofinal.exceptions.CartManagementException;
import com.mercadolibre.w4g9projetofinal.exceptions.ObjectNotFoundException;
import com.mercadolibre.w4g9projetofinal.repository.BatchRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/***
 * Classe de serviço para Representative
 *
 * @author Marcos Sá
 */
@Service
@AllArgsConstructor
public class BatchService {

    private final String ASCENDING_ORDER = "asc";
    private final String DESCENDING_ORDER = "desc";


    /*** Instancia de repositório: <b>BatchRepository</b>.
     */
    private BatchRepository batchRepository;


    private SectionService sectionService;

    /*** Método que verifica estoque
     *
     * @param idAdvertise ID do Anuncio a ser verificado o estoque
     * @param qtdProduct qantidade deitens para validar se existem no estoque
     */
    public Batch verifyStock(Long idAdvertise, int qtdProduct) {
        Batch batch = batchRepository.findByAdvertise_Id(idAdvertise).orElseThrow(() ->
                new CartManagementException("Lote referente ao anuncio " + idAdvertise + " não encontrado"));
        batch.verifyStock(qtdProduct);
        return batch;
    }

    /*** Método que dá baixa de Produtos no estoque
     *
     * @param orderItemList Lista de Produtos com as respectivas quantidades para dar baixa no estoque
     */
    public void updateStock(List<OrderItem> orderItemList) {
        List<Batch> batchIterable = new ArrayList<>();
        for (OrderItem cartItem : orderItemList) {
            Batch batch = verifyStock(cartItem.getAdvertise().getId(), cartItem.getQuantity());
            Section section = batch.getInboundOrder().getSection();
            batch.updateStock(cartItem.getQuantity());
            batchIterable.add(batch);
            section.updateStock(cartItem.getQuantity());
            sectionService.save(section);
        }
        batchRepository.saveAll(batchIterable);
    }

    public List<Batch> findByProductId(Long idProduct) {
        return batchRepository.findByProduct_Id(idProduct);
    }

    public Map<Batch, RefrigerationType> findByDueDateBeforeAndSectionId(int numberOfDays, Long sectionId) {
        LocalDate dueDateMax = LocalDate.now().plusDays(numberOfDays);
        List<Batch> batchList = batchRepository.findByDueDateBeforeAndSectionId(sectionId, dueDateMax);
        if (batchList.size() == 0) throw new ObjectNotFoundException("There is no Batch for this Filter.");
        Section section = sectionService.findById(sectionId);
        return getBatchRefrigerationTypeMap(section.getRefrigerationType(), batchList);
    }

    public Map<Batch, RefrigerationType> findByDueDateBeforeAndRefrigerationType(int numberOfDays,
                                                               String productType,
                                                               String orderBy) {
        RefrigerationType refrigerationType = RefrigerationType.toEnum(productType);
        LocalDate dueDateMax = LocalDate.now().plusDays(numberOfDays);
        List<Batch> batchList = batchRepository
                .findByDueDateBeforeAndRefrigerationType(refrigerationType, dueDateMax);
        switch (orderBy) {
            case ASCENDING_ORDER:
                batchList = batchList.stream()
                        .sorted(Comparator.comparing(Batch::getDueDate))
                        .collect(Collectors.toList());
                break;
            case DESCENDING_ORDER:
                batchList = batchList.stream()
                        .sorted(Comparator.comparing(Batch::getDueDate).reversed())
                        .collect(Collectors.toList());
                break;
            default:
                throw new BusinessException("Order param acceptable: ''asc'' and ''desc'' ");
        }
        return getBatchRefrigerationTypeMap(refrigerationType, batchList);
    }

    private Map<Batch, RefrigerationType> getBatchRefrigerationTypeMap(
            RefrigerationType refrigerationType, List<Batch> batchList) {
        Map<Batch, RefrigerationType> batches = new HashMap<>();
        for (Batch b : batchList) {
            batches.put(b, refrigerationType);
        }
        return batches;
    }
}

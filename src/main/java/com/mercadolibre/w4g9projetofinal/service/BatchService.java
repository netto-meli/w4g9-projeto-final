package com.mercadolibre.w4g9projetofinal.service;

import com.mercadolibre.w4g9projetofinal.entity.Batch;
import com.mercadolibre.w4g9projetofinal.entity.OrderItem;
import com.mercadolibre.w4g9projetofinal.entity.Section;
import com.mercadolibre.w4g9projetofinal.entity.Seller;
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
 * @author Fernando Netto
 * @author Marcos
 */
@Service
@AllArgsConstructor
public class BatchService {
    /*** Instancia de repositório: <b>BatchRepository</b>.
     */
    private BatchRepository batchRepository;

    /*** Instancia de repositório: <b>BatchRepository</b>.
     */
    private SectionService sectionService;

    /*** Método que verifica estoque
     *
     * @param idAdvertise ID do Anuncio a ser verificado o estoque
     * @param qtdProduct qantidade deitens para validar se existem no estoque
     * @return lote
     */
    public Batch verifyStock(Long idAdvertise, int qtdProduct) {
        Batch batch = batchRepository.findByAdvertise_Id(idAdvertise).orElseThrow(() ->
                new CartManagementException("Lote referente ao anuncio " + idAdvertise + " não encontrado"));
        this.verifyStockOfBatch(batch, qtdProduct);
        return batch;
    }

    /*** Realiza verificação do estoque da quantidade de itens de um produto para o carrinho
     *
     * @param batch lote
     * @param qtd Quantidade de itens vendidos de um produto.
     */
    private void verifyStockOfBatch(Batch batch, int qtd) {
        if ( qtd > batch.getCurrentQuantity() ){
            String erro = "Imposssível realizar compra, pois o Produto "
                    + batch.getId()
                    + " tem somente "
                    + batch.getCurrentQuantity()
                    + " itens em estoque, e você está tentando comprar "
                    + qtd
                    + " itens.";
            throw new CartManagementException(erro);
        }
    }

    /*** Realiza baixa no estoque da quantidade de itens de um produto que foi vendido
     *
     * @param batch lote
     * @param qtd Quantidade de itens vendidos de um produto.
     */
    private void updateStock(Batch batch, int qtd) {
        int quantity = batch.getCurrentQuantity();
        quantity -= qtd;
        batch.setCurrentQuantity(quantity);
    }

    /*** Método que dá baixa de Produtos no estoque
     *
     * @param orderItemList Lista de Produtos com as respectivas quantidades para dar baixa no estoque
     */
    public void updateStock(List<OrderItem> orderItemList) {
        List<Batch> batchIterable = new ArrayList<>();
        for (OrderItem cartItem : orderItemList) {
            Batch batch = this.verifyStock(cartItem.getAdvertise().getId(), cartItem.getQuantity());
            Section section = batch.getInboundOrder().getSection();
            this.updateStock(batch, cartItem.getQuantity());
            batchIterable.add(batch);
            this.updateStock(section, cartItem.getQuantity());
            sectionService.save(section);
        }
        batchRepository.saveAll(batchIterable);
    }

    /*** Realiza baixa no estoque da quantidade de itens de um produto que foi vendido
     *
     * @param section setor
     * @param qtd Quantidade de itens vendidos de um produto.
     */
    public void updateStock(Section section, int qtd) {
        int quantity = section.getCurrentStock();
        quantity -= qtd;
        section.setCurrentStock(quantity);
    }

    /***
     * Metodo para buscar todos llotes filtrados por prooduto
     * @param idProduct id do produto
     * @return lista de lotes
     */
    public List<Batch> findByProductId(Long idProduct) {
        return batchRepository.findByProduct_Id(idProduct);
    }

    /***
     * Lista de lotes eseus tipos de refrigeracao, filtrado por setor e data de validade
     * @param numberOfDays numero de dias para filtrar data de validae
     * @param sectionId id do setor
     * @return lista (Map) com lotes e seus tipos de refrigeracao
     */
    public Map<Batch, RefrigerationType> findByDueDateBeforeAndSectionId(int numberOfDays, Long sectionId) {
        LocalDate dueDateMax = LocalDate.now().plusDays(numberOfDays);
        List<Batch> batchList = batchRepository.findByDueDateBeforeAndSectionId(sectionId, dueDateMax);
        if (batchList.size() == 0) throw new ObjectNotFoundException("There is no Batch for this Filter.");
        Section section = sectionService.findById(sectionId);
        return getBatchRefrigerationTypeMap(section.getRefrigerationType(), batchList);
    }

    /***
     * Lista de lotes e seus tipos de refrigeração, filtrado por setor e data de validade
     * @param numberOfDays numero de dias para filtrar data de validade
     * @param productType tipo de refrigeração
     * @param orderBy ordenação crescente ou decrescente
     * @return lista (Map) com lotes e seus tipos de refrigeração
     */
    public Map<Batch, RefrigerationType> findByDueDateBeforeAndRefrigerationType(int numberOfDays,
                                                               String productType,
                                                               String orderBy) {
        RefrigerationType refrigerationType = RefrigerationType.toEnum(productType);
        LocalDate dueDateMax = LocalDate.now().plusDays(numberOfDays);
        List<Batch> batchList = batchRepository
                .findByDueDateBeforeAndRefrigerationType(refrigerationType, dueDateMax);
        if (batchList.size() == 0) throw new ObjectNotFoundException("There is no Batch for this Filter.");
        switch (orderBy) {
            case "asc":
                batchList = batchList.stream()
                        .sorted(Comparator.comparing(Batch::getDueDate))
                        .collect(Collectors.toList());
                break;
            case "desc":
                batchList = batchList.stream()
                        .sorted(Comparator.comparing(Batch::getDueDate).reversed())
                        .collect(Collectors.toList());
                break;
            default:
                throw new BusinessException("Order param acceptable: ''asc'' and ''desc'' ");
        }
        return getBatchRefrigerationTypeMap(refrigerationType, batchList);
    }

    /***
     * Mapeamento da lista, e tipo de refrigeração
     * @param refrigerationType tipo de refrigeração
     * @param batchList lista de lotes
     * @return Mapeamento de tipos resfriamento e lotes
     */
    private Map<Batch, RefrigerationType> getBatchRefrigerationTypeMap(
            RefrigerationType refrigerationType, List<Batch> batchList) {
        Map<Batch, RefrigerationType> batches = new HashMap<>();
        for (Batch b : batchList) {
            batches.put(b, refrigerationType);
        }
        return batches;
    }
}

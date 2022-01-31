package com.mercadolibre.w4g9projetofinal.service;

import com.mercadolibre.w4g9projetofinal.entity.Batch;
import com.mercadolibre.w4g9projetofinal.entity.OrderItem;
import com.mercadolibre.w4g9projetofinal.entity.Section;
import com.mercadolibre.w4g9projetofinal.exceptions.CartManagementException;
import com.mercadolibre.w4g9projetofinal.repository.BatchRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/***
 * Classe de serviço para Representative
 *
 * @author Marcos Sá
 */
@Service
@AllArgsConstructor
public class BatchService {

    private BatchRepository repository;

    /*** Instancia de repositório: <b>SectionRepository</b>.
     */
    private SectionService sectionService;

    /*** Método que verifica estoque
     *
     * @param idAdvertise ID do Anuncio a ser verificado o estoque
     * @param qtdProduct qantidade deitens para validar se existem no estoque
     */
    public Batch verifyStock(Long idAdvertise, int qtdProduct) {
        Batch batch = repository.findByAdvertise_Id(idAdvertise).orElseThrow( () ->
                new CartManagementException("Lote referente ao anuncio " + idAdvertise + " não encontrado"));
        batch.verifyStock(qtdProduct);
        return batch;
    }


    /*** Método que dá baixa de Produtos no estoque
     *
     * @param orderItemList Lista de Produtos com as respectivas quantidades para dar baixa no estoque
     */
    /*
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
        repository.saveAll(batchIterable);
    }

     */
}

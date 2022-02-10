package com.mercadolibre.w4g9projetofinal.service;

import com.mercadolibre.w4g9projetofinal.entity.Batch;
import com.mercadolibre.w4g9projetofinal.entity.InboundOrder;
import com.mercadolibre.w4g9projetofinal.entity.Section;
import com.mercadolibre.w4g9projetofinal.exceptions.BusinessException;
import com.mercadolibre.w4g9projetofinal.exceptions.ObjectNotFoundException;
import com.mercadolibre.w4g9projetofinal.exceptions.SectionManagementException;
import com.mercadolibre.w4g9projetofinal.repository.SectionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/*** Service dos métodos do setor
 * <b> {@link #validateBatchSection(List, Section, boolean)} Valida se lote está valida, temperatura indicada do setor e se o estoque tem espaço</b>
 * <b> {@link #updateOldSectionStock(InboundOrder, List)} Atualiza o valor do estoque com base no lote recebido</b>
 * <b> {@link #save(Section)} Método para salvar uma setor</b>
 * <b> {@link #update(Section)} Método para atualizar uma setor</b>
 * <b> {@link #delete(Long)} Método para deletar uma setor</b>
 * <b> {@link #findAll()} Método para listar os setores disponiveis</b>
 * <b> {@link #findById(Long)} Método que retorna um setor buscado pelo ID</b>
 * <b> {@link #findByInboundOrderId(Long)} Método para retornar uma ordem de entrada pelo ID</b>
 *
 * @author Felipe bontempo
 * @version 0.3
 */
@Service
@AllArgsConstructor
public class SectionService {
    /*** Instancia de repositório: <b>SectionRepository</b>.
     */
    private SectionRepository sectionRepository;

    public Section validateBatchSection(List<Batch> batchStock, Section dbSection, boolean isUpdate) {
        StringBuilder msg1 = new StringBuilder();
        StringBuilder msg2 = new StringBuilder();
        boolean throwExeption = false;
        for (Batch b : batchStock) {
            if ( !b.getDueDate().isAfter(LocalDate.now()) ){
                msg2 = new StringBuilder("\nBatch: " + b.getId()
                        + " has a Due Date of " + b.getDueDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            }
            if (b.getMinTemperature() != dbSection.getMinTeperature()) {
                if (!throwExeption) {
                    throwExeption = true;
                    msg1 = new StringBuilder("Batch number(s): ");
                }
                msg1.append(b.getId()).append(", ");
            } else {
                int qtd = 0;
                if (isUpdate) qtd = b.getCurrentQuantity();
                else qtd = b.getInitialQuantity();
                qtd = this.validateAvailableSpaceInStock(
                        qtd, dbSection.getStockLimit(), dbSection.getCurrentStock(), dbSection.getName(), b.getId());
                dbSection.setCurrentStock(qtd);
            }
        }
        if (throwExeption || msg2.length() != 0) {
            if (msg1.length() > 0) msg1.append("does not belong to the Section Informed.");
            msg1.append(msg2);
            throw new SectionManagementException(msg1.toString());
        }
        return dbSection;
    }

    private int validateAvailableSpaceInStock(int quantity, int maxStock, int currentStock, String name, Long id) {
        currentStock += quantity;
        if ( maxStock < currentStock )
            throw new SectionManagementException("Setor "
                    + name
                    + " doesn't have available stock for this update");
        return currentStock;
    }

    public List<Batch> updateOldSectionStock(InboundOrder oldInboundOrder, List<Batch> newBatch) {
        Section sectionToUpdate = oldInboundOrder.getSection();
        int qtd = sectionToUpdate.getCurrentStock();
        List<Batch> completeListBatch = new ArrayList<>();
        for (Batch ob:oldInboundOrder.getBatchList()) {
            completeListBatch.add(ob);
            qtd -= ob.getCurrentQuantity();
        }
        sectionToUpdate.setCurrentStock(qtd);
        for (Batch b : newBatch) {
            if (!completeListBatch.contains(b)) completeListBatch.add(b);
            for (Batch ob : oldInboundOrder.getBatchList()) {
                if (b.getId().equals(ob.getId())) {
                    if (!b.getAdvertise().getId().equals(ob.getAdvertise().getId()))
                        throw new BusinessException("Cannot change Advertise of a Batch");
                    int i = completeListBatch.indexOf(ob);
                    completeListBatch.set(i,b);
                }
            }
        }
        oldInboundOrder.setSection(sectionToUpdate);
        return completeListBatch;
    }

    public Section save(Section section) {
        return sectionRepository.save(section);
    }

    public Section update(Section section) {
        Section newSection = findById(section.getId());
        updateSection(section, newSection);
        return sectionRepository.save(newSection);
    }

    public void delete(Long id) {
        Optional<Section> section = sectionRepository.findById(id);
        section.orElseThrow(() -> new ObjectNotFoundException("Setor não encontrado"));
        sectionRepository.deleteById(id);
    }

    public List<Section> findAll() {
        return sectionRepository.findAll();
    }

    public Section findById(Long id) {
        return sectionRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Section not found! Please check the id."));
    }

    public Section findByInboundOrderId(Long id) {
        return sectionRepository.findByInboundOrder_Id(id)
                .orElseThrow(() -> new ObjectNotFoundException("Setor nao encontrado através do ID da Inbound Order"));
    }

    //Método para update de Section
    private static void updateSection(Section obj, Section newObj) {
        newObj.setName(obj.getName());
        newObj.setRefrigerationType(obj.getRefrigerationType());
        newObj.setWarehouse(obj.getWarehouse());
        newObj.setStockLimit(obj.getStockLimit());
        newObj.setCurrentStock(obj.getCurrentStock());
        newObj.setMaxTeperature(obj.getMaxTeperature());
        newObj.setMinTeperature(obj.getMinTeperature());
    }
}



package com.mercadolibre.w4g9projetofinal.service;

import com.mercadolibre.w4g9projetofinal.entity.*;
import com.mercadolibre.w4g9projetofinal.exceptions.BusinessException;
import com.mercadolibre.w4g9projetofinal.exceptions.ObjectNotFoundException;
import com.mercadolibre.w4g9projetofinal.exceptions.SectionManagementException;
import com.mercadolibre.w4g9projetofinal.repository.InboundOrderRepository;
import com.mercadolibre.w4g9projetofinal.security.entity.UserSS;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/***
 * Classe referente a Ordem de Entrada
 */
@Service
@AllArgsConstructor
public class InboundOrderService {

    private InboundOrderRepository inboundOrderRepository;
    private SellerService sellerService;
    private SectionService sectionService;
    private RepresentativeService representativeService;

    public List<InboundOrder> findAll() {
        List<InboundOrder> result = new ArrayList<>();
        Iterable<InboundOrder> iterable = inboundOrderRepository.findAll();
        iterable.forEach(result::add);
        return result;
    }

    public InboundOrder findById( Long id ) {
        return inboundOrderRepository.findById(id)
                .orElseThrow( () -> new ObjectNotFoundException("Inbound Order not found! Please check the id.") );
    }

    /**
     * Gerenciador de Ordem de Entrada
     * @param user Representante
     * @param inboundOrder Ordem de Entrada
     * @param isUpdate é uma atualização (ou inclusão)
     * @return Ordem de Entrada
     */
    public InboundOrder inboundOrderManager(UserSS user, InboundOrder inboundOrder, boolean isUpdate) {
        Section section = sectionService.findById(inboundOrder.getSection().getId());
        InboundOrder oldInboundOrder = this.getOldInboundOrder(inboundOrder, isUpdate);
        if(!inboundOrder.getSection().getId().equals(oldInboundOrder.getSection().getId()))
            throw new SectionManagementException("Cannot change Section from an Inbound Order");
        this.validateWarehouse(user, inboundOrder, section);
        inboundOrder.setSection(section);
        Seller seller = sellerService.verifySellerInInboundOrder(inboundOrder.getBatchList());
        inboundOrder.setSeller(seller);
        if (isUpdate) {
            List<Batch> completeBatch =
                sectionService.updateOldSectionStock(oldInboundOrder, inboundOrder.getBatchList());
            inboundOrder.setBatchList(completeBatch);
        }
        section = sectionService.validateBatchSection(
                inboundOrder.getBatchList(), oldInboundOrder.getSection(), isUpdate);
        inboundOrder.setSection(section);
        this.setInboundOrderToBatchList(inboundOrder);
        return inboundOrderRepository.save(inboundOrder);
    }

    /***
     * Busca ordem de entrada antiga (ou reutiliza se é uma nova)
     * @param inboundOrder ordem de entrada
     * @param isUpdate é atualização?
     * @return Ordem de Entrada antiga
     */
    private InboundOrder getOldInboundOrder(InboundOrder inboundOrder, boolean isUpdate) {
        InboundOrder oldInboundOrder;
        Optional<InboundOrder> optionalInboundOrder = inboundOrderRepository.findById(inboundOrder.getId());
        if(isUpdate) oldInboundOrder = optionalInboundOrder
                .orElseThrow(() -> new ObjectNotFoundException("Inbound Order not found! Please check the id."));
        else {
            if(optionalInboundOrder.isPresent())
                throw new BusinessException("InboundOrder already exists, please update via PUT");
            oldInboundOrder = inboundOrder;
        }
        return oldInboundOrder;
    }

    /***
     * Valida armazém
     * @param user representante
     * @param inboundOrder ordem de entrada
     * @param section setor
     */
    private void validateWarehouse(UserSS user, InboundOrder inboundOrder, Section section) {
        if (!section.getWarehouse().getId()
                .equals(inboundOrder.getSection().getWarehouse().getId())) {
            throw new SectionManagementException("Section: "
                    + inboundOrder.getSection().getId()
                    + " does not belong to the Warehouse: "
                    + inboundOrder.getSection().getWarehouse().getId() );
        }
        Representative representative = representativeService.findById(user.getId());
        if (!section.getWarehouse().equals( representative.getWarehouse() )){
            throw new BusinessException("Representative: "
                    + representative.getName()
                    + " not part of the Warehouse: "
                    + section.getWarehouse().getName() );
        }
        inboundOrder.setRepresentative(representative);
    }

    /***
     * Atualiza lote, com referência a Ordem de Entrada
     * @param inboundOrder Ordem atualizada
     */
    private void setInboundOrderToBatchList(InboundOrder inboundOrder) {
        if(inboundOrder.getBatchList() == null)
            inboundOrder.setBatchList(new ArrayList<>());
        for (Batch b: inboundOrder.getBatchList())
            b.setInboundOrder(inboundOrder);
    }
}

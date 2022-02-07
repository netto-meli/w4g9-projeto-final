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

@Service
@AllArgsConstructor
public class InboundOrderService {

    private InboundOrderRepository inboundOrderRepository;
    @Autowired
    private SellerService sellerService;
    @Autowired
    private SectionService sectionService;
    @Autowired
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
        inboundOrder.setInboundOrderToBatchList();
        return inboundOrderRepository.save(inboundOrder);
    }

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
                    + section.getWarehouse().getNome() );
        }
        inboundOrder.setRepresentative(representative);
    }
}

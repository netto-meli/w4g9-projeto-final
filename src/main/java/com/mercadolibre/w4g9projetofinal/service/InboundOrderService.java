package com.mercadolibre.w4g9projetofinal.service;

import com.mercadolibre.w4g9projetofinal.entity.Advertise;
import com.mercadolibre.w4g9projetofinal.entity.InboundOrder;
import com.mercadolibre.w4g9projetofinal.entity.Representative;
import com.mercadolibre.w4g9projetofinal.entity.Section;
import com.mercadolibre.w4g9projetofinal.exceptions.BusinessException;
import com.mercadolibre.w4g9projetofinal.exceptions.ObjectNotFoundException;
import com.mercadolibre.w4g9projetofinal.exceptions.SectionManagementException;
import com.mercadolibre.w4g9projetofinal.repository.InboundOrderRepository;
import com.mercadolibre.w4g9projetofinal.security.UserSS;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class InboundOrderService {

    private InboundOrderRepository inboundOrderRepository;
    @Autowired
    private AdvertiseService advertiseService;
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

    @Transactional
    public InboundOrder createInboundOrder(UserSS user, InboundOrder inboundOrder) {
        Section section = sectionService.findById(inboundOrder.getSection().getId());
        validateWarehouse(user, inboundOrder, section);
        inboundOrder.setSection(section);
        sellerService.verifySellerInInboundOrder(inboundOrder.getBatchList());
        sectionService.updateOldSectionStock(inboundOrder, inboundOrder.getBatchList());
        section = sectionService.validateBatchSection(inboundOrder.getBatchList(), section, false);
        inboundOrder.setSection(section);
        Advertise advertise = advertiseService.findById(
                Objects.requireNonNull( inboundOrder.getBatchList()
                                .stream().findFirst()
                                .orElseThrow(() -> new ObjectNotFoundException("Anuncio não encontrado.")))
                        .getAdvertise().getId());
        inboundOrder.setSeller(advertise.getSeller());
        inboundOrder.setInboundOrderToBatchList();
        return inboundOrderRepository.save(inboundOrder);
    }

    @Transactional
    public InboundOrder update(UserSS user, InboundOrder io) {
        InboundOrder oldInboundOrder = inboundOrderRepository.findById(io.getId())
                .orElseThrow( () -> new ObjectNotFoundException("Ordem de Entrada não encontrada.") );
        Section section = sectionService.findById(io.getSection().getId());
        validateWarehouse(user, io, section);
        sellerService.verifySellerInInboundOrder(io.getBatchList());
        sectionService.updateOldSectionStock(oldInboundOrder, io.getBatchList());
        if(oldInboundOrder.getId().equals(io.getSection().getId()))
            io.setSection(oldInboundOrder.getSection());
        else io.setSection(sectionService.findById(io.getSection().getId()));
        section = sectionService.validateBatchSection(io.getBatchList(),
                section, true);
        io.setSection(section);
        io.setSeller(oldInboundOrder.getSeller());
        io.setInboundOrderToBatchList();
        return inboundOrderRepository.save(io);
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

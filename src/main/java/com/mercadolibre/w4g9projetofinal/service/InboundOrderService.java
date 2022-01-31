package com.mercadolibre.w4g9projetofinal.service;

import com.mercadolibre.w4g9projetofinal.entity.Advertise;
import com.mercadolibre.w4g9projetofinal.entity.InboundOrder;
import com.mercadolibre.w4g9projetofinal.entity.Representative;
import com.mercadolibre.w4g9projetofinal.entity.Section;
import com.mercadolibre.w4g9projetofinal.exceptions.ObjectNotFoundException;
import com.mercadolibre.w4g9projetofinal.repository.InboundOrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private SectionService sectionService;

    /*
    public InboundOrder createInboundOrder(Representative representative, InboundOrder inboundOrder) {
        Section section = sectionService.validateSectionBatches(
                inboundOrder.getSection(),
                inboundOrder.getBatchList());
        inboundOrder.setSection(section);
        Advertise advertise = advertiseService.findById(
                Objects.requireNonNull( inboundOrder.getBatchList()
                                .stream().findFirst()
                                .orElseThrow(() -> new ObjectNotFoundException("Anuncio não encontrado.")))
                        .getAdvertise().getId());
        inboundOrder.setSeller(advertise.getSeller());
        inboundOrder.setRepresentative(representative);
        inboundOrder.setInboundOrderToBatchList();
        return this.save(inboundOrder);
    }

     */

    public InboundOrder save(InboundOrder io) {
        return inboundOrderRepository.save(io);
    }

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
}

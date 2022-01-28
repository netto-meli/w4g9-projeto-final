package com.mercadolibre.w4g9projetofinal.service;

import com.mercadolibre.w4g9projetofinal.dtos.request.BatchRequestDTO;
import com.mercadolibre.w4g9projetofinal.entity.InboundOrder;
import com.mercadolibre.w4g9projetofinal.entity.Section;
import com.mercadolibre.w4g9projetofinal.repository.InboundOrderRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InboundOrderService {

    InboundOrderRepository inboundOrderRepository;

    public InboundOrderService(InboundOrderRepository inboundOrdereRepository ) {
        this.inboundOrderRepository = inboundOrdereRepository;
    }

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
        // todo NULL
        return inboundOrderRepository.findById( id ).orElse(null);
    }

    public void validateBatchesToSection(Section section, List<BatchRequestDTO> batchStock) {
        // todo se der ruim, throw exception section
        // todo e se sector esta certo com warrehouse
    }
}

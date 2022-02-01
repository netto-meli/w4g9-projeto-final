package com.mercadolibre.w4g9projetofinal.controller;

import com.mercadolibre.w4g9projetofinal.dtos.converter.BatchConverter;
import com.mercadolibre.w4g9projetofinal.dtos.converter.InboundOrderConverter;
import com.mercadolibre.w4g9projetofinal.dtos.request.InboundOrderRequestDTO;
import com.mercadolibre.w4g9projetofinal.dtos.response.BatchResponseDTO;
import com.mercadolibre.w4g9projetofinal.dtos.response.InboundOrderResponseDTO;
import com.mercadolibre.w4g9projetofinal.entity.InboundOrder;
import com.mercadolibre.w4g9projetofinal.entity.Representative;
import com.mercadolibre.w4g9projetofinal.service.InboundOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/fresh-products/inboundorder")
public class InboundOrderController {

    @Autowired
    InboundOrderService inboundOrderService;

    @PostMapping
    public ResponseEntity<List<BatchResponseDTO>> createInboundOrder(
            @RequestBody InboundOrderRequestDTO inboundOrderRequestDTO,
            UriComponentsBuilder uriBuilder) {
        // todo autentication
        Representative representative = new Representative(1L,null, null, null, null);
        InboundOrder inboundOrder = InboundOrderConverter.convertDtoToEntity(inboundOrderRequestDTO);
        inboundOrder = inboundOrderService.createInboundOrder(representative, inboundOrder);
        List<BatchResponseDTO> response = BatchConverter.convertEntityListToDtoList(inboundOrder.getBatchList());
        URI uri = uriBuilder
                .path("/{id}")
                .buildAndExpand(inboundOrder.getId())
                .toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @PutMapping
    public ResponseEntity<List<BatchResponseDTO>> updateInboundOrder(
            @RequestBody InboundOrderRequestDTO request,
            UriComponentsBuilder uriBuilder) {
        InboundOrder io = InboundOrderConverter.convertDtoToEntity(request);
        io = inboundOrderService.save(io);
        List<BatchResponseDTO> response = BatchConverter.convertEntityListToDtoList(io.getBatchList());
        URI uri = uriBuilder
                .path("/{id}")
                .buildAndExpand(io.getId())
                .toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InboundOrderResponseDTO> findInboundOrderById(@PathVariable Long id) {
        InboundOrder io = inboundOrderService.findById(id);
        InboundOrderResponseDTO response = InboundOrderConverter.convertEntityToDto(io);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping
    public ResponseEntity<List<InboundOrderResponseDTO>> findAllInboundOrders() {
        List<InboundOrder> inboundOrderList = inboundOrderService.findAll();
        List<InboundOrderResponseDTO> response = InboundOrderConverter.convertEntityListToDtoList(inboundOrderList);
        return ResponseEntity.ok().body(response);
    }
}

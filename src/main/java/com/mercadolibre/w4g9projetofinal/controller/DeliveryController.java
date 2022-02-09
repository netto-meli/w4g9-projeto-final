package com.mercadolibre.w4g9projetofinal.controller;

import com.mercadolibre.w4g9projetofinal.dtos.converter.BatchConverter;
import com.mercadolibre.w4g9projetofinal.dtos.converter.InboundOrderConverter;
import com.mercadolibre.w4g9projetofinal.dtos.request.InboundOrderRequestDTO;
import com.mercadolibre.w4g9projetofinal.dtos.response.BatchResponseDTO;
import com.mercadolibre.w4g9projetofinal.dtos.response.InboundOrderResponseDTO;
import com.mercadolibre.w4g9projetofinal.entity.InboundOrder;
import com.mercadolibre.w4g9projetofinal.security.entity.UserSS;
import com.mercadolibre.w4g9projetofinal.service.InboundOrderService;
import com.mercadolibre.w4g9projetofinal.service.TransporterService;
import com.mercadolibre.w4g9projetofinal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/fresh-products/inboundorder")
@PreAuthorize("hasRole('ADMIN') OR hasRole('REPRESENTATIVE')")
public class DeliveryController {

    @Autowired
    TransporterService transporterService;
/*
    @PostMapping
    public ResponseEntity<List<BatchResponseDTO>> createInboundOrder(
            @RequestBody @Valid InboundOrderRequestDTO inboundOrderRequestDTO,
            UriComponentsBuilder uriBuilder) {
        UserSS user = UserService.authenticated();
        InboundOrder inboundOrder = InboundOrderConverter.convertDtoToEntity(inboundOrderRequestDTO);
        inboundOrder = inboundOrderService.inboundOrderManager(user, inboundOrder, false);
        List<BatchResponseDTO> response = BatchConverter.convertEntityListToDtoList(inboundOrder.getBatchList());
        URI uri = uriBuilder
                .path("/{id}")
                .buildAndExpand(inboundOrder.getId())
                .toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @PutMapping
    public ResponseEntity<List<BatchResponseDTO>> updateInboundOrder(
            @RequestBody @Valid InboundOrderRequestDTO request,
            UriComponentsBuilder uriBuilder) {
        UserSS user = UserService.authenticated();
        InboundOrder io = InboundOrderConverter.convertDtoToEntity(request);
        io = inboundOrderService.inboundOrderManager(user, io, true);
        List<BatchResponseDTO> response = BatchConverter.convertEntityListToDtoList(io.getBatchList());
        URI uri = uriBuilder
                .path("/{id}")
                .buildAndExpand(io.getId())
                .toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping
    public ResponseEntity<List<InboundOrderResponseDTO>> findAllInboundOrders() {
        List<InboundOrder> inboundOrderList = inboundOrderService.findAll();
        List<InboundOrderResponseDTO> response = InboundOrderConverter.convertEntityListToDtoList(inboundOrderList);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InboundOrderResponseDTO> findInboundOrderById(@PathVariable Long id) {
        InboundOrder io = inboundOrderService.findById(id);
        InboundOrderResponseDTO response = InboundOrderConverter.convertEntityToDto(io);
        return ResponseEntity.ok().body(response);
    }
 */
}

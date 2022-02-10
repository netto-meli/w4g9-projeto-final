package com.mercadolibre.w4g9projetofinal.controller;

import com.mercadolibre.w4g9projetofinal.dtos.converter.BatchConverter;
import com.mercadolibre.w4g9projetofinal.dtos.converter.InboundOrderConverter;
import com.mercadolibre.w4g9projetofinal.dtos.request.InboundOrderRequestDTO;
import com.mercadolibre.w4g9projetofinal.dtos.response.BatchResponseDTO;
import com.mercadolibre.w4g9projetofinal.dtos.response.InboundOrderResponseDTO;
import com.mercadolibre.w4g9projetofinal.entity.InboundOrder;
import com.mercadolibre.w4g9projetofinal.security.entity.UserSS;
import com.mercadolibre.w4g9projetofinal.service.InboundOrderService;
import com.mercadolibre.w4g9projetofinal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

/***
 * @author Fernando
 */
@RestController
@RequestMapping(value = "/api/v1/fresh-products/inboundorder")
@PreAuthorize("hasRole('ADMIN') OR hasRole('REPRESENTATIVE')")
public class InboundOrderController {

    @Autowired
    InboundOrderService inboundOrderService;

    @PostMapping
    public ResponseEntity<List<BatchResponseDTO>> insert(
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
    public ResponseEntity<List<BatchResponseDTO>> update(
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
    public ResponseEntity<List<InboundOrderResponseDTO>> findAll() {
        List<InboundOrder> inboundOrderList = inboundOrderService.findAll();
        List<InboundOrderResponseDTO> response = InboundOrderConverter.convertEntityListToDtoList(inboundOrderList);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InboundOrderResponseDTO> findById(@PathVariable Long id) {
        InboundOrder io = inboundOrderService.findById(id);
        InboundOrderResponseDTO response = InboundOrderConverter.convertEntityToDto(io);
        return ResponseEntity.ok().body(response);
    }
}

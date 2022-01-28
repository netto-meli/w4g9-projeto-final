package com.mercadolibre.w4g9projetofinal.controller;

import com.mercadolibre.w4g9projetofinal.dtos.converter.BatchConverter;
import com.mercadolibre.w4g9projetofinal.dtos.converter.InboundOrderConverter;
import com.mercadolibre.w4g9projetofinal.dtos.converter.SectionConverter;
import com.mercadolibre.w4g9projetofinal.dtos.converter.SellerConverter;
import com.mercadolibre.w4g9projetofinal.dtos.request.InboundOrderRequestDTO;
import com.mercadolibre.w4g9projetofinal.dtos.request.SellerRequestDTO;
import com.mercadolibre.w4g9projetofinal.dtos.response.BatchResponseDTO;
import com.mercadolibre.w4g9projetofinal.dtos.response.SellerResponseDTO;
import com.mercadolibre.w4g9projetofinal.entity.*;
import com.mercadolibre.w4g9projetofinal.entity.enums.CargoRepresentante;
import com.mercadolibre.w4g9projetofinal.service.AdvertiseService;
import com.mercadolibre.w4g9projetofinal.service.InboundOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/fresh-products/inboundorder/")
public class InboundOrderController {

    @Autowired
    InboundOrderService inboundOrderService;
    @Autowired
    AdvertiseService advertiseService;

    @PostMapping
    public ResponseEntity<List<BatchResponseDTO>> createInboundOrder(
            @RequestBody InboundOrderRequestDTO inboundOrderRequestDTO,
            UriComponentsBuilder uriBuilder) {
        InboundOrder io = InboundOrderConverter.convertDtoToEntity(inboundOrderRequestDTO, new Representative());
        Section section = SectionConverter.convertDtoToEntity(inboundOrderRequestDTO.getSection());
        // todo ver se ta null vazio (validate)
        List<Advertise> list1 = new ArrayList<>();
        List<Batch> list2 = new ArrayList<>();
        Representative representative = new Representative(1L,"ame", "email", CargoRepresentante.LIDER);
        Seller seller = new Seller(1L,"ame", "email", list1, list2);/*advertiseService.findSellerByadvertiseId(
                inboundOrderRequestDTO.getBatchStock()
                        .get(0)
                        .getProductId());*/
        io.setSeller(seller);
        io.setRepresentative(representative);
        io = inboundOrderService.save(io);
        List<BatchResponseDTO> response = BatchConverter.convertEntityListToDtoList(io.getBatchList());
        URI uri = uriBuilder
                .path("/{id}")
                .buildAndExpand(io.getId())
                .toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @PutMapping
    public ResponseEntity<List<BatchResponseDTO>> updateInboundOrder(
            @RequestBody InboundOrderRequestDTO request,
            UriComponentsBuilder uriBuilder) {
        InboundOrder io = InboundOrderConverter.convertDtoToEntity(request, new Representative());
        io = inboundOrderService.save(io);
        List<BatchResponseDTO> response = BatchConverter.convertEntityListToDtoList(io.getBatchList());
        URI uri = uriBuilder
                .path("/{id}")
                .buildAndExpand(io.getId())
                .toUri();
        return ResponseEntity.created(uri).body(response);
    }
/*
    @GetMapping
    public ResponseEntity<List<InboundOrderResponseDTO>> findAllInboundOrders() {
        List<InboundOrder> inboundOrderList = inboundOrderService.findAll();
        List<InboundOrderResponseDTO> response = InboundOrderConverter.convertEntityListToDtoList(inboundOrderList);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InboundOrderResponseDTO> findInboundOrderById(@PathVariable Long id) {
        InboundOrder io = inboundOrderService.findById(id);
        InboundOrderResponseDTO response = InboundOrderConverter.convertEntityToDto(io, new Representative());
        return ResponseEntity.ok().body(response);
    }*/
}

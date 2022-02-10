package com.mercadolibre.w4g9projetofinal.controller;

import com.mercadolibre.w4g9projetofinal.dtos.converter.TransporterConverter;
import com.mercadolibre.w4g9projetofinal.dtos.request.DeliveryRequestDTO;
import com.mercadolibre.w4g9projetofinal.dtos.request.TransporterRequestDTO;
import com.mercadolibre.w4g9projetofinal.dtos.response.TransporterResponseDTO;
import com.mercadolibre.w4g9projetofinal.entity.Transporter;
import com.mercadolibre.w4g9projetofinal.security.entity.UserSS;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/fresh-products/delivery")
@PreAuthorize("hasRole('ADMIN') OR hasRole('REPRESENTATIVE')")
public class DeliveryController {

    @Autowired
    TransporterService transporterService;

    @PostMapping
    public ResponseEntity<TransporterResponseDTO> insert(
            @RequestBody @Valid TransporterRequestDTO transporterRequestDTO,
            UriComponentsBuilder uriBuilder) {
        UserSS user = UserService.authenticated();
        Transporter transporter = TransporterConverter.convertDtoToEntity(transporterRequestDTO, user.getId());
        transporter = transporterService.insert(transporter);
        TransporterResponseDTO response = TransporterConverter.convertEntityToDto(transporter);
        URI uri = uriBuilder
                .path("/{id}")
                .buildAndExpand(response.getId())
                .toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<TransporterResponseDTO> update(@PathVariable Long id,
            @RequestBody @Valid TransporterRequestDTO transporterRequestDTO) {
        UserSS user = UserService.authenticated();
        Transporter transporter = TransporterConverter.convertDtoToEntity(transporterRequestDTO, user.getId());
        transporter.setId(id);
        transporter = transporterService.update(transporter);
        TransporterResponseDTO response = TransporterConverter.convertEntityToDto(transporter);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        transporterService.delete(id);
        return ResponseEntity.ok("Transporter with ID: " + id + " deleted.");
    }

    @GetMapping
    public ResponseEntity<List<TransporterResponseDTO>> findAllI() {
        List<Transporter> transporterList = transporterService.findAll();
        List<TransporterResponseDTO> response = TransporterConverter.convertEntityListToDtoList(transporterList);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TransporterResponseDTO> findById(@PathVariable Long id) {
        Transporter transporter = transporterService.findById(id);
        TransporterResponseDTO response = TransporterConverter.convertEntityToDto(transporter);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping(value = "/byStatus/")
    public ResponseEntity<List<TransporterResponseDTO>> findByStatus(
            @RequestParam(defaultValue = "false")  boolean isInRoute) {
        List<Transporter> transporterList = transporterService.findByStatus(isInRoute);
        List<TransporterResponseDTO> response = TransporterConverter.convertEntityListToDtoList(transporterList);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping(value = "/delivery/")
    public ResponseEntity<TransporterResponseDTO> callDelivery(
            @RequestBody @Valid List<DeliveryRequestDTO> deliveryIdList,
            UriComponentsBuilder uriBuilder) {
        List<Long> idList = deliveryIdList.stream().map(DeliveryRequestDTO::getId).collect(Collectors.toList());
        Transporter transporter = transporterService.calldelivery(idList);
        TransporterResponseDTO response = TransporterConverter.convertEntityToDto(transporter);
        URI uri = uriBuilder
                .path("/{id}")
                .buildAndExpand(response.getId())
                .toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @PutMapping(value = "/delivery/{idTransporter}")
    public ResponseEntity<TransporterResponseDTO> confirmDelivery(
            @PathVariable Long idTransporter,
            @RequestBody @Valid List<DeliveryRequestDTO> deliveryIdList) {
        List<Long> idList = deliveryIdList.stream().map(DeliveryRequestDTO::getId).collect(Collectors.toList());
        Transporter transporter = transporterService.confirmDelivery(idTransporter, idList);
        TransporterResponseDTO response = TransporterConverter.convertEntityToDto(transporter);
        return ResponseEntity.ok(response);
    }
}

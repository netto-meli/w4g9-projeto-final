package com.mercadolibre.w4g9projetofinal.controller;

import com.mercadolibre.w4g9projetofinal.dtos.converter.BatchConverter;
import com.mercadolibre.w4g9projetofinal.dtos.response.BatchByDueDateRequestDTO;
import com.mercadolibre.w4g9projetofinal.entity.Batch;
import com.mercadolibre.w4g9projetofinal.entity.enums.RefrigerationType;
import com.mercadolibre.w4g9projetofinal.service.BatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1/fresh-products/due-date")
public class DueDateController {

    @Autowired
    BatchService batchService;

    @GetMapping("/bySection/{numberOfDays}")
    public ResponseEntity<List<BatchByDueDateRequestDTO>> findByDueDateBeforeAndSectionId(
            @PathVariable int numberOfDays,
            @RequestParam Long sectionId) {
        Map<Batch, RefrigerationType> batcheMap = batchService.findByDueDateBeforeAndSectionId(numberOfDays, sectionId);
        List<BatchByDueDateRequestDTO> response = BatchConverter.convertEntityMapToDtoByDueDateList(batcheMap);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/byRefrigeration/{numberOfDays}")
    public ResponseEntity<List<BatchByDueDateRequestDTO>> findByDueDateBeforeAndRefrigerationType(
            @PathVariable int numberOfDays,
            @RequestParam String refrigerationType,
            @RequestParam String orderBy) {
        Map<Batch, RefrigerationType> batcheMap =
                batchService.findByDueDateBeforeAndRefrigerationType(numberOfDays, refrigerationType, orderBy);
        List<BatchByDueDateRequestDTO> response = BatchConverter.convertEntityMapToDtoByDueDateList(batcheMap);
        return ResponseEntity.ok().body(response);
    }
}


package com.mercadolibre.w4g9projetofinal.controller;

import com.mercadolibre.w4g9projetofinal.dtos.converter.BatchConverter;
import com.mercadolibre.w4g9projetofinal.dtos.response.BatchByDueDateRequestDTO;
import com.mercadolibre.w4g9projetofinal.entity.Batch;
import com.mercadolibre.w4g9projetofinal.entity.enums.RefrigerationType;
import com.mercadolibre.w4g9projetofinal.service.BatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/***
 * @author Fernando
 */
@RestController
@RequestMapping(value = "/api/v1/fresh-products/due-date")
@PreAuthorize("hasRole('ADMIN') OR hasRole('REPRESENTATIVE')")
public class DueDateController {

    @Autowired
    BatchService batchService;

    /***
     * Busca Lotes, conforme datade validade, filtrado por setor
     * @param numberOfDays nnumero de dias para expiração da validae
     * @param sectionId setor
     * @return lista
     */
    @GetMapping("/bySection/{numberOfDays}")
    public ResponseEntity<List<BatchByDueDateRequestDTO>> findByDueDateBeforeAndSectionId(
            @PathVariable int numberOfDays,
            @RequestParam Long sectionId) {
        Map<Batch, RefrigerationType> batcheMap = batchService.findByDueDateBeforeAndSectionId(numberOfDays, sectionId);
        List<BatchByDueDateRequestDTO> response = BatchConverter.convertEntityMapToDtoByDueDateList(batcheMap);
        return ResponseEntity.ok().body(response);
    }

    /***
     * Busca Lotes, conforme datade validade, filtrado por tipo de refrigeração e ordenado crescente ou decrescente
     * @param numberOfDays nnumero de dias para expiração da validae
     * @param refrigerationType tipo de refrigeração
     * @param orderBy ordenação asc / desc
     * @return lista
     */
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


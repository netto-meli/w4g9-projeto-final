package com.mercadolibre.w4g9projetofinal.service;

import com.mercadolibre.w4g9projetofinal.entity.Batch;
import com.mercadolibre.w4g9projetofinal.entity.Product;
import com.mercadolibre.w4g9projetofinal.entity.enums.OrderByProductInLote;
import com.mercadolibre.w4g9projetofinal.exceptions.ObjectNotFoundException;
import com.mercadolibre.w4g9projetofinal.service.BatchService;
import com.mercadolibre.w4g9projetofinal.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductService {

    private ProductRepository repository;

    private BatchService batchService;

    public List<Product> findAll() {
        return repository.findAll();
    }

    public Product findById(Long id) {
        Optional<Product> product = repository.findById(id);
        return product.orElseThrow(() ->
                new ObjectNotFoundException("Produto não encontrado! Por favor verifique dados informados."));
    }

    public List<Product> findByCategoryProduct(String category) {
        if (!(category == null)) {
            return repository.findAll().stream().filter(p -> p.getCategoryRefrigeration().getCod().equals(category)).collect(Collectors.toList());
        }
/*
        if(category.equals(RefrigerationType.FROZEN.getCod())){
            return repository.findAll().stream().filter(p-> p.getCategoryRefrigeration().getCod().equals(category)).collect(Collectors.toList());
        }

        if(category.equals(RefrigerationType.FRESH.getCod())){
            return repository.findAll().stream().filter(p-> p.getCategoryRefrigeration().getCod().equals(category)).collect(Collectors.toList());
        }*/

        return null;
    }

    public List<Batch> findByBatchInProduct(Long idProduct) {
        return batchService.findByProductId(idProduct);
    }

    public List<Batch> OrderByBatchInProduct(Long idProduct, String orderBy) {
        List<Batch> batch = findByBatchInProduct(idProduct);
        if (OrderByProductInLote.ORDER_BY_LOTE.getCod().equals(orderBy)) {
            return batch.stream().sorted(Comparator.comparing(Batch::getId)).collect(Collectors.toList());
        }
        if(OrderByProductInLote.ORDER_BY_QUANTITY.getCod().equals(orderBy)){
            return batch.stream().sorted(Comparator.comparing(Batch::getCurrentQuantity)).collect(Collectors.toList());
        }
        if(OrderByProductInLote.ORDER_BY_DUEDATE.getCod().equals(orderBy)){
            return batch.stream().sorted(Comparator.comparing(Batch::getDueDate)).collect(Collectors.toList());
        }
        return null;
    }

}

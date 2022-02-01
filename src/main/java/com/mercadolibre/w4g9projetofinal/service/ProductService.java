package com.mercadolibre.w4g9projetofinal.service;

import com.mercadolibre.w4g9projetofinal.entity.Product;
import com.mercadolibre.w4g9projetofinal.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductService {

    private ProductRepository repository;

    public List<Product> findAll() {
        return repository.findAll();
    }


    public List<Product> findByCategoryProduct(String category){
        if(!(category == null)) {
            return repository.findAll().stream().filter(p-> p.getCategoryRefrigeration().getCod().equals(category)).collect(Collectors.toList());
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






}

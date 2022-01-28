package com.mercadolibre.w4g9projetofinal.service;
import com.mercadolibre.w4g9projetofinal.entity.Buyer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BuyerService {
    @Autowired
    private BuyerService repository;

    public List<Buyer> findAll() {
        List<Buyer> list = repository.findAll();
        return list;
    }

    public Buyer findById(Long id) {
        Optional<Buyer> obj = Optional.ofNullable(repository.findById(id));
        return obj.orElse(null);
    }

    public Buyer insert(Buyer buyer) {
        return repository.insert(buyer);
    }
}

package com.mercadolibre.w4g9projetofinal.service;

import com.mercadolibre.w4g9projetofinal.dtos.converter.RepresentativeConverter;
import com.mercadolibre.w4g9projetofinal.dtos.response.RepresentativeResponseDTO;
import com.mercadolibre.w4g9projetofinal.entity.Representative;
import com.mercadolibre.w4g9projetofinal.entity.Seller;
import com.mercadolibre.w4g9projetofinal.entity.enums.CargoRepresentante;
import com.mercadolibre.w4g9projetofinal.repository.RepresentativeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RepresentativeService {

    @Autowired
    private RepresentativeRepository repository;

    public List<Representative> findAll() {
        List<Representative> list = repository.findAll();
        return list;
    }

    public Representative findById(Long id) {
        Optional<Representative> obj = repository.findById(id);
        return obj.orElse(null);
    }

    public Representative insert(Representative obj) {
        return repository.save(obj);
    }

    public Representative update(Representative newObj) {
        Representative obj = findById(newObj.getId());
        RepresentativeConverter.updateRepresentation(newObj, obj);
        return repository.save(obj);
    }

    public void delete(Long id) {
        Representative obj = findById(id);
        repository.delete(obj);
    }


}

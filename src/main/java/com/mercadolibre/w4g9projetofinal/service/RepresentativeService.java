package com.mercadolibre.w4g9projetofinal.service;

import com.mercadolibre.w4g9projetofinal.dtos.RepresentativeDTO;
import com.mercadolibre.w4g9projetofinal.entity.Representative;
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

    public List<RepresentativeDTO> findAll() {
        List<RepresentativeDTO> list = repository.findAll().stream().map(x -> new RepresentativeDTO(x.getId(), x.getName(), x.getJob())).collect(Collectors.toList());
        return list;
    }

    public Representative findById(Long id) {
        Optional<Representative> obj = repository.findById(id);
        return obj.orElse(null);
    }

    public Representative insert(RepresentativeDTO objDto) {
        Representative obj = new Representative(objDto.getId(), objDto.getName(), objDto.getJob());
        obj.setId(null);
        obj.setJob(CargoRepresentante.toEnum(obj.getJob().getCod()));
        return repository.save(obj);
    }
}

package com.mercadolibre.w4g9projetofinal.service;

import com.mercadolibre.w4g9projetofinal.entity.Advertise;
import com.mercadolibre.w4g9projetofinal.exceptions.ObjectNotFoundException;
import com.mercadolibre.w4g9projetofinal.repository.AdvertiseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AdvertiseService {

    private AdvertiseRepository repository;

    public List<Advertise> findAll() {
       return repository.findAll();
    }

    public Advertise findById(Long id) {
        Optional<Advertise> obj = repository.findById(id);
        return obj.orElseThrow( () -> new ObjectNotFoundException("Anuncio não encontrado! Por favor verifique o id."));
    }

    public Advertise insert(Advertise advertise) {
        return repository.save(advertise);
    }

    public Advertise update(Advertise newAdvertise) {
        Advertise advertise = findById(newAdvertise.getId());
        updateSeller(newAdvertise, advertise);
        return repository.save(advertise);
    }

    public void delete(Long id) {
        Advertise advertise = findById(id);
        repository.delete(advertise);
    }

    private static void updateSeller(Advertise advertise, Advertise newAdvertise) {
        newAdvertise.setId(advertise.getId());
        newAdvertise.setSeller(advertise.getSeller());
        newAdvertise.setDescription(advertise.getDescription());
        newAdvertise.setPrice(advertise.getPrice());
        newAdvertise.setProduct(advertise.getProduct());
        newAdvertise.setStatus(advertise.getStatus());
    }
}

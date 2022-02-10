package com.mercadolibre.w4g9projetofinal.service;

import com.mercadolibre.w4g9projetofinal.entity.Advertise;
import com.mercadolibre.w4g9projetofinal.exceptions.ObjectNotFoundException;
import com.mercadolibre.w4g9projetofinal.repository.AdvertiseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/***
 * Classe de Servico do anuncio
 * lista todos anuncios
 * busca por id
 * insere anuncio
 * altera anuncio
 * exclui anuncio
 * @author Leonardo
 */
@Service
@AllArgsConstructor
public class AdvertiseService {

    /***
     * instancia de repositorio do anuncio
     */
    private final AdvertiseRepository repository;

    /***
     * metodo de listagem de anuncio
     * @return todos os anuncios
     */
    public List<Advertise> findAll() {
       return repository.findAll();
    }

    /**
     * Metodo para buscar anuncio por id
     * @param id id
     * @return o anuncio procurado
     */
    public Advertise findById(Long id) {
        Optional<Advertise> obj = repository.findById(id);
        return obj.orElseThrow( () -> new ObjectNotFoundException("Anuncio não encontrado! Por favor verifique o id."));
    }

    /**
     * Metodo para inserir anuncio
     * @param advertise anuncio
     * @return status 200 quando salvo
     */
    public Advertise insert(Advertise advertise) {
        return repository.save(advertise);
    }

    /**
     * Metodo para alterar informaçoes de anuncio
     * @param newAdvertise anuncio
     * @return o novo anuncio salvo status 200
     */
    public Advertise update(Advertise newAdvertise) {
        Advertise advertise = findById(newAdvertise.getId());
        updateSeller(newAdvertise, advertise);
        return repository.save(advertise);
    }

    /**
     * Metodo para deletar anuncio por id
     * @param id id
     */
    public void delete(Long id) {
        Advertise advertise = findById(id);
        repository.delete(advertise);
    }

    /**
     * Metodo para atualizar um vendedor pelo anuncio
     * @param advertise anuncio
     * @param newAdvertise anuncio
     */
    private static void updateSeller(Advertise advertise, Advertise newAdvertise) {
        newAdvertise.setDescription(advertise.getDescription());
        newAdvertise.setProduct(advertise.getProduct());
        newAdvertise.setSeller(advertise.getSeller());
        newAdvertise.setPrice(advertise.getPrice());
        newAdvertise.setStatus(advertise.getStatus());
        newAdvertise.setFreeShipping(advertise.isFreeShipping());
    }
}

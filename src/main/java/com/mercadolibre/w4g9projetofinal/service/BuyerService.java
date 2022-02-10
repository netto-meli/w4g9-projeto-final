package com.mercadolibre.w4g9projetofinal.service;

import com.mercadolibre.w4g9projetofinal.entity.Buyer;
import com.mercadolibre.w4g9projetofinal.exceptions.ExistingUserException;
import com.mercadolibre.w4g9projetofinal.exceptions.ObjectNotFoundException;
import com.mercadolibre.w4g9projetofinal.repository.BuyerRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Classe de servico de comprador
 * lista todos os anuncios
 * lista comprador por id
 * inserir um novo comprador
 * alterar informacoes de comprador
 * deletar comprador
 * para alterar Representante pelo comprador
 * @author Leonardo
 */
@Service
@AllArgsConstructor
public class BuyerService {

    /*** Instancia de BCryptPasswordEncoder: <b>BCryptPasswordEncoder</b>.
     */
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * Instancia para Buyer Repository
     */
    private final BuyerRepository repository;

    /**
     * Metodo que lista todos os compradores
     * @return todos os compradores
     */
    public List<Buyer> findAll() {
        return repository.findAll();
    }

    /**
     * Metodo de busca de comprador por id
     * @param id id
     * @return comprador do id da procura
     */
    public Buyer findById(Long id) {
        Optional<Buyer> buyer = repository.findById(id);
        return buyer.orElseThrow(() ->
                new ObjectNotFoundException("Comprador não encontrado! Por favor verifique dados informados."));
    }

    /**
     * Metodo para inserir um novo comprador
     * @param buyer comprador
     * @return status 200
     */
    public Buyer insert(Buyer buyer) {
        buyer.setPassword(bCryptPasswordEncoder.encode(buyer.getPassword()));
        try {
            return repository.save(buyer);
        } catch (DataIntegrityViolationException e) {
            throw new ExistingUserException("Username ou Email existente na base de dados");
        }
    }

    /**
     * Metodo para alterar informacoes de comprador
     * @param buyer comprador
     * @return comprador alterado
     */
    public Buyer update(Buyer buyer) {
        Buyer newBuyer = findById(buyer.getId());
        updateRepresentation(newBuyer, buyer);
        buyer.setPassword(bCryptPasswordEncoder.encode(buyer.getPassword()));
        return repository.save(buyer);
    }

    /**
     * Metodo para deletar comprador por id
     * @param id id
     */
    public void delete(Long id) {
        Buyer buyer = findById(id);
        repository.delete(buyer);
    }

    /**
     * Metodo para alterar Representante pelo comprador
     * @param buyer comprador
     * @param newBuyer comprador
     */
    private static void updateRepresentation(Buyer buyer, Buyer newBuyer) {
        newBuyer.setUsername(buyer.getUsername());
        newBuyer.setName(buyer.getName());
        newBuyer.setEmail(buyer.getEmail());
        newBuyer.setPassword(buyer.getPassword());
        newBuyer.setAddress(buyer.getAddress());
    }

}

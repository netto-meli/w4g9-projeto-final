package com.mercadolibre.w4g9projetofinal.service;

import com.mercadolibre.w4g9projetofinal.entity.Seller;
import com.mercadolibre.w4g9projetofinal.exceptions.ObjectNotFoundException;
import com.mercadolibre.w4g9projetofinal.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/***
 * Classe de serviço para Seller
 *
 * @author Marcos Sá
 */

@Service
public class SellerService {

    @Autowired
    private BCryptPasswordEncoder pe;

    @Autowired
    private SellerRepository repository;

    public List<Seller> findAll() {
        List<Seller> list = repository.findAll();
        return list;
    }

    public Seller findById(Long id) {
        Optional<Seller> obj = repository.findById(id);
        return obj.orElseThrow( () -> new ObjectNotFoundException("Vendedor não encontrado! Por favor verifique o id."));
    }

    public Seller findByEmail(String email) {
        Seller obj = repository.findByEmail(email);
        if(obj == null) {
            throw new ObjectNotFoundException("Usuário não encontrado");
        }
        return obj;
    }

    public Seller insert(Seller obj) {
        obj.setPass(pe.encode(obj.getPass()));
        return repository.save(obj);
    }

    public Seller update(Seller newObj) {
        Seller obj = findById(newObj.getId());
        updateSeller(newObj, obj);
        return repository.save(obj);
    }

    public void delete(Long id) {
        Seller obj = findById(id);
        repository.delete(obj);
    }


    //Método para update de Seller
    private static void updateSeller(Seller obj, Seller newObj) {
        newObj.setId(obj.getId());
        newObj.setName(obj.getName());
        newObj.setEmail(obj.getEmail());
    }
}

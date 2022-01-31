package com.mercadolibre.w4g9projetofinal.service;

import com.mercadolibre.w4g9projetofinal.entity.Representative;
import com.mercadolibre.w4g9projetofinal.exceptions.ObjectNotFoundException;
import com.mercadolibre.w4g9projetofinal.repository.RepresentativeRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/***
 * Classe de serviço para Representative
 *
 * @author Marcos Sá
 */

@Service
@AllArgsConstructor
public class RepresentativeService {

    private RepresentativeRepository repository;

    public List<Representative> findAll() {
        return repository.findAll();
    }

    public Representative findById(Long id) {
        Optional<Representative> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Representante não encontrado! Por favor verifique o id."));
    }

    public Representative insert(Representative obj) {
        return repository.save(obj);
    }

    public Representative update(Representative newObj) {
        Representative obj = findById(newObj.getId());
        updateRepresentation(newObj, obj);
        return repository.save(obj);
    }

    public void delete(Long id) {
        Representative obj = findById(id);
        repository.delete(obj);
    }

    //Método para update de Representation
    private static void updateRepresentation(Representative obj, Representative newObj) {
        newObj.setName(obj.getName());
        newObj.setJob(obj.getJob());
        newObj.setEmail(obj.getEmail());
    }


}

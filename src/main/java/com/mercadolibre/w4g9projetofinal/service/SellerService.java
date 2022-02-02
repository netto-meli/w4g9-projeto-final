package com.mercadolibre.w4g9projetofinal.service;

import com.mercadolibre.w4g9projetofinal.entity.Seller;
import com.mercadolibre.w4g9projetofinal.exceptions.ExistingUserException;
import com.mercadolibre.w4g9projetofinal.exceptions.ObjectNotFoundException;
import com.mercadolibre.w4g9projetofinal.repository.SellerRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
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
@AllArgsConstructor
public class SellerService {

    /*** Instancia de BCryptPasswordEncoder: <b>BCryptPasswordEncoder</b>.
     */
    private BCryptPasswordEncoder pe;

    /*** Instancia de repositório: <b>SellerRepository</b>.
     */
    private SellerRepository repository;

    /*** Método que retorna lista de Sellers */
    public List<Seller> findAll() {
        return repository.findAll();
    }

    /*** Método que busca Seller por Id
     * @param id ID do Seller a ser retornado
     */
    public Seller findById(Long id) {
        Optional<Seller> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Vendedor não encontrado! Por favor verifique o id."));
    }

    /*** Método que insere um Seller
     * @param obj objeto Seller a ser inserido
     */
    public Seller insert(Seller obj) {
        obj.setPassword(pe.encode(obj.getPassword()));
        try {
            return repository.save(obj);
        } catch (DataIntegrityViolationException e) {
            throw new ExistingUserException("Username ou Email existente na base de dados");
        }
    }

    /*** Método que atualiza um Seller já existente
     *
     * @param newObj Objeto com informações para atualização de um seller existente
     */
    public Seller update(Seller newObj) {
        Seller obj = findById(newObj.getId());
        updateSeller(newObj, obj);
        return repository.save(obj);
    }

    /*** Método deleta um Seller do Bando de dados
     *
     * @param id ID do Seller a ser deletado
     */
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
